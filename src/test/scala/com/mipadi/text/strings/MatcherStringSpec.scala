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

package com.mipadi.text.strings

import org.scalatest._
import com.mipadi.text.strings._


class MatcherStringSpec extends FlatSpec with Matchers {
  "A string" should "match a partial regex if it exists" in {
    ("    version      := \"0.1.0-SNAPSHOT\"," =~ "version") should be (true)
  }

  it should "match an anchored regex if it exists" in {
    ("    version      := \"0.1.0-SNAPSHOT\"," =~ "^    version") should be (true)
  }

  it should "not match a partial regex if it does not exist" in {
    ("some string" =~ "version") should be (false)
  }

  it should "not match an anchored regex if it does not exist" in {
    ("    version      := \"0.1.0-SNAPSHOT\"," =~ "^version") should be (false)
  }
}
