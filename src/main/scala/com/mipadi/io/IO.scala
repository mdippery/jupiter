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


/** Encapsulates an I/O operation.
 *
 *  @tparam A
 *    Return type of the I/O operation
 *  @param run
 *    A function that should be run when this `[[com.mipadi.io.IO IO]]`
 *    operation is applied.
 *
 *  @see
 *    [[https://gist.github.com/jdegoes/7cc7e7aacd032773f3c24123d0d486d4
 *      A pedagogical implementation of the IO monad in Scala in 14 LOC]]
 *  @see
 *    [[https://engineering.sharethrough.com/blog/2016/04/18/explaining-monads-part-1/
 *      Mondas are confusing. Let us help.]]
 *  @see
 *    [[https://medium.com/@sinisalouc/demystifying-the-monad-in-scala-cc716bb6f534
 *      Demystifying the Monad in Scala]]
 */
trait IO[A] {

  /** Applies the given function to the value produced by this I/O operation.
   *
   *  This is equivalent to the monadic ''bind'' operation (''>>='' in
   *  Haskell).
   *
   *  @tparam B
   *    Return type of the operation chained to this I/O operation
   *  @param f
   *    The function to apply to the result of this I/O operation
   *  @return
   *    A new I/O operation that is the result of applying the given
   *    function to `run`
   */
  def flatMap[B](f: A => IO[B]): IO[B]

  /** Applies the given function to the value produced by this I/O operation
   *  and lifts it into another I/O operation.
   *
   *  @tparam B
   *    The return type of the given function
   *  @param f
   *    The function to map onto the value calculated by this I/O operation
   *  @return
   *    A new I/O operation that produces the result of applying the given
   *    function to `run` when evalulated
   */
  def map[B](f: A => B): IO[B] = flatMap(x => IO { f(x) })

  /** Evaluates the I/O operation and produces its result.
   *
   *  @return
   *    The result of evaluating the I/O operation
   */
  def apply(): A

  /** An alias for `apply()`. */
  def unsafePerformIO(): A = apply()

  /** An alias for `flatMap`. */
  def >>= [B](f: A => IO[B]): IO[B] = flatMap(f)

  /** Compose two I/O operations together.
   *
   *  The target of the method is evaluated, but its return value is discarded.
   *  `io` is also evaluated, and the result of its evaluation is returned.
   *
   *  @param io
   *    The second I/O operation to evaluate
   *  @return
   *    The result of evaluating `io`
   */
  def >> [B](io: IO[B]): IO[B] = IO {
    unsafePerformIO()
    io.unsafePerformIO()
  }
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
   *  This is equivalent to the monadic ''unit'' function.
   *
   *  @param run
   *    The operation to run when this object is applied.
   *  @return
   *    A new `[[com.mipadi.io.IO IO]]` object that wraps the `run` function.
   */
  def apply[A](run: => A): IO[A] = new IOImpl(run)
}


private class IOImpl[A](run: => A) extends IO[A] {
  override def flatMap[B](f: A => IO[B]): IO[B] =
    IO { f(unsafePerformIO()).unsafePerformIO() }

  override def apply(): A = run
}
