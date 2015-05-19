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
      return left;
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
   * @param value The value to encapsulate as left.
   * @param rightType The right type (to aid in type inference)
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
      return right;
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
   * @param value The value to encapsulate as right.
   * @param rightType The right type (to aid in type inference)
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
}
