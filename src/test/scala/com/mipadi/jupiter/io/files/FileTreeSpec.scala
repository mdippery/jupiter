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

import java.io.File
import org.scalatest._


class FileTreeSpec extends FlatSpec with Matchers {
  val path = "src/main/scala/com/mipadi/jupiter/io"
  val root = new File(path)
  val tree = new FileTree(root)

  val noPath = "blah"
  val noRoot = new File(noPath)
  val noTree = new FileTree(noRoot)

  val filePath = "build.sbt"
  val fileRoot = new File(filePath)
  val fileTree = new FileTree(fileRoot)

  "A file tree" should "describe itself using its path" in {
    root.toString should be (path)
  }

  "A directory's file tree" should "list all its files and directories" in {
    val expectedFilenames = Array(
      "src/main/scala/com/mipadi/jupiter/io/IO.scala",
      "src/main/scala/com/mipadi/jupiter/io/files",
      "src/main/scala/com/mipadi/jupiter/io/files/FileTree.scala",
      "src/main/scala/com/mipadi/jupiter/io/files/Locatable.scala",
      "src/main/scala/com/mipadi/jupiter/io/files/package.scala",
      "src/main/scala/com/mipadi/jupiter/io/package.scala",
      "src/main/scala/com/mipadi/jupiter/io/terminal",
      "src/main/scala/com/mipadi/jupiter/io/terminal/colors.scala",
      "src/main/scala/com/mipadi/jupiter/io/terminal/package.scala"
    )
    val expected = expectedFilenames.map { new File(_) }
    tree.all should equal(expected)
  }

  it should "list all its files" in {
    val expectedFilenames = List(
      "src/main/scala/com/mipadi/jupiter/io/IO.scala",
      "src/main/scala/com/mipadi/jupiter/io/files/FileTree.scala",
      "src/main/scala/com/mipadi/jupiter/io/files/Locatable.scala",
      "src/main/scala/com/mipadi/jupiter/io/files/package.scala",
      "src/main/scala/com/mipadi/jupiter/io/package.scala",
      "src/main/scala/com/mipadi/jupiter/io/terminal/colors.scala",
      "src/main/scala/com/mipadi/jupiter/io/terminal/package.scala"
    )
    val expected = expectedFilenames.map { new File(_) }
    tree.filesOnly should equal (expected)
  }

  it should "return an empty listing if it does not exist" in {
    noTree.all shouldBe empty
  }

  it should "return an empty file listing if it does not exist" in {
    noTree.filesOnly shouldBe empty
  }

  "A file's file tree" should "be empty" in {
    fileTree.all shouldBe empty
  }

  it should "return an empty file listing" in {
    fileTree.filesOnly shouldBe empty
  }
}
