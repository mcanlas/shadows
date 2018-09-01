package com.htmlism.shadows.plato

trait ScalaShow[A] {
  def show(x: A): String
}

object ScalaShow {
  implicit val typeClass: ScalaShow[TypeClass] =
    new ScalaShow[TypeClass] {
      def show(x: TypeClass): String = {
        s"""typeclass ${x.name} {
           |}
        """.stripMargin
      }
    }
}