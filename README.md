# EasyNotify
An Android Library to send Firebase notifications to users easily.

# Demo

![With empty EditText](https://github.com/iammannan/EasyNotifyLibProject/blob/master/demo1.png "With empty EditText")
![Completed all EditText](https://github.com/iammannan/EasyNotifyLibProject/blob/master/demo2.png "Completed all EditText")
![Final Result](https://github.com/iammannan/EasyNotifyLibProject/blob/master/demo3.png "Final Result")
![Demo Video](https://github.com/iammannan/EasyNotifyLibProject/blob/master/demo4.png "Demo Video")

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
	        compile 'com.github.iammannan:EasyNotifyLibProject:1.1'
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
   or
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
  * Full Code - Example
  ```java
    	EasyNotify easyNotify = new EasyNotify(api_key.getText().toString());
        easyNotify.setSendBy(EasyNotify.TOPIC);
        easyNotify.setTopic(topic.getText().toString());
        easyNotify.setTitle(title.getText().toString());
        easyNotify.setBody(body.getText().toString());
        easyNotify.setClickAction(click_action.getText().toString());
        easyNotify.setSound(sound.getText().toString());
        easyNotify.nPush();
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


