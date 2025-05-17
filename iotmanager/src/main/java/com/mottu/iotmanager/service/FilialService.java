package com.mottu.iotmanager.service;

import com.mottu.iotmanager.dto.FilialDTO;
import com.mottu.iotmanager.model.Filial;
import com.mottu.iotmanager.repository.FilialRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FilialService {

    private final FilialRepository filialRepository;
    private final ModelMapper modelMapper;

    @CacheEvict(value = "filiais", allEntries = true)
    public FilialDTO save(FilialDTO dto) {
        Filial filial = modelMapper.map(dto, Filial.class);
        return modelMapper.map(filialRepository.save(filial), FilialDTO.class);
    }

    @Cacheable("filiais")
    public Page<FilialDTO> findAll(Pageable pageable) {
        return filialRepository.findAll(pageable)
                .map(f -> modelMapper.map(f, FilialDTO.class));
    }

    
    @Cacheable("filiais")
    public Page<FilialDTO> findByNomeOrCidade(String nome, String cidade, Pageable pageable) {
        return filialRepository.findByNomeContainingIgnoreCaseOrCidadeContainingIgnoreCase(nome, cidade, pageable)
                .map(f -> modelMapper.map(f, FilialDTO.class));
    }

    public FilialDTO findById(Long id) {
        Filial filial = filialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Filial não encontrada"));
        return modelMapper.map(filial, FilialDTO.class);
    }

    @Transactional
    @CacheEvict(value = "filiais", allEntries = true)
    public FilialDTO update(Long id, FilialDTO dto) {
        Filial filial = filialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Filial não encontrada"));

        filial.setNome(dto.getNome());
        filial.setEndereco(dto.getEndereco());
        filial.setCidade(dto.getCidade());

        Filial updated = filialRepository.save(filial);
        return modelMapper.map(updated, FilialDTO.class);
    }

    @CacheEvict(value = "filiais", allEntries = true)
    public void delete(Long id) {
        filialRepository.deleteById(id);
    }
}
