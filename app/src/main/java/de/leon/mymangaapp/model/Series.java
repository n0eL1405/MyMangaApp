package de.leon.mymangaapp.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import de.leon.mymangaapp.model.db.DbSeries;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@lombok.Data
@Getter
public class Series {

    @NonNull
    private ID id;
    @NonNull
    private String name;
    @NonNull
    private List<String> genre;
    @NonNull
    private Author author;
    @NonNull
    private Publisher publisher;
    @NonNull
    private Status status = Status.RUNNING;
    @NonNull
    private List<Volume.ID> relatedVolumes;

    public Series(
            @NonNull ID id,
            @NonNull String name,
            @NonNull List<String> genre,
            @NonNull Author author,
            @NonNull Publisher publisher,
            @NonNull Status status,
            @NonNull List<Volume.ID> relatedVolumes) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.author = author;
        this.publisher = publisher;
        this.status = status;
        this.relatedVolumes = relatedVolumes;
    }

    public String getGenreAsString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < genre.size() - 1; i++) {
            builder.append(genre.get(i)).append("/");
        }
        builder.append(genre.get(genre.size() - 1));

        return builder.toString();
    }

    public void addRelatedVolume(Volume.ID id) {
        relatedVolumes.add(id);
    }

    public void removeRelatedVolume(Volume.ID id) {
        relatedVolumes = relatedVolumes.stream().filter(currentId -> !currentId.equals(id)).collect(Collectors.toList());
    }

    public static Series fromDbObject(DbSeries series, Author author, Publisher publisher) {
        return new Series(
                ID.of(series.getRowId()),
                series.getName(),
                series.getGenre(),
                author,
                publisher,
                Status.fromDbString(series.getStatus()),
                series.getRelatedVolumes()
                        .stream()
                        .map(Volume.ID::of)
                        .collect(Collectors.toList())
        );
    }

    public enum Status {
        RUNNING, FINISHED, CANCELLED;

        public static Status fromDbString(String dbStatus) {
            switch (dbStatus) {
                case "Laufend":
                    return RUNNING;
                case "Abgeschlossen":
                    return FINISHED;
                case "Abgebrochen":
                    return CANCELLED;
                default:
                    throw new RuntimeException("UNKNOWN ENUM \"" + dbStatus + "\"");
            }
        }

        public String toDbString() {
            switch (this) {
                default:
                case RUNNING:
                    return "Laufend";
                case FINISHED:
                    return "Abgeschlossen";
                case CANCELLED:
                    return "Abgebrochen";
            }
        }
    }

    @AllArgsConstructor(staticName = "of")
    public static class ID {
        @NonNull
        private String rawValue;
    }
}
