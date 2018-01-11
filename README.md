# T21Log

[![Bintray](https://api.bintray.com/packages/worldline-spain/maven/t21-log/images/download.svg)](https://bintray.com/worldline-spain/maven/t21-log/_latestVersion)


## Why
- Enable or disable the logcat depending on the BuildConfig.
- Strings are not concatenated with the operator '+', we use `StringBuilder` class
- Send the log cat by email (or whatever)

## How to add to your project
- Maven
```xml
<dependency>
   <groupId>com.tempos21.android.commons.utils</groupId>
   <artifactId>t21-log</artifactId>
   <version>2.0.0</version>
   <type>pom</type>
 </dependency>
```
- Gradle
```xml
compile 'com.tempos21.android.commons.utils:t21-log:2.0.0'
```
- Ivy
```xml
<dependency org='com.tempos21.android.commons.utils' name='t21-log' rev='2.0.0'>
  <artifact name='t21-log' ext='pom' ></artifact>
</dependency>
```

## How to use

### Setting up

In your application class or your launcher activity (or main/base activity) you should call this method:

* `T21Log.initialize(String, boolean, boolean, Context);`
	* First is the tag of your application. This tag will be in the whole app.
	* Second is to set enabled or disabled the log to console (Android Monitor).
	* Third is to set enabled or disabled the log to file.
	* Fourth is your application's context.

	If you don't call this method then nothing will be printed.
	Is a good practice call like this `T21Log.initialize("AppName", BuildConfig.DEBUG, BuildConfig.DEBUG, context);`, in order to avoid print log messages in release versions.
    
### Printing logs

Some examples if you set your tag "[SAMPLE_APP]":

* `T21Log.d("Hello")`; --> 'D/[SAMPLE_APP]: Hello'

* `T21Log.d(CLASS_TAG, "Hello")`; --> 'D/[SAMPLE_APP]: [MainActivity] Hello' (if CLASS_TAG == '[MainActivity]')

* `T21Log.d(CLASS_TAG, "onCreate", "adding messages", 3, true, "separated by commas");` --> 'D/[SAMPLE_APP]: [MainActivity] onCreate adding messages 3 true separated by commas' (if CLASS_TAG == '[MainActivity]')

### Log file location

If you enable log to file (set to **true** the third parameter in `initialize()` method), a new file is created to store the log. This file, which name is **T21Log.log** is located under the application files' folder, in a folder called **logs**. You can get this File calling `T21Log.getLogFile()`.

## Sample

Write a line in the log monitor and send a file with the content of the log.

## Contributing to the project

Feel free to report any issues or suggest new features.

## License

Copyright 2016 Worldline Iberia

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
