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


/** Useful classes for easily working with files.
 *
 *  Provides implicit conversions from `java.io.File` to
 *  `[[com.mipadi.io.files.RichFile RichFile]]` with the purpose of adding
 *  some useful methods.
 */
package object files {

  /** Implicitly converts `java.io.File` objects and adds some extension
   *  methods. Also allows `File` objects to be ordered based on their
   *  paths.
   *
   *  @param f
   *    The wrapped file.
   */
  implicit class RichFile(f: File) extends Ordered[File] {
    private lazy val fs = Option(f.listFiles) getOrElse { new Array[File](0) }

    /** The wrapped file's path */
    val path = f.getPath

    /** The wrapped file's absolute path */
    val absolutePath = f.getAbsolutePath

    /** Compares the receiver's path to `that`'s path.
     *
     *  Determines a logical sorting for `File` objects.
     *
     *  @param that
     *    The file whose path the receiver's should be compared to.
     *  @return
     *    - '''< 0''' if `this` comes before `that`
     *    - '''> 0''' if `this` comes after `that`
     *    - '''0''' if `this` is equal to `that`
     */
    def compare(that: File) = f.getPath compare that.getPath

    /** Returns a sequence of all paths rooted under this file.
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
     *   ["src/main/scala/com/mipadi/io/files",
     *    "src/main/scala/com/mipadi/io/files/package.scala",
     *    "src/main/scala/com/mipadi/io/IO.scala",
     *    "src/main/scala/com/mipadi/io/package.scala",
     *    "src/main/scala/com/mipadi/io/terminal",
     *    "src/main/scala/com/mipadi/io/terminal/colors",
     *    "src/main/scala/com/mipadi/io/terminal/colors/package.scala",
     *    "src/main/scala/com/mipadi/io/terminal/package.scala"]
     *  }}}
     *
     *  @return
     *    A sequence of all paths rooted under this file, sorted by name.
     *    If this file is not a directory, an empty sequence is returned.
     */
    def subtree: Seq[File] =
      (fs.directories.foldEntries((memo, e) => (memo :+ e) ++ e.subtree) ++
       fs.files.foldEntries(_ :+ _)).sorted

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
     *   ["src/main/scala/com/mipadi/io/files/package.scala",
     *    "src/main/scala/com/mipadi/io/IO.scala",
     *    "src/main/scala/com/mipadi/io/package.scala",
     *    "src/main/scala/com/mipadi/io/terminal/colors/package.scala",
     *    "src/main/scala/com/mipadi/io/terminal/package.scala"]
     *  }}}
     *
     *  @return
     *    A sequence of all non-directory entries under this file, sorted by
     *    name. If this file is not a directory, an empty sequence is
     *    returned.
     */
    def subtreeFiles: Seq[File] =
      (fs.directories.foldEntries(_ ++ _.subtreeFiles) ++
       fs.files.foldEntries(_ :+ _)).sorted
  }

  private[files] implicit class FileArray(fs: Array[File]) {
    lazy val files = fs.filterNot(_.isDirectory)
    lazy val directories = fs.filter(_.isDirectory)

    def foldEntries(op: (Array[File], File) => Array[File]) =
      fs.foldLeft(new Array[File](0))(op)
  }
}
