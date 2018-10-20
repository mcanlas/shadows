package com.htmlism.shadows

import scalaz.NonEmptyList

package object plato {
  type Nel[A] = NonEmptyList[A]
  val Nel = NonEmptyList

  val A  = BasicType("A")
  val B  = BasicType("B")
  val FA = ConstructedOne("F", "A")
  val FB = ConstructedOne("F", "B")

  val Ax = Parameter("x", A)

  implicit class Ops(s: Symbol) {
    def ntc: NullaryTypeConstructor =
      NullaryTypeConstructor(s.name)

    def utc: UnaryTypeConstructor =
      UnaryTypeConstructor(s.name)
  }

  def linearize(ts: TypeSignature): (List[TypeSignature], TerminalTypeSignature) = {
    @annotation.tailrec
    def rec(ts: TypeSignature, args: List[TypeSignature]): (List[TypeSignature], TerminalTypeSignature) =
      ts match {
        case FunctionConsType(a, b) =>
          rec(b, args :+ a)

        case term: TerminalTypeSignature =>
          (args, term)
      }

    rec(ts, Nil)
  }
}
