package com.htmlism.shadows

import cats.data.NonEmptyList

package object plato:
  type Nel[A] = NonEmptyList[A]
  val Nel = NonEmptyList

  val A = TypeVariable("A")
  val B = TypeVariable("B")
  val FA = ConstructedVariable("F", A)
  val FB = ConstructedVariable("F", B)

  val Ax = Parameter("x", A)

  implicit class Ops(s: String):
    def ntc: NullaryTypeConstructor =
      NullaryTypeConstructor(s)

    def utc: UnaryTypeConstructor =
      UnaryTypeConstructor(s)

  def linearize(ts: TypeSignature): (List[TypeSignature], TerminalTypeSignature) =
    @annotation.tailrec
    def rec(ts: TypeSignature, args: List[TypeSignature]): (List[TypeSignature], TerminalTypeSignature) =
      ts match
        case FunctionConsType(a, b) =>
          rec(b, args :+ a)

        case term: TerminalTypeSignature =>
          (args, term)

    rec(ts, Nil)

  implicit final class AnyOps[A](oa: A):
    @inline def |>[B](f: A => B): B =
      f(oa)
