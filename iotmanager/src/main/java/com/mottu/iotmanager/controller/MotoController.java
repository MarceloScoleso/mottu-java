package com.mottu.iotmanager.controller;

import com.mottu.iotmanager.dto.MotoDTO;
import com.mottu.iotmanager.service.MotoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/motos")
@RequiredArgsConstructor
public class MotoController {

    private final MotoService motoService;

    @PostMapping
    public ResponseEntity<MotoDTO> create(@RequestBody @Valid MotoDTO dto) {
        MotoDTO saved = motoService.save(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<Page<MotoDTO>> getAll(@RequestParam(defaultValue = "") String modelo, Pageable pageable) {
        Page<MotoDTO> page = motoService.findAll(modelo, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotoDTO> getById(@PathVariable Long id) {
        try {
            MotoDTO dto = motoService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MotoDTO> update(@PathVariable Long id, @RequestBody @Valid MotoDTO dto) {
        try {
            MotoDTO updated = motoService.update(id, dto);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            motoService.delete(id);
            return ResponseEntity.noContent().build(); 
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
