package com.example.catalog.model;

public class Track {
    private String id;
    private String name;
    private long duration_ms;
    private boolean explicit;
    private String uri;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDuration_ms() {
        return duration_ms;
    }

    public void setDuration_ms(long duration_ms) {
        this.duration_ms = duration_ms;
    }

    public boolean isExplicit() {
        return explicit;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setTrack(Track track){
        this.name = track.getName();
        this.duration_ms = track.getDuration_ms();
        this.explicit = track.isExplicit();
        this.uri = track.getUri();
    }
}
