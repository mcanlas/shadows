package com.htmlism.shadows.plato

trait ChimeraShow[A] {
  def show(x: A): String
}

object ChimeraShow {
  implicit val typeClass: ChimeraShow[TypeClass] =
    new ChimeraShow[TypeClass] {
      def show(x: TypeClass): String = {
        val haskellConstraintSignature =
          x match {
            case BasicTypeClass(_, p, _) =>
              p match {
                case NullaryTypeConstructor(s) =>
                  s.toLowerCase + " => "

                case ConstrainedNtc(s, c) =>
                  c.name + " " + s.toLowerCase + " => "
              }

            case ConstructorClass(_, p, _) =>
              p match {
                case UnaryTypeConstructor(s) =>
                  s.toLowerCase + " => "

                case ConstrainedUtc(s, c) =>
                  c.name + " " + s.toLowerCase + " => "
              }
          }

        s"""class $haskellConstraintSignature${x.name}
           |
           |typeclass ${x.name} {
           |}
        """.stripMargin
      }
    }
}