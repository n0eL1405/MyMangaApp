package de.leon.mymangaapp.model;

@lombok.Data
public abstract class Data {

    private final String id;

    public Data(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
