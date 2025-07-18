
# InstaNav — Instagram-style Tab Navigation for Compose Multiplatform
[![Maven Central](https://img.shields.io/maven-central/v/dev.kadoglou/instanav.core?label=maven%20central)](https://central.sonatype.com/artifact/dev.kadoglou/instanav.core) [![Kotlin](https://img.shields.io/badge/Kotlin-2.0.20-blue.svg?style=flat&logo=kotlin)](https://kotlinlang.org)

![badge-android](http://img.shields.io/badge/platform-android-6EDB8D.svg?style=flat) ![badge-ios](http://img.shields.io/badge/platform-ios-AAAAFF.svg?style=flat)

A Kotlin Multiplatform library that mimics the tab navigation behaviour seen in apps like Instagram and YouTube.  
This behaviour is particularly noticeable on Android, where the back button allows users to navigate across previously visited tabs in reverse order — a pattern not native to iOS.

For those unfamiliar: Instagram’s tab navigation remembers the order in which tabs were visited. When pressing "back", it navigates to the *last visited tab*. Each tab is also kept uniquely in the tab stack — it's never added twice.

Each tab maintains its own back stack, and the user moves between tabs with predictable, intuitive behaviour — just like in the apps we use daily.

Here’s a demo video showing the behaviour in action, including the tab stack and individual back stacks for each tab.

### Demo Video

