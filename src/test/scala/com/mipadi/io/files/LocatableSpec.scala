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
import java.nio.file.{FileSystems, Path}
import org.scalatest._


class LocatableSpec extends FlatSpec with Matchers {
  val file = new File("src")
  val path = FileSystems.getDefault.getPath("src")

  val fEv = Locatable.LocatableFile
  val pEv = Locatable.LocatablePath

  val fLs = new File("src/main/scala/com/mipadi/net")
  val pLs = FileSystems.getDefault.getPath("src", "main", "scala", "com", "mipadi", "net")
  val fs  = List("Addressable", "NetworkConvertible", "RichURI", "package").map(s => new File(s"src/main/scala/com/mipadi/net/$s.scala"))
  val ps  = List("Addressable", "NetworkConvertible", "RichURI", "package").map(s => FileSystems.getDefault.getPath("src", "main", "scala", "com", "mipadi", "net", s"$s.scala"))

  "A file" should "be convertible to a file" in {
    fEv.toFile(file) should be (file)
  }

  it should "be convertible to a path" in {
    fEv.toPath(file) should be (path)
  }

  it should "join two paths to make a new path" in {
    val expected = "src/main/scala"
    val arg = new File("src/main")
    fEv.join(arg, "scala").toString should be (expected)
  }

  it should "return a file listing" in {
    fEv.getFiles(fLs) should equal (fs)
  }

  it should "return an empty file listing if it is not a directory" in {
    val f = new File("build.sbt")
    fEv.getFiles(f) shouldBe empty
  }

  it should "return its path" in {
    fEv.getPath(file) should be ("src")
  }

  it should "return its absolute path" in {
    fEv.getAbsolutePath(file).head should be ('/')
  }

  it should "return true if it is a directory" in {
    fEv.isDirectory(file) should be (true)
  }

  it should "return false if it is not a directory" in {
    val f = new File("build.sbt")
    fEv.isDirectory(f) should be (false)
  }

  "A path" should "be convertible to a file" in {
    pEv.toFile(path) should be (file)
  }

  it should "be convertible to a path" in {
    pEv.toPath(path) should be (path)
  }

  it should "join two paths to make a new path" in {
    val expected = "src/main/scala"
    val arg = FileSystems.getDefault.getPath("src", "main")
    pEv.join(arg, "scala").toString should be (expected)
  }

  it should "return a file listing" in {
    pEv.getFiles(pLs) should equal (ps)
  }

  it should "return an empty file listing if it is not a directory" in {
    val p = FileSystems.getDefault.getPath("build.sbt")
    pEv.getFiles(p) shouldBe empty
  }

  it should "return its path" in {
    pEv.getPath(path) should be ("src")
  }

  it should "return its absolute path" in {
    pEv.getAbsolutePath(path).head should be ('/')
  }

  it should "return true if it is a directory" in {
    pEv.isDirectory(path) should be (true)
  }

  it should "return false if it is not a directory" in {
    val p = FileSystems.getDefault.getPath("build.sbt")
    pEv.isDirectory(p) should be (false)
  }
}
