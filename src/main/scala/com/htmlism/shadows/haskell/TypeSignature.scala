package com.htmlism.shadows.haskell

sealed trait TypeSignature

case class Proper(s: String) extends TypeSignature

case class ConstructedOne(from: String, x: TypeSignature) extends TypeSignature
