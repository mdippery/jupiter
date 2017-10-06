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


/** Contains implicit conversions that add a `times` method to both ints
 *  and longs, so that they can be used like this:
 *
 *  {{{
 *  import com.mipadi.jupiter.core.StepIterator._
 *  15.times.foreach { println(_) }
 *  }}}
 *
 *  @since 1.1
 */
object StepIterator {

  /** An implicit conversion that adds a `times` method to ints.
   *
   *  {{{
   *  import com.mipadi.jupiter.core.StepIterator._
   *  15.times.foreach { println(_) }
   *  }}}
   *
   *  @since 1.1
   */
  implicit class SteppedInt(self: Int) {

    /** Iterates over the int a number of times. */
    def times: Seq[Int] = 0 until self
  }

  /** An implicit conversion that adds a `times` method to longs.
   *
   *  {{{
   *  import com.mipadi.jupiter.core.StepIterator._
   *  15L.times.foreach { println(_) }
   *  }}}
   *
   *  @since 1.1
   */
  implicit class SteppedLong(self: Long) {

    /** Iterates over the int a number of times. */
    def times: Seq[Long] = 0L until self
  }
}
