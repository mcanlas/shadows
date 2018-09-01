package com.htmlism.shadows.plato

object Run extends App {
  val option =
    DataClass("Option", Nel.nels('A.ntc), Nel(
      Constructor("Some", A),
      Constructor("None")
    ))

  val list =
    DataClass("List", Nel.nels('A.ntc), Nel(
      Constructor("Cons", A, ConstructedOne("List", "A")),
      Constructor("Nil")
    ))

  val either =
    DataClass("Either", Nel.nels('A.ntc, 'B.ntc), Nel(
      Constructor("Left", A),
      Constructor("Right", B)
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
}
