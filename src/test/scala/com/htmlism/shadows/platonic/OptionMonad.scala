package com.htmlism.shadows.platonic

import scalaz.NonEmptyList

object OptionMonad {
  private val tpAb =
    NonEmptyList(
      UnaryHole("A"),
      UnaryHole("B"),
    )

  val functor =
    TypeClass("Functor", UnaryTypeConstructor("F"), List(
      PolymorphicMethod("map", tpAb, List(
        Parameter("fa", UnaryTypeConstructor("F") of "A"),
        Parameter("f", FunctionType("A", "B"))
      ), AppliedType(UnaryTypeConstructor("F"), NullaryType("B")))
    ), Nil)

  val applicative =
    TypeClass("Applicative", UnaryTypeConstructor("F"), List(
      PolymorphicMethod("pure", NonEmptyList(UnaryHole("A")), Nil, UnaryTypeConstructor("F") of "A")
    ), List(functor))

  val monad =
    TypeClass("Monad", UnaryTypeConstructor("F"), List(
      PolymorphicMethod("flatMap", tpAb, List(
        Parameter("fa", UnaryTypeConstructor("F") of "A"),
        Parameter("f", FunctionType("A", "B"))
      ), AppliedType(UnaryTypeConstructor("F"), NullaryType("B")))
    ), List(applicative))

  val option = TypeClassInstance(monad, UnaryTypeConstructor("Option"))
}