package com.htmlism.shadows.platonic

import scalaz._, Scalaz._

case class GenericClass(name: String, typeParameters: NonEmptyList[TypeParameter], methods: List[MethodLike])