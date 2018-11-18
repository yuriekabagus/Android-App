package storabot.suka.dev.projektest.API;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by lenovo on 17/05/2017.
 */


public class ApiSendPost {
    /** The default socket timeout in milliseconds */
    public static final int DEFAULT_TIMEOUT_MS = 2500;

    /** The default number of retries */
    public static final int DEFAULT_MAX_RETRIES = 1;

    /** The default backoff multiplier */
    public static final float DEFAULT_BACKOFF_MULT = 1f;
    public static void sendPost(final String url, final Map<String, String> data, final Context context, final ApiRequest target) {
        final RequestQueue rq = Volley.newRequestQueue(context);
        StringRequest strReq =  new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                target.onResponseComplete(response);
                rq.stop();
                return;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.getClass() == NoConnectionError.class){
                    Log.e("error","no1");
                    target.onRequestTimeOut("Please check your internet connection");
                }else if(error.getClass() == TimeoutError.class){
                    Log.e("error","no2");
                    target.onRequestTimeOut("Request time out, please try again");
                }else if(error.getClass() == ServerError.class){
                 //  Feedback.SendFeedback("");
                    Log.e("error","no3");
                    target.onRequestTimeOut("There is unknown error, please try again");
                }else{
                    Log.e("error","no4");
                    target.onRequestTimeOut("There is unknown error, please try again");
                }
                rq.stop();
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=UTF-8");
                params.put("access-token", "ggolvSv4UpUH_a9Qk5x5KAC2YudbptpltVYZ");
                params.put("_format", "json");

                return params;
            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return data;
            }
            @Override
            public RetryPolicy getRetryPolicy() {
                setSequence(0);
                // here you can write a custom retry policy and return it
                return super.getRetryPolicy();
            }
        };
        strReq.setShouldCache(false);
        // Wait 30 seconds and don't retry more than once
        strReq.setRetryPolicy(new DefaultRetryPolicy(15000, 0,
                10000));
        rq.add(strReq);

    }

    public static void sendGET(final String url, final Map<String, String> data, final Context context, final ApiRequest target) {
        final RequestQueue rq = Volley.newRequestQueue(context);
        StringRequest strReq =  new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                target.onResponseComplete(response);
                rq.stop();
                return;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.getClass() == NoConnectionError.class){
                    Log.e("error","no1");
                    target.onRequestTimeOut("Please check your internet connection");
                }else if(error.getClass() == TimeoutError.class){
                    Log.e("error","no2");
                    target.onRequestTimeOut("Request time out, please try again");
                }else if(error.getClass() == ServerError.class){
                    //  Feedback.SendFeedback("");
                    Log.e("error","no3");
                    target.onRequestTimeOut("There is unknown error, please try again");
                }else{
                    Log.e("error","no4");
                    target.onRequestTimeOut("There is unknown error, please try again");
                }
                rq.stop();
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("access-token", "ggolvSv4UpUH_a9Qk5x5KAC2YudbptpltVYZ");
                params.put("_format", "json");

                return params;
            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return data;
            }
            @Override
            public RetryPolicy getRetryPolicy() {
                setSequence(0);
                // here you can write a custom retry policy and return it
                return super.getRetryPolicy();
            }
        };
        strReq.setShouldCache(false);
        // Wait 30 seconds and don't retry more than once
        strReq.setRetryPolicy(new DefaultRetryPolicy(15000, 0,
                10000));
        rq.add(strReq);

    }
    public static void sendPUT(final String url, final Map<String, String> data, final Context context, final ApiRequest target) {
        final RequestQueue rq = Volley.newRequestQueue(context);
        StringRequest strReq =  new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                target.onResponseComplete(response);
                rq.stop();
                return;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.getClass() == NoConnectionError.class){
                    Log.e("error","no1");
                    target.onRequestTimeOut("Please check your internet connection");
                }else if(error.getClass() == TimeoutError.class){
                    Log.e("error","no2");
                    target.onRequestTimeOut("Request time out, please try again");
                }else if(error.getClass() == ServerError.class){
                    //  Feedback.SendFeedback("");
                    Log.e("error","no3");
                    target.onRequestTimeOut("There is unknown error, please try again");
                }else{
                    Log.e("error","no4");
                    target.onRequestTimeOut("There is unknown error, please try again");
                }
                rq.stop();
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=UTF-8");
                params.put("access-token", "ggolvSv4UpUH_a9Qk5x5KAC2YudbptpltVYZ");
                params.put("_format", "json");

                return params;
            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return data;
            }
            @Override
            public RetryPolicy getRetryPolicy() {
                setSequence(0);
                // here you can write a custom retry policy and return it
                return super.getRetryPolicy();
            }
        };
        strReq.setShouldCache(false);
        // Wait 30 seconds and don't retry more than once
        strReq.setRetryPolicy(new DefaultRetryPolicy(15000, 0,
                10000));
        rq.add(strReq);

    }
    public static void sendDELETE(final String url, final Map<String, String> data, final Context context, final ApiRequest target) {
        final RequestQueue rq = Volley.newRequestQueue(context);
        StringRequest strReq =  new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                target.onResponseComplete(response);
                rq.stop();
                return;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.getClass() == NoConnectionError.class){
                    Log.e("error","no1");
                    target.onRequestTimeOut("Please check your internet connection");
                }else if(error.getClass() == TimeoutError.class){
                    Log.e("error","no2");
                    target.onRequestTimeOut("Request time out, please try again");
                }else if(error.getClass() == ServerError.class){
                    //  Feedback.SendFeedback("");
                    Log.e("error","no3");
                    target.onRequestTimeOut("There is unknown error, please try again");
                }else{
                    Log.e("error","no4");
                    target.onRequestTimeOut("There is unknown error, please try again");
                }
                rq.stop();
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=UTF-8");
                params.put("access-token", "ggolvSv4UpUH_a9Qk5x5KAC2YudbptpltVYZ");
                params.put("_format", "json");

                return params;
            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return data;
            }
            @Override
            public RetryPolicy getRetryPolicy() {
                setSequence(0);
                // here you can write a custom retry policy and return it
                return super.getRetryPolicy();
            }
        };
        strReq.setShouldCache(false);
        // Wait 30 seconds and don't retry more than once
        strReq.setRetryPolicy(new DefaultRetryPolicy(15000, 0,
                10000));
        rq.add(strReq);

    }


    public interface ResponseFBListener{
        public String onResponseComplete(String response);
        public void onRequestTimeOut(String errMessage);
    }

    public static String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            int len = messageDigest.length;
            StringBuilder sb = new StringBuilder( len << 1);
            for (int i = 0; i < len; i++) {
                sb.append(Character.forDigit((messageDigest[i] & 0xf0) >> 4, 16));
                sb.append(Character.forDigit(messageDigest[i] & 0x0f, 16));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            //e.printStackTrace();
        }
        return "";
    }
}

