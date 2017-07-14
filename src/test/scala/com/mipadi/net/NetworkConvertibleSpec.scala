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

package com.mipadi.net

import org.scalatest._
import com.mipadi.net.NetworkConversions._


class NetworkConvertibleSpec extends FlatSpec with Matchers {
  "A byte" should "be convertible to a byte array" in {
    val b1: Byte = 1
    b1.toNetworkBytes should be (Array(1))
    val b2: Byte = 127
    b2.toNetworkBytes should be (Array(127))
    val b3: Byte = -126
    b3.toNetworkBytes should be (Array(-126))
  }

  "A short" should "be convertible to a byte array" in {
    val s1: Short = 1
    s1.toNetworkBytes should be (Array(0, 1))
    val s2: Short = 1027
    s2.toNetworkBytes should be (Array(4, 3))
    val s3: Short = -1027
    s3.toNetworkBytes should be (Array(-5, -3))
  }

  "An integer" should "be convertible to a byte array" in {
    val i1 = 1
    i1.toNetworkBytes should be (Array(0, 0, 0, 1))
    val i2 = 123456789
    i2.toNetworkBytes should be (Array(7, 91, -51, 21))
    val i3 = -987234
    i3.toNetworkBytes should be (Array(-1, -16, -17, -98))
  }

  "A long" should "be converitble to a byte array" in {
    val l1 = 1L
    l1.toNetworkBytes should be (Array(0, 0, 0, 0, 0, 0, 0, 1))
    val l2 = 876123456109L
    l2.toNetworkBytes should be (Array(0, 0, 0, -53, -3, 7, 2, 109))
    val l3 = -107345L
    l3.toNetworkBytes should be (Array(-1, -1, -1, -1, -1, -2, 92, -81))
  }

  "A float" should "be convertible to a byte array" in {
    val f1 = 1.0F
    f1.toNetworkBytes should be (Array(63, -128, 0, 0))
    val f2 = 100100100.111F
    f2.toNetworkBytes should be (Array(76, -66, -19, 1))
    val f3 = -222333444.5556F
    f3.toNetworkBytes should be (Array(-51, 84, 8, -96))
  }

  "A double" should "be convertible to a byte array" in {
    val d1 = 1.0
    d1.toNetworkBytes should be (Array(63, -16, 0, 0, 0, 0, 0, 0))
    val d2 = 100200300.444
    d2.toNetworkBytes should be (Array(65, -105, -29, -67, -79, -58, -89, -16))
    val d3 = -123456789.00225
    d3.toNetworkBytes should be (Array(-63, -99, 111, 52, 84, 2, 77, -45))
  }
}
