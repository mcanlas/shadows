package com.htmlism.shadows.plato

import scalaz._, Scalaz._

trait HaskellShow[A] {
  def show(x: A): String
}

object HaskellShow {
  implicit val hsTypeClass: HaskellShow[TypeClass] =
    new HaskellShow[TypeClass] {
      def show(x: TypeClass): String = {
        val (left, right) =
          x match {
            case OverStar(_, p, _) =>
              val constraint =
                p match {
                  case NullaryTypeConstructor(s) =>
                    ""

                  case ConstrainedNtc(s, c) =>
                    c.name + " " + s.toLowerCase + " => "
                }

              (constraint, x.name + " " + p.name.toLowerCase)

            case OverStarStar(_, p, _) =>
              val constraint =
                p match {
                  case UnaryTypeConstructor(s) =>
                    ""

                  case ConstrainedUtc(s, c) =>
                    c.name + " " + s.toLowerCase + " => "
                }

              (constraint, x.name + " " + p.name.toLowerCase)
          }

        val methods =
          x.methods.map(mToStr)

        val lines =
          List(s"class $left$right where") ++ methods

        lines.mkString("\n")
      }
    }

  private def mToStr(m: Method) = {
    val signature = {
      val (args, ret) = m.signature |> linearize

      (args :+ ret).map(tsToStr).mkString(" -> ")
    }

    "  " + m.name + " :: " + signature
  }

  private def tsToStr(ts: TypeSignature): String =
    ts match {
      case FunctionConsType(a, b) =>
        s"(${tsToStr(a)} -> ${tsToStr(b)})"

      case ConstructedOne(f, a) =>
        f.toLowerCase + " " + a.toLowerCase

      case BasicType(s) =>
        s.toLowerCase
    }

  implicit val hsDataClass: HaskellShow[DataClass] =
    new HaskellShow[DataClass] {
      def show(x: DataClass): String = {
        val typeParameters = x.typeParameters.map(_.name.toLowerCase).toList.mkString(" ")

        val constructors = x.constructors.map(cToStr).toList.mkString(" | ")

        s"data ${x.name} $typeParameters = $constructors"
      }
    }

  private def cToStr(c: Constructor) = {
    val parameters =
      c.typeSignatures
        .map {
          case BasicType(s) => s.toLowerCase
          case ConstructedOne(f, a) => s"($f ${a.toLowerCase})"
          case _ => throw new IllegalStateException
        }
        .mkString(" ")

    s"${c.name} $parameters"
  }
}