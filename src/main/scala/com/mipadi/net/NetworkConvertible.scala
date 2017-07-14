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

package com.mipadi.net

import java.nio.ByteBuffer


/** A type class for converting values to network byte order.
 *
 *  @see
 *    [[http://danielwestheide.com/blog/2013/02/06/the-neophytes-guide-to-scala-part-12-type-classes.html
 *      Type classes in Scala]]
 */
trait NetworkConvertible[T] {
  def toNetworkBytes(x: T): Array[Byte]
}

/** Contains default implicit conversions for variable types to network byte
 *  order.
 */
object NetworkConvertible {
  implicit object NetworkConvertibleByte extends NetworkConvertible[Byte] {
    override def toNetworkBytes(x: Byte): Array[Byte] = Array(x)
  }

  implicit object NetworkConvertibleShort extends NetworkConvertible[Short] {
    override def toNetworkBytes(x: Short): Array[Byte] =
      ByteBuffer.allocate(2).putShort(x).array
  }

  implicit object NetworkConvertibleInt extends NetworkConvertible[Int] {
    override def toNetworkBytes(x: Int): Array[Byte] =
      ByteBuffer.allocate(4).putInt(x).array
  }

  implicit object NetworkConvertibleLong extends NetworkConvertible[Long] {
    override def toNetworkBytes(x: Long): Array[Byte] =
      ByteBuffer.allocate(8).putLong(x).array
  }

  implicit object NetworkConvertibleFloat extends NetworkConvertible[Float] {
    override def toNetworkBytes(x: Float): Array[Byte] =
      ByteBuffer.allocate(4).putFloat(x).array
  }

  implicit object NetworkConvertibleDouble extends NetworkConvertible[Double] {
    override def toNetworkBytes(x: Double): Array[Byte] =
      ByteBuffer.allocate(8).putDouble(x).array
  }
}


/** Converts Scala's scalar types to arrays of bytes in network byte order.
 *
 *  `toNetworkBytes` can be called on bytes, shorts, ints, longs, and floats
 *  to convert them into an array of bytes in network byte order:
 *
 *  {{{
 *  import com.mipadi.net.NetworkConversions._
 *  val i = 100
 *  val bytes = i.toNetworkBytes
 *  }}}
 */
object NetworkConversions {

  /** Implicitly converts scalar types into arrays of bytes in network
   *  byte order.
   *
   *  @param wrapped
   *    The wrapped value
   */
  implicit class NetworkConversion[T](wrapped: T)(implicit ev: NetworkConvertible[T]) {

    /** Converts the wrapped value to network byte order.
     *
     *  @return
     *    An array of bytes representing the wrapped value in network
     *    byte order.
     */
    def toNetworkBytes: Array[Byte] = ev.toNetworkBytes(wrapped)
  }
}
