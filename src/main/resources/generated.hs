class Functor

class Applicative

class Monad

class Semigroup

class Monoid

data Boolean = True | False

data Option a = Some a | None

data List a = Cons a (List a) | Nil

data Either a b = Left a | Right b

data NonEmptyList a = Nel a (List a)

data JsonValue = JNull | JBool Boolean | JString String | JArray (List JsonValue) | JObject
