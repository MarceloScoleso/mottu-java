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
    public Page<FilialDTO> getAll(
            @RequestParam(required = false, defaultValue = "") String nome,
            @RequestParam(required = false, defaultValue = "") String cidade,
            Pageable pageable) {

        // Se parâmetros vazios, retorna tudo
        if (nome.isEmpty() && cidade.isEmpty()) {
            return filialService.findAll(pageable);
        }
        // Busca filtrada por nome ou cidade
        return filialService.findByNomeOrCidade(nome, cidade, pageable);
    }

    @GetMapping("/{id}")
    public FilialDTO getById(@PathVariable Long id) {
        return filialService.findById(id);
    }

    @PutMapping("/{id}")
    public FilialDTO update(@PathVariable Long id, @RequestBody @Valid FilialDTO dto) {
        return filialService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        filialService.delete(id);
    }
}
