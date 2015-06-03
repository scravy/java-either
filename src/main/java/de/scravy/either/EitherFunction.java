package de.scravy.either;

public interface EitherFunction<L, R1, R2> {

  Either<L, R2> apply(final R1 value);
}