package com.htmlism.shadows.haskell

object DataDeclaration {
  def apply(s: String, tps: String*): DataDeclaration =
    DataDeclaration(s, tps.toList, Nil)
}

case class DataDeclaration(name: String, typeParameters: List[String], constructors: List[Constructor]) {
  def cons(s: String): DataDeclaration =
    copy(constructors = constructors :+ Constructor(s, Nil))

  def cons(s: String, argument: String): DataDeclaration =
    copy(constructors = constructors :+ Constructor(s, List(Proper(argument))))

  def cons(s: String, arguments: TypeSignature*): DataDeclaration =
    copy(constructors = constructors :+ Constructor(s, arguments.toList))
}

case class Constructor(name: String, arguments: List[TypeSignature])