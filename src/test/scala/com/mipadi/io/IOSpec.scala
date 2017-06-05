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

package com.mipadi.io

import org.scalatest._


class IOSpec extends FlatSpec with Matchers {
  "An IO action" should "return a value when it is applied" in {
    val io = IO { 5 }
    io() should be (5)
  }

  it should "not evaluate its function until it is applied" in {
    var i = 0
    val io = IO { i += 1; i }
    i should be (0)
    io() should be (1)
    i should be (1)
  }

  it should "bind another IO operation" in {
    var i = 0
    var io = IO { i += 10; i }
    i should be (0)
    io = io flatMap { _ => i -= 5; IO { i } }
    i should be (0)
    io() should be (5)
    i should be (5)
  }

  it should "obey the left-identity law of monads" in {
    val x = 5
    val f = (x: Int) => IO { x * 4 }
    val io = IO { x }
    val left = io flatMap f
    val right = f(x)
    left() should be (right())
  }

  it should "obey the right-identity law of monads" in {
    val m = IO { 5 }
    val unit = (x: Int) => IO { x }
    val left = m flatMap unit
    left() should be (m())
  }

  it should "obey the associativity law of monads" in {
    val f = (x: Int) => IO { x * 2 }
    val g = (x: Int) => IO { x * 10 }
    val io = IO { 5 }
    val left = io flatMap f flatMap g
    val right = io flatMap { x => f(x) flatMap g }
    left() should be (right())
  }
}
