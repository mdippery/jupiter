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
import java.nio.file.Path


/** Represents a recursive list of files at a given file system root.
 *
 *  @param root
 *    The root of the directory tree
 */
class FileTree(val root: File) {
  override def toString: String = root.path

  private lazy val entries: Seq[File] = Option(root.listFiles) map { files =>
    files.toSeq
  } getOrElse {
    List()
  }

  /** Returns a sequence of all paths rooted under this file, including
   *  both files and directories.
   *
   *  For example, given this tree:
   *  {{{
   *  src/main/scala/com/mipadi/io
   *  ├── files
   *  │   └── package.scala
   *  ├── IO.scala
   *  ├── package.scala
   *  └── terminal
   *      ├── colors
   *      │   └── package.scala
   *      └── package.scala
   *
   *  3 directories, 5 files
   *  }}}
   *
   *  This method would return a sequence of:
   *
   *  {{{
   *  ["src/main/scala/com/mipadi/io/files",
   *   "src/main/scala/com/mipadi/io/files/package.scala",
   *   "src/main/scala/com/mipadi/io/IO.scala",
   *   "src/main/scala/com/mipadi/io/package.scala",
   *   "src/main/scala/com/mipadi/io/terminal",
   *   "src/main/scala/com/mipadi/io/terminal/colors",
   *   "src/main/scala/com/mipadi/io/terminal/colors/package.scala",
   *   "src/main/scala/com/mipadi/io/terminal/package.scala"]
   *  }}}
   *
   *  @return
   *    A sequence of all paths rooted under this file, sorted by name.
   *    If this file is not a directory, an empty sequence is returned.
   */
  def all: Seq[File] = (entries ++ entries.flatMap(_.subtree.all)).sorted

  /** Returns a sequence of all ''file'' paths rooted under this file.
   *  Directories are excluded from this listing.
   *
   *  For example, given this tree:
   *  {{{
   *  src/main/scala/com/mipadi/io
   *  ├── files
   *  │   └── package.scala
   *  ├── IO.scala
   *  ├── package.scala
   *  └── terminal
   *      ├── colors
   *      │   └── package.scala
   *      └── package.scala
   *
   *  3 directories, 5 files
   *  }}}
   *
   *  This method would return a sequence of:
   *
   *  {{{
   *  ["src/main/scala/com/mipadi/io/files/package.scala",
   *   "src/main/scala/com/mipadi/io/IO.scala",
   *   "src/main/scala/com/mipadi/io/package.scala",
   *   "src/main/scala/com/mipadi/io/terminal/colors/package.scala",
   *   "src/main/scala/com/mipadi/io/terminal/package.scala"]
   *  }}}
   *
   *  @return
   *    A sequence of all non-directory entries under this file, sorted by
   *    name. If this file is not a directory, an empty sequence is
   *    returned.
   */
  def filesOnly: Seq[File] = all.filterNot(_.isDirectory)

  /** Tests whether the directory tree contains the given file.
   *
   *  @tparam A
   *    The type of path-like object whose presence is being tested
   *  @param file
   *    The file to test
   *  @param ev
   *    Implicit delegate that converts the argument to a file
   *  @return
   *    `true` if the file is contained within this directory tree
   */
  def contains[A](file: A)(implicit ev: Locatable[A]): Boolean =
    all contains ev.toFile(file)
}
