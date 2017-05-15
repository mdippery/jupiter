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

package com.mipadi.date

import java.util.Date


/** Useful methods and operations for representations of Unix timestamps.
 *
 *  Provides a way to convert a Scala `Double` representing a Unix timestamp
 *  to a `java.util.Date` object:
 *
 *  {{{
 *  import com.mipadi.date.UnixTimestamp._
 *  val timestamp = 1490999455.0
 *  val date = timestamp.toDate
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
     *  import com.mipadi.date.UnixTimestamp._
     *  val timestamp = 1490999455.0
     *  val date = timestamp.toDate
     *  }}}
     *
     *  @return
     *    The `java.util.Date` object associated with the Unix timestamp
     *    respresented by the Scala `Double`.
     */
    def toDate: Date = new Date((d * 1000).toLong)
  }
}
