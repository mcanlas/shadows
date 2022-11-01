package com.htmlism.shadows.plato

/**
  * A type parameter in Scala. Can describe types (e.g. `A`) or unary type constructors (e.g. `F`).
  *
  * @group Kinds
  */
sealed trait Kind:
  def name: String

/**
  * @group Kinds
  */
sealed trait Star extends Kind

/**
  * @group Kinds
  */
sealed trait StarStar extends Kind

/**
  * Like `A`.
  *
  * @group Kinds
  */
case class NullaryTypeConstructor(name: String) extends Star

/**
  * Like `F`.
  *
  * @group Kinds
  */
case class UnaryTypeConstructor(name: String) extends StarStar

/**
  * Like `A : Monoid`.
  *
  * @group Kinds
  */
case class ConstrainedNtc(name: String, constraint: TypeClassStar) extends Star

/**
  * Like `F : Monad`.
  *
  * @group Kinds
  */
case class ConstrainedUtc(name: String, constraint: TypeClassStarStar) extends StarStar
