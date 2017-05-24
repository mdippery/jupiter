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


/** A type class for path-like objects.
 *
 *  @see
 *    [[http://danielwestheide.com/blog/2013/02/06/the-neophytes-guide-to-scala-part-12-type-classes.html
 *      Type classes in Scala]]
 */
trait Locatable[T] {
  /** Joins a path-like object and a string together to form a new path. */
  def join(a: T, b: String): Path
}

/** Provides implicit vals for `java.io.File` and `java.nio.file.Path`. */
object Locatable {
  implicit object LocatablePath extends Locatable[Path] {
    override def join(a: Path, b: String): Path =
      FileSystems.getDefault.getPath(a.toString, b)
  }

  implicit object LocatableFile extends Locatable[File] {
    override def join(a: File, b: String): Path =
      FileSystems.getDefault.getPath(a.toPath.toString, b)
  }
}
