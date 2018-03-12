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


/** Extends Scala's `Seq` trait with additional methods.
 *
 *  In particular, `Seq` contains the
 *  `[[com.mipadi.jupiter.collection.Seq.RichSeq RichSeq]]` implicit
 *  conversion, with extends Scala's sequences with some additional
 *  useful methods:
 *
 *  {{{
 *  import com.mipadi.jupiter.collection.Seq._
 *  val seq = List(1, 2, 3, 4, 5)
 *  val rotatedSeq = seq.rotate(3)
 *  }}}
 */
object Seq {

  /** Extends Scala's `Seq` trait with additional methods and properties.
   *
   *  This implicit conversion can be imported to extend sequences with
   *  the methods defined by `RichSeq`:
   *
   *  {{{
   *  import com.mipadi.jupiter.collection.Seq._
   *  val seq = List(1, 2, 3, 4, 5)
   *  val rotatedSeq = seq.rotate(3)
   *  }}}
   *
   *  @since 1.1
   */
  implicit class RichSeq[A](seq: Seq[A]) {

    /** All possible unique rotations of the sequence. */
    lazy val rotations: Seq[Seq[A]] = (0 until seq.length) map (seq.rotate(_))

    /** Rotates the sequence to the left or right. Positive numbers rotate
     *  a number of slots to the left; negative numbers rotate a number of
     *  slots to the right.
     *
     *  For example, calling `rotate(2)` on this sequence:
     *
     *  {{{
     *  val seq = List(1, 2, 3, 4, 5)
     *  }}}
     *
     *  will return this:
     *
     *  {{{
     *  List(3, 4, 5, 1, 2)
     *  }}}
     *
     *  Likewise, calling `rotate(-2)` on the same sequence will return
     *  this:
     *
     *  {{{
     *  List(4, 5, 1, 2, 3)
     *  }}}
     *
     *  @param n
     *    Rotates the sequence the number of slots to the left, in the
     *    case of positive `n`; or right, in the case of negative `n`.
     *  @return
     *    A rotated sequence.
     */
    def rotate(n: Int): Seq[A] = n match {
      case 0          => seq
      case _ if n > 0 => seq.drop(n) ++ seq.take(n)
      case _ if n < 0 => seq.reverse.rotate(n.abs).reverse
    }
  }
}
