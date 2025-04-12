package de.leon.mymangaapp.model;

import androidx.annotation.Nullable;

import java.time.LocalDate;

import de.leon.mymangaapp.model.db.DbVolume;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@lombok.Data
@Getter
@AllArgsConstructor
public class Volume {

    @NonNull
    private ID id;
    @NonNull
    private String seriesId;
    @Nullable
    private Integer volumeNumber;
    @NonNull
    private Integer edition;
    @NonNull
    private Double price;
    @NonNull
    private LocalDate releaseDate;
    private boolean read = false;
    @NonNull
    private String note;

    public Volume(@NonNull ID id, @NonNull String seriesId, @Nullable Integer volumeNumber, @NonNull Integer edition, @NonNull Double price, @NonNull LocalDate releaseDate, @NonNull boolean read) {
        this(id, seriesId, volumeNumber, edition, price, releaseDate, read, "");
    }

    /**
     *  If the volumeNumber is null, return the note.
     *  <br>
     *  If a Series doesn't have normal Volumes but is like a collection, the note should contain the title of the manga.
     */
    public String getVolumeOrNote() {
        return volumeNumber != null ? "# " + volumeNumber : note;
    }

    public static Volume fromDbObject(DbVolume volume) {
        return new Volume(ID.of(volume.getRowId()), volume.getSeries(), volume.getVolumeNumber(), volume.getEdition(), volume.getPrice(), volume.getReleaseDate(), volume.isRead(), volume.getNote());
    }

    @AllArgsConstructor(staticName = "of")
    public static class ID {
        @NonNull
        private String rawValue;
    }
}
