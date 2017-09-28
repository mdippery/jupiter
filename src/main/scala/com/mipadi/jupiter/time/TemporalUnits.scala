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

import scala.math.{Fractional, Integral}


/** Convert longs, ints, and doubles to temporal units like seconds, minutes,
 *  hours, etc.
 */
object TemporalUnits {

  /** Implicitly converts an integral value to temporal units like
   *  seconds, minutes, hours, etc.
   *
   *  With this conversion, callers can easily calculate temporal units from
   *  a raw number:
   *
   *  {{{
   *  import com.mipadi.jupiter.time.TemporalUnits._
   *  val seconds = 864000
   *  val minutes = seconds.minutes
   *  }}}
   *
   *  @param self
   *    The wrapped value
   *  @param ev
   *    A divider for the given types of values
   */
  implicit class TemporalIntegral[T](self: T)(implicit ev: Integral[T]) {
    private val SecondsPerDay = 86400
    private val SecondsPerSiderealDay = 86164.0916
    private val DaysPerYear = 365
    private val SecondsPerAstronomicalYear = 31557600

    /** The number of seconds.
     *
     *  @return
     *    The number of seconds represented by the wrapped value
     */
    def seconds: T = self

    /** The number of minutes.
     *
     *  There are 60 seconds in a minute.
     *
     *  @return
     *    The number of minutes represented by the wrapped value
     */
    def minutes: T = ev.quot(seconds, ev.fromInt(60))

    /** The number of hours.
     *
     *  There are 60 minutes or 3600 seconds in an hour.
     *
     *  @return
     *    The number of hours represented by the wrapped value
     */
    def hours: T = ev.quot(minutes, ev.fromInt(60))

    /** The number of calendar days.
     *
     *  There are 24 hours or 86,400 seconds in an hour.
     *
     *  @return
     *    The number of days represented by the wrapped value
     */
    def days: T = ev.quot(seconds, ev.fromInt(SecondsPerDay))

    /** The number of ''sidereal days''.
     *
     *  A ''sidereal day'' is the amount of time taken by Earth to rotate
     *  on its axis relative to the stars. It is defined as 86164.0916
     *  seconds, or about 4 minutes shorter than a solary day.
     *
     *  @return
     *    The number of sidereal days represented by the wrapped value
     */
    def siderealDays: Double = ev.toDouble(seconds) / SecondsPerSiderealDay

    /** The number of weeks
     *
     *  There are 7 days in a week.
     *
     *  @return
     *    The number of weeks represented by the wrapped value
     */
    def weeks: T = ev.quot(days, ev.fromInt(7))

    /** The number of years.
     *
     *  Assumes a year of 365 days.
     *
     *  @return
     *    The number of years represented by the wrapped value
     */
    def years: T = ev.quot(days, ev.fromInt(DaysPerYear))

    /** The number of ''astronomical years''.
     *
     *  An ''astronomical year'' is the amount of time it takes Earth to
     *  revolve around the sun. It is equal to 31,557,600 seconds, or about
     *  365.25 days.
     *
     *  @return
     *    The number of astronomical years represented by the wrapped value
     */
    def astronomicalYears: Double = ev.toDouble(seconds) / SecondsPerAstronomicalYear.toDouble
  }

  /** Implicitly converts a fractional value to temporal units like
   *  seconds, minutes, hours, etc.
   *
   *  With this conversion, callers can easily calculate temporal units from
   *  a raw number:
   *
   *  {{{
   *  import com.mipadi.jupiter.time.TemporalUnits._
   *  val seconds = 864000
   *  val minutes = seconds.minutes
   *  }}}
   *
   *  @param self
   *    The wrapped value
   *  @param ev
   *    A divider for the given types of values
   */
  implicit class TemporalFractional[T](self: T)(implicit ev: Fractional[T]) {
    private val SecondsPerDay = 86400
    private val SecondsPerSiderealDay = 86164.0916
    private val DaysPerYear = 365
    private val SecondsPerAstronomicalYear = 31557600

    /** The number of seconds.
     *
     *  @return
     *    The number of seconds represented by the wrapped value
     */
    def seconds: T = self

    /** The number of minutes.
     *
     *  There are 60 seconds in a minute.
     *
     *  @return
     *    The number of minutes represented by the wrapped value
     */
    def minutes: T = ev.div(seconds, ev.fromInt(60))

    /** The number of hours.
     *
     *  There are 60 minutes or 3600 seconds in an hour.
     *
     *  @return
     *    The number of hours represented by the wrapped value
     */
    def hours: T = ev.div(minutes, ev.fromInt(60))

    /** The number of calendar days.
     *
     *  There are 24 hours or 86,400 seconds in an hour.
     *
     *  @return
     *    The number of days represented by the wrapped value
     */
    def days: T = ev.div(seconds, ev.fromInt(SecondsPerDay))

    /** The number of ''sidereal days''.
     *
     *  A ''sidereal day'' is the amount of time taken by Earth to rotate
     *  on its axis relative to the stars. It is defined as 86164.0916
     *  seconds, or about 4 minutes shorter than a solary day.
     *
     *  @return
     *    The number of sidereal days represented by the wrapped value
     */
    def siderealDays: Double = ev.toDouble(seconds) / SecondsPerSiderealDay

    /** The number of weeks
     *
     *  There are 7 days in a week.
     *
     *  @return
     *    The number of weeks represented by the wrapped value
     */
    def weeks: T = ev.div(days, ev.fromInt(7))

    /** The number of years.
     *
     *  Assumes a year of 365 days.
     *
     *  @return
     *    The number of years represented by the wrapped value
     */
    def years: T = ev.div(days, ev.fromInt(DaysPerYear))

    /** The number of ''astronomical years''.
     *
     *  An ''astronomical year'' is the amount of time it takes Earth to
     *  revolve around the sun. It is equal to 31,557,600 seconds, or about
     *  365.25 days.
     *
     *  @return
     *    The number of astronomical years represented by the wrapped value
     */
    def astronomicalYears: Double = ev.toDouble(seconds) / SecondsPerAstronomicalYear.toDouble
  }
}
