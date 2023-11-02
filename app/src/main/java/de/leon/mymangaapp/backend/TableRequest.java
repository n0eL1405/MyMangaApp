package de.leon.mymangaapp.backend;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import lombok.NonNull;

public class TableRequest extends JsonArrayRequest {

    private final static String URL_TEMPLATE = "https://api.appsheet.com/api/v2/apps/{appId}/tables/{tableName}/Action?applicationAccessKey={accessKey}";

    private final JSONObject parameter;

    public TableRequest(@NonNull String appId, @NonNull String tableName, @NonNull String accessKey, @NonNull JSONObject parameter, @NonNull Response.Listener<VolleyError> onError, @NonNull Response.Listener<JSONArray> listener) {
        super(Request.Method.POST, URL_TEMPLATE.replace("{appId}", appId).replace("{tableName}", tableName).replace("{accessKey}", accessKey),
                null, listener, onError::onResponse);
        this.parameter = parameter;
    }

    @Override
    public byte[] getBody() {
        return parameter.toString().getBytes();
    }

    @Override
    public String getBodyContentType() {
        return "application/json; charset=utf-8";
    }
}
