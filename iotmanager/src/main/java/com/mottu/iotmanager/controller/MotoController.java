package com.mottu.iotmanager.controller;

import com.mottu.iotmanager.dto.MotoDTO;
import com.mottu.iotmanager.service.MotoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/motos")
@RequiredArgsConstructor
public class MotoController {
    private final MotoService motoService;

    @PostMapping
    public MotoDTO create(@RequestBody @Valid MotoDTO dto) {
        return motoService.save(dto);
    }

    @GetMapping
    public Page<MotoDTO> getAll(@RequestParam(defaultValue = "") String modelo, Pageable pageable) {
        return motoService.findAll(modelo, pageable);
    }

    @GetMapping("/{id}")
    public MotoDTO getById(@PathVariable Long id) {
        return motoService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        motoService.delete(id);
    }
}
