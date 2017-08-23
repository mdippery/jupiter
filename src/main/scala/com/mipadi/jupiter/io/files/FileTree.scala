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
import java.nio.file.Path


/** Represents a recursive list of files at a given file system root.
 *
 *  `FileTree` is parameterized by any type that conforms to $locatable,
 *  which by default are $file and $path. Thus, `FileTree` can work with
 *  old-style Java files or new-style Java paths. Combined with the the `p`
 *  string interpolation prefix from `[[com.mipadi.jupiter.io.files]]`,
 *  callers can easily create file trees for `Path`s or `File`s:
 *
 *  {{{
 *  import java.io.File
 *  import com.mipadi.jupiter.io.files._
 *  import com.mipadi.jupiter.io.files.FileTree
 *  import com.mipadi.jupiter.io.files.Locatable._
 *
 *  val files = new File("src/main/scala").subtree.all
 *  val paths = (p"src" / "main" / "scala").subtree.all
 *  }}}
 *
 *  $richfile also adds the `subtree` extension method to `Locatable` types
 *  (namely, $file and $path), making it trivial to get subtrees.
 *
 *  @define file
 *    `java.io.File`
 *  @define locatable
 *    `[[com.mipadi.jupiter.io.files.Locatable Locatable]]`
 *  @define path
 *    `java.nio.file.Path`
 *  @define richfile
 *    `[[com.mipadi.jupiter.io.files.RichFile RichFile]]`
 *
 *  @tparam T
 *    The locatable object type serving as the file tree's root
 *  @param root
 *    The root of the directory tree
 *  @param ev
 *    An implicit delegate for handling file system actions
 */
class FileTree[T](val root: T)(implicit ev: Locatable[T]) {
  override def toString: String = root.path

  private lazy val entries: Seq[T] = ev.getFiles(root)

  /** Returns a sequence of all paths rooted under this file, including
   *  both files and directories.
   *
   *  For example, given this tree:
   *
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
  def all: Seq[T] = (entries ++ entries.flatMap(_.subtree.all)).sorted

  /** Returns a sequence of all ''file'' paths rooted under this file.
   *  Directories are excluded from this listing.
   *
   *  For example, given this tree:
   *
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
  def filesOnly: Seq[T] = all.filterNot(_.isDirectory)

  /** Tests whether the directory tree contains the given file.
   *
   *  @param file
   *    The file to test
   *  @return
   *    `true` if the file is contained within this directory tree
   */
  def contains(file: T): Boolean = all contains file
}
