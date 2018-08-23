package com.htmlism.shadows.platonic

trait ScalaShow[A] {
  def show(x: A): String
}

object ScalaShow {
  implicit val typeClass: ScalaShow[TypeClass] =
    new ScalaShow[TypeClass] {
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
}