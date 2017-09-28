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

import scala.collection.immutable.NumericRange
import scala.math


/** Contains an implicit conversion for longs and adds some useful
 *  methods to `long`. Since Scala's ints can be promoted to longs,
 *  these extension methods are available to ints as well. The implicit
 *  class `[[com.mipadi.jupiter.math.Numeric.RichLong RichLong]]` can
 *  be imported with:
 *
 *  {{{
 *  import com.mipadi.jupiter.math.Numeric._
 *  val isPrime = 100.isPrime
 *  }}}
 *
 *  @since 1.1
 */
object Numeric {

  /** An implicit conversion from longs that adds some useful methods to
   *  `long`. Since ints can be promoted to longs, these methods are
   *  available to ints as well. The implicit class can be imported with:
   *
   *  {{{
   *  import com.mipadi.jupiter.math.Numeric._
   *  val isPrime = 100.isPrime
   *  }}}
   *
   *  @since 1.1
   */
  implicit class RichLong(self: Long) {

    /** The wrapped long's divisors */
    lazy val divisors: Seq[Long] = (1L to self / 2).filter(_ divides self)

    /** The wrapped long's individual digits */
    lazy val digits: Seq[Int] = self.toString.map(_.toString).map(_.toInt)

    /** `true` if the wrapped long is prime */
    lazy val isPrime: Boolean = self match {
      case 1 => false
      case 2 => true
      case _ => !(2L to math.sqrt(self).toLong).exists(_ divides self)
    }

    /** Returns `true` if the wrapped long divides evenly into `b`. This
     *  is equivalent to the mathematical notation `a|b` (where `a` is the
     *  wrapped long).
     *
     *  @param b
     *    The dividend
     *  @return
     *    `true` if the wrapped long divides evenly into `b`
     */
    def divides(b: Long): Boolean = b % self == 0

    /** Returns a range using the wrapped long as an upper bound to the
     *  specified lower bound.
     *
     *  @param lower
     *    Lower bound of the range
     *  @return
     *    Range from the wrapped long down to the specified lower bound
     */
    def downto(lower: Long): NumericRange[Long] = (self to lower by -1)
  }
}
