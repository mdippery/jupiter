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

import java.net.URL
import org.scalatest._
import com.mipadi.net.RichURI._


class ExtendedURLSpec extends FlatSpec with Matchers {
  val url = new URL("http://monkey-robot.com:7777/path/to/file.html")

  "A URL" should "return its host" in {
    url.host should be ("monkey-robot.com")
  }

  it should "return its protocol" in {
    url.protocol should be ("http")
  }

  it should "return its port" in {
    url.port should be (Some(7777))
  }

  it should "return its path" in {
    url.path should be ("/path/to/file.html")
  }

  it should "return its path as an array of components" in {
    val expected = List("/", "path", "to", "file.html")
    url.pathComponents should equal (expected)
  }

  "A URL without a port" should "return None for its port" in {
    val url = new URL("http://monkey-robot.com/path/to/file.html")
    url.port should be (None)
  }

  "A URL to a directory" should "return its path" in {
    val url = new URL("http://monkey-robot.com:7777/path/to/")
    url.path should be ("/path/to/")
  }

  it should "return its path as an array of components" in {
    val url = new URL("http://monkey-robot.com:7777/path/to/")
    val expected = List("/", "path", "to")
    url.pathComponents should equal (expected)
  }

  "A URL without a trailing slash" should "return its path" in {
    val url = new URL("http://monkey-robot.com:7777/path/to")
    url.path should be ("/path/to")
  }

  it should "return its path as an array of components" in {
    val url = new URL("http://monkey-robot.com:7777/path/to")
    val expected = List("/", "path", "to")
    url.pathComponents should equal (expected)
  }

  "A root URL" should "return its path" in {
    val url = new URL("http://monkey-robot.com:7777/")
    url.path should be ("/")
  }

  it should "return its path as an array of path components" in {
    val url = new URL("http://monkey-robot.com:7777/")
    val expected = List("/")
    url.pathComponents should equal (expected)
  }

  "A root URL without a trailing slash" should "return its path" in {
    val url = new URL("http://monkey-robot.com:7777")
    url.path should be ("")
  }

  it should "return its path as an array of components" in {
    val url = new URL("http://monkey-robot.com:7777")
    val expected = List("/")
    url.pathComponents should equal (expected)
  }
}
