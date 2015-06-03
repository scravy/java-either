package de.scravy.either;

public interface BiFunction<A, B, C> {

  C apply(final A value, final B value2);
}
