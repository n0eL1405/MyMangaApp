package de.leon.mymangaapp.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Data
@AllArgsConstructor
public abstract class DbData {

    private final Integer rowNumber;
    private final String rowId;
    private final String userEmail;

}
