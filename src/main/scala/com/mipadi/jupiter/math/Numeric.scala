/*
 * Copyright (C) 2017 Michael Dippery <michael@monkey-robot.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mipadi.jupiter.math


/** A type class that represents types on which numeric operations
 *  can be performed.
 *
 *  @since 1.1
 *
 *  @see
 *    [[http://danielwestheide.com/blog/2013/02/06/the-neophytes-guide-to-scala-part-12-type-classes.html
 *      Type classes in Scala]]
 */
trait Numeric[T] {
  def divide(x: T, y: Long): T
  def divide(x: T, y: Int): T
  def divide(x: T, y: Double): Double

  /** Returns `true` if `a` divides evenly into `b`, that is, `b / a` has
   *  no remainder. Equivalent to `a|b`.
   *
   *  @param a
   *    Divisor
   *  @param b
   *    Original number
   *  @return
   *    `true` if `a` divides evenly into `b` with no remainder
   */
  def divides(a: T, b: T): Boolean
}

/** Contains default implicit objects for various numeric types.
 *
 *  @since 1.1
 */
object Numeric {

  /** A default implicit for longs */
  implicit object NumericLong extends Numeric[Long] {
    override def divide(x: Long, y: Long): Long = x / y
    override def divide(x: Long, y: Int): Long = x / y
    override def divide(x: Long, y: Double): Double = x / y

    override def divides(a: Long, b: Long): Boolean = b % a == 0
  }

  /** A default implicit for ints */
  implicit object NumericInt extends Numeric[Int] {
    override def divide(x: Int, y: Long): Int = x / y.toInt
    override def divide(x: Int, y: Int): Int = x / y
    override def divide(x: Int, y: Double): Double = x / y

    override def divides(a: Int, b: Int): Boolean = b % a == 0
  }

  /** A default implicit for doubles */
  implicit object NumericDouble extends Numeric[Double] {
    override def divide(x: Double, y: Long): Double = x / y
    override def divide(x: Double, y: Int): Double = x / y
    override def divide(x: Double, y: Double): Double = x / y

    override def divides(a: Double, b: Double): Boolean = true
  }
}
