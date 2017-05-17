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

package com.mipadi.net

import java.net.URI


/** Additional operations for `java.net.URI` classes.
 *
 *  In particular, `RichURI` provides an implicit conversion to
 *  `[[com.mipadi.net.RichURI.ExtendedURI ExtendedURI]]`, which allows
 *  additional methods to be called on `java.net.URI` objects:
 *
 *  {{{
 *  import java.net.URI
 *  import com.mipadi.net.RichURI._
 *  val uri = new URI("http://monkey-robot.com/archives")
 *  val parts = uri.pathComponents
 *  }}}
 */
object RichURI {

  /** Converts an instance of `java.net.URI` and adds additional methods.
   *
   *  For example:
   *
   *  {{{
   *  import java.net.URI
   *  import com.mipadi.net.RichURI._
   *  val uri = new URI("http://monkey-robot.com/archives")
   *  val parts = uri.pathComponents
   *  }}}
   *
   *  @param uri
   *    The wrapped URI
   */
  implicit class ExtendedURI(uri: URI) {

    /** The URI's protocol. */
    lazy val protocol: String = uri.getScheme

    /** The URI's host. */
    lazy val host: String = uri.getHost

    /** The URI's path. */
    lazy val path: String = uri.getPath

    /** The URI's port.
     *
     *  If a port is not specified, `None` is returned instead.
     */
    lazy val port: Option[Int] = uri.getPort match {
      case -1 => None
      case p  => Some(p)
    }

    /** Each part of the URI's path. */
    lazy val pathComponents: Seq[String] = uri.path match {
      case "/" => Array("/")
      case p   => Array("/") ++ p.split("/").tail
    }
  }
}
