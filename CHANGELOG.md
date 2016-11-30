* CHANGELOG

** v1.0.8

Initialization method changed. Now you have to initialize in the following way:

- `T21Log.initialize(String, String);`
	* First is the tag of your application. This tag will be in the whole app.
	* Second is to set enabled or disabled the log. If you don't call this method then nothing will be printed. Is a good practice call like this `T21Log.initialize("AppName", BuildConfig.DEBUG);`, in order to avoid print log messages in release versions.

** v1.0.7

Clean AndroidManifest.xml of android:name="" and android:icon="", that can cause conflicts on some projects