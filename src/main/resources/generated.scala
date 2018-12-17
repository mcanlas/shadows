package donotcollide

//

sealed trait Boolean

case object True extends Boolean

case object False extends Boolean

//

sealed trait Option[+A]

case class Some(x: A) extends Option[]

case object None extends Option[]

//

sealed trait List[+A]

case class Cons(x: A, xs: List[A]) extends List[]

case object Nil extends List[]

//

sealed trait Either[+A, +B]

case class Left(x: A) extends Either[]

case class Right(x: B) extends Either[]

//

sealed trait NonEmptyList[+A]

case class Nel(x: A, xs: List[A]) extends NonEmptyList[]
