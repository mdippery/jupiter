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

package com.mipadi.time

import java.time.{LocalDateTime, ZonedDateTime, ZoneId}
import java.util.Date


/** Provides various conversions for datetime-like objects.
 *
 *  Namely, it allows the conversion of datetime objects to types
 *  from the new `java.time` API through the use of the
 *  `[[com.mipadi.time.DateConversions.BridgedDate BridgedDate]]` implicit
 *  conversion.
 */
object DateConversions {

  /** Serves as a bridge between `java.util.Date` and the new `java.time`
   *  API in Java 8.
   *
   *  Adds two methods to seamlessly convert `java.util.Date` to
   *  `java.time.LocalDateTime` and `java.time.ZonedDateTime`.
   *
   *  @param dt
   *    The wrapped date
   *  @param ev
   *    An implicit delegate for handling datetime type conversions
   */
  implicit class BridgedDate[T](dt: T)(implicit ev: DateTime[T]) {

    /** Converts the datetime object into a legacy-style date.
     *
     *  @return
     *    A date
     */
    def toDate: Date = ev.toDate(dt)

    /** Converts the datetime object into a date.
     *
     *  Assumes that the `java.util.Date` is in UTC.
     *
     *  @return
     *    The converted datetime
     */
    def toLocal: LocalDateTime = ev.toLocal(dt)

    /** Converts the datetime object into a zoned datetime.
     *
     *  Assumes that the datetime object is in UTC if it does not already
     *  have a zone.
     *
     *  @return
     *    The converted datetime
     */
    def toZoned: ZonedDateTime = ev.toZoned(dt)
  }
}
