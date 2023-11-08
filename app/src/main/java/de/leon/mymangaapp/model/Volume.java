package de.leon.mymangaapp.model;

import androidx.annotation.Nullable;

import java.time.LocalDate;

import lombok.NonNull;

@lombok.Data
public class Volume extends Data {

    @NonNull
    private String seriesId;
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

    public Volume(@NonNull String id, @NonNull String seriesId, @Nullable Integer volumeNumber, @NonNull Integer edition, @NonNull Double price, @NonNull LocalDate releaseDate, @NonNull boolean read, @NonNull String note) {
        super(id);
        this.seriesId = seriesId;
        this.volumeNumber = volumeNumber;
        this.edition = edition;
        this.price = price;
        this.releaseDate = releaseDate;
        this.read = read;
        this.note = note;
    }

    public Volume(@NonNull String id, @NonNull String seriesId, @Nullable Integer volumeNumber, @NonNull Integer edition, @NonNull Double price, @NonNull LocalDate releaseDate, @NonNull boolean read) {
        this(id, seriesId, volumeNumber, edition, price, releaseDate, read, "");
    }

    public String getSeriesId() {
        return seriesId;
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

    public boolean isRead() {
        return read;
    }

    public String getNote() {
        return note;
    }

    /**
     *  If the volumeNumber is null, return the note.
     *  <br>
     *  If a Series doesn't have normal Volumes but is like a collection, the note should contain the title of the manga.
     */
    public String getVolumeOrNote() {
        return volumeNumber != null ? "# " + volumeNumber : note;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public static Volume fromDbObject(DbVolume volume) {
        return new Volume(volume.getRowId(), volume.getSeries(), volume.getVolumeNumber(), volume.getEdition(), volume.getPrice(), volume.getReleaseDate(), volume.isRead(), volume.getNote());
    }
}
