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

import com.mipadi.jupiter.math.Numeric._


class NumericSpec extends FlatSpec with Matchers {

  "A long" should "return true if another long can divide evenly into it" in {
    (2L divides 6L) should be (true)
  }

  it should "return false if another long cannot divide evenly into it" in {
    (10L divides 18L) should be (false)
  }

  it should "return a sequence of its individual digits" in {
    123L.digits should be (List(1, 2, 3))
  }

  it should "return its divisors" in {
    24L.divisors should be (List(1, 2, 3, 4, 6, 8, 12))
  }

  it should "return true if it is prime" in {
    2L.isPrime should be (true)
    23L.isPrime should be (true)
  }

  it should "return false if it is not prime" in {
    1L.isPrime should be (false)
    1000L.isPrime should be (false)
  }

  it should "return its factorial" in {
    5L.factorial should be (120)
  }

  "An int" should "return true if another int can divide evenly into it" in {
    (2 divides 6) should be (true)
  }

  it should "return false if another int cannot divide evenly into it" in {
    (10 divides 18) should be (false)
  }

  it should "return a sequence of its individual digits" in {
    123.digits should be (List(1, 2, 3))
  }

  it should "return its divisors" in {
    24.divisors should be (List(1, 2, 3, 4, 6, 8, 12))
  }

  it should "return false if it is not prime" in {
    1.isPrime should be (false)
    1000.isPrime should be (false)
  }

  it should "return true if it is prime" in {
    2.isPrime should be (true)
    23.isPrime should be (true)
  }

  it should "return a reversed range" in {
    (10 downto 1).map(_.toInt) should be (List(10, 9, 8, 7, 6, 5, 4, 3, 2, 1))
  }

  it should "return its factorial" in {
    5.factorial should be (120)
  }
}
