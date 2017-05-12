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


package object files {
  implicit class RichFile(f: File) extends Ordered[File] {
    private lazy val fs = Option(f.listFiles) getOrElse { new Array[File](0) }

    val path = f.getPath
    val absolutePath = f.getAbsolutePath

    def compare(that: File) = f.getPath compare that.getPath

    def subtree: Seq[File] =
      (fs.directories.foldEntries { (memo, e) => (memo :+ e) ++ e.subtree } ++
       fs.files.foldEntries(_ :+ _)).sorted

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
