package com.htmlism.shadows.plato

trait HaskellShow[A] {
  def show(x: A): String
}

object HaskellShow {
  implicit val typeClass: HaskellShow[TypeClass] =
    new HaskellShow[TypeClass] {
      def show(x: TypeClass): String = {
        val (left, right) =
          x match {
            case BasicTypeClass(_, p, _) =>
              val constraint =
                p match {
                  case NullaryTypeConstructor(s) =>
                    ""

                  case ConstrainedNtc(s, c) =>
                    c.name + " " + s.toLowerCase + " => "
                }

              (constraint, x.name + " " + p.name.toLowerCase)

            case ConstructorClass(_, p, _) =>
              val constraint =
                p match {
                  case UnaryTypeConstructor(s) =>
                    ""

                  case ConstrainedUtc(s, c) =>
                    c.name + " " + s.toLowerCase + " => "
                }

              (constraint, x.name + " " + p.name.toLowerCase)
          }

        s"""class $left$right where
        """.stripMargin
      }
    }
}