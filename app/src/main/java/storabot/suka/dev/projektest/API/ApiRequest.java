package storabot.suka.dev.projektest.API;

import android.content.Context;

import java.util.Map;

/**
 * Created by lenovo on 17/05/2017.
 */

public abstract class ApiRequest implements ApiSendPost.ResponseFBListener{
    public ApiRequest(String url, Map<String, String> data, Context ctx,String Method){
        if(Method.equals("POST")){
            ApiSendPost.sendPost(url, data, ctx, this);
        }if(Method.equals("GET")){
            ApiSendPost.sendGET(url, data, ctx, this);
        }if(Method.equals("DELETE")){
            ApiSendPost.sendPUT(url, data, ctx, this);
        }if(Method.equals("PUT")){
            ApiSendPost.sendDELETE(url, data, ctx, this);
        }
    }
}
