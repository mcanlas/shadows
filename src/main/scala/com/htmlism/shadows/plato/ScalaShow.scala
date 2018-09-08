package com.htmlism.shadows.plato

import scalaz._, Scalaz._

trait ScalaShow[A] {
  def show(x: A): String
}

object ScalaShow {
  implicit val ssDataClass: ScalaShow[DataClass] =
    new ScalaShow[DataClass] {
      def show(x: DataClass): String = {
        val typeParameters = x.typeParameters.map(_.name).mkString(", ")

        val constructors =
          x.constructors.map(cToStr(x)).toList

        val groups =
          List(s"sealed trait ${x.name}[$typeParameters]") ::: constructors

        groups.mkString("\n\n")
      }
    }

  private def cToStr(x: DataClass)(c: Constructor) = {
    if (c.typeSignatures.isEmpty)
      "case object " + c.name
    else {
      val parameters =
        c.typeSignatures
          .map {
            case BasicType(s) => s
            case ConstructedOne(f, a) => s"$f[$a]"
            case _ => throw new IllegalStateException
          }
          .map(t => s"_: $t")
          .mkString(", ")

      "case class " + c.name + s"($parameters) extends ${x.name}" // TODO parameters
    }
  }
}
