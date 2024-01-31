package com.tryout.demo.db;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<UrlMapping, Long> specifies that the table entries are of type URL Mappings and the type of the primary key is Long.
public interface UrlMappingJpa extends JpaRepository<UrlMapping, Long> {

}
