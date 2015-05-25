package de.scravy.either;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.experimental.UtilityClass;
import de.scravy.pair.Pair;
import de.scravy.pair.Pairs;

/**
 * Static utility methods for dealing with {@link Either} values.
 *
 * @since 1.1.0
 *
 * @author Julian Fleischer
 */
@UtilityClass
public class Eithers {

  /**
   * @since 1.1.0
   * @param source
   *          The source collection.
   * @param lefts
   *          The collection to copy lefts into.
   * @param rights
   *          The collection to copy rights into.
   */
  public static <L, R> void partitionInto(
      final Collection<Either<L, R>> source,
      final Collection<L> lefts, final Collection<R> rights) {
    for (final Either<L, R> either : source) {
      if (either.isLeft()) {
        lefts.add(either.getLeft());
      } else if (either.isRight()) {
        rights.add(either.getRight());
      }
    }
  }

  /**
   * @since 1.1.0
   * @param source
   *          The source collection.
   * @return A pair of lists of lefts and rights.
   */
  public static <L, R> Pair<List<L>, List<R>> parition(
      final Collection<Either<L, R>> source) {
    final Pair<List<L>, List<R>> result = Pairs.from(
        (List<L>) new ArrayList<L>(source.size() / 2),
        (List<R>) new ArrayList<R>(source.size() / 2));
    partitionInto(source, result.getFirst(), result.getSecond());
    return result;
  }
}
