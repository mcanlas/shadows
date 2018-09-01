package com.htmlism.shadows.plato

trait ChimeraShow[A] {
  def show(x: A): String
}

object ChimeraShow {
  implicit val typeClass: ChimeraShow[TypeClass] =
    new ChimeraShow[TypeClass] {
      def show(x: TypeClass): String = {
        val unaryTc = x.f.name + "[_]"

        x.allMethods.foreach(println)

        s"""trait ${x.name}[$unaryTc] {
           |}
           |
           |object ${x.name} {
           |}
        """.stripMargin
      }
    }