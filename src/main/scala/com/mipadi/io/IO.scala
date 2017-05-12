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


/** Represents an I/O operation.
 *
 *  @param run
 *    A function that should be run when this `[[com.mipadi.io.IO IO]]`
 *    operation is applied.
 */
class IO[A](run: => A) {

  /** Run the function wrapped by this object.
   *
   *  @return
   *    The result of applying the operation wrapped by this object.
   */
  def apply(): A = run
}

/** Wraps a function or sequence of operations in an `[[com.mipadi.io.IO IO]]`
 *  object.
 *
 *  An `[[com.mipadi.io.IO IO]]` object represents a function or sequence of
 *  steps that can be performed at a later time. Creating such an object is
 *  fairly simple:
 *
 *  {{{
 *  val printIO = IO { println("This is an IO operation") }
 *  }}}
 *
 *  The operation can be run by simply applying the object:
 *
 *  {{{
 *  printIO()
 *  }}}
 */
object IO {

  /** Creates a new `[[com.mipadi.io.IO IO]]` object.
   *
   *  `[[com.mipadi.io.IO IO]]` objects can be created easily by simply
   *  writing something like:
   *
   *  {{{
   *  val getInt = IO {
   *    var i = 10
   *    10 *= 2
   *  }
   *  }}}
   *
   *  @param run
   *    The operation to run when this object is applied.
   *  @return
   *    A new `[[com.mipadi.io.IO IO]]` object that wraps the `run` function.
   */
  def apply[A](run: => A): IO[A] = new IO(run)
}
