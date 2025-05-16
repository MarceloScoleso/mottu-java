package com.mottu.iotmanager.service;

import com.mottu.iotmanager.dto.MotoDTO;
import com.mottu.iotmanager.model.Filial;
import com.mottu.iotmanager.model.Moto;
import com.mottu.iotmanager.repository.FilialRepository;
import com.mottu.iotmanager.repository.MotoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class MotoService {
    private final MotoRepository motoRepository;
    private final FilialRepository filialRepository;
    private final ModelMapper modelMapper;

    public MotoDTO save(MotoDTO dto) {
        Moto moto = modelMapper.map(dto, Moto.class);
        Filial filial = filialRepository.findById(dto.getFilialId())
                .orElseThrow(() -> new EntityNotFoundException("Filial não encontrada"));
        moto.setFilial(filial);
        return modelMapper.map(motoRepository.save(moto), MotoDTO.class);
    }

    @Cacheable("motos")
    public Page<MotoDTO> findAll(String modelo, Pageable pageable) {
        Page<Moto> page = motoRepository.findByModeloContainingIgnoreCase(modelo, pageable);
        return page.map(moto -> modelMapper.map(moto, MotoDTO.class));
    }

    public MotoDTO findById(Long id) {
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Moto não encontrada"));
        return modelMapper.map(moto, MotoDTO.class);
    }

    public void delete(Long id) {
        motoRepository.deleteById(id);
    }
}