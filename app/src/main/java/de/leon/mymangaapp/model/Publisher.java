package de.leon.mymangaapp.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import de.leon.mymangaapp.model.db.DbPublisher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@lombok.Data
@Getter
public class Publisher {

    @NonNull
    private ID id;
    @NonNull
    private String name;
    @NonNull
    private List<Series.ID> relatedSeries;

    public Publisher(
            @NonNull ID id,
            @NonNull String name,
            @NonNull List<Series.ID> relatedSeries) {
        this.id = id;
        this.name = name;
        this.relatedSeries = relatedSeries;
    }

    public void addRelatedSeries(Series.ID seriesId) {
        relatedSeries.add(seriesId);
    }

    public void removeRelatedSeries(Series.ID seriesId) {
        relatedSeries = relatedSeries.stream().filter(id -> !id.equals(seriesId)).collect(Collectors.toList());
    }

    public static Publisher fromDbObject(DbPublisher publisher) {
        return new Publisher(
                ID.of(publisher.getRowId()),
                publisher.getName(),
                publisher.getRelatedSeries()
                        .stream()
                        .map(Series.ID::of)
                        .collect(Collectors.toList())
        );
    }

    @AllArgsConstructor(staticName = "of")
    public static class ID {
        @NonNull
        private String rawValue;
    }
}
