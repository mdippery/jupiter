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

package com.mipadi.jupiter.misc.sprockets

import org.scalatest._
import com.mipadi.jupiter.core.logging.Logging


class Widget extends Logging


class LoggingSpec extends FlatSpec with Matchers {
  "The Logging trait" should "add a logger to every class" in {
    val widget = new Widget
    widget.log.getName should be ("com.mipadi.jupiter.misc.sprockets")
  }
}
