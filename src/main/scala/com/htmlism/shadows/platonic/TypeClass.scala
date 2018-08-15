package com.htmlism.shadows.platonic

case class TypeClass(name: String, f: UnaryTypeConstructor, methods: List[MethodLike], supertypes: List[TypeClass])