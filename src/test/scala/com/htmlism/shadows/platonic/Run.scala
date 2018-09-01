package com.htmlism.shadows.platonic

object Run extends App {
  println {
    implicitly[ChimeraShow[TypeClass]].show(OptionMonad.monad)
  }
}
