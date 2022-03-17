# <img src=".buildscript/flurrylogo.png" width="30"/> Flurry Marketing Sample App - Android(Kotlin)

## Overview

This project showcases how Flurry Marketing can be integrated into your Android App. It also demonstrates how you can use Flurry Analytics to get basic insights into your users and app performance. 

## Features

This sample app demonstrates how to integrate Flurry Marketing by using auto integration and manual integration.

* Auto Integration - Flurry handles everything for you! We'll receive the notification, convert it to a FlurryMessage, show the notification, handle the notification click, handle the dismissed notification, and handle all the Flurry logging associated with it. Want some customized functionality? You have the option of passing us a listener to handle any of these steps yourself.

* Manual Integration - You handle the notification receivers and log events (notification received, notification opened, notification cancelled) to Flurry.

Different product flavors are created for auto/manual integration in the sample app. Select build variant in Android Studio to switch between auto and manual integration.

## Project Setup

1. Clone your repository and open the project in Android Studio. 
2. Build project and launch on emulator or device. 

## Troubleshoot

If the sample app could not get any notification, try to restart/cold start emulator or device.