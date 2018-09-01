package com.htmlism.shadows.plato

trait ScalaShow[A] {
  def show(x: A): String
}

object ScalaShow {
  implicit val typeClass: ScalaShow[TypeClass] =
    new ScalaShow[TypeClass] {
      def show(x: TypeClass): String = {
        val (left, right) =
          x match {
            case BasicTypeClass(_, p, _) =>
              val constraint =
                p match {
                  case NullaryTypeConstructor(s) =>
                    ""

                  case ConstrainedNtc(s, c) =>
                    " extends " + c.name + s"[$s]"
                }

              (x.name + s"[${p.name}]", constraint)

            case ConstructorClass(_, p, _) =>
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

  private def toStr(m: Method) =
    "  def " + m.name + ": "
}