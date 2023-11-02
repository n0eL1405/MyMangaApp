package de.leon.mymangaapp.model;

@lombok.Data
public abstract class DbData {

    private final Integer rowNumber;
    private final String rowId;
    private final String userEmail;

    public DbData(Integer rowNumber, String rowId, String userEmail) {
        this.rowNumber = rowNumber;
        this.rowId = rowId;
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public String getRowId() {
        return rowId;
    }
}
