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

package com.mipadi.io

import java.io.File
import org.scalatest._
import com.mipadi.io.files._


class RichFileSpec extends FlatSpec with Matchers {
  val dir = new File("src/main/scala/com/mipadi/io")
  val file = new File("build.sbt")
  val noFile = new File("blah")

  "A RichFile" should "have a path property" in {
    dir.path should be ("src/main/scala/com/mipadi/io")
  }

  it should "have an absolutePath property" in {
    dir.absolutePath.endsWith("src/main/scala/com/mipadi/io") should be (true)
  }

  it should "list all the files and directories in its subtree" in {
    val expectedFilenames = Array(
      "src/main/scala/com/mipadi/io/IO.scala",
      "src/main/scala/com/mipadi/io/files",
      "src/main/scala/com/mipadi/io/files/package.scala",
      "src/main/scala/com/mipadi/io/package.scala",
      "src/main/scala/com/mipadi/io/terminal",
      "src/main/scala/com/mipadi/io/terminal/colors",
      "src/main/scala/com/mipadi/io/terminal/colors/package.scala",
      "src/main/scala/com/mipadi/io/terminal/package.scala"
    )
    val expected = expectedFilenames.map { new File(_) }
    dir.subtree should equal (expected)
  }

  it should "list all the files in its subtree" in {
    val expectedFilenames = List(
      "src/main/scala/com/mipadi/io/IO.scala",
      "src/main/scala/com/mipadi/io/files/package.scala",
      "src/main/scala/com/mipadi/io/package.scala",
      "src/main/scala/com/mipadi/io/terminal/colors/package.scala",
      "src/main/scala/com/mipadi/io/terminal/package.scala"
    )
    val expected = expectedFilenames.map { new File(_) }
    dir.subtreeFiles should equal (expected)
  }

  it should "should have an empty subtree if it is not a directory" in {
    file.subtree shouldBe empty
  }

  it should "have an empty subtree of files if it is not a directory" in {
    val expected = List()
    file.subtree shouldBe empty
  }

  it should "return an empty subtree if it does not exist" in {
    noFile.subtreeFiles shouldBe empty
  }

  it should "return an empty subtree of files if it does not exist" in {
    noFile.subtreeFiles shouldBe empty
  }

  it should "be comparable by its pathnames" in {
    (dir < file) should be (false)
    (file < dir) should be (true)
  }

  it should "be equal to itself" in {
    val dir2 = new File("src/main/scala/com/mipadi/io")
    (dir == dir) should be (true)
    (dir == dir2) should be (true)
  }

  it should "be unequal to anything else" in {
    (dir == file) should be (false)
  }
}
