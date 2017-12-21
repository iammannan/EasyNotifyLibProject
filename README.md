# EasyNotify
An Android Library to send Firebase notifications to users easily.

# Demo



# Download

* Step 1. Add it in your root build.gradle at the end of repositories:
```java
    allprojects {
        repositories {
          ...
          maven { url 'https://jitpack.io' }
        }
    }
```
* Step 2. Add the dependency
```java
    dependencies {
	        compile 'com.github.iammannan:EasyNotifyLibProject:1.0'
	}
```

# EasyNotify
* Initialize
```java
    EasyNotify easyNotify = new EasyNotify("YOUR_APP_API_KEY");
```
* Send by TOPIC or TOKEN
```java
   easyNotify.setSendBy(EasyNotify.TOPIC);
   easyNotify.setSendBy(EasyNotify.TOKEN);
```
 * IF Send by TOPIC
 ```java
     easyNotify.setTopic("YOUR_TOPIC");
 ```
 * IF Send by TOKEN
 ```java
     easyNotify.setToken("YOUR_FIREBASE_TOKEN");
 ```
 * IF Send by TOKEN
 ```java
     easyNotify.setToken("YOUR_FIREBASE_TOKEN");
 ```
 * Notification Optional Parameters
 ```java
        easyNotify.setTitle("YOUR_TITLE_STRING");
        easyNotify.setBody("YOUR_BODY_STRING");
        easyNotify.setClickAction("YOUR_CLICK_ACTION");
        easyNotify.setSound("default");
 ```
 * Push your Notificaton to Firebase server
 ```java
     easyNotify.nPush();
 ```
 * Set EasyNotify Listener, Check your push request success or not.
 ```java
   easyNotify.setEasyNotifyListener(new EasyNotify.EasyNotifyListener() {
            @Override
            public void onNotifySuccess(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNotifyError(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }

        });
 ``` 


