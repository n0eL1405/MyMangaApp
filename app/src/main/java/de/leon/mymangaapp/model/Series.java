package de.leon.mymangaapp.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.NonNull;

@lombok.Data
public class Series extends Data {

    private enum Status {
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
    private List<String> relatedVolumes;

    public Series(String id, String name, List<String> genre, Author author, Publisher publisher, Status status, List<String> relatedVolumes) {
        super(id);
        this.name = name;
        this.genre = genre;
        this.author = author;
        this.publisher = publisher;
        this.status = status;
        this.relatedVolumes = relatedVolumes;
    }

    public String getName() {
        return name;
    }

    public List<String> getGenre() {
        return genre;
    }

    public String getGenreAsString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < genre.size() - 1; i++) {
            builder.append(genre.get(i)).append("/");
        }
        builder.append(genre.get(genre.size() - 1));

        return builder.toString();
    }

    public Author getAuthor() {
        return author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<String> getRelatedSeries() {
        return relatedVolumes;
    }

    public void addRelatedSeries(String seriesId) {
        relatedVolumes.add(seriesId);
    }

    public void removeRelatedSeries(String seriesId) {
        relatedVolumes = relatedVolumes.stream().filter(id -> !Objects.equals(id, seriesId)).collect(Collectors.toList());
    }

    public static Series fromDbObject(DbSeries series, Author author, Publisher publisher) {
        return new Series(series.getRowId(), series.getName(), series.getGenre(), author, publisher, Status.fromDbString(series.getStatus()), series.getRelatedVolumes());
    }
}
