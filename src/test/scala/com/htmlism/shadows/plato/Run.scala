package com.htmlism.shadows.plato

object Run extends App {
  val functor =
    TypeClass
      .k1("Functor", UnaryTypeConstructor("F"))
      .w(Method("map", FA =>: (A =>: B) =>: FB))

  val applicative =
    TypeClass
      .k1("Applicative", ConstrainedUtc("F", functor))
      .w(Method("map", FA =>: (A =>: B) =>: FB))

  val monad =
    TypeClass
      .k1("Monad", ConstrainedUtc("F", applicative))
      .w(Method("map", FA =>: (A =>: B) =>: FB))

  for (tc <- List(functor, applicative, monad)) {
    println {
      implicitly[ChimeraShow[TypeClass]].show(tc)
    }

    println
  }
}
