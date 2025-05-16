package com.mottu.iotmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mottu.iotmanager.model.Filial;

@Repository
public interface FilialRepository extends JpaRepository<Filial, Long> {
}
