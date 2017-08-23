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
import java.nio.file.FileSystems
import org.scalatest._
import com.mipadi.jupiter.io.files._


class RichFileSpec extends FlatSpec with Matchers {
  val dir = new File("src/main/scala/com/mipadi/jupiter/io")
  val file = new File("build.sbt")
  val noFile = new File("blah")

  val pDir = FileSystems.getDefault.getPath("src", "main", "scala", "com", "mipadi", "jupiter", "io")
  val pFile = FileSystems.getDefault.getPath("build.sbt")
  val pNoFile = FileSystems.getDefault.getPath("blah")

  "A file or directory" should "be comparable by its pathnames" in {
    dir should be > file
    file should be < dir

    pDir should be > pFile
    pFile should be < pDir
  }

  it should "be equal to itself" in {
    val dir2 = new File("src/main/scala/com/mipadi/jupiter/io")
    dir should === (dir)
    dir should === (dir2)

    val pDir2 = FileSystems.getDefault.getPath("src", "main", "scala", "com", "mipadi", "jupiter", "io")
    pDir should === (pDir)
    pDir should === (pDir2)
  }

  it should "be unequal to anything else" in {
    dir should !== (file)
    pDir should !== (pFile)
  }

  it should "have a path property" in {
    dir.path should be ("src/main/scala/com/mipadi/jupiter/io")
    pDir.path should be ("src/main/scala/com/mipadi/jupiter/io")
  }

  it should "have an absolutePath property" in {
    dir.absolutePath.endsWith("src/main/scala/com/mipadi/jupiter/io") should be (true)
    pDir.absolutePath.endsWith("src/main/scala/com/mipadi/jupiter/io") should be (true)
  }

  "A directory" should "return a subtree listing its files" in {
    dir.subtree.all.length should be (10)
    pDir.subtree.all.length should be (10)
  }

  it should "return a subtree listing its files only" in {
    dir.subtree.filesOnly.length should be (7)
    pDir.subtree.filesOnly.length should be (7)
  }

  it should "return an empty subtree if it does not exist" in {
    noFile.subtree.all shouldBe empty
    pNoFile.subtree.all shouldBe empty
  }

  it should "return an empty subtree of files if it does not exist" in {
    noFile.subtree.all shouldBe empty
    pNoFile.subtree.all shouldBe empty
  }

  it should "return true when checked to see if it is a directory" in {
    dir.isDirectory should be (true)
    pDir.isDirectory should be (true)
  }

  "A file" should "should have an empty subtree" in {
    file.subtree.all shouldBe empty
    pFile.subtree.all shouldBe empty
  }

  it should "have an empty subtree of files" in {
    file.subtree.all shouldBe empty
    pFile.subtree.all shouldBe empty
  }

  it should "return false when checked to see if it is a directory" in {
    file.isDirectory should be (false)
    pFile.isDirectory should be (false)
  }
}
