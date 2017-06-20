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

package com.mipadi.io.terminal


/** Provides a nice way to work with colors in the terminal.
 *
 *  String objects can be implicitly converted to a $colorizedstring,
 *  which allows programs to print colored strings to a terminal, like so:
 *
 *  {{{
 *  import com.mipadi.io.terminal.colors._
 *  println(s"The word \${"red".red} is red in this line.")
 *  }}}
 *
 *  @define colorizedstring
 *    `[[com.mipadi.io.terminal.colors.ColorizedString ColorizedString]]`
 */
package object colors {

  /** A wrapper for strings that adds methods to print them in color.
   *
   *  Strings can be colorized simply by calling the appropriate method on
   *  them.
   *
   *  {{{
   *  import com.mipadi.io.terminal.colors._
   *  val magentaString = "magenta".magenta
   *  }}}
   *
   *  @param s
   *    The wrapped string.
   */
  implicit class ColorizedString(s: String) {
    def black: String = colorize(Console.BLACK)

    def blue: String = colorize(Console.BLUE)

    def cyan: String = colorize(Console.CYAN)

    def green: String = colorize(Console.GREEN)

    def magenta: String = colorize(Console.MAGENTA)

    def red: String = colorize(Console.RED)

    def white: String = colorize(Console.WHITE)

    def yellow: String = colorize(Console.YELLOW)

    private def colorize(color: String): String =
      s"$color$s${Console.RESET}"
  }
}
