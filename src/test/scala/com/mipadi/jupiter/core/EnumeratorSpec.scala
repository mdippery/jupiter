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

package com.mipadi.jupiter.core

import org.scalatest._

import com.mipadi.jupiter.core.Enumerator._


class EnumeratorSpec extends FlatSpec with Matchers {
  "An int" should "be iterated over a number of times" in {
    var s = 0
    var c = 0
    5.times.foreach { i =>
      c += 1
      s += i
    }
    c should be (5)
    s should be (10)
  }

  "A long int" should "be iterated over a number of times" in {
    var s = 0L
    var c = 0
    5L.times.foreach { i =>
      c += 1
      s += i
    }
    c should be (5)
    s should be (10L)
  }
}
