package de.leon.mymangaapp.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.NonNull;

@lombok.Data
public class Publisher extends Data {

    @NonNull
    private String name;
    private List<String> relatedSeries;

    public Publisher(String id, String name, List<String> relatedSeries) {
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

    public static Publisher fromDbObject(DbPublisher publisher) {
        return new Publisher(publisher.getRowId(), publisher.getName(), publisher.getRelatedSeries());
    }
}
