package com.htmlism.shadows
package plato

import cats.effect._
import cats.implicits._

import mouse.any._

object Run extends IOApp {
  private val boolean =
    DataClass(
      "Boolean",
      Nel.of(
        Constructor("True"),
        Constructor("False")
      )
    )

  // polymorphic
  private val option =
    DataClass(
      "Option",
      Nel.of(
        Constructor("Some", Ax),
        Constructor("None")
      )
    )

  // polymorphic
  private val list =
    DataClass(
      "List",
      Nel.of(
        Constructor("Cons", Ax, Parameter("xs", ConstructedLiteral("List", A))),
        Constructor("Nil")
      )
    )

  // polymorphic over two parameters
  private val either =
    DataClass(
      "Either",
      Nel.of(
        Constructor("Left", Ax),
        Constructor("Right", Parameter("x", B))
      )
    )

  /**
    * In Haskell, a constructor can have same name as the data type.
    *
    * In Scala, this can be true for nullary constructors (case objects) but not for
    * other constructors (case classes).
    */
  // example of one constructor
  private val nel =
    DataClass(
      "NonEmptyList",
      Nel.of(
        Constructor("Nel", Ax, Parameter("xs", ConstructedLiteral("List", A)))
      )
    )

  private val json =
    DataClass(
      "JsonValue",
      Nel.of(
        Constructor("JNull"),
        Constructor("JBool", Parameter("b", TypeLiteral("Boolean"))),
        Constructor("JString", Parameter("s", TypeLiteral("String"))),
        Constructor("JArray", Parameter("xs", ConstructedLiteral("List", TypeLiteral("JsonValue")))),
        Constructor("JObject")
      )
    )

  private val functor =
    TypeClass
      .k1("Functor", "F".utc)
      .w(Method("map", FA =>: (A =>: B) =>: FB))

  private val applicative =
    TypeClass
      .k1("Applicative", ConstrainedUtc("F", functor))
      .w(Method("pure", A =>: FA))

  private val monad =
    TypeClass
      .k1("Monad", ConstrainedUtc("F", applicative))
      .w(Method("flatMap", FA =>: (A =>: FB) =>: FB))

  private val semigroup =
    TypeClass
      .k0("Semigroup", "A".ntc)
      .w(Method("plus", A =>: A =>: A))

  private val monoid =
    TypeClass
      .k0("Monoid", ConstrainedNtc("A", semigroup))
      .w(Method("zero", A))

  for (tc <- List(functor, applicative, monad, semigroup, monoid)) {
    println {
      implicitly[HaskellShow[TypeClass]].show(tc)
    }

    println()

    println {
      implicitly[SimulacrumShow[TypeClass]].show(tc)
    }

    println()
  }

  /**
    * Prints translated source code given a transpiler and some code.
    *
    * @param c A transpiler from language `A` to language `B`
    * @param x An expression in language `A`
    *
    * @tparam A A source language
    * @tparam B A destination language
    */
  def show[A, B: ShadowShow](c: Transpiler[A, B])(x: A): String =
    (x |> c.transpile)
      .map(implicitly[ShadowShow[B]].show)
      .mkString("\n\n")

  private val typeClasses =
    List(functor, applicative, monad, semigroup, monoid)

  private val dataClasses =
    List(boolean, option, list, either, nel, json)

  private[this] val writeHaskell =
    PrintWriterResource[IO]("generated.hs")
      .use { hs =>
        (typeClasses ::: dataClasses)
          .map(show(haskell.HaskellCompiler))
          .mkString("\n\n") |> hs.println
      }

  private[this] val writeScala =
    PrintWriterResource[IO]("generated.scala")
      .use { sc =>
        dataClasses
          .map(show(vanilla.ScalaCompiler))
          .::("package donotcollide")
          .mkString("\n\n//\n\n") |> sc.println
      }

  def run(args: List[String]): IO[ExitCode] =
    List(writeHaskell, writeScala).parSequence
      .as(ExitCode.Success)
}
