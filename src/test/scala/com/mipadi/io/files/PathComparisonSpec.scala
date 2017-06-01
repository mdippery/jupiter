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

package com.mipadi.io.files

import java.io.File
import java.nio.file.FileSystems
import org.scalatest._
import com.mipadi.io.files._


class PathComparisonSpec extends FlatSpec with Matchers {
  val tree = new File("src/main/scala").subtree

  "A file tree" should "return true if it contains a file" in {
    val f = new File("src/main/scala/com/mipadi/core/Bundle.scala")
    (tree contains f) should be (true)
  }

  it should "return false if it does not contain a file" in {
    val f = new File("build.sbt")
    (tree contains f) should be (false)
  }

  it should "return true if it contains a path" in {
    val p = FileSystems.getDefault.getPath("src", "main", "scala", "com", "mipadi", "core", "Bundle.scala")
    (tree contains p) should be (true)
  }

  it should "return false if it does not contain a path" in {
    val p = FileSystems.getDefault.getPath("build.sbt")
    (tree contains p) should be (false)
  }

  it should "return true if it contains an interpolated path" in {
    val p = p"src" / "main" / "scala" / "com" / "mipadi" / "core" / "Bundle.scala"
    (tree contains p) should be (true)
  }

  it should "return false if it does not contain an interpolated path" in {
    val p = p"build.sbt"
    (tree contains p) should be (false)
  }
}
