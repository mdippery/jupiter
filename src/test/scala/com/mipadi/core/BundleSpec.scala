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

import java.util.regex.Pattern
import org.scalatest._


class BundleSpec extends FlatSpec with Matchers {
  def contents = """/*
                   | * Commentary
                   | * Copyright (C) 2017 Michael Dippery <michael@monkey-robot.com>
                   | *
                   | * This program is free software: you can redistribute it and/or modify
                   | * it under the terms of the GNU General Public License as published by
                   | * the Free Software Foundation, either version 3 of the License, or
                   | * (at your option) any later version.
                   | *
                   | * This program is distributed in the hope that it will be useful,
                   | * but WITHOUT ANY WARRANTY; without even the implied warranty of
                   | * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
                   | * GNU General Public License for more details.
                   | *
                   | * You should have received a copy of the GNU General Public License
                   | * along with this program.  If not, see <http://www.gnu.org/licenses/>.
                   | */
                   |
                   |lazy val root = (project in file("."))
                   |  .settings(
                   |    name         := "Commentary",
                   |    organization := "com.mipadi",
                   |    version      := "0.1.0-SNAPSHOT",
                   |    scalaVersion := "2.12.1",
                   |
                   |    libraryDependencies ++= Seq(
                   |      "com.github.scopt" %% "scopt" % "3.5.0",
                   |      "org.json4s" %% "json4s-native" % "3.5.1",
                   |      "org.scalaj" %% "scalaj-http" % "2.3.0",
                   |      "org.scalatest" %% "scalatest" % "3.0.1" % Test
                   |    )
                   |  )
                   |""".stripMargin.split("\n")

  def regexFor(needle: String) = Pattern.compile("^\\s*" + needle + "\\s+:=.*$")

  "A list of lines from an SBT source file" should "match on the name specified" in {
    contents.find(_.contains("name")) should be (Some("    name         := \"Commentary\","))
  }

  it should "match on the version specified" in {
    contents.find(regexFor("version").matcher(_).matches()) should be (Some("    version      := \"0.1.0-SNAPSHOT\","))
  }
}
