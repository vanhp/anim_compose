# anim_compose

## Table of Contents

- [Introduction](#introduction)
- [Prerequisite](#prerequisite)
- [Usage](#usage)
- [Limitation](#limitation)
- [Work in progresss](#work-in-progress)
- [License](#license)
- [Disclaimer](#disclaimer)

## Introduction
A project to demostration of the integration between Huawei Ads and Appsflyer in Jetpack Compose app.

## Prerequisite
    - Huawei account Appgallery Connect account
    - Appsflyer account access for developer
    - 
    - 
    - 



 SDK integration:
 
    - HMS ads version
    - Appsflyer version 6.8.2
    - 
    - 


## Usage

### On Window 10
install WSL if not already present.
run the bash shell then type ./makeapk.exp you should see output flash by at the end you should see 

Building the Android App...
-   Generated Android APK at *./app-release-signed.apk*
-   Generated Android App Bundle at *./app-release-bundle.aab*

You may copy the APK to install on your phone.

### On Linux and MacOS

the script can be run directly in the console terminal with the command ./makeapk.exp

FYI: these files are in Unix line ending format LF instead of Windows CRLF, you can check by looking at the status bar on vscode


## Limitation

Currently only test on Window 10 WSL and Windows command prompt.
- Web site must have manifest.json file present

## Work in progress

auto-generate manifest.json file if one not present.

## License
see [LICENSE](./LICENSE.md) for more info


## Disclaimer

This is not an officially supported Futurewei product
