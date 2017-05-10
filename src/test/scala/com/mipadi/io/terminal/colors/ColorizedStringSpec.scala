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

package com.mipadi.io.terminal.colors

import org.scalatest._
import com.mipadi.io.terminal.colors._


class ColorizedStringSpec extends FlatSpec with Matchers {
  val s = "string"

  "A ColorizedString" should "become black" in {
    (s.black + " suffix") should be ("\u001b[30mstring\u001b[0m suffix")
  }

  it should "become blue" in {
    (s.blue + " suffix") should be ("\u001b[34mstring\u001b[0m suffix")
  }

  it should "become cyan" in {
    (s.cyan + " suffix") should be ("\u001b[36mstring\u001b[0m suffix")
  }

  it should "become green" in {
    (s.green + " suffix") should be ("\u001b[32mstring\u001b[0m suffix")
  }

  it should "become magenta" in {
    (s.magenta + " suffix") should be ("\u001b[35mstring\u001b[0m suffix")
  }

  it should "become red" in {
    (s.red + " suffix") should be ("\u001b[31mstring\u001b[0m suffix")
  }

  it should "become white" in {
    (s.white + " suffix") should be ("\u001b[37mstring\u001b[0m suffix")
  }

  it should "become yellow" in {
    (s.yellow + " suffix") should be ("\u001b[33mstring\u001b[0m suffix")
  }
}
