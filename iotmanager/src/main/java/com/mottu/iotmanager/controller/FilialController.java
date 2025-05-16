package com.mottu.iotmanager.controller;

import com.mottu.iotmanager.dto.FilialDTO;
import com.mottu.iotmanager.service.FilialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/filiais")
@RequiredArgsConstructor
public class FilialController {
    private final FilialService filialService;

    @PostMapping
    public FilialDTO create(@RequestBody @Valid FilialDTO dto) {
        return filialService.save(dto);
    }

    @GetMapping
    public Page<FilialDTO> getAll(Pageable pageable) {
        return filialService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public FilialDTO getById(@PathVariable Long id) {
        return filialService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        filialService.delete(id);
    }
}