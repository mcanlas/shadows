package com.htmlism.shadows.plato

sealed trait TypeSignature:
  def =>:(left: TypeSignature): FunctionConsType =
    FunctionConsType(left, this)

sealed trait TerminalTypeSignature extends TypeSignature

case class TypeLiteral(name: String)                             extends TerminalTypeSignature
case class TypeVariable(name: String)                            extends TerminalTypeSignature
case class ConstructedLiteral(name: String, arg: TypeSignature)  extends TerminalTypeSignature
case class ConstructedVariable(name: String, arg: TypeSignature) extends TerminalTypeSignature

case class FunctionConsType(a: TypeSignature, b: TypeSignature) extends TypeSignature

// TODO a dedicated tuple2 type might go here
