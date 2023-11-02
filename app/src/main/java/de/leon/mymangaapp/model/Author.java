package de.leon.mymangaapp.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@lombok.Data
public class Author extends Data {
    @NonNull
    private String name;
    private List<String> relatedSeries;

    public Author(String id, String name, List<String> relatedSeries) {
        super(id);
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

    public static Author fromDbObject(DbAuthor author) {
        return new Author(author.getRowId(), author.getName(), author.getRelatedSeries());
    }
}
