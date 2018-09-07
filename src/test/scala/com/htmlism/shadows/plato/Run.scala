package com.htmlism.shadows
package plato

import scalaz._, Scalaz._

object Run extends App {
  // polymorphic
  val option =
    DataClass("Option", Nel.nels('A.ntc), Nel(
      Constructor("Some", A),
      Constructor("None")
    ))

  // polymorphic
  val list =
    DataClass("List", Nel.nels('A.ntc), Nel(
      Constructor("Cons", A, ConstructedOne("List", "A")),
      Constructor("Nil")
    ))

  // polymorphic over two parameters
  val either =
    DataClass("Either", Nel.nels('A.ntc, 'B.ntc), Nel(
      Constructor("Left", A),
      Constructor("Right", B)
    ))

  // example of one constructor
  val nel =
    DataClass("NonEmptyList", Nel.nels('A.ntc), Nel(
      Constructor("Nel", A, ConstructedOne("List", "A"))
    ))

  val functor =
    TypeClass
      .k1("Functor", 'F.utc)
      .w(Method("map", FA =>: (A =>: B) =>: FB))

  val applicative =
    TypeClass
      .k1("Applicative", ConstrainedUtc("F", functor))
      .w(Method("pure", A =>: FA))

  val monad =
    TypeClass
      .k1("Monad", ConstrainedUtc("F", applicative))
      .w(Method("flatMap", FA =>: (A =>: FB) =>: FB))

  val semigroup =
    TypeClass
      .k0("Semigroup", 'A.ntc)
      .w(Method("plus", A =>: A =>: A))

  val monoid =
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

  for (tc <- List(option, list, either, nel)) {
    println {
      implicitly[ScalaShow[DataClass]].show(tc)
    }

    println("\n--\n")
  }

  def show[A, B : ShadowShow](c: Transpiler[A, B])(x: A): Unit =
    println {
      x |>
        c.transpile |>
        implicitly[ShadowShow[B]].show
    }

  List(option, list, either, nel)
    .foreach { d =>
      println("\n------\n")

      show(haskell.HaskellCompiler)(d)

      println("\n--\n")

      show(scala.ScalaCompiler)(d)
    }
}
