package com.htmlism.shadows.plato

import scalaz._, Scalaz._

trait SimulacrumShow[A] {
  def show(x: A): String
}

object SimulacrumShow {
  implicit val simTypeClass: SimulacrumShow[TypeClass] =
    new SimulacrumShow[TypeClass] {
      def show(x: TypeClass): String = {
        val (left, right) =
          x match {
            case OverStar(_, p, _) =>
              val constraint =
                p match {
                  case NullaryTypeConstructor(s) =>
                    ""

                  case ConstrainedNtc(s, c) =>
                    " extends " + c.name + s"[$s]"
                }

              (x.name + s"[${p.name}]", constraint)

            case OverStarStar(_, p, _) =>
              val constraint =
                p match {
                  case UnaryTypeConstructor(s) =>
                    ""

                  case ConstrainedUtc(s, c) =>
                    " extends " + c.name + s"[$s]"
                }

              (x.name + s"[${p.name}]", constraint)
          }

        val methods =
          x.methods.map(toStr)

        val lines =
          List(s"typeclass $left$right {") ++ methods ++ List("}")

        lines.mkString("\n")
      }
    }

  private def toStr(m: Method) = {
    val (args, ret) = m.signature |> linearize

    "  def " + m.name + ": "
  }
}
