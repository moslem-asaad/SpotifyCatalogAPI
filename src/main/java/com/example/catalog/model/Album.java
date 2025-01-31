package com.example.catalog.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Album {
    private String id;
    private String name;
    private String uri;
    private String release_date;
    private int total_tracks;
    private List<Image> images;
    private List<Track> tracks;

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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getTotal_tracks() {
        return total_tracks;
    }

    public void setTotal_tracks(int total_tracks) {
        this.total_tracks = total_tracks;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public Album addTrack(Track track){
        if (track == null || tracks == null) return null;
        if (getTrackById(track.getId())!=null) return null;
        tracks.add(track);
        return this;
    }

    private Track getTrackById(String id){
        for (Track track: tracks){
            if (track.getId().equals(id)) return track;
        }
        return null;
    }

    public Album updateTrack(Track track) {
        if (track == null || tracks == null) return null;
        for (int i = 0; i < tracks.size(); i++) {
            if (tracks.get(i).getId().equals(track.getId())) {
                //tracks.set(i, track);
                tracks.get(i).setTrack(track);
                return this;
            }
        }
        return null;
    }

    public Album deleteTrack(String id){
        if (id == null || id.isEmpty()) return null;
        for (int i = 0; i < tracks.size(); i++) {
            if (tracks.get(i).getId().equals(id)){
                tracks.remove(i);
                return this;
            }
        }
        return null;
    }
}
