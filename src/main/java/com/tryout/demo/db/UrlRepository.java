package com.tryout.demo.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// the class which talks to database through JPA interface which is a only dependency here
@Repository
public class UrlRepository {

    @Autowired
    private UrlMappingJpa repository;

    public UrlMapping getParticularMapping(UrlMapping urlMapping){
        return repository.findAll().stream().filter(record -> record.getUrl() == urlMapping.getUrl() && urlMapping.getGeneratedURL() == record.getGeneratedURL()).findFirst().orElse(null);
    }

    public String getRedirection(String url){
        UrlMapping urlMapping = repository.findAll().stream().filter(record -> {
            System.out.println(record.getGeneratedURL() + " " + url);
            return record.getGeneratedURL().equals(url.toString());
        }).findFirst().orElse(null);
        if (urlMapping == null)
            return "error";
        return urlMapping.getUrl();
    }

    public void add(UrlMapping urlMapping){
        repository.save(urlMapping);
    }
}
