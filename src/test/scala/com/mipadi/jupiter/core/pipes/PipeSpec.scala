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

package com.mipadi.jupiter.core.pipes

import org.scalatest._
import com.mipadi.jupiter.core.pipes._


class PipeSpec extends FlatSpec with Matchers {
  private def map[A, B](f: A => B)(items: List[A]): List[B] = items match {
    case Nil    => List.empty
    case h :: t => f(h) :: map(f)(t)
  }

  "A pipe" should "pipe an object into a sequence of methods" in {
    List(1, 2, 3, 4) |> map (x => x * 2) should equal (List(2, 4, 6, 8))
  }
}
