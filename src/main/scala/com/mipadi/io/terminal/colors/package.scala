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
 *  String objects can be implicitly converted to a
 *  [[com.mipadi.io.terminal.colors.ColorizedString ColorizedString]],
 *  which allows programs to print colored strings to a terminal, like so:
 *
 *  {{{
 *  import com.mipadi.io.terminal.colors._
 *  println(s"The word \${"red".red} is red in this line.")
 *  }}}
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
    def black: String = s"${Console.BLACK}$s${Console.RESET}"

    def blue: String = s"${Console.BLUE}$s${Console.RESET}"

    def cyan: String = s"${Console.CYAN}$s${Console.RESET}"

    def green: String = s"${Console.GREEN}$s${Console.RESET}"

    def magenta: String = s"${Console.MAGENTA}$s${Console.RESET}"

    def red: String = s"${Console.RED}$s${Console.RESET}"

    def white: String = s"${Console.WHITE}$s${Console.RESET}"

    def yellow: String = s"${Console.YELLOW}$s${Console.RESET}"
  }
}
