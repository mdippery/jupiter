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

package com.mipadi.jupiter.io.files


import java.nio.file.Path
import org.scalatest._
import com.mipadi.jupiter.io.files._


class PathInterpolationSpec extends FlatSpec with Matchers {
  "A path to a single file" should "be interpolated from a string" in {
    val path = p"build.sbt"
    path shouldBe a [Path]
    path.toString should be ("build.sbt")
  }

  "A root path" should "be interpolated from a string" in {
    val path = p"/"
    path shouldBe a [Path]
    path.toString should be ("/")
  }

  "A relative path to a file" should "be interpolated from a string" in {
    val path = p"src/main/scala/com/mipadi/io/files/package.scala"
    path shouldBe a [Path]
    path.toString should be ("src/main/scala/com/mipadi/io/files/package.scala")
  }

  "An absolute path to a file" should "be interpolated from a string" in {
    val path = p"/etc/init.d/httpd"
    path shouldBe a [Path]
    path.toString should be ("/etc/init.d/httpd")
  }

  "A relative path to a file" should "be built from strings" in {
    val path = p"src" / "main" / "scala" / "com" / "mipadi" / "io" / "files" / "package.scala"
    path shouldBe a [Path]
    path.toString should be ("src/main/scala/com/mipadi/io/files/package.scala")
  }

  "An absolute path to a file" should "be built from strings" in {
    val path = p"/" / "etc" / "init.d" / "httpd"
    path shouldBe a [Path]
    path.toString should be ("/etc/init.d/httpd")
  }
}
