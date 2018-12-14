package com.htmlism.shadows.haskell

object Examples {
  private val bool =
    DataDeclaration("Bool")
      .cons("True")
      .cons("False")

  private val just =
    DataDeclaration("Maybe", "a")
      .cons("Just", "a")
      .cons("Nothing")

  private val either =
    DataDeclaration("Either", "a", "b")
      .cons("Left", "a")
      .cons("Right", "b")

  private val nel =
    DataDeclaration("NonEmptyList", List(), Nil)
      .cons("NonEmptyList", Proper("a"), ConstructedOne("List", Proper("a")))
}
