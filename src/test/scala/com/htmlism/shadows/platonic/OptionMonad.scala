package com.htmlism.shadows.platonic

import scalaz.NonEmptyList

object OptionMonad {
  private val tpAb =
    NonEmptyList(
      UnaryHole("A"),
      UnaryHole("B"),
    )

  val functor =
    TypeClass("Functor", List(
      PolymorphicMethod("map", tpAb, List(
        Parameter("fa", UnaryTypeConstructor("F") of "A"),
        Parameter("f", FunctionType("A", "B"))
      ), AppliedType(UnaryTypeConstructor("F"), NullaryType("B")))
    ), Nil)

  val applicative =
    TypeClass("Applicative", List(
      PolymorphicMethod("pure", NonEmptyList(UnaryHole("A")), Nil, UnaryTypeConstructor("F") of "A")
    ), List(functor))

  val monad =
    TypeClass("Monad", List(
      PolymorphicMethod("flatMap", tpAb, List(
        Parameter("fa", UnaryTypeConstructor("F") of "A"),
        Parameter("f", FunctionType("A", "B"))
      ), AppliedType(UnaryTypeConstructor("F"), NullaryType("B")))
    ), List(applicative))

  val option = TypeClassInstance(monad, UnaryTypeConstructor("Option"))
}