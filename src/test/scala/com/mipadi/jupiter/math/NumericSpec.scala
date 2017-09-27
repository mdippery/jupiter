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


class NumericSpec extends FlatSpec with Matchers {
  "A long" should "be divided by another long" in {
    Numeric.NumericLong.divide(40000000000L, 1000L) should === (40000000L)
  }

  it should "be divided by an int" in {
    Numeric.NumericLong.divide(40000000000L, 1000) should === (40000000L)
  }

  it should "be divided by a double" in {
    val expected = 40000000.0
    val actual = Numeric.NumericLong.divide(40000000000L, 1000.0)
    val delta = (actual - expected).abs
    delta should be <= 0.01
  }

  it should "return true if another long can divide evenly into it" in {
    Numeric.NumericLong.divides(2L, 6L) should be (true)
  }

  it should "return false if another long cannot divide evenly into it" in {
    Numeric.NumericLong.divides(10L, 18L) should be (false)
  }

  "An int" should "be divided by a long" in {
    Numeric.NumericInt.divide(10000, 98L) should === (102)
  }

  it should "be divided by another int" in {
    Numeric.NumericInt.divide(10000, 98) should === (102)
  }

  it should "be divided by a double" in {
    val expected = 102.04
    val actual = Numeric.NumericInt.divide(10000, 98.0)
    val delta = (actual - expected).abs
    delta should be <= 0.01
  }

  it should "return true if another int can divide evenly into it" in {
    Numeric.NumericInt.divides(2, 6) should be (true)
  }

  it should "return false if another int cannot divide evenly into it" in {
    Numeric.NumericInt.divides(10, 18) should be (false)
  }

  "A double" should "be divided by a long" in {
    val expected = 8771.6375
    val actual = Numeric.NumericDouble.divide(982423.4, 112L)
    val delta = (actual - expected).abs
    delta should be <= 0.01
  }

  it should "be divided by an int" in {
    val expected = 8771.6375
    val actual = Numeric.NumericDouble.divide(982423.4, 112)
    val delta = (actual - expected).abs
    delta should be <= 0.01
  }

  it should "be divided by a double" in {
    val expected = 8752.1
    val actual = Numeric.NumericDouble.divide(982423.4, 112.25)
    val delta = (actual - expected).abs
    delta should be <= 0.01
  }

  it should "return true if another double can divide evenly into it" in {
    Numeric.NumericDouble.divides(2.3, 6.8) should be (true)
  }
}
