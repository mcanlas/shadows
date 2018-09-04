package com.htmlism.shadows

object ShadowShow {
  implicit def shadowForList[A](implicit s: ShadowShow[A]): ShadowShow[List[A]] =
    new ShadowShow[List[A]] {
      def show(xs: List[A]): String =
        xs.map(s.show).mkString("\n\n")
    }
}

trait ShadowShow[A] {
  def show(x: A): String
}
