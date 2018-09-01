package com.htmlism.shadows.plato

trait HaskellShow[A] {
  def show(x: A): String
}

object HaskellShow {
  implicit val typeClass: HaskellShow[TypeClass] =
    new HaskellShow[TypeClass] {
      def show(x: TypeClass): String = {
        val constraintSignature =
          x match {
            case BasicTypeClass(_, p, _) =>
              p match {
                case NullaryTypeConstructor(s) =>
                  ""

                case ConstrainedNtc(s, c) =>
                  c.name + " " + s.toLowerCase + " => "
              }

            case ConstructorClass(_, p, _) =>
              p match {
                case UnaryTypeConstructor(s) =>
                  ""

                case ConstrainedUtc(s, c) =>
                  c.name + " " + s.toLowerCase + " => "
              }
          }

        s"""class $constraintSignature${x.name} where
        """.stripMargin
      }
    }
}