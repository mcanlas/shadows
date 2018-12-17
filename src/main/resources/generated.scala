package donotcollide

//

sealed trait Boolean

case object True extends Boolean

case object False extends Boolean

//

sealed trait Option[+A]

case class Some[A](x: A) extends Option[A]

case object None extends Option[Nothing]

//

sealed trait List[+A]

case class Cons[A](x: A, xs: List[A]) extends List[A]

case object Nil extends List[Nothing]

//

sealed trait Either[+A, +B]

case class Left[A](x: A) extends Either[A, Nothing]

case class Right[B](x: B) extends Either[Nothing, B]

//

sealed trait NonEmptyList[+A]

case class Nel[A](x: A, xs: List[A]) extends NonEmptyList[A]
