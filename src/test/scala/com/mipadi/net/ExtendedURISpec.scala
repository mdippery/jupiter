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
import org.scalatest._
import com.mipadi.net.RichURI._


class ExtendedURISpec extends FlatSpec with Matchers {
  val uri = new URI("http://monkey-robot.com:7777/path/to/file.html")

  "A URI" should "return its host" in {
    uri.host should be ("monkey-robot.com")
  }

  it should "return its protocol" in {
    uri.protocol should be ("http")
  }

  it should "return its port" in {
    uri.port should be (Some(7777))
  }

  it should "return its path" in {
    uri.path should be ("/path/to/file.html")
  }

  it should "return its path as an array of components" in {
    val expected = List("/", "path", "to", "file.html")
    uri.pathComponents should equal (expected)
  }

  "A URI without a port" should "return None for its port" in {
    val uri = new URI("http://monkey-robot.com/path/to/file.html")
    uri.port should be (None)
  }

  "A URI to a directory" should "return its path" in {
    val uri = new URI("http://monkey-robot.com:7777/path/to/")
    uri.path should be ("/path/to/")
  }

  it should "return its path as an array of components" in {
    val uri = new URI("http://monkey-robot.com:7777/path/to/")
    val expected = List("/", "path", "to")
    uri.pathComponents should equal (expected)
  }

  "A URI without a trailing slash" should "return its path" in {
    val uri = new URI("http://monkey-robot.com:7777/path/to")
    uri.path should be ("/path/to")
  }

  it should "return its path as an array of components" in {
    val uri = new URI("http://monkey-robot.com:7777/path/to")
    val expected = List("/", "path", "to")
    uri.pathComponents should equal (expected)
  }

  "A root URI" should "return its path" in {
    val uri = new URI("http://monkey-robot.com:7777/")
    uri.path should be ("/")
  }

  it should "return its path as an array of path components" in {
    val uri = new URI("http://monkey-robot.com:7777/")
    val expected = List("/")
    uri.pathComponents should equal (expected)
  }

  "A root URI without a trailing slash" should "return its path" in {
    val uri = new URI("http://monkey-robot.com:7777")
    uri.path should be ("")
  }

  it should "return its path as an array of components" in {
    val uri = new URI("http://monkey-robot.com:7777")
    val expected = List("/")
    uri.pathComponents should equal (expected)
  }
}
