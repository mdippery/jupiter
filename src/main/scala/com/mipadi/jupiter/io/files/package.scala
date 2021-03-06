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

package com.mipadi.jupiter.io

import java.io.File
import java.nio.file.{FileSystems, Path}


/** Useful classes for easily working with files and paths.
 *
 *  Provides an implicit conversion from $file to $richfile, which adds
 *  some useful methods to `File`. It also provides an implicit conversion
 *  from $path to $richpath, which provides a more convenient way to work
 *  with paths in Scala.  Finally, it provides a `p` prefix for strings, so
 *  paths can be created directly from a string:
 *
 *  @define file
 *    `java.io.File`
 *  @define path
 *    `java.nio.file.Path`
 *  @define richfile
 *    `[[com.mipadi.jupiter.io.files.RichFile RichFile]]`
 *  @define richpath
 *    `[[com.mipadi.jupiter.io.files.RichPath RichPath]]`
 *
 *  {{{
 *  import com.mipadi.jupiter.io.files._
 *  val path = p"src/main/scala"
 *  }}}
 */
package object files {

  /** Implicitly converts file-like objects and adds some extension
   *  methods. Also allows file-like objects to be ordered based on their
   *  paths.
   *
   *  @tparam T
   *    The `[[com.mipadi.jupiter.io.files.Locatable Locatable]]` type
   *  @param f
   *    The wrapped file
   *  @param ev
   *    An implicit delegate for handling file-like operations
   */
  implicit class RichFile[T](f: T)(implicit ev: Locatable[T]) extends Ordered[T] {

    /** The wrapped file's path */
    val path = ev.getPath(f)

    /** The wrapped file's absolute path */
    val absolutePath = ev.getAbsolutePath(f)

    /** `true` if the file is a directory */
    def isDirectory = ev.isDirectory(f)

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
    override def compare(that: T): Int = ev.getPath(f) compare ev.getPath(that)

    /** A listing of all files rooted under this path.
     *
     *  @return
     *    A listing of all files under the path represented by the wrapped
     *    file
     */
    def subtree: FileTree[T] = new FileTree(f)
  }


  /** Allows paths to be built using the `/` operator.
   *
   *  @param path
   *    The wrapped path
   *  @param ev
   *    The path locator delegate
   */
  implicit class RichPath[T](path: T)(implicit ev: Locatable[T]) {

    /** Create a new path consisting of `that` appended to `path`.
     *
     *  This allows callers to create new paths like this:
     *
     *  {{{
     *  import com.mipadi.jupiter.io.files._
     *  val start = new File("src").toPath
     *  val path = start / "main" / "scala" / "com" / "mipadi"
     *  }}}
     *
     *  @param that
     *    The path component to append to `path`
     *  @return
     *    A new path consisting of `that` appended to `path`
     */
    def / (that: String): Path = ev.join(path, that)
  }


  /** Allows paths to be built from strings using the `p` prefix:
   *
   *  {{{
   *  import com.mipadi.jupiter.io.files._
   *  val path = p"path/to/file.txt"
   *  }}}
   *
   *  @param ctx
   *    The wrapped string context
   */
  implicit class PathStringContext(ctx: StringContext) {

    /** Build a path using the `p` prefix:
     *
     *  {{{
     *  import com.mipadi.jupiter.io.files._
     *  val path = p"path/to/file.txt"
     *  }}}
     *
     *  @param args
     *    String context arguments
     *  @return
     *    A path represented by the given string context
     */
    def p(args: Any*): Path = FileSystems.getDefault.getPath(ctx.parts(0))
  }
}
