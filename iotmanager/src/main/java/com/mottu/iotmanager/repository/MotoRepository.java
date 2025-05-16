package com.mottu.iotmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mottu.iotmanager.model.Moto;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long> {
    Page<Moto> findByModeloContainingIgnoreCase(String modelo, Pageable pageable);
}