package com.htmlism.shadows.platonic

import scalaz._

case class GenericClassInstance(name: String, satisfies: GenericClass, typeParameters: NonEmptyList[String])