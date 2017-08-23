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


/** Provides a mechanism for piping or threading objects through methods
 *  and functions.
 *
 *  Any object can be implicitly converted to a $pipe. This allows an object
 *  to be threaded through a series of functions, similar to Clojure's thread
 *  operator `->` or F#'s pipe forward operator `|>`. Instead of this:
 *
 *  {{{
 *  val x = someMethod(anotherMethod(aThirdMethod(obj)))
 *  }}}
 *
 *  You can do this:
 *
 *  {{{
 *  val x = obj |> aThirdMethod |> anotherMethod |> someMethod
 *  }}}
 *
 *  Which reads a bit better in many cases.
 *
 *  @define pipe
 *    `[[com.mipadi.jupiter.core.pipes.Pipe Pipe]]`
 */
package object pipes {

  /** An implicit conversion that adds a `|>` operator to all classes. */
  implicit class Pipe[A](item: A) {

    /** Allows an object to be easily piped through a sequence of methods
     *  or functions.
     *
     *  @param f
     *    The function that will be applied to the receiver.
     *  @return
     *    The result of applying `f` to the receiver.
     */
    def |>[B] (f: A => B): B = f(item)
  }
}
