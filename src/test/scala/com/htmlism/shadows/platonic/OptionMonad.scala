package com.htmlism.shadows.platonic

object OptionMonad {
  val functor =
    TypeClass("Functor", List(
      Method("map", List(
        Parameter("fa", Type("F[A]")),
        Parameter("f", Type("A => B"))
      ), Type("F[B]"))
    ), Nil)

  val applicative =
    TypeClass("Applicative", List(
      Method("pure", Nil, Type("F[A]"))
    ), List(functor))

  val monad =
    TypeClass("Monad", List(
      Method("flatMap", List(
        Parameter("fa", Type("F[A]")),
        Parameter("f", Type("A => B"))
      ), Type("F[B]"))
    ), List(applicative))

  val option = TypeClassInstance(monad, "Option")
}