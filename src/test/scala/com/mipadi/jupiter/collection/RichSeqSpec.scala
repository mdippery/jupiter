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

package com.mipadi.jupiter.collection

import org.scalatest._
import com.mipadi.jupiter.collection.Seq._


class RichSeqSpec extends FlatSpec with Matchers {
  val seq5 = List(1, 2, 3, 4, 5)

  "A sequence" should "rotate to the left when given a positive offset" in {
    seq5.rotate(0) should be (List(1, 2, 3, 4, 5))
    seq5.rotate(1) should be (List(2, 3, 4, 5, 1))
    seq5.rotate(2) should be (List(3, 4, 5, 1, 2))
    seq5.rotate(3) should be (List(4, 5, 1, 2, 3))
    seq5.rotate(4) should be (List(5, 1, 2, 3, 4))
    seq5.rotate(5) should be (List(1, 2, 3, 4, 5))
  }

  it should "rotate to the right when given a negative offset" in {
    seq5.rotate(-0) should be (List(1, 2, 3, 4, 5))
    seq5.rotate(-1) should be (List(5, 1, 2, 3, 4))
    seq5.rotate(-2) should be (List(4, 5, 1, 2, 3))
    seq5.rotate(-3) should be (List(3, 4, 5, 1, 2))
    seq5.rotate(-4) should be (List(2, 3, 4, 5, 1))
    seq5.rotate(-5) should be (List(1, 2, 3, 4, 5))
  }

  it should "return all possible rotations" in {
    val expected = List(
      List(1, 2, 3, 4, 5),
      List(2, 3, 4, 5, 1),
      List(3, 4, 5, 1, 2),
      List(4, 5, 1, 2, 3),
      List(5, 1, 2, 3, 4)
    )
    seq5.rotations should be (expected)
  }
}
