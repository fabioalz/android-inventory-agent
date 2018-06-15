#!/usr/bin/env bash
#
#  LICENSE
#
#  This file is part of Flyve MDM Inventory Agent for Android.
#
#  Inventory Agent for Android is a subproject of Flyve MDM. Flyve MDM is a 
#  mobile device management software.
#
#  Flyve MDM Inventory Agent for Android is free software: you can redistribute 
#  it and/or modify it under the terms of the GNU General Public License
#  as published by the Free Software Foundation; either version 3
#  of the License, or (at your option) any later version.
#
#  Flyve MDM Inventory Agent for Android is distributed in the hope that it will be 
#  useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#  GNU General Public License for more details.
#  ------------------------------------------------------------------------------
#  @author    Rafael Hernandez - <rhernandez@teclib.com>
#  @copyright Copyright (c) 2017 - 2018 Flyve MDM
#  @license   GPLv3 https://www.gnu.org/licenses/gpl-3.0.html
#  @link      https://github.com/flyve-mdm/android-inventory-agent
#  @link      http://flyve.org/android-inventory-agent
#  @link      https://flyve-mdm.com/
#  ------------------------------------------------------------------------------
#

GH_COMMIT_MESSAGE=$(git log --pretty=oneline -n 1 $CIRCLE_SHA1)

if [[ $GH_COMMIT_MESSAGE == *"ci(release): generate CHANGELOG.md for version"* || $GH_COMMIT_MESSAGE == *"build(properties): add new properties values"* || $GH_COMMIT_MESSAGE == *"ci(release): update version on android manifest"* ]]; then
    echo "Running duplicated"
    exit 1
fi