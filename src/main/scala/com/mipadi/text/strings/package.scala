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

package com.mipadi.text

import java.util.regex.{Matcher, Pattern}


/** Provides operations for working with string objects.
 *
 *  Strings can implicitly be converted to a $matcherstring, which allows
 *  them to use the `~=` operator to check if a string matches a regex:
 *
 *  {{{
 *  import com.mipadi.text.strings._
 *  val matches = "my string" =~ "string$"
 *  }}}
 *
 *  @define matcherstring
 *    `[[com.mipadi.text.strings.MatcherString MatcherString]]`
 */
package object strings {

  /** Implicitly converts Scala strings to $matcherstring objects.
   *
   *  This implicit conversion allows Scala strings to use the `~=` operator
   *  to check if they match a given regex:
   *
   *  {{{
   *  import com.mipadi.text.strings._
   *  val matches = "my string" =~ "string$"
   *  }}}
   *
   *  @define matcherstring
   *    `[[com.mipadi.text.strings.MatcherString MatcherString]]`
   *
   *  @param s
   *    The wrapped string.
   */
  implicit class MatcherString(s: String) {

    /** Matches a string against a given regex.
     *
     *  @param needle
     *    The regex to match against the receiver.
     *  @return
     *    `true` if the receiver matches the regex.
     */
    def =~ (needle: String): Boolean =
      matchesPartial(needle)

    private def patternFor(needle: String): Pattern =
      Pattern.compile(needle)

    private def matcherFor(needle: String): Matcher =
      patternFor(needle).matcher(s)

    private def matchesPartial(needle: String): Boolean =
      matcherFor(needle).find
  }
}
