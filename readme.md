# I Hate Android Pie

## Intro

[Android P feature spotlight: Location accuracy is now a binary setting, battery saving mode is gone](https://www.androidpolice.com/2018/03/08/android-p-feature-spotlight-location-accuracy-now-binary-setting-battery-saving-mode-gone/)

## So?

Inspired by [this answer](https://android.stackexchange.com/a/212866/259952)

I made this shitty app to control location_mode

With ugly user interface and a handy quick setting tile

```bash
# first we need grant permissions via adb
adb shell pm grant cc.yii2.batterysavingforlocation android.permission.WRITE_SECURE_SETTINGS

# then we can check if we done right
adb shell dumpsys package cc.yii2.batterysavingforlocation
```

Enjoy~

## Screenshots

![main](https://user-images.githubusercontent.com/5501843/68833203-de035e80-06ed-11ea-904e-652bfac30dc3.png)
![tile](https://user-images.githubusercontent.com/5501843/68833235-f07d9800-06ed-11ea-96d5-5ddac0481d09.png)

## Disclaimer

```c
#include <std_disclaimer.h>
/*
 *
 * We are not responsible for bricked devices, dead SD cards,
 * thermonuclear war, or you getting fired because the alarm app failed. Please
 * do some research if you have any concerns about features included in this app
 * before install it! YOU are choosing to make these modifications, and if
 * you point the finger at us for messing up your device, we will laugh at you.
 *
 */
```
