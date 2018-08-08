package com.htmlism.shadows.platonic

import scalaz.NonEmptyList

object OptionMonad {
  private val tpAb =
    NonEmptyList(
      TypeParameter("A", Nil),
      TypeParameter("B", Nil),
    )

  val functor =
    TypeClass("Functor", List(
      PolymorphicMethod("map", tpAb, List(
        Parameter("fa", Kind("F") of "A"),
        Parameter("f", FunctionType("A", "B"))
      ), UnaryType(Kind("F"), NullaryType("B")))
    ), Nil)

  val applicative =
    TypeClass("Applicative", List(
      PolymorphicMethod("pure", NonEmptyList(TypeParameter("A", Nil)), Nil, Kind("F") of "A")
    ), List(functor))

  val monad =
    TypeClass("Monad", List(
      PolymorphicMethod("flatMap", tpAb, List(
        Parameter("fa", Kind("F") of "A"),
        Parameter("f", FunctionType("A", "B"))
      ), UnaryType(Kind("F"), NullaryType("B")))
    ), List(applicative))

  val option = TypeClassInstance(monad, "Option")
}