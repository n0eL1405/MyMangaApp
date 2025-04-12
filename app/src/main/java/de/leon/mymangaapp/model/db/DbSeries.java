package de.leon.mymangaapp.model.db;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class DbSeries extends DbData {

    @NonNull
    private String name;
    @NonNull
    private List<String> genre;
    @NonNull
    private String authorRowId;
    @NonNull
    private String publisherRowId;
    @NonNull
    private String status;
    @NonNull
    private List<String> relatedVolumes;

    public DbSeries(Integer rowNumber, String rowId, String userEmail, String name, List<String> genre, String authorRowId, String publisherRowId, String status, List<String> relatedVolumes) {
        super(rowNumber, rowId, userEmail);
        this.name = name;
        this.genre = genre;
        this.authorRowId = authorRowId;
        this.publisherRowId = publisherRowId;
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

    public String getAuthorRowId() {
        return authorRowId;
    }

    public String getPublisher() {
        return publisherRowId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getRelatedVolumes() {
        return relatedVolumes;
    }

    public void addRelatedVolume(String seriesId) {
        relatedVolumes.add(seriesId);
    }

    public void removeRelatedVolume(String seriesId) {
        relatedVolumes = relatedVolumes.stream().filter(id -> !Objects.equals(id, seriesId)).collect(Collectors.toList());
    }

    public static DbSeries fromJsonObject(JSONObject object) throws JSONException {
        return new DbSeries(
                object.getInt("_RowNumber"),
                object.getString("Row ID"),
                object.getString("UserEmail"),
                object.getString("Name"),
                List.of(object.getString("Genre").split("/")),
                object.getString("Author"),
                object.getString("Verlag"),
                object.getString("Status"),
                List.of(object.getString("Related Reihens").split(" , "))
        );
    }
}
