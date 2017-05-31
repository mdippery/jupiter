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
import java.time.temporal.ChronoUnit
import java.util.Date


/** A type class for datetime-like objects.
 *
 *  @see
 *    [[http://danielwestheide.com/blog/2013/02/06/the-neophytes-guide-to-scala-part-12-type-classes.html
 *      Type classes in Scala]]
 */
trait DateTime[T] {

  /** Calculates the corresponding time at midnight. */
  def atMidnight(dt: T): T

  /** Calculates the seconds since the Unix epoch. */
  def sinceEpoch(dt: T): Long
}

/** Provides default implicits for various datetime-like objects. */
object DateTime {
  implicit object DateTimeDate extends DateTime[Date] {
    override def atMidnight(dt: Date): Date =
      new Date(dt.toInstant.truncatedTo(ChronoUnit.DAYS).toEpochMilli)

    override def sinceEpoch(dt: Date): Long =
      dt.toInstant.getEpochSecond
  }

  implicit object DateTimeLocal extends DateTime[LocalDateTime] {
    override def atMidnight(dt: LocalDateTime): LocalDateTime =
      dt.truncatedTo(ChronoUnit.DAYS)

    override def sinceEpoch(dt: LocalDateTime): Long =
      dt.toEpochSecond(ZoneId.of("UTC").getRules.getOffset(dt))
  }

  implicit object DateTimeZoned extends DateTime[ZonedDateTime] {
    override def atMidnight(dt: ZonedDateTime): ZonedDateTime =
      dt.truncatedTo(ChronoUnit.DAYS)

    override def sinceEpoch(dt: ZonedDateTime): Long =
      dt.toEpochSecond
  }
}
