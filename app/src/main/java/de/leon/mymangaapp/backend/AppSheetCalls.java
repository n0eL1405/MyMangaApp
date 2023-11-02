package de.leon.mymangaapp.backend;


import android.content.Context;

import androidx.annotation.NonNull;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import de.leon.mymangaapp.model.DbAuthor;
import de.leon.mymangaapp.model.DbPublisher;
import de.leon.mymangaapp.model.DbSeries;
import de.leon.mymangaapp.model.DbVolume;
import de.leon.mymangaapp.ui.PropertiesUtil;

public class AppSheetCalls {

    private enum Table {
        AUTHOR,
        PUBLISHER,
        SERIES,
        VOLUME;

        @NonNull
        public String getName() {
            switch (this) {
                case AUTHOR:
                    return "Authoren";
                case SERIES:
                    return "Reihen";
                case VOLUME:
                    return "Baende";
                case PUBLISHER:
                    return "Verlaege";
            }
            return "";
        }
    }

    private final RequestQueue requestQueue;

    private final String appId;
    private final String accessKey;


    public AppSheetCalls(Context context) throws IOException {
        this.appId = PropertiesUtil.getSecret(context, PropertiesUtil.SecretKey.APP_ID);
        this.accessKey = PropertiesUtil.getSecret(context, PropertiesUtil.SecretKey.APP_ACCESS_KEY);
        this.requestQueue = Volley.newRequestQueue(context);
    }

    // ### AUTHOR ###
    public void getAllAuthor(Response.Listener<List<DbAuthor>> onResponse, Response.Listener<VolleyError> onError) {

        List<DbAuthor> authorList = new LinkedList<>();

        JSONObject parameters;
        try {
            parameters = new JSONObject();
            parameters.put("Action", "Find");

            JSONObject props = new JSONObject();
            props.put("Locale", "de-DE");

            parameters.put("Properties", props);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonArrayRequest jsonObjectRequest = new TableRequest(appId, Table.AUTHOR.getName(), accessKey, parameters, onError,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            authorList.add(DbAuthor.fromJsonObject(response.getJSONObject(i)));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    onResponse.onResponse(authorList);
                });

        requestQueue.add(jsonObjectRequest);
        requestQueue.start();
    }

