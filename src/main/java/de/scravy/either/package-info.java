/**
 * <p>Do you know the <code>Either</code> type from Haskell?
 * So this is the crippled Java version of it (an explicit sum type).
 *
 * <h2>Usage</h2>
 *
 * <pre>
 *   final Either&lt;String, Exception&gt; value = Either.left("Hello World!");
 *     if (value.isLeft()) {
 *       System.out.println(value.getLeft());
 *     }
 *   }
 * </pre>
 * <p>-or
 *
 * <pre>
 *   final Either&lt;String, Exception&gt; value = Either.left("Hello World!");
 *     if (value instanceof Either.Left) {
 *       System.out.println(value.getLeft());
 *     }
 *   }
 * </pre>
 *
 * @since 1.0.0
 * @author Julian Fleischer
 */
package de.scravy.either;

