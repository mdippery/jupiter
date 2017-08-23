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

package com.mipadi.jupiter.core

import java.io.File

import scala.io.Source

import com.mipadi.jupiter.core.internal._
import com.mipadi.jupiter.core.pipes._
import com.mipadi.jupiter.text.strings._


/** Represents a source of bundle information. */
private trait BundleSource {
  /** The name of the bundle. */
  def name: String

  /** The version of the bundle. */
  def version: String

  /** The class for which this bundle has been loaded. */
  def cls: Class[_]
}


private class ManifestSource(val cls: Class[_]) extends BundleSource {
  def name: String = cls.getPackage.getImplementationTitle

  def version: String = cls.getPackage.getImplementationVersion
}


private class BuildSBTSource(val cls: Class[_], val file: File) extends BundleSource {
  def name: String = findLine("name")

  def version: String = findLine("version")

  private def contents: Iterator[String] =
    Source.fromFile(file.getAbsolutePath).getLines

  private def findLine(needle: String): String =
    contents.find(_ =~ s"$needle\\s+:=").fold("")(_.value)
}


/** Contains metadata about an application or library. */
trait Bundle {
  /** The name of the bundle. */
  def name: String

  /** The version of the bundle. */
  def version: String
}

/** Loads bundles for a given class. */
object Bundle {
  private def versionFromManifest(cls: Class[_]): Option[String] =
    Option(new ManifestSource(cls).version)

  private def bundleSource(cls: Class[_]): BundleSource = versionFromManifest(cls) map { v =>
    new ManifestSource(cls)
  } getOrElse {
    new BuildSBTSource(cls, new File("build.sbt"))
  }

  /** Loads the bundle associated with the given class.
   *
   *  Often times, this is as simple as calling:
   *
   *  {{{
   *  val bundle = Bundle.forClass(getClass)
   *  }}}
   *
   *  @param cls
   *    The class for which a bundle should be loaded.
   *  @return
   *    The bundle associated with the given class.
   */
  def forClass(cls: Class[_]): Bundle =
    cls |> bundleSource |> (s => new BundleImpl(s))
}


private class BundleImpl(source: BundleSource) extends Bundle {
  def name: String = source.name

  def version: String = source.version
}
