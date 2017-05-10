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
  "An IO action" should "return a value when run is called" in {
    val io = IO { 5 }
    io() should be (5)
  }

  it should "not evaluate its function until run is called" in {
    var i = 0
    val io = IO { i += 1; i }
    i should be (0)
    io() should be (1)
    i should be (1)
  }
}
