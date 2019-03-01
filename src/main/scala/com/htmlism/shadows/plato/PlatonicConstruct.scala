package com.htmlism.shadows.plato

/**
  * A top-level Platonic construct is either a data class or a type class.
  */
sealed trait PlatonicConstruct

object DataClass {
  def typeRegistry(dc: DataClass): List[String] =
    dc.constructors.list.toList
      .flatMap(typeRegistry)
      .distinct

  def typeRegistry(con: Constructor): List[String] =
    con.parameters
      .map(_.sig)
      .flatMap(typeRegistry)

  def typeRegistry(ts: TypeSignature): List[String] =
    ts match {
      case TypeVariable(x) =>
        List(x)

      case FunctionConsType(f, g) =>
        typeRegistry(f) ::: typeRegistry(g)

      case _ =>
        Nil
    }
}

case class DataClass(name: String, constructors: Nel[Constructor]) extends PlatonicConstruct {
  lazy val typeRegistry: List[String] =
    DataClass.typeRegistry(this)
}

object Constructor {
  def apply(name: String, typeSignatures: Parameter*): Constructor =
    Constructor(name, typeSignatures.toList)
}

case class Constructor(name: String, parameters: List[Parameter])

/**
  * @param name Not used in Haskell.
  */
case class Parameter(name: String, sig: TypeSignature)

// ====================================================================================================

object TypeClass {
  def k0(name: String, parameter: Star): OverStar =
    OverStar(name, parameter, Nil)

  def k1(name: String, parameter: StarStar): OverStarStar =
    OverStarStar(name, parameter, Nil)
}

sealed trait TypeClass extends PlatonicConstruct {
  def name: String

  def methods: List[Method]
}

/**
  * Think `Semigroup[A]`.
  */
case class OverStar(name: String, parameter: Star, methods: List[Method]) extends TypeClass {
  def w(m: Method): OverStar = copy(methods = m +: methods)
}

/**
  * Think `Monad[List]` where `List[A]`.
  */
case class OverStarStar(name: String, parameter: StarStar, methods: List[Method]) extends TypeClass {
  def w(m: Method): OverStarStar = copy(methods = m +: methods)
}