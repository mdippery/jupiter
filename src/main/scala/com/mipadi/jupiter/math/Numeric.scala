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
  def divide(lhs: T, rhs: Long): T
  def divide(lhs: T, rhs: Double): Double
}


/** Type class for whole numbers */
trait Integral[T] extends Numeric[T] {

  /** Returns the individual digits of the given number.
   *
   *  @param n
   *    The number
   *  @return
   *    The individual digits of `n`
   */
  def digits(n: T): Seq[Int] = n.toString.map(_.toString).map(_.toInt)

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


/** Type class for numbers with a fractional (or decimal) component */
trait Fractional[T] extends Numeric[T]


/** Contains default implicit objects for various numeric types.
 *
 *  @since 1.1
 */
object Numeric {

  /** A default implicit for longs */
  implicit object NumericLong extends Integral[Long] {
    override def divide(lhs: Long, rhs: Long): Long = lhs / rhs
    override def divide(lhs: Long, rhs: Double): Double = lhs / rhs

    override def divides(a: Long, b: Long): Boolean = b % a == 0
  }

  /** A default implicit for ints */
  implicit object NumericInt extends Integral[Int] {
    override def divide(lhs: Int, rhs: Long): Int = lhs / rhs.toInt
    override def divide(lhs: Int, rhs: Double): Double = lhs / rhs

    override def divides(a: Int, b: Int): Boolean = b % a == 0
  }

  /** A default implicit for doubles */
  implicit object NumericDouble extends Fractional[Double] {
    override def divide(lhs: Double, rhs: Long): Double = lhs / rhs
    override def divide(lhs: Double, rhs: Double): Double = lhs / rhs
  }
}
