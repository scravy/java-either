package de.scravy.either;

public interface Function<A, B> {

  B apply(final A value);
}
