package www.aql.com.utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jason on 2016/5/23.
 */
public class CommenJsonParser {
    private OnParseJsonListener listener;

    public CommenJsonParser(String json, OnParseJsonListener listener) {
        this.listener = listener;
        parseJson(json);
    }

    private void parseJson(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonObject_header = jsonObject.optJSONObject("Header");
            Boolean isSuccess = jsonObject_header.optBoolean("IsSuccess");
            boolean body = jsonObject.optBoolean("HotBody");
            if (body && isSuccess) {
                listener.onParse(true);
            } else {
                listener.onParse(false);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public interface OnParseJsonListener {
        void onParse(boolean isOk);
    }
}

