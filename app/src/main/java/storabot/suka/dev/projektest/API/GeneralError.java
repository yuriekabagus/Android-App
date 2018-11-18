package storabot.suka.dev.projektest.API;

/**
 * Created by temp on 7/5/17.
 */

public interface GeneralError {
    public void onError(String errMessage);
    public void onRequestTimeOut(String errMessage);
}
