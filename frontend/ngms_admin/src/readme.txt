set environmental paths in my pc
ng version
Angular CLI: 14.1.3
Node: 14.15.4
Package Manager: npm 8.13.2
OS: win32 x64

npm install @ionic/angular@6
ionic -v
6.20.1

npm install
npm i @capacitor/android
npm install -g cordova-res for splash
ionic capacitor add android / ionic capacitor add ios (optional)
ionic capacitor build
ionic capacitor run
? What platform would you like to run? android
? Which device would you like to target?
Pixel 2 API 30 (emulator) (Pixel_5_API_30)
Pixel 3 API 30 (emulator) (Pixel_5_API_30)
> Pixel 5 API 30 (emulator) (Pixel_5_API_30)

ionic capacitor build --release android
ionic capacitor build --prod
ionic capacitor --prod
ionic capacitor build

ionic capacitor update
ionic capacitor add
ionic capacitor copy android

npm install -g @ionic/cli
npm uninstall -g ionic
ionic start
ionic info


//-------------------------------
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.ionic6.project">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">

        <activity
            android:configChanges="orientation|keyboardHidden|keyboard|screenSize|locale|smallestScreenSize|screenLayout|uiMode"
            android:name="com.ionic6.project.MainActivity"
            android:label="@string/title_activity_main"
            android:theme="@style/AppTheme.NoActionBarLaunch"
            android:launchMode="singleTask">

            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>

        </activity>

        <provider
            android:name="androidx.core.content.FileProvider"
            android:authorities="${applicationId}.fileprovider"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/file_paths"></meta-data>
        </provider>
    </application>

    <!-- Permissions -->

    <uses-permission android:name="android.permission.INTERNET" />
<!-- Geolocation API -->
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-feature android:name="android.hardware.location.gps" />


</manifest>
