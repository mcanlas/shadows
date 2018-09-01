package com.htmlism.shadows.plato

trait ChimeraShow[A] {
  def show(x: A): String
}

object ChimeraShow {
  implicit val typeClass: ChimeraShow[TypeClass] =
    new ChimeraShow[TypeClass] {
      def show(x: TypeClass): String = {
        s"""class ${x.name}
           |
           |typeclass ${x.name} {
           |}
        """.stripMargin
      }
    }
}