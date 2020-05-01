class  Functor where

class  Applicative where

class  Monad where

class  Semigroup where

class  Monoid where

data Boolean = True | False

data Option a = Some a | None

data List a = Cons a (List a) | Nil

data Either a b = Left a | Right b

data NonEmptyList a = Nel a (List a)

data JsonValue = JNull | JBool Boolean | JString String | JArray (List JsonValue) | JObject
