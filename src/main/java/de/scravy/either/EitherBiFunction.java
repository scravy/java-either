package de.scravy.either;

public interface EitherBiFunction<L, R1, B, R2> {
  Either<L, R2> apply(final R1 value, final B value2);
}