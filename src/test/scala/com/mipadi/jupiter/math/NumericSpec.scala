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

import org.scalatest._

import com.mipadi.jupiter.math.NumericSpec._


object NumericSpec {
  implicit class RichLong[T](self: T)(implicit ev: Integral[T]) {
    lazy val digits: Seq[Int ] = ev.digits(self)

    def divides(b: T): Boolean = ev.divides(self, b)

    def / (lhs: T, rhs: Long): T = ev.divide(lhs, rhs)
    def / (lhs: T, rhs: Double): Double = ev.divide(lhs, rhs)
  }

  implicit class RichDouble[T](self: T)(implicit ev: Fractional[T]) {
    def / (lhs: T, rhs: Long): T = ev.divide(lhs, rhs)
    def / (lhs: T, rhs: Double): Double = ev.divide(lhs, rhs)
  }
}


class NumericSpec extends FlatSpec with Matchers {
  "A long" should "be divided by another long" in {
    (40000000000L / 1000L) should === (40000000L)
  }

  it should "be divided by an int" in {
    (40000000000L / 1000) should === (40000000L)
  }

  it should "be divided by a double" in {
    val expected = 40000000.0
    val actual = 40000000000L / 1000.0
    val delta = (actual - expected).abs
    delta should be <= 0.01
  }

  it should "return true if another long can divide evenly into it" in {
    (2L divides 6L) should be (true)
  }

  it should "return false if another long cannot divide evenly into it" in {
    (10L divides 18L) should be (false)
  }

  it should "return a sequence of its individual digits" in {
    123L.digits should be (List(1, 2, 3))
  }

  "An int" should "be divided by a long" in {
    (10000 / 98L) should === (102)
  }

  it should "be divided by another int" in {
    (10000 / 98) should === (102)
  }

  it should "be divided by a double" in {
    val expected = 102.04
    val actual = 10000 / 98.0
    val delta = (actual - expected).abs
    delta should be <= 0.01
  }

  it should "return true if another int can divide evenly into it" in {
    (2 divides 6) should be (true)
  }

  it should "return false if another int cannot divide evenly into it" in {
    (10 divides 18) should be (false)
  }

  it should "return a sequence of its individual digits" in {
    123.digits should be (List(1, 2, 3))
  }

  "A double" should "be divided by a long" in {
    val expected = 8771.6375
    val actual = 982423.4 / 112L
    val delta = (actual - expected).abs
    delta should be <= 0.01
  }

  it should "be divided by an int" in {
    val expected = 8771.6375
    val actual = 982423.4 / 112
    val delta = (actual - expected).abs
    delta should be <= 0.01
  }

  it should "be divided by a double" in {
    val expected = 8752.1
    val actual = 982423.4 / 112.25
    val delta = (actual - expected).abs
    delta should be <= 0.01
  }
}
