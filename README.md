## Latvian Module

The repository implements the Latvian module for the LanguageTool

To see the module code visit https://github.com/Cerins/languagetool/tree/lv-module/languagetool-language-modules/lv.

### Prerequisites

The Latvian module requires https://github.com/PeterisP/morphology.git installed locally.

Steps to do so:
1. `git clone https://github.com/PeterisP/morphology.git`
2. `cd morphology`
3. `mvn install -DskipTests`
4. `mvn install:install-file \
   -Dfile=./target/morphology-2.5.4-SNAPSHOT.jar \
   -DgroupId=lv.ailab.morphology \
   -DartifactId=morphology \
   -Dversion=2.5.4-SNAPSHOT \
   -Dpackaging=jar`

After this the languagetool project can be installed as normal.

### Installation

After completing the preprequisites the installation follows the standard lanuagetool installation.

1. `git clone --depth=100 https://github.com/Cerins/languagetool` - depth 100 is chosen since it includes all the module commits
2. `cd languagetool`
3. `mvn install -DskipTests` - skips tests, since they take quite a lot of time, but it is better to remove the flag if the time is not a concern.

### Running

Should be done after completing installation steps.

#### CLI

1. `cd ./languagetool-standalone/target/LanguageTool-6.5-SNAPSHOT/LanguageTool-6.5-SNAPSHOT/`
2. `echo "Šis ir pārbaudes teikums." | java -jar languagetool-commandline.jar -l lv`

#### Firefox

1. Install the languagetool extension
2. `cd ./languagetool-standalone/target/LanguageTool-6.5-SNAPSHOT/LanguageTool-6.5-SNAPSHOT/`
3. `touch server.properties`
4. `touch org/languagetool/resource/lv/common_words.txt`
5. `java -cp languagetool-server.jar org.languagetool.server.HTTPServer --config server.properties --port 8081 --allow-origin`
6. Manage the languagetool extension and in advanced options set that server is localhost.

#### LibreOffice

The directory ./languagetool-office-extension/target should contain a zip, rename the *.zip to *.oxt to install it in LibreOffice/OpenOffice.


## LanguageTool

**LanguageTool** is an Open Source proofreading software for English, Spanish, French, German,
Portuguese, Polish, Dutch, and [more than 20 other languages](https://languagetool.org/languages/).
It finds many errors that a simple spell checker cannot detect.

* **[Jobs at LanguageTool](https://languagetool.org/careers)**
* [LanguageTool Forum](https://forum.languagetool.org)
* [How to run your own LanguageTool server](https://dev.languagetool.org/http-server)
* [HTTP API documentation](https://languagetool.org/http-api/swagger-ui/#!/default/post_check)
* [How to use our public server via HTTP](https://dev.languagetool.org/public-http-api)
* [How to use LanguageTool from Java](https://dev.languagetool.org/java-api) ([Javadoc](https://languagetool.org/development/api/index.html?org/languagetool/JLanguageTool.html))

For more information, please see our homepage at https://languagetool.org,
[this README](https://github.com/languagetool-org/languagetool/blob/master/languagetool-standalone/README.md),
and [CHANGES](https://github.com/languagetool-org/languagetool/blob/master/languagetool-standalone/CHANGES.md).

The LanguageTool core (this repo) is freely available under the LGPL 2.1 or later.

## Docker

Try one of the following projects for a community-contributed Docker file:

- https://github.com/meyayl/docker-languagetool ([Docker Hub](https://hub.docker.com/r/meyay/languagetool))
- https://github.com/Erikvl87/docker-languagetool ([Docker Hub](https://hub.docker.com/r/erikvl87/languagetool))
- https://github.com/silvio/docker-languagetool ([Docker Hub](https://hub.docker.com/r/silviof/docker-languagetool))

## Contributions

[The development overview](https://dev.languagetool.org/development-overview) describes
how you can contribute error detection rules.

For more technical details, see [our dev pages](https://dev.languagetool.org).

## Scripted installation and building
To install or build using a script, simply type:
```
curl -L https://raw.githubusercontent.com/languagetool-org/languagetool/master/install.sh | sudo bash <options>
```

If you wish to have more options, download the `install.sh` script. Usage options follow:

```
sudo bash install.sh <options>

Usage: install.sh <option> <package>
Options:
   -h --help                   Show help
   -b --build                  Builds packages from the bleeding edge development copy of LanguageTool
   -c --command <command>      Specifies post-installation command to run (default gui when screen is detected)
   -q --quiet                  Shut up LanguageTool installer! Only tell me important stuff!
   -t --text <file>            Specifies what text to be spellchecked by LanguageTool command line (default spellcheck.txt)
   -d --depth <value>          Specifies the depth to clone when building LanguageTool yourself (default 1).
   -p --package <package>      Specifies package to install when building (default all)
   -o --override <OS>          Override automatic OS detection with <OS>
   -a --accept                 Accept the oracle license at http://java.com/license. Only run this if you have seen the license and agree to its terms!
   -r --remove <all/partial>   Removes LanguageTool install. <all> uninstalls the dependencies that were auto-installed. (default partial)

Packages(only if -b is specified):
   standalone                  Installs standalone package
   wikipedia                   Installs Wikipedia package
   office-extension            Installs the LibreOffice/OpenOffice extension package

Commands:
   GUI                         Runs GUI version of LanguageTool
   commandline                 Runs command line version of LanguageTool
   server                      Runs server version of LanguageTool
```

## Alternate way to build from source

Before start: you will need to clone from GitHub and install Java 8 and Apache Maven.

Warning: a complete clone requires downloading more than 500 MB and needs more than 1500 MB on disk.
This can be reduced if you only need the last few revisions of the master branch
by creating a shallow clone:

    git clone --depth 5 https://github.com/languagetool-org/languagetool.git

A shallow clone downloads less than 60 MB and needs less than 200 MB on disk.

In the root project folder, run:

    mvn clean test

(sometimes you can skip Maven step for repeated builds)

    ./build.sh languagetool-standalone package -DskipTests

Test the result in `languagetool-standalone/target/`.

    ./build.sh languagetool-wikipedia package -DskipTests

Test the result in `languagetool-wikipedia/target`.

    ./build.sh languagetool-office-extension package -DskipTests

Test the result in `languagetool-office-extension/target`, rename the `*.zip` to `*.oxt` to install it in LibreOffice/OpenOffice.

Now you can use the bleeding edge development copy of LanguageTool `*.jar` files, be aware that it might contain regressions.


### How to run under Mac M1 or M2

1. Install Brew for Rosetta: `arch -x86_64 /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install.sh)"`
2. Install openjdk for Rosetta: `arch -x86_64 brew install openjdk`
3. Install Maven for Rosetta: `arch -x86_64 brew install maven`
4. Now run build scripts

### License

Unless otherwise noted, this software - the LanguageTool core - is distributed under the LGPL, see
file [COPYING.txt](https://github.com/languagetool-org/languagetool/blob/master/COPYING.txt).

