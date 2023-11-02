package de.leon.mymangaapp.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.NonNull;

@lombok.Data
public class DbVolume extends DbData {

    @NonNull
    private String seriesRowId;
    private Integer volumeNumber;
    @NonNull
    private Integer edition;
    @NonNull
    private Double price;
    @NonNull
    private LocalDate releaseDate;
    @NonNull
    private boolean read = false;
    @NonNull
    private String note;

    public DbVolume(Integer rowNumber, String rowId, String userEmail, String seriesRowId, Integer volumeNumber, Integer edition, Double price, LocalDate releaseDate, boolean read, String note) {
        super(rowNumber, rowId, userEmail);
        this.seriesRowId = seriesRowId;
        this.volumeNumber = volumeNumber;
        this.edition = edition;
        this.price = price;
        this.releaseDate = releaseDate;
        this.read = read;
        this.note = note;
    }

    public DbVolume(@NonNull Integer rowNumber, @NonNull String rowId, @NonNull String userEmail, @NonNull String seriesRowId, Integer volumeNumber, @NonNull Integer edition, @NonNull Double price, @NonNull LocalDate releaseDate, @NonNull boolean read) {
        this(rowNumber, rowId, userEmail, seriesRowId, volumeNumber, edition, price, releaseDate, read, "");
    }

    public String getSeries() {
        return seriesRowId;
    }

    public Integer getVolumeNumber() {
        return volumeNumber;
    }

    public Integer getEdition() {
        return edition;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public boolean isRead() {
        return read;
    }

    public String getNote() {
        return note;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public static DbVolume fromJsonObject(JSONObject object) throws JSONException {
        return new DbVolume(
                object.getInt("_RowNumber"),
                object.getString("Row ID"),
                object.getString("UserEmail"),
                object.getString("Reihe"),
                object.getInt("Band"),
                object.getInt("Auflage"),
                Double.parseDouble(object.getString("Preis")),
                LocalDate.parse(object.getString("Erscheinungstag"), DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                dbBoolToBoolean(object.getString("Gelesen")),
                object.getString("Anmerkung")
        );
    }

    private static Boolean dbBoolToBoolean(String dbBool) {
        if (dbBool.toLowerCase().equals("y")) {
            return true;
        } else if (dbBool.toLowerCase().equals("n")) {
            return false;
        } else {
            throw new UnsupportedOperationException("UNKNOWN BOOL \"" + dbBool + "\"");
        }
    }
}
