package de.leon.mymangaapp.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.NonNull;

@lombok.Data
public class DbPublisher extends DbData {

    @NonNull
    private String name;
    private List<String> relatedSeries;

    public DbPublisher(Integer rowNumber, String rowId, String userEmail, String name, List<String> relatedSeries) {
        super(rowNumber, rowId, userEmail);
        this.name = name;
        this.relatedSeries = relatedSeries;
    }

    public String getName() {
        return name;
    }

    public List<String> getRelatedSeries() {
        return relatedSeries;
    }

    public void addRelatedSeries(String seriesId) {
        relatedSeries.add(seriesId);
    }

    public void removeRelatedSeries(String seriesId) {
        relatedSeries = relatedSeries.stream().filter(id -> !Objects.equals(id, seriesId)).collect(Collectors.toList());
    }

    public static DbPublisher fromJsonObject(JSONObject object) throws JSONException {
        return new DbPublisher(
                object.getInt("_RowNumber"),
                object.getString("Row ID"),
                object.getString("UserEmail"),
                object.getString("Name"),
                List.of(object.getString("Related Reihens").split(" , "))
        );
    }
}
