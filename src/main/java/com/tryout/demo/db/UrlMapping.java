package com.tryout.demo.db;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
// Marking this class as the class which will be stored in the table by the name URLMAPPING
@Entity
public class UrlMapping {
    @Id
    @GeneratedValue
    private long id;
    private String url;
    private String generatedURL;

    public UrlMapping(long id, String url, String generatedURL) {
        this.id = id;
        this.url = url;
        this.generatedURL = generatedURL;
    }

    public UrlMapping() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGeneratedURL() {
        return generatedURL;
    }

    public void setGeneratedURL(String generatedURL) {
        this.generatedURL = generatedURL;
    }

    @Override
    public String toString() {
        return "UrlMapping{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", generatedURL='" + generatedURL + '\'' +
                '}';
    }
}
