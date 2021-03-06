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

package com.mipadi.jupiter.core.logging

import java.util.logging.Logger


/** Adds a log to extending classes.
 *
 *  The log automatically has the same name as the package that
 *  contains the extending class.
 */
trait Logging {
  /** A log with the same name as the package of the extending class. */
  val log = Logger.getLogger(getClass.getPackage.getName)
}
