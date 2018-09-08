package com.htmlism.shadows
package scala

object Template {
  implicit val scalaShow: ShadowShow[Template] =
    new ShadowShow[Template] {
      def show(x: Template): String =
        x match {
          case a: ScalaClass  => ScalaClassShow.show(a)
          case a: ScalaObject => ScalaObjectShow.show(a)
          case a: Trait       => TraitShow.show(a)
        }
    }
}

sealed trait Template {
  def name: String
}

case class ScalaObject(name: String, isCase: Boolean, typeParameters: List[String]) extends Template

case class ScalaClass(name: String, isCase: Boolean) extends Template

case class Trait(name: String, isSealed: Boolean, typeParameters: List[String]) extends Template

object ScalaObjectShow extends ShadowShow[ScalaObject] {
  def show(x: ScalaObject): String = {
    val strCase =
      if (x.isCase)
        "case "
      else
        ""
    s"${strCase}object ${x.name}"
  }
}

object ScalaClassShow extends ShadowShow[ScalaClass] {
  def show(x: ScalaClass): String = {
    val strCase =
      if (x.isCase)
        "case "
      else
        ""
    s"${strCase}class ${x.name}"
  }
}

object TraitShow extends ShadowShow[Trait] {
  def show(x: Trait): String = {
    val strSealed =
      if (x.isSealed)
        "sealed "
      else
        ""

    s"${strSealed}trait ${x.name}"
  }
}