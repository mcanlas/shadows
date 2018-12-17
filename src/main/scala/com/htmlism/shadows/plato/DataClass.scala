package com.htmlism.shadows.plato

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

case class DataClass(name: String, constructors: Nel[Constructor]) {
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
