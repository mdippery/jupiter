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

package com.mipadi.core

import java.io.File
import scala.io.Source
import com.mipadi.text.strings._


trait BundleSource {
  def name: String

  def version: String
}


private class ManifestSource extends BundleSource {
  def name: String = getClass.getPackage.getImplementationTitle

  def version: String = getClass.getPackage.getImplementationVersion
}


private class BuildSBTSource(file: File) extends BundleSource {
  def name: String = findLine("name")

  def version: String = findLine("version")

  private def contents: Iterator[String] =
    Source.fromFile(file.getAbsolutePath).getLines

  private def findLine(needle: String): String =
    contents.find(_ =~ s"$needle\\s+:=").fold("") { _.value }

  private implicit class KeyValueString(s: String) {
    def key: String = s.splitAndGet(0).removeUnwantedChars

    def value: String = s.splitAndGet(1).removeUnwantedChars

    private[core] def splitAndGet(i: Int) = s.split(" := ")(i)

    private[core] def removeUnwantedChars: String =
      s.replaceAll("[\",]", "").trim
  }
}


object Bundle {
  private def versionFromManifest: Option[String] =
    Option(new ManifestSource().version)

  private def bundleSource: BundleSource = versionFromManifest map { v =>
    new ManifestSource()
  } getOrElse {
    new BuildSBTSource(new File("build.sbt"))
  }

  def mainBundle: Bundle = new Bundle(bundleSource)
}

class Bundle(source: BundleSource) {
  def name: String = source.name

  def version: String = source.version
}
