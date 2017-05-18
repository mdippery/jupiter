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
import com.mipadi.io.files._


class RichPathSpec extends FlatSpec with Matchers {
  "A path" should "be buildable using strings" in {
    val start = FileSystems.getDefault().getPath("src")
    val path = start / "main" / "scala" / "com" / "mipadi" / "io" / "files" / "package.scala"
    path.toString should be ("src/main/scala/com/mipadi/io/files/package.scala")
  }

  "A file path" should "be buildable using strings" in {
    val start = new File("src/main/scala")
    val path = start / "com" / "mipadi" / "io" / "files" / "package.scala"
    path.toString should be ("src/main/scala/com/mipadi/io/files/package.scala")
  }
}
