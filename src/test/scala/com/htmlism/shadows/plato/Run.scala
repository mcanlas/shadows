package com.htmlism.shadows
package plato

import java.io.PrintWriter

import scalaz._, Scalaz._

object Run extends App {
  private val boolean =
    DataClass("Boolean",
              Nel(
                Constructor("True"),
                Constructor("False")
              ))

  // polymorphic
  private val option =
    DataClass("Option",
              Nel(
                Constructor("Some", Ax),
                Constructor("None")
              ))

  // polymorphic
  private val list =
    DataClass("List",
              Nel(
                Constructor("Cons", Ax, Parameter("xs", ConstructedLiteral("List", A))),
                Constructor("Nil")
              ))

  // polymorphic over two parameters
  private val either =
    DataClass("Either",
              Nel(
                Constructor("Left", Ax),
                Constructor("Right", Parameter("x", B))
              ))

  /**
    * In Haskell, a constructor can have same name as the data type.
    *
    * In Scala, this can be true for nullary constructors (case objects) but not for
    * other constructors (case classes).
    */
  // example of one constructor
  private val nel =
    DataClass("NonEmptyList",
              Nel(
                Constructor("Nel", Ax, Parameter("xs", ConstructedLiteral("List", A)))
              ))

  private val json =
    DataClass("JsonValue",
              Nel(
                Constructor("JNull"),
                Constructor("JBool"),
                Constructor("JString"),
                Constructor("JArray"),
                Constructor("JObject")
              ))

  private val functor =
    TypeClass
      .k1("Functor", 'F.utc)
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
      .k0("Semigroup", 'A.ntc)
      .w(Method("plus", A =>: A =>: A))

  private val monoid =
    TypeClass
      .k0("Monoid", ConstrainedNtc("A", semigroup))
      .w(Method("zero", A))

  for (tc <- List(functor, applicative, monad, semigroup, monoid)) {
    println {
      implicitly[HaskellShow[TypeClass]].show(tc)
    }

    println

    println {
      implicitly[SimulacrumShow[TypeClass]].show(tc)
    }

    println
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
  def show[A, B: ShadowShow](c: Transpiler[A, B], x: A, out: PrintWriter): Unit =
    (x |> c.transpile)
      .map(implicitly[ShadowShow[B]].show)
      .mkString("\n\n") |> out.println

  writer("generated.hs") { hs =>
    writer("generated.scala") { sc =>
      sc.println("package donotcollide")

      List(boolean, option, list, either, nel, json)
        .foreach { d =>
          show(haskell.HaskellCompiler, d, hs)
          hs.println()

          sc.println("\n//\n")
          show(scala.ScalaCompiler, d, sc)
        }
    }
  }

  private def writer(s: String)(f: PrintWriter => Unit): Unit = {
    val out = new java.io.PrintWriter("src/main/resources/" + s)
    f(out)
    out.close()
  }
}
