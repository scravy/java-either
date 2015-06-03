package de.scravy.either;

import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * The <code>Either</code> type (an explicit sum type for Java).
 *
 * @since 1.0.0
 *
 * @author Julian Fleischer
 *
 * @param <L>
 *          The left type.
 * @param <R>
 *          The right type.
 */
public abstract class Either<L, R> {

  /**
   * The <code>Left</code> type.
   *
   * @since 1.0.0
   *
   * @author Julian Fleischer
   *
   * @param <L>
   *          The left type.
   * @param <R>
   *          The right type.
   */
  @Value
  @EqualsAndHashCode(callSuper = false)
  public final static class Left<L, R> extends Either<L, R> {

    private final L left;

    @Override
    public boolean isLeft() {
      return true;
    }

    @Override
    public L getLeft() {
      return this.left;
    }
  }

  /**
   * Creates a left value.
   *
   * @since 1.0.0
   *
   * @param value
   *          The value to encapsulate as left.
   * @return An either value!
   *
   * @param <L>
   *          The left type.
   * @param <R>
   *          The right type.
   */
  public static <L, R> Either<L, R> left(final L value) {
    return new Left<L, R>(value);
  }

  /**
   * Creates a left value.
   *
   * @since 1.1.0
   *
   * @param value
   *          The value to encapsulate as left.
   * @param rightType
   *          The right type (to aid in type inference)
   * @return An either value!
   */
  public static <L, R> Either<L, R> left(final L value, final Class<R> rightType) {
    return new Left<L, R>(value);
  }

  /**
   * The <code>Right</code> type.
   *
   * @since 1.0.0
   *
   * @author Julian Fleischer
   *
   * @param <L>
   *          The left type.
   * @param <R>
   *          The right type.
   */
  @Value
  @EqualsAndHashCode(callSuper = false)
  public final static class Right<L, R> extends Either<L, R> {

    private final R right;

    @Override
    public boolean isRight() {
      return true;
    }

    @Override
    public R getRight() {
      return this.right;
    }
  }

  /**
   * Creates a right value.
   *
   * @since 1.0.0
   *
   * @param value
   *          The value to encapsulate as left.
   * @return An either value!
   *
   * @param <L>
   *          The left type.
   * @param <R>
   *          The right type.
   */
  public static <L, R> Either<L, R> right(final R value) {
    return new Right<L, R>(value);
  }

  /**
   * Creates a right value.
   *
   * @since 1.1.0
   *
   * @param value
   *          The value to encapsulate as right.
   * @param leftType
   *          The right type (to aid in type inference)
   * @return An either value!
   */
  public static <L, R> Either<L, R> right(final R value, final Class<L> leftType) {
    return new Right<L, R>(value);
  }

  private Either() {

  }

  /**
   * Checks whether this <code>Either</code> is a left value.
   *
   * @since 1.0.0
   *
   * @return Whether this is a Left value or not.
   */
  public boolean isLeft() {
    return false;
  }

  /**
   * Checks whether this <code>Either</code> is a right value.
   *
   * @since 1.0.0
   *
   * @return Whether this is a Right value or not.
   */
  public boolean isRight() {
    return false;
  }

  /**
   * Returns the left value (if this is a left value).
   *
   * @since 1.0.0
   *
   * @return The unwrapped left value.
   *
   * @throws UnsupportedOperationException
   *           if this is not actually a {@link Left}.
   */
  public L getLeft() {
    throw new UnsupportedOperationException();
  }

  /**
   * Returns the right value (if this is a right value).
   *
   * @since 1.0.0
   *
   * @return The unwrapped right value.
   *
   * @throws UnsupportedOperationException
   *           if this is not actually a {@link Right}.
   */
  public R getRight() {
    throw new UnsupportedOperationException();
  }

  /**
   * Chains this either to a functions that accepts its right argument and
   * returns an Either.
   *
   * If this either is a left value, the function will not be invoked but an
   * either with that left value will be returned.
   *
   * @since 1.2.0
   * @param f
   *          A function taking a value of the right type, producing an Either
   *          value.
   * @return A new Either value which contains the result of the application of
   *         f with the right value or an Either value with the same left value
   *         as this if this was a left.
   */
  @SuppressWarnings("unchecked")
  public final <R2> Either<L, R2> bind(final EitherFunction<L, R, R2> f) {
    if (isLeft()) {
      return (Either<L, R2>) this;
    }
    return f.apply(getRight());
  }

  /**
   * Chains this either to a function that accepts its rights argument and an
   * additional argument and returns an Either.
   *
   * @since 1.2.0
   * @param f
   *          A function taking a value of the right type and an additional
   *          value of an arbitrary type, producing an Either value.
   * @param value
   *          An additional value which will be supplied as seconds argument to
   *          the function.
   * @return A new Either value which contains the result of the application of
   *         f with the right value and the additionally supplied value or an
   *         Either value with the same left value as this if this was a left.
   */
  @SuppressWarnings("unchecked")
  public final <R2, B> Either<L, R2> bind(
      final EitherBiFunction<L, R, B, R2> f, final B value) {
    if (isLeft()) {
      return (Either<L, R2>) this;
    }
    return f.apply(getRight(), value);
  }

  /**
   * Chains this either to an ordinary function.
   *
   * The supplied function is invoked with this Eithers right value iff it is a
   * right value or not at all if it is a left value.
   *
   * <pre>
   * Either.right(3).chainOrdinary(a -&gt; a + 1); // Right(4)
   * </pre>
   *
   * <pre>
   * Either.left(3).chainOrdinary(a -&gt; a + 1); // Left(3)
   * </pre>
   *
   * @since 1.2.0
   * @param f
   *          An ordinary function, accepting a value of the right type and
   *          producing a new value.
   * @return A new Right Either if this was a right value, containing the result
   *         of the application of f, or a Left Either with the Left value of
   *         this Either.
   */
  @SuppressWarnings("unchecked")
  public final <R2> Either<L, R2> map(
      final Function<R, R2> f) {
    if (isLeft()) {
      return (Either<L, R2>) this;
    }
    return Either.right(f.apply(getRight()));
  }

  /**
   * Chains this either to an ordinary function.
   *
   * The supplied function is invoked with this Eithers right value and the
   * given additional value iff it is a right value or not at all if it is a
   * left value.
   *
   * @since 1.2.0
   * @param f
   *          An ordinary function, accepting a value of the right type and an
   *          additional value, and producing a new value.
   * @param value
   *          An additional value of arbitrary type.
   * @return A new Right Either if this was a right value, containing the result
   *         of the application of f, or a Left Either with the Left value of
   *         this Either.
   */
  @SuppressWarnings("unchecked")
  public final <B, R2> Either<L, R2> map(
      final BiFunction<R, B, R2> f, final B value) {
    if (isLeft()) {
      return (Either<L, R2>) this;
    }
    return Either.right(f.apply(getRight(), value));
  }

  /**
   * Consumes this Either.
   *
   * @since 1.2.0
   * @param leftConsumer
   * @param rightConsumer
   */
  public final void consume(
      final EitherConsumer<L> leftConsumer,
      final EitherConsumer<R> rightConsumer) {
    if (isLeft()) {
      leftConsumer.consume(getLeft());
    } else {
      rightConsumer.consume(getRight());
    }
  }
}
