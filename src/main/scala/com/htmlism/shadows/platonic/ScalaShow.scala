package com.htmlism.shadows.platonic

trait ScalaShow[A] {
  def show(x: A): String
}

object ScalaShow {
  implicit val typeClass: ScalaShow[TypeClass] =
    new ScalaShow[TypeClass] {
      def show(x: TypeClass): String = {
        s"""trait ${x.name}[${x.f.name}] {
           |}
           |
           |object ${x.name} {
           |}
        """.stripMargin
      }
    }
}