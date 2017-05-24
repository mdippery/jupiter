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
import java.util.{Calendar, GregorianCalendar}
import org.scalatest._
import com.mipadi.time.DateConversions._


class DateConversionsSpec extends FlatSpec with Matchers {
  "A date" should "be convertible to a local datetime" in {
    val date = new GregorianCalendar(2017, Calendar.MAY, 23, 17, 33, 1).getTime
    val expected = LocalDateTime.of(2017, 5, 24, 0, 33, 1)
    date.toLocal should be (expected)
  }

  it should "be convertible to a zoned datetime" in {
    val date = new GregorianCalendar(2017, Calendar.MAY, 23, 17, 33, 1).getTime
    val expected = ZonedDateTime.of(2017, 5, 24, 0, 33, 1, 0, ZoneId.of("UTC"))
    date.toZoned should be (expected)
  }
}
