#!/bin/bash
#
# Copyright (C) 2022 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

# Verify bash version. macOS comes with bash 3 preinstalled.
if [[ ${BASH_VERSINFO[0]} -lt 4 ]]
then
  echo "You need at least bash 4 to run this script."
  exit 1
fi

# exit when any command fails
set -e

if [[ $# -lt 2 ]]; then
   echo "Usage: bash customizer.sh my.new.package [ApplicationName]" >&2
   exit 2
fi

PACKAGE=$1
APPNAME=$2
SUBDIR=${PACKAGE//.//} # Replaces . with /
DEFAULT_PACKAGE="com.baec23.composetemplate"
DEFAULT_PACKAGE_SUBDIR=${DEFAULT_PACKAGE//.//}
DEFAULT_APPNAME="ComposeTemplate"

for n in $(find . -type d \( -path '*/src/androidTest' -or -path '*/src/main' -or -path '*/src/test' \) )
do
  echo "Creating $n/java/$SUBDIR"
  mkdir -p $n/java/$SUBDIR
  echo "Moving files to $n/java/$SUBDIR"
  mv $n/java/$DEFAULT_PACKAGE_SUBDIR/* $n/java/$SUBDIR
  echo "Removing old $n/java/$DEFAULT_PACKAGE_SUBDIR"
  rm -rf $n/java/$DEFAULT_PACKAGE_SUBDIR
done

# Rename package and imports
echo "Renaming packages to $PACKAGE"
find ./ -type f -name "*.kt" -exec sed -i.bak "s/package $DEFAULT_PACKAGE/package $PACKAGE/g" {} \;
find ./ -type f -name "*.kt" -exec sed -i.bak "s/import $DEFAULT_PACKAGE/import $PACKAGE/g" {} \;

# Gradle files
find ./ -type f -name "*.kts" -exec sed -i.bak "s/$DEFAULT_PACKAGE/$PACKAGE/g" {} \;

# Rename app
if [[ $APPNAME ]]
then
    echo "Renaming app to $APPNAME"
    find ./ -type f \( -name "$DEFAULT_APPNAME.kt" -or -name "settings.gradle.kts" -or -name "*.xml" \) -exec sed -i.bak "s/$DEFAULT_APPNAME/$APPNAME/g" {} \;
    find ./ -name "$DEFAULT_APPNAME.kt" | sed "p;s/$DEFAULT_APPNAME/$APPNAME/" | tr '\n' '\0' | xargs -0 -n 2 mv
    find . -name "*.bak" -type f -delete
fi

# Update AndroidManifest.xml with new application name
if [[ $APPNAME ]]; then
    echo "Updating android:name in AndroidManifest.xml to $APPNAME"
    find ./ -type f -name "AndroidManifest.xml" -exec sed -i.bak "s/android:name=\"$DEFAULT_APPNAME\"/android:name=\"$APPNAME\"/g" {} \;
fi

# Update settings.gradle.kts with new application name
if [[ $APPNAME ]]; then
    echo "Updating rootProject.name in settings.gradle.kts to $APPNAME"
    find ./ -type f -name "settings.gradle.kts" -exec sed -i.bak "s/rootProject.name = \"$DEFAULT_APPNAME\"/rootProject.name = \"$APPNAME\"/g" {} \;
fi

# Update Theme.kt with new theme name
if [[ $APPNAME ]]; then
    echo "Updating theme in Theme.kt to ${APPNAME}Theme"
    find ./ -type f -name "Theme.kt" -exec sed -i.bak "s/${DEFAULT_APPNAME}Theme/${APPNAME}Theme/g" {} \;
fi

# Update MainActivity.kt with new theme name
if [[ $APPNAME ]]; then
    echo "Updating theme in MainActivity.kt to ${APPNAME}Theme"
    find ./ -type f -name "MainActivity.kt" -exec sed -i.bak "s/${DEFAULT_APPNAME}Theme/${APPNAME}Theme/g" {} \;
fi

# Update strings.xml with new application name
if [[ $APPNAME ]]; then
    echo "Updating strings in strings.xml to $APPNAME"
    find ./ -type f -name "strings.xml" -exec sed -i.bak "s/$DEFAULT_APPNAME/$APPNAME/g" {} \;
fi

#echo "Removing additional files"
#rm -rf .google/
#rm -rf .github/

#rm -rf .git/

echo "Cleaning up"
find . -name "*.bak" -type f -delete
rm -rf customizer.sh

echo "Done!"
read -p "Press Enter to exit"
