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
import java.nio.file.{FileSystems, Path}
import scala.language.implicitConversions


/** Useful classes for easily working with files and paths.
 *
 *  Provides an implicit conversion from `java.util.File` to
 *  `[[com.mipadi.io.files.RichFile RichFile]]`, which adds some useful
 *  methods to `File`. It also provides an implicit conversion from
 *  `java.nio.file.Path` to `[[com.mipadi.io.files.RichPath RichPath]]`,
 *  which provides a more convenient way to work with paths in Scala.
 *  Finally, it provides a `p` prefix for strings, so paths can be created
 *  directly from a string:
 *
 *  {{{
 *  import com.mipadi.io.files._
 *  val path = p"src/main/scala"
 *  }}}
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
    private lazy val fs: Seq[File] = Option(f.listFiles) map { files =>
      files.toSeq
    } getOrElse {
      List()
    }

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
    override def compare(that: File): Int = f.getPath compare that.getPath

    /** A listing of all files rooted under this path.
     *
     *  @return
     *    A listing of all files under the path represented by the wrapped
     *    file
     */
    def subtree: FileTree = new FileTree(f)
  }


  /** Allows paths to be built using the `/` operator.
   *
   *  @param path
   *    The wrapped path
   */
  implicit class RichPath(path: Path) {

    /** Create a new path consisting of `that` appended to `path`.
     *
     *  This allows callers to create new paths like this:
     *
     *  {{{
     *  import com.mipadi.io.files._
     *  val start = new File("src").toPath
     *  val path = start / "main" / "scala" / "com" / "mipadi"
     *  }}}
     *
     *  @param that
     *    The path component to append to `path`
     *  @return
     *    A new path consisting of `that` appended to `path`
     */
    def / (that: String): Path =
      FileSystems.getDefault.getPath(path.toString, that)
  }


  /** Coerces `java.io.File` objects to `[[com.mipadi.io.files.RichPath
   *  RichPath]]` objects, allowing new paths to be built using the `/`
   *  operator.
   *
   *  @param file
   *    The wrapped file
   *  @return
   *    A new `[[com.mipadi.io.files.RichPath RichPath]]` object wrapping
   *    `file`
   */
  implicit def fileToRichPath(file: File): RichPath = new RichPath(file.toPath)


  /** Allows paths to be built from strings using the `p` prefix:
   *
   *  {{{
   *  import com.mipadi.io.files._
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
     *  import com.mipadi.io.files._
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
