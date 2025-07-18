# InstaNav — Instagram-style Tab Navigation for Compose Multiplatform

[![Maven Central](https://img.shields.io/maven-central/v/dev.kadoglou/instanav.core?label=maven%20central)](https://central.sonatype.com/artifact/dev.kadoglou/instanav.core)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.20-blue.svg?style=flat&logo=kotlin)](https://kotlinlang.org)  
![badge-android](http://img.shields.io/badge/platform-android-6EDB8D.svg?style=flat) ![badge-ios](http://img.shields.io/badge/platform-ios-AAAAFF.svg?style=flat)

---

A Kotlin Multiplatform library that mimics the tab navigation behaviour seen in apps like Instagram and YouTube.

This behaviour is particularly noticeable on Android, where the back button allows users to navigate across previously visited tabs in reverse order — a pattern not native to iOS.

For those unfamiliar: Instagram’s tab navigation remembers the order in which tabs were visited. When pressing "back", it navigates to the *last visited tab*. Each tab is also kept uniquely in the tab stack — it's never added twice.

The first tab (home tab) is treated specially: it's reinserted if popped earlier, ensuring the app always exits from the home tab.

This creates a smooth and intuitive experience, where users move across tabs naturally and return to the home screen before the app closes.

---

### 🎥 Demo Video

https://github.com/user-attachments/assets/d53144c4-ba82-4419-8a04-b52901e65a59

---

### 🚀 Usage

Add the dependency to your project:

```kotlin
implementation("dev.kadoglou:instanav.core:1.0.0")
```

---

### 🛠️ Setup

Define your tabs and navigation state:

```kotlin
val tabs = listOf(
    InstaTab("tab_1", ScreenA.route),
    InstaTab("tab_2", ScreenB.route),
)

val instaNavState = rememberInstaNavState(
    initialTabs = listOf("tab_1") // The first tab is treated as the "home tab"
)

val controllers = List(tabs.size) { rememberNavController() }
val tabNavControllers = remember(controllers) {
    tabs.mapIndexed { i, tab -> tab.tabRoute to controllers[i] }.toMap()
}
```

---

### 🧩 Use `InstaNavHost`

Replace your normal `NavHost` with `InstaNavHost` and pass in the state, tabs, and controllers.

```kotlin
InstaNavHost(
    state = instaNavState,
    tabs = tabs,
    tabNavControllers = tabNavControllers
) {
    composable(ScreenA.route) { ScreenA().context() }
    composable(ScreenB.route) { ScreenB().context() }

    // You can also use custom extensions like animatableComposable
    animatableComposable(SubScreenA.route) { SubScreenA().context() }
}
```

---

### 🧭 Handle Tab Navigation

Switch tabs with:

```kotlin
instaNavState.navigateTo("tab_2")
```

This will:
- Bring the tab to the front if it was already visited.
- Push it to the tab history stack (no duplicates).
- Keep its navigation state intact.

To go "back" between visited tabs:

```kotlin
instaNavState.popBackTabStack()
```

---

### 🧱 Scaffold Example

You can embed this into your `Scaffold` and use something like a `BottomBar` to control navigation.  
The `LocalInstaNavController` is used to expose the active `NavHostController` to your screens.

```kotlin
CompositionLocalProvider(
    LocalInstaNavController provides tabNavControllers.getValue(instaNavState.currentTab)
) {
    Scaffold(
        bottomBar = {
            BottomBar(
                currentTab = instaNavState.currentTab,
                tabs = tabs
            ) { selectedTab ->
                instaNavState.navigateTo(selectedTab)
            }
        }
    ) { padding ->
        Box(Modifier.padding(padding)) {
            InstaNavHost(
                state = instaNavState,
                tabs = tabs,
                tabNavControllers = tabNavControllers
            ) {
                composable(ScreenA.route) { ScreenA().context() }
                composable(ScreenB.route) { ScreenB().context() }

                animatableComposable(SubScreenA.route) { SubScreenA().context() }
            }
        }
    }
}
```

---

### 💾 Saving State (Optional)

To persist tab history across process death:

```kotlin
val instaNavState = rememberInstaNavState(
    initialTabs = listOf("tab_1"),
    saveTab = { it },           // Convert to String
    restoreTab = { it }         // Restore from String
)
```

---

### 🧪 Tips

- Use a `CompositionLocalProvider` to expose the active tab's `NavHostController`:

```kotlin
CompositionLocalProvider(
    LocalInstaNavController provides tabNavControllers.getValue(instaNavState.currentTab)
) {
    // Screens can now use LocalInstaNavController.current
}
```

- Tabs are only composed if they’re currently in the stack (`openTabs`), helping reduce memory usage.
