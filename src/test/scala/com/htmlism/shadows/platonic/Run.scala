package com.htmlism.shadows.platonic

object Run extends App {
  println {
    implicitly[ScalaShow[TypeClass]].show(OptionMonad.monad)
  }
}
