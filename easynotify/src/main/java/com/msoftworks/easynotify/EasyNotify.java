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

    public void setNtitle(String ntitle) {
        this.ntitle = ntitle;
    }

    public void setNbody(String nbody) {
        this.nbody = nbody;
    }

    public void setNclick_action(String nclick_action) {
        this.nclick_action = nclick_action;
    }

    public void setNtopic(String ntopic) {
        this.ntopic = ntopic;
    }

    public void setAPI_KEY(String API_KEY) {
        this.API_KEY = API_KEY;
    }

    public void setNsound(String nsound) {
        this.nsound = nsound;
    }

    private String ntitle;
    private String nbody;
    private String nclick_action;
    private String ntopic;
    private String nsound;
    private String API_KEY;

    private void sendNotification() throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse(FireConst.APP_TYPE_JSON);

        JSONObject notificationObject = new JSONObject();
        JSONObject dataObject = new JSONObject();
        JSONObject bodyObject = new JSONObject();

        notificationObject
                .put("title",ntitle)
                .put("body",nbody)
                .put("sound",nsound);

        dataObject.put("message","newMessage");

        bodyObject
                .put("to","/topics/"+ntopic)
                .put("notification",notificationObject)
                .put("data",dataObject);

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
        public void onNotifySuccess(String SuccessResponse);

        public void onNotifyError(String ErrorResponse);
    }

    private EasyNotifyListener listener;
    private Response response;

    public EasyNotify() {
        this.listener = null;
    }

    public void setEasyNotifyListener(EasyNotifyListener listener)
    {
        this.listener=listener;
    }

}