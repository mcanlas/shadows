package com.htmlism.shadows
package scala

object Trait {
  implicit val scalaShow: ShadowShow[Trait] =
    new ShadowShow[Trait] {
      def show(x: Trait): String = ""
    }
}

trait Trait
