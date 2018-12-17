data Boolean = True | False

data Option a = Some a | None

data List a = Cons a (List a) | Nil

data Either a b = Left a | Right b

data NonEmptyList a = Nel a (List a)
