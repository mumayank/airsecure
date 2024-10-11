<img src="./assets/readme_header.png" alt="Alt text" width="500"/>

# AirSecure 🔒

Welcome to **AirSecure**! 😎

Do you worry about the security of your Android app and all the sensitive user data it handles? Well, **AirSecure** is here to save the day! 🦸‍♂️

This handy library takes care of the heavy lifting by providing out-of-the-box security checks to help keep malicious users at bay. Whether it’s a rooted device, an attached debugger, developer options being toggled on, or if the app is running on an emulator—**AirSecure** has got you covered! It even makes sure your app’s windows stay secure from prying eyes. 🔐

The best part? You can integrate this power-packed security solution into your app in just under **2 minutes**! 🚀

## Why AirSecure?

Developers work hard on building amazing apps, but **malicious actors** could potentially exploit security vulnerabilities, putting your app’s integrity and user data at risk. That’s where **AirSecure** comes in! Designed with security-sensitive apps in mind, this library is:

- Easy to implement 🎉
- Packed with powerful features 💪
- Available in just a few lines of code ✍️

## Features 🛡️

With **AirSecure**, you can:

- **Detect Developer Options**: Know when a user has these options turned on!
- **Debugger Detection**: Alerts when a debugger is attached.
- **Root Detection**: Detects if the device is rooted to prevent any unwanted access.
- **Emulator Detection**: Identify if the app is running on an emulator.
- **Secure Window**: Keep your app window secure and block screenshots or recording.

All these checks provide instant feedback and callbacks, so you can take action when something’s fishy! 🕵️‍♂️

## Getting Started 🚀

Ready to secure your app? Here's how you can get started in **just a couple of minutes**:

### Step 1: Add AirSecure to your project

First, add **jitpack** to your project’s build.gradle:

```gradle
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}
```

Next, add the dependency for **AirSecure** in your app's build.gralde:

```gradle
dependencies {
    implementation 'com.github.mumayank:airsecure:VERSION'
}
```

Make sure to replace `VERSION` with the latest release:

[![Release](https://jitpack.io/v/mumayank/airsecure.svg)](https://jitpack.io/#mumayank/airsecure)

### Step 2: Use AirSecure in your app!

Here’s how simple it is to implement **AirSecure**:

```kotlin
// Set secure window to prevent screenshots/recordings
AirSecure.setSecureWindow(window)

// Get a security report to check if your app is secure
val appSecurityReport = AirSecure.getAppSecurityReport(context)
if (appSecurityReport is AirSecure.AppSecurityReport.AppIsNotSecure) {
    // Handle security issues here
}

// Periodically check for any app security violations
AirSecure.checkAppSecurityReportPeriodically(context) { appSecurityViolationTypes ->  
    // Respond to security violations here
}
```

That’s it! Your app is now protected by **AirSecure**! 🛡️

## What’s Inside the GitHub Repo? 🗂️

In this repository, you’ll find two modules:

1. **AirSecure Library**: The core module providing all the security features.
2. **Sample App**: A simple Android app demonstrating how you can use **AirSecure** in your own project.

Feel free to play around with the sample app to see how it works! 🎮

## FAQs 🤔

**Q: What Android versions does AirSecure support?**
- **AirSecure** works on Android 5.0 (Lollipop) and above. 🎯

**Q: Can I customize the security checks?**
- Absolutely! **AirSecure** gives you flexibility by providing callbacks so you can handle any security violations in your own way.

**Q: Does it impact my app’s performance?**
- Not at all! **AirSecure** is lightweight and optimized to run seamlessly alongside your app. 💨 (The library uses 5s intevals to monitor the app by default (customizable), and also uses coroutine's IO dispatcher to check emulator related dir files or rooting)

## Contribution 🛠️

Want to make **AirSecure** even better? We welcome contributions! Please feel free to submit pull requests, report issues, or suggest features. Let’s make the Android world more secure, one app at a time! 🛡️

## Shoutout
 
Thanks to [rootbeer](https://github.com/scottyab/rootbeer) library for making it easier to detect root. This library utilizes rootbeer internally.

---

## SEO Keywords 📈

android security library, android app security, prevent rooting, android app protection, root detection android, android developer options detection, android emulator detection, android secure window, prevent screenshots android, security for sensitive apps, debugger detection android, android security checks, android anti-debugging, security-first android library, android security callbacks, prevent hacking android apps, protect android user data, secure android apps easily, android app development library, security-focused android development, anti-debugger android, android app safety, android protect data integrity, detect rooted devices android, emulator detection android library, android secure framework, detect developer mode android, android security open source, airsecure android library, airsecure android app security, android maven security library, android security project github, airsecure github project, secure android windows easily, stop screen capture android, android sensitive data protection, android root detection, android prevent recording, secure android data, developer settings detection android, security APIs android, android library for secure apps, jitpack security library, android anti-tampering library, simple android security library, android anti-rooting, android security with airsecure, security module for android apps, app protection android, android secure app development, android security solutions, airsecure maven dependency, android app defense mechanisms, android security library integration, jitpack android security, detect security risks android, stop hacking android apps, prevent emulator use android apps, airsecure android toolkit, android secure solutions library, detect rooted android easily, jitpack security for android, android library secure windows.
