package com.htmlism.shadows.plato

object Run extends App {
  val option =
    DataClass("Option", Nel.nels('A.ntc), Nel(
      Constructor("Some", 'A.ntc),
      Constructor("None")
    ))

  // TODO
//  val list =
//    DataClass("List", Nel.nels('A.ntc), Nel(
//      Constructor("Cons", 'ListA.ntc),
//      Constructor("Nil")
//    ))

  val either =
    DataClass("Either", Nel.nels('A.ntc, 'B.ntc), Nel(
      Constructor("Left", 'A.ntc),
      Constructor("Right", 'B.ntc)
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
