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

package com.mipadi.math


/** A type class that represents types on which division can be performed.
 *
 *  @see
 *    [[http://danielwestheide.com/blog/2013/02/06/the-neophytes-guide-to-scala-part-12-type-classes.html
 *      Type classes in Scala]]
 */
trait Dividable[T] {
  def divide(x: T, y: Long): T
  def divide(x: T, y: Int): T
  def divide(x: T, y: Double): Double
}

/** Containts default implicit objects for various dividable types. */
object Dividable {

  /** A default implicit for longs */
  implicit object DividableLong extends Dividable[Long] {
    override def divide(x: Long, y: Long): Long = x / y
    override def divide(x: Long, y: Int): Long = x / y
    override def divide(x: Long, y: Double): Double = x / y
  }

  /** A default implicit for ints */
  implicit object DividableInt extends Dividable[Int] {
    override def divide(x: Int, y: Long): Int = x / y.toInt
    override def divide(x: Int, y: Int): Int = x / y
    override def divide(x: Int, y: Double): Double = x / y
  }

  /** A default implicit for doubles */
  implicit object DividableDouble extends Dividable[Double] {
    override def divide(x: Double, y: Long): Double = x / y
    override def divide(x: Double, y: Int): Double = x / y
    override def divide(x: Double, y: Double): Double = x / y
  }
}
