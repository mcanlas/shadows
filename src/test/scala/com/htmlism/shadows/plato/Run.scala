package com.htmlism.shadows.plato

object Run extends App {
  val option =
    DataClass("Option", Nel.nels(NullaryTypeConstructor("A")), Nel(
      Constructor("Some", List(NullaryTypeConstructor("F"))),
      Constructor("None", Nil)
    ))

  val either =
    DataClass("Either", Nel.nels(NullaryTypeConstructor("A"), NullaryTypeConstructor("B")), Nel(
      Constructor("Left", List(NullaryTypeConstructor("A"))),
      Constructor("Right", List(NullaryTypeConstructor("B")))
    ))

  val functor =
    TypeClass
      .k1("Functor", UnaryTypeConstructor("F"))
      .w(Method("map", FA =>: (A =>: B) =>: FB))

  val applicative =
    TypeClass
      .k1("Applicative", ConstrainedUtc("F", functor))
      .w(Method("pure", A =>: FA))

  val monad =
    TypeClass
      .k1("Monad", ConstrainedUtc("F", applicative))
      .w(Method("flatMap", FA =>: (A =>: FB) =>: FB))

  for (tc <- List(functor, applicative, monad)) {
    println {
      implicitly[HaskellShow[TypeClass]].show(tc)
    }

    println

    println {
      implicitly[SimulacrumShow[TypeClass]].show(tc)
    }

    println
  }
}
