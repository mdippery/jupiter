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

import java.net.{URI, URL}


/** A type class for URI-like objects
 *  @see
 *    [[http://danielwestheide.com/blog/2013/02/06/the-neophytes-guide-to-scala-part-12-type-classes.html
 *      Type classes in Scala]]
 */
trait Addressable[T] {

  /** Returns a URI's scheme (or protocol) */
  def getScheme(uri: T): String

  /** Returns a URI's host. */
  def getHost(uri: T): String

  /** Returns a URI's path */
  def getPath(uri: T): String

  /** Returns a URI's port, or -1 if no port is specified. */
  def getPort(uri: T): Int
}

/** Implicit URI-like converts for `java.net.URI` and `java.net.URL`. */
object Addressable {
  implicit object AddressableURI extends Addressable[URI] {
    def getScheme(uri: URI): String = uri.getScheme
    def getHost(uri: URI): String = uri.getHost
    def getPath(uri: URI): String = uri.getPath
    def getPort(uri: URI): Int = uri.getPort
  }

  implicit object AddressableURL extends Addressable[URL] {
    def getScheme(uri: URL): String = uri.getProtocol
    def getHost(uri: URL): String = uri.getHost
    def getPath(uri: URL): String = uri.getPath
    def getPort(uri: URL): Int = uri.getPort
  }
}
