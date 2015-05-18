package de.scravy.either;

import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * 
 * @since 1.0.0
 * 
 * @author Julian Fleischer
 *
 * @param <L>
 * @param <R>
 */
public abstract class Either<L, R> {

  /**
   * 
   * @since 1.0.0
   * 
   * @author Julian Fleischer
   *
   * @param <L>
   * @param <R>
   */
  @Value
  @EqualsAndHashCode(callSuper = false)
  public final static class Left<L, R> extends Either<L, R> {

    private final L left;

    @Override
    public boolean isLeft() {
      return true;
    }

  }

  /**
   * 
   * @since 1.0.0
   * 
   * @author Julian Fleischer
   * 
   * @param value
   * @return
   */
  public static <A, B> Either<A, B> left(final A value) {
    return new Left<A, B>(value);
  }

  /**
   * 
   * @since 1.0.0
   * 
   * @author Julian Fleischer
   *
   * @param <L>
   * @param <R>
   */
  @Value
  @EqualsAndHashCode(callSuper = false)
  public final static class Right<L, R> extends Either<L, R> {

    private final R right;

    @Override
    public boolean isLeft() {
      return true;
    }

  }

  /**
   * 
   * @since 1.0.0
   * 
   * @param value
   * @return
   */
  public static <A, B> Either<A, B> right(final B value) {
    return new Right<A, B>(value);
  }

  private Either() {

  }

  /**
   * 
   * @since 1.0.0
   * 
   * @return
   */
  public boolean isLeft() {
    return false;
  }

  /**
   * 
   * @since 1.0.0
   * 
   * @return
   */
  public boolean isRight() {
    return false;
  }

  /**
   * 
   * @since 1.0.0
   * 
   * @return
   */
  public L getLeft() {
    throw new UnsupportedOperationException();
  }

  /**
   * 
   * @since 1.0.0
   * 
   * @return
   */
  public R getRight() {
    throw new UnsupportedOperationException();
  }
}