    public void getAuthorById(String id, Response.Listener<Optional<DbAuthor>> onResponse, Response.Listener<VolleyError> onError) {

        JSONObject parameter;
        try {
            parameter = new JSONObject();
            parameter.put("Action", "Find");

            JSONObject props = new JSONObject();
            props.put("Locale", "de-DE");

            parameter.put("Properties", props);

            parameter.put("Selector", "Filter(" + Table.AUTHOR.getName() + ", [Row ID] = " + id + ")");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonArrayRequest request = new TableRequest(appId, Table.AUTHOR.getName(), accessKey, parameter, onError,
                response -> {
                    if (response.length() >= 1) {
                        try {
                            onResponse.onResponse(Optional.of(DbAuthor.fromJsonObject(response.optJSONObject(0))));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        requestQueue.add(request);
        requestQueue.start();
    }

    // ### PUBLISHER ###
    public void getAllPublisher(Response.Listener<List<DbPublisher>> onResponse, Response.Listener<VolleyError> onError) {

        List<DbPublisher> list = new LinkedList<>();

        JSONObject parameters;
        try {
            parameters = new JSONObject();
            parameters.put("Action", "Find");

            JSONObject props = new JSONObject();
            props.put("Locale", "de-DE");

            parameters.put("Properties", props);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonArrayRequest jsonObjectRequest = new TableRequest(appId, Table.PUBLISHER.getName(), accessKey, parameters, onError,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            list.add(DbPublisher.fromJsonObject(response.getJSONObject(i)));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    onResponse.onResponse(list);
                });

        requestQueue.add(jsonObjectRequest);
        requestQueue.start();
    }

    public void getPublisherById(String id, Response.Listener<Optional<DbPublisher>> onResponse, Response.Listener<VolleyError> onError) {

        JSONObject parameter;
        try {
            parameter = new JSONObject();
            parameter.put("Action", "Find");

            JSONObject props = new JSONObject();
            props.put("Locale", "de-DE");

            parameter.put("Properties", props);

            parameter.put("Selector", "Filter(" + Table.PUBLISHER.getName() + ", [Row ID] = " + id + ")");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonArrayRequest request = new TableRequest(appId, Table.PUBLISHER.getName(), accessKey, parameter, onError,
                response -> {
                    if (response.length() >= 1) {
                        try {
                            onResponse.onResponse(Optional.of(DbPublisher.fromJsonObject(response.optJSONObject(0))));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        requestQueue.add(request);
        requestQueue.start();
    }

    // ### SERIES ###
    public void getAllSeries(Response.Listener<List<DbSeries>> onResponse, Response.Listener<VolleyError> onError) {

        List<DbSeries> list = new LinkedList<>();

        JSONObject parameters;
        try {
            parameters = new JSONObject();
            parameters.put("Action", "Find");

            JSONObject props = new JSONObject();
            props.put("Locale", "de-DE");

            parameters.put("Properties", props);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonArrayRequest jsonObjectRequest = new TableRequest(appId, Table.SERIES.getName(), accessKey, parameters, onError,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            list.add(DbSeries.fromJsonObject(response.getJSONObject(i)));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    onResponse.onResponse(list);
                });

        requestQueue.add(jsonObjectRequest);
        requestQueue.start();
    }

    public void getSeriesById(String id, Response.Listener<Optional<DbSeries>> onResponse, Response.Listener<VolleyError> onError) {

        JSONObject parameter;
        try {
            parameter = new JSONObject();
            parameter.put("Action", "Find");

            JSONObject props = new JSONObject();
            props.put("Locale", "de-DE");

            parameter.put("Properties", props);

            parameter.put("Selector", "Filter(" + Table.SERIES.getName() + ", [Row ID] = " + id + ")");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonArrayRequest request = new TableRequest(appId, Table.SERIES.getName(), accessKey, parameter, onError,
                response -> {
                    if (response.length() >= 1) {
                        try {
                            onResponse.onResponse(Optional.of(DbSeries.fromJsonObject(response.optJSONObject(0))));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        requestQueue.add(request);
        requestQueue.start();
    }

    // ### VOLUME ###
    public void getAllVolumes(Response.Listener<List<DbVolume>> onResponse, Response.Listener<VolleyError> onError) {

        List<DbVolume> list = new LinkedList<>();

        JSONObject parameters;
        try {
            parameters = new JSONObject();
            parameters.put("Action", "Find");

            JSONObject props = new JSONObject();
            props.put("Locale", "de-DE");

            parameters.put("Properties", props);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonArrayRequest jsonObjectRequest = new TableRequest(appId, Table.VOLUME.getName(), accessKey, parameters, onError,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            list.add(DbVolume.fromJsonObject(response.getJSONObject(i)));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    onResponse.onResponse(list);
                });

        requestQueue.add(jsonObjectRequest);
        requestQueue.start();
    }

    public void getVolumeById(String id, Response.Listener<Optional<DbVolume>> onResponse, Response.Listener<VolleyError> onError) {

        JSONObject parameter;
        try {
            parameter = new JSONObject();
            parameter.put("Action", "Find");

            JSONObject props = new JSONObject();
            props.put("Locale", "de-DE");

            parameter.put("Properties", props);

            parameter.put("Selector", "Filter(" + Table.VOLUME.getName() + ", [Row ID] = " + id + ")");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonArrayRequest request = new TableRequest(appId, Table.VOLUME.getName(), accessKey, parameter, onError,
                response -> {
                    if (response.length() >= 1) {
                        try {
                            onResponse.onResponse(Optional.of(DbVolume.fromJsonObject(response.optJSONObject(0))));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        requestQueue.add(request);
        requestQueue.start();
    }
}
