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

import java.net.{URI, URL}


/** Additional operations for `java.net.URI` and `java.net.URL` classes.
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

  /** A type class for URI-like objects
   *  @see
   *    [[http://danielwestheide.com/blog/2013/02/06/the-neophytes-guide-to-scala-part-12-type-classes.html
   *      Type classes in Scala]]
   */
  trait URILike[T] {

    /** Returns a URI's scheme.
     *
     *  @param uri
     *    The URI-like object
     *  @return
     *    Its scheme (or protocol)
     */
    def getScheme(uri: T): String

    /** Returns a URI's host.
     *
     *  @param uri
     *    The URI-like object
     *  @return
     *    Its host
     */
    def getHost(uri: T): String

    /** Returns a URI's path
     *
     *  @param uri
     *    The URI-like object
     *  @return
     *    Its path
     */
    def getPath(uri: T): String

    /** Returns a URI's port
     *
     *  @param uri
     *    The URI-like object
     *  @return
     *    Its port, or -1 if no port is specified
     */
    def getPort(uri: T): Int
  }

  /** Implicit URI-like converts for `java.net.URI` and `java.net.URL`. */
  object URILike {
    implicit object URILikeURI extends URILike[URI] {
      def getScheme(uri: URI): String = uri.getScheme
      def getHost(uri: URI): String = uri.getHost
      def getPath(uri: URI): String = uri.getPath
      def getPort(uri: URI): Int = uri.getPort
    }

    implicit object URILikeURL extends URILike[URL] {
      def getScheme(uri: URL): String = uri.getProtocol
      def getHost(uri: URL): String = uri.getHost
      def getPath(uri: URL): String = uri.getPath
      def getPort(uri: URL): Int = uri.getPort
    }
  }

  /** Extends `java.net.URI` with useful methods and a more Scala-like API.
   *
   *  `ConvertibleURI` is especially useful for implicit conversions to
   *  more specific types of URIs. For example, it could be used to create
   *  a specialized `ConvertibleMongoURI` implicit:
   *
   *  {{{
   *  import com.mipadi.net.RichURI.ConvertibleURI
   *  implicit class ConvertibleMongoURI(val uri: URI) extends ConvertibleURI {
   *    lazy val database: Option[String] = uri.pathComponents.lift(1)
   *    lazy val collection: Option[String] = uri.pathComponents.lift(2)
   *  }
   *  }}}
   *
   *  Classes that extend `ConvertibleURI` need only provide a `uri` method,
   *  and `ConvertibleURI` will do the rest of the work automagically.
   */
  trait ConvertibleURI[T] {

    /** The path-like object delegate */
    def ev: URILike[T]

    /** The wrapped URI. */
    def uri: T

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
  implicit class ExtendedURI[T](val uri: T)(implicit val ev: URILike[T])
    extends ConvertibleURI[T]
}
