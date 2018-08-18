package com.msoftworks.easynotify;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Mannar Mannan V on 12/14/2017.
 */

public class EasyNotify {

    private String ntitle = "title";
    private String nbody = "body";
    private String nclick_action = "MAINACTIVITY";
    private String ntopic = "allDevices";
    private String ntoken =  "A0CALSKNLKAS45CLKSC655";
    private String nsound = "default";
    private String sendBy = TOPIC;
    private String API_KEY;

    public static String TOPIC = "SEND_BY_TOPIC";
    public static String TOKEN = "SEND_BY_TOKEN";

    private JSONObject notificationObject;
    private JSONObject dataObject;
    private JSONObject bodyObject;

    public void setTitle(String ntitle) {
        this.ntitle = ntitle;
    }

    public void setBody(String nbody) {
        this.nbody = nbody;
    }

    public void setClickAction(String nclick_action) {
        this.nclick_action = nclick_action;
    }

    public void setTopic(String ntopic) {
        this.ntopic = ntopic;
    }

    public void setToken(String ntoken) {
        this.ntoken = ntoken;
    }

    public void setSound(String nsound) {
        this.nsound = nsound;
    }

    public void setSendBy(String sendBy) {
        this.sendBy = sendBy;
    }

    private void sendNotification() throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse(FireConst.APP_TYPE_JSON);

        notificationObject = new JSONObject();
        dataObject = new JSONObject();
        bodyObject = new JSONObject();

        notificationObject
                .put("title",ntitle)
                .put("body",nbody)
                .put("sound",nsound);

        dataObject.put("message","newMessage");

        if(sendBy==TOPIC)
        {
            bodyObject
                    .put("to","/topics/"+ntopic)
                    .put("notification",notificationObject)
                    .put("data",dataObject);
        }
        if(sendBy==TOKEN)
        {
            bodyObject
                    .put("to",ntoken)
                    .put("notification",notificationObject)
                    .put("data",dataObject);
        }

        RequestBody body = RequestBody.create(mediaType, bodyObject.toString());

        Request request = new Request.Builder()
                .url(FireConst.HOST_URL)
                .post(body)
                .addHeader(FireConst.CONTENT_TYPE, FireConst.APP_TYPE_JSON)
                .addHeader(FireConst.AUTHORIZATION,"key="+API_KEY)
                .build();

        response = client.newCall(request).execute();
    }

    public void nPush()
    {
        new AsyncTask<Void,Void,Void>()
        {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    sendNotification();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                String rc = null;
                try {
                    rc = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(response.code()==200)
                {
                    listener.onNotifySuccess(rc);
                }
                else
                {
                    listener.onNotifyError(rc);
                }
                super.onPostExecute(aVoid);
            }
        }.execute();
    }

    public interface EasyNotifyListener
    {
        public void onNotifySuccess(String MessageId);

        public void onNotifyError(String ErrorResponse);
    }

    private EasyNotifyListener listener;
    private Response response;

    public EasyNotify(String API_KEY) {
        this.listener = null;
        this.API_KEY = API_KEY;
    }

    public void setEasyNotifyListener(EasyNotifyListener listener)
    {
        this.listener=listener;
    }

}