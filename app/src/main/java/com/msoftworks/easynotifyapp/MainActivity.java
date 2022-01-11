package com.msoftworks.easynotifyapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.msoftworks.easynotify.EasyNotify;

public class MainActivity extends AppCompatActivity {

    EditText title,body,topic,sound,api_key,click_action;
    Button push;
    public static final String API_KEY = "AAAAd-IeGNU:APA91bF5KAFaNuehf-V6myfaH3OWOg7rLJb9wQdBkb9EGE3fuAvgvgkulhXWcXMg4E-drpB4xFTaofC8xrxI-pwmr913C2cvdo0UVn6XRyaFmUlTeSm0Mki2Mo82MiJcla4-DgigdvGD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        title=findViewById(R.id.ntitle);
        body=findViewById(R.id.nbody);
        topic=findViewById(R.id.ntopic);
        sound=findViewById(R.id.nsound);
        api_key=findViewById(R.id.api_key);
        click_action=findViewById(R.id.nclick_action);

        push=findViewById(R.id.push);

        api_key.setText(API_KEY);
        topic.setText("ALL");

        push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(title.getText().length()==0||body.getText().length()==0||topic.getText().length()==0||
                        sound.getText().length()==0||api_key.getText().length()==0||click_action.getText().length()==0)
                {
                    Snackbar.make(v, "Please fill all the textBoxes", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else   callEasyNotify();

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void callEasyNotify() {
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
    }
}
