package com.htmlism.shadows.plato

object Run extends App {
  val functor =
    ConstructorClass("Functor", UnaryTypeConstructor("F"))

  println {
    implicitly[ChimeraShow[TypeClass]].show(functor)
  }
}
