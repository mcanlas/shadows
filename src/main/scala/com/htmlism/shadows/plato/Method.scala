package com.htmlism.shadows.plato

object Method {
  def apply(name: String, signature: TypeSignature): Method =
    Method(name, None, signature)
}

case class Method(name: String, symbolicAlias: Option[String], signature: TypeSignature)
