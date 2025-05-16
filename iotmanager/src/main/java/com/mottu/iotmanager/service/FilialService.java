package com.mottu.iotmanager.service;

import com.mottu.iotmanager.dto.FilialDTO;
import com.mottu.iotmanager.model.Filial;
import com.mottu.iotmanager.repository.FilialRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilialService {
    private final FilialRepository filialRepository;
    private final ModelMapper modelMapper;

    public FilialDTO save(FilialDTO dto) {
        Filial filial = modelMapper.map(dto, Filial.class);
        return modelMapper.map(filialRepository.save(filial), FilialDTO.class);
    }

    @Cacheable("filiais")
    public Page<FilialDTO> findAll(Pageable pageable) {
        return filialRepository.findAll(pageable)
                .map(f -> modelMapper.map(f, FilialDTO.class));
    }

    public FilialDTO findById(Long id) {
        Filial filial = filialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Filial não encontrada"));
        return modelMapper.map(filial, FilialDTO.class);
    }

    public void delete(Long id) {
        filialRepository.deleteById(id);
    }
}
