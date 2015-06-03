package de.scravy.either;

public interface EitherConsumer<X> {

  void consume(final X value);
}
