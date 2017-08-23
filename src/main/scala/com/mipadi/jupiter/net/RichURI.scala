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

package com.mipadi.jupiter.net


/** Extends `java.net.URI` with useful methods and a more Scala-like API.
 *
 *  `RichURI` is especially useful for implicit conversions to
 *  more specific types of URIs. For example, it could be used to create
 *  a specialized `RichMongoURI` implicit:
 *
 *  {{{
 *  import com.mipadi.jupiter.net.RichURI
 *  implicit class RichMongoURI(uri: URI)(implicit ev: Addressable[URI])
 *    extends ConvertibleURI {
 *    lazy val database: Option[String] = uri.pathComponents.lift(1)
 *    lazy val collection: Option[String] = uri.pathComponents.lift(2)
 *  }
 *  }}}
 *
 *  @constructor
 *    Creates a new `RichURI`. The `uri` parameter is the wrapped URI-like
 *    object. Also takes an implicit `Addressable[T]` parameter, to which
 *    `RichURI` delegates many of its URI-like operations. `Addressable`
 *    implicits for both `java.net.URI` and `java.net.URL` can be imported
 *    from the `[[com.mipadi.jupiter.net.Addressable Addressable]]` object:
 *
 *    {{{
 *    import com.mipadi.jupiter.net.Addressable._
 *    }}}
 *
 *  @param uri
 *    The wrapped URI-like object
 *  @param ev
 *    The delegate responsible for getting data from the URI-like object
 */
class RichURI[T](uri: T)(implicit ev: Addressable[T]) {
  /** The URI's protocol. */
  lazy val protocol: String = ev.getScheme(uri)

  /** The URI's host. */
  lazy val host: String = ev.getHost(uri)

  /** The URI's path. */
  lazy val path: String = ev.getPath(uri)

  /** The URI's port.
   *
   *  If a port is not specified, `None` is returned instead.
   */
  lazy val port: Option[Int] = ev.getPort(uri) match {
    case -1 => None
    case p  => Some(p)
  }

  /** Each part of the URI's path. */
  lazy val pathComponents: Seq[String] = path match {
    case "/" => Array("/")
    case p   => Array("/") ++ p.split("/").tail
  }
}

/** Additional operations for `java.net.URI` and `java.net.URL` classes.
 *
 *  In particular, `RichURI` provides an implicit conversion to
 *  `[[com.mipadi.jupiter.net.RichURI.ExtendedURI ExtendedURI]]`, which allows
 *  additional methods to be called on `java.net.URI` objects:
 *
 *  {{{
 *  import java.net.URI
 *  import com.mipadi.jupiter.net.RichURI._
 *  val uri = new URI("http://monkey-robot.com/archives")
 *  val parts = uri.pathComponents
 *  }}}
 */
object RichURI {

  /** Implicit converts a instances of `java.net.URI` and `java.net.URL`,
   *  adding additional methods.
   *
   *  For example:
   *
   *  {{{
   *  import java.net.URI
   *  import com.mipadi.jupiter.net.RichURI._
   *  val uri = new URI("http://monkey-robot.com/archives")
   *  val parts = uri.pathComponents
   *  }}}
   *
   *  @param uri
   *    The wrapped URI
   *  @param ev
   *    The addressable delegate
   */
  implicit class ExtendedURI[T](uri: T)(implicit ev: Addressable[T])
    extends RichURI[T](uri)
}
