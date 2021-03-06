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

package com.mipadi.jupiter.time

import java.time.{ZonedDateTime, ZoneId}
import java.time.temporal.ChronoUnit
import java.util.Date


/** Useful methods and operations for representations of Unix timestamps.
 *
 *  Provides a way to convert a Scala `Double` representing a Unix timestamp
 *  to a `java.util.Date` object:
 *
 *  {{{
 *  import com.mipadi.jupiter.time.UnixTimestamp._
 *  val timestamp = 1490999455.0
 *  val date = timestamp.toDate
 *  }}}
 *
 *  It also provides a way to get the number of seconds since the Unix epoch
 *  from a `java.util.Date` object:
 *
 *  {{{
 *  import com.mipadi.jupiter.time.UnixTimestamp._
 *  val date = new Date()
 *  val seconds = date.sinceEpoch
 *  }}}
 */
object UnixTimestamp {

  /** Adds extension methods to Scala `Double`s.
   *
   *  @param d
   *    The wrapped Unix timestamp
   */
  implicit class ConvertibleUnixTimestamp(d: Double) {

    /** Converts a Scala `Double` to a `java.util.Date` object.
     *
     *  `toDate` can be called on a Scala `Double` representing a Unix
     *  timestamp to convert it into the associated `Date`:
     *
     *  {{{
     *  import com.mipadi.jupiter.time.UnixTimestamp._
     *  val timestamp = 1490999455.0
     *  val date = timestamp.toDate
     *  }}}
     *
     *  Since Unix timestamps represent times in UTC, callers can easily
     *  convert the returned date to a `java.time.ZonedDateTime` instance
     *  using the `[[com.mipadi.jupiter.time.DateConversions.BridgedDate
     *  BridgedDate]]` implicit conversion:
     *
     *  {{{
     *  import com.mipadi.jupiter.time.DateConversions._
     *  import com.mipadi.jupiter.time.UnixTimestamp._
     *  val timestamp = 1490999455.0
     *  val date = timestamp.toDate.toZoned
     *  }}}
     *
     *  @return
     *    The `java.util.Date` object associated with the Unix timestamp
     *    respresented by the Scala `Double`.
     */
    def toDate: Date = new Date((d * 1000).toLong)
  }


  /** Adds extension methods to datetime-like objects.
   *
   *  @param dt
   *    The wrapped date
   *  @param ev
   *    A delegate for date behaviors and conversions
   */
  implicit class ConvertibleUnixDateTime[T](dt: T)(implicit ev: DateTime[T]) {

    /** Returns midnight for the given date.
     *
     *  @return
     *    The date at midnight
     */
    def atMidnight: T = ev.atMidnight(dt)

    /** Calculates the number of seconds since the Unix epoch.
     *
     *  `sinceEpoch` can be called on a `java.util.Date` to convert it into
     *  a Unix timestamp represented as a `Long`:
     *
     *  {{{
     *  import com.mipadi.jupiter.time.UnixTimestamp._
     *  val date = new Date()
     *  val seconds = date.sinceEpoch
     *  }}}
     *
     *  @return
     *    The number of seconds since the Unix epoch
     */
    def sinceEpoch: Long = ev.sinceEpoch(dt)
  }
}
