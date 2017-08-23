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

import org.scalatest._
import com.mipadi.jupiter.time.TemporalUnits._


class TemporalUnitsSpec extends FlatSpec with Matchers {
  val i = 864000
  val iy = 167140800

  val l = 864000L
  val ly = 167140800L

  val d = 864000.0
  val dy = 167140800.0

  "An int" should "represent seconds" in {
    i.seconds should === (864000)
  }

  it should "represent minutes" in {
    i.minutes should === (14400)
  }

  it should "represent hours" in {
    i.hours should === (240)
  }

  it should "represent days" in {
    i.days should === (10)
  }

  it should "represent sidereal days" in {
    val actual = i.siderealDays
    val expected = 10.027
    val delta = (actual - expected).abs
    delta should be <= 0.01
  }

  it should "represent weeks" in {
    i.weeks should === (1)
  }

  it should "represent years" in {
    iy.years should === (5)
  }

  it should "represent astronomical years" in {
    val actual = iy.astronomicalYears
    val expected = 5.297
    val delta = (actual - expected).abs
    delta should be <= 0.01
  }

  "A long" should "represent seconds" in {
    l.seconds should === (864000L)
  }

  it should "represent minutes" in {
    l.minutes should === (14400L)
  }

  it should "represent hours" in {
    l.hours should === (240L)
  }

  it should "represent days" in {
    l.days should === (10L)
  }

  it should "represent sidereal days" in {
    val actual = l.siderealDays
    val expected = 10.027
    val delta = (actual - expected).abs
    delta should be <= 0.01
  }

  it should "represent weeks" in {
    l.weeks should === (1L)
  }

  it should "represent years" in {
    ly.years should === (5L)
  }

  it should "represent astronomical years" in {
    val actual = ly.astronomicalYears
    val expected = 5.297
    val delta = (actual - expected).abs
    delta should be <= 0.01
  }

  "A double" should "represent seconds" in {
    d.seconds should === (864000.0)
  }

  it should "represent minutes" in {
    d.minutes should === (14400.0)
  }

  it should "represent hours" in {
    d.hours should === (240.0)
  }

  it should "represent days" in {
    d.days should === (10.0)
  }

  it should "represent sidereal days" in {
    val actual = d.siderealDays
    val expected = 10.027
    val delta = (actual - expected).abs
    delta should be <= 0.01
  }

  it should "represent weeks" in {
    val actual = d.weeks
    val expected = 1.429
    val delta = (actual - expected).abs
    delta should be <= 0.01
  }

  it should "represent years" in {
    val actual = dy.years
    val expected = 5.3
    val delta = (actual - expected).abs
    delta should be <= 0.01
  }

  it should "represent astronomical years" in {
    val actual = dy.astronomicalYears
    val expected = 5.297
    val delta = (actual - expected).abs
    delta should be <= 0.01
  }
}
