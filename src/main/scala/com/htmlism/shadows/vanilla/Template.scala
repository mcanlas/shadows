package com.htmlism.shadows
package vanilla

object Template:
  implicit val scalaShow: ShadowShow[Template] =
    new ShadowShow[Template]:
      def show(x: Template): String =
        x match
          case a: ScalaClass  => ScalaClassShow.show(a)
          case a: ScalaObject => ScalaObjectShow.show(a)
          case a: Trait       => TraitShow.show(a)

  def supersStr(xs: List[String]): String =
    if xs.isEmpty then ""
    else " extends " + xs.mkString(" with ")

  def tpStr(xs: List[String]): String =
    if xs.isEmpty then ""
    else "[" + xs.mkString(", ") + "]"

sealed trait Template:
  def name: String

  def supers: List[String]

case class ScalaObject(name: String, isCase: Boolean, typeParameters: List[String], supers: List[String])
    extends Template

case class ScalaClass(
    name: String,
    isCase: Boolean,
    typeParameters: List[String],
    supers: List[String],
    parameters: List[String]
) extends Template

case class Trait(name: String, isSealed: Boolean, typeParameters: List[String], supers: List[String]) extends Template

object ScalaObjectShow extends ShadowShow[ScalaObject]:
  def show(x: ScalaObject): String =
    val strCase =
      if x.isCase then "case "
      else ""

    val tpStr =
      Template.tpStr(x.typeParameters)

    val supers =
      Template.supersStr(x.supers)

    s"${strCase}object ${x.name}$tpStr$supers"

object ScalaClassShow extends ShadowShow[ScalaClass]:
  def show(x: ScalaClass): String =
    val strCase =
      if x.isCase then "case "
      else ""

    val tpStr =
      Template.tpStr(x.typeParameters)

    val supers =
      Template.supersStr(x.supers)

    val parameters =
      if x.parameters.isEmpty then ""
      else "(" + x.parameters.mkString(", ") + ")"

    s"${strCase}class ${x.name}$tpStr$parameters$supers"

object TraitShow extends ShadowShow[Trait]:
  def show(x: Trait): String =
    val strSealed =
      if x.isSealed then "sealed "
      else ""

    val tpStr =
      Template.tpStr(x.typeParameters)

    val supers =
      Template.supersStr(x.supers)

    s"${strSealed}trait ${x.name}$tpStr$supers"
