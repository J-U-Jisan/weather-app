package org.vaadin.example.entity;

public class Location {
    private Integer id;
    private String name;
    private Double latitude;
    private Double longitude;
    private String country;
    private String addedAsFavorite;

    public Location() {
    }

    public Location(Integer id, String name, Double latitude, Double longitude, String country) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddedAsFavorite() {
        return addedAsFavorite;
    }

    public void setAddedAsFavorite(String addedAsFavorite) {
        this.addedAsFavorite = addedAsFavorite;
    }
}
