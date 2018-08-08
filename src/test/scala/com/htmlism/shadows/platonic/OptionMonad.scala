package com.htmlism.shadows.platonic

object OptionMonad {
  val functor =
    TypeClass("Functor", List(
      Method("map", List(
        Parameter("fa", UnaryType(Kind("F"), NullaryType("A"))),
        Parameter("f", FunctionType(NullaryType("A"), NullaryType("B")))
      ), UnaryType(Kind("F"), NullaryType("B")))
    ), Nil)

  val applicative =
    TypeClass("Applicative", List(
      Method("pure", Nil, UnaryType(Kind("F"), NullaryType("A")))
    ), List(functor))

  val monad =
    TypeClass("Monad", List(
      Method("flatMap", List(
        Parameter("fa", UnaryType(Kind("F"), NullaryType("A"))),
        Parameter("f", FunctionType(NullaryType("A"), NullaryType("B")))
      ), UnaryType(Kind("F"), NullaryType("B")))
    ), List(applicative))

  val option = TypeClassInstance(monad, "Option")
}