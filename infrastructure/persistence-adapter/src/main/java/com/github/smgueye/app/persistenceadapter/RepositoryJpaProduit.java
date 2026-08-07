package com.github.smgueye.app.persistenceadapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


public interface RepositoryJpaProduit extends JpaRepository<EntiteJpaProduit, UUID> {

  boolean existsBySku(String sku);
}
