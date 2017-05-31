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
import java.util.{Calendar, Date, TimeZone}
import org.scalatest._
import com.mipadi.time.UnixTimestamp._


class UnixTimestampSpec extends FlatSpec with Matchers {
  "A Unix timestamp represented as a double" should "be convertible to a date" in {
    val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    cal.set(Calendar.MILLISECOND, 0)
    cal.set(2017, Calendar.MARCH, 31, 15, 30, 55)
    val expected = cal.getTime
    val timestamp = 1490974255.0
    timestamp.toDate should be (expected)
  }

  it should "be convertible to a date and back to a timestamp" in {
    val timestamp = 1490999455.0
    timestamp.toDate.sinceEpoch should be (timestamp)
  }

  "A Unix timestamp represented as an integer" should "be convertible to a date" in {
    val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    cal.set(Calendar.MILLISECOND, 0)
    cal.set(2017, Calendar.MARCH, 31, 15, 30, 55)
    val expected = cal.getTime
    val timestamp = 1490974255
    timestamp.toDate should be (expected)
  }

  it should "be convertible to a date and back to a timestamp" in {
    val timestamp = 1490999455
    timestamp.toDate.sinceEpoch should be (timestamp)
  }

  "A date" should "return the number of seconds since the Unix epoch" in {
    val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    cal.set(Calendar.MILLISECOND, 0)
    cal.set(2017, Calendar.MAY, 23, 16, 3, 49)
    val date = cal.getTime
    date.sinceEpoch should be (1495555429)
  }

  it should "be convertible to a Unix timestamp and back to a date" in {
    val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    cal.set(Calendar.MILLISECOND, 0)
    cal.set(2017, Calendar.MAY, 23, 16, 3, 49)
    val date = cal.getTime
    date.sinceEpoch.toDate should be (date)
  }

  it should "return the date at midnight UTC" in {
    val expected = new Date(1496188800L * 1000)
    val date = new Date(1496257644L * 1000)
    date.atMidnight should be (expected)
  }

  "A local datetime" should "return the number of seconds since the Unix epoch" in {
    val date = LocalDateTime.of(2017, 5, 23, 16, 3, 49)
    date.sinceEpoch should be (1495555429)
  }

  it should "return the date at midnight" in {
    val expected = LocalDateTime.of(2017, 5, 23, 0, 0, 0)
    val date = LocalDateTime.of(2017, 5, 23, 16, 3, 49)
    date.atMidnight should be (expected)
  }

  "A zoned datetime" should "return the number of seconds since the Unix epoch" in {
    val date = ZonedDateTime.of(2017, 5, 23, 16, 3, 49, 0, ZoneId.of("UTC"))
    date.sinceEpoch should be (1495555429)
  }

  it should "return the date at midnight" in {
    val expected = ZonedDateTime.of(2017, 5, 23, 0, 0, 0, 0, ZoneId.of("UTC"))
    val date = ZonedDateTime.of(2017, 5, 23, 16, 3, 49, 0, ZoneId.of("UTC"))
    date.atMidnight should be (expected)
  }
}
