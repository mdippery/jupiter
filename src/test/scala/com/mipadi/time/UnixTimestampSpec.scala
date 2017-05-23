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

import java.time.{ZonedDateTime, ZoneId}
import java.util.{Calendar, Date, GregorianCalendar, TimeZone}
import org.scalatest._
import com.mipadi.time.UnixTimestamp._


class UnixTimestampSpec extends FlatSpec with Matchers {
  "A Unix timestamp represented as a double" should "be convertible to a date" in {
    val timestamp = 1490999455.0
    timestamp.toDate should be (new GregorianCalendar(2017, Calendar.MARCH, 31, 15, 30, 55).getTime)
  }

  it should "be convertible to a date and back to a timestamp" in {
    val timestamp = 1490999455.0
    timestamp.toDate.sinceEpoch should be (timestamp)
  }

  "A Unix timestamp represented as an integer" should "be convertible to a date" in {
    val timestamp = 1490999455
    timestamp.toDate should be (new GregorianCalendar(2017, Calendar.MARCH, 31, 15, 30, 55).getTime)
  }

  it should "be convertible to a date and back to a timestamp" in {
    val timestamp = 1490999455
    timestamp.toDate.sinceEpoch should be (timestamp)
  }

  "A date" should "be convertible to a Unix timestamp" in {
    val date = new GregorianCalendar(2017, Calendar.MAY, 23, 16, 3, 49).getTime
    date.sinceEpoch should be (1495580629)
  }

  it should "be convertible to a Unix timestamp and back to a date" in {
    val date = new GregorianCalendar(2017, Calendar.MAY, 23, 16, 3, 49).getTime
    date.sinceEpoch.toDate should be (date)
  }

  it should "return the date at midnight UTC" in {
    val expected = ZonedDateTime.of(2017, 5, 23, 0, 0, 0, 0, ZoneId.of("UTC"))

    val date = new GregorianCalendar(2017, Calendar.MAY, 23, 16, 3, 49).getTime
    date.atMidnight should be (expected)
    date.atMidnight.getZone.getId should be ("UTC")
  }
}
