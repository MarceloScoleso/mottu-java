package com.mottu.iotmanager.service;

import com.mottu.iotmanager.dto.MotoDTO;
import com.mottu.iotmanager.model.Filial;
import com.mottu.iotmanager.model.Moto;
import com.mottu.iotmanager.repository.FilialRepository;
import com.mottu.iotmanager.repository.MotoRepository;
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
public class MotoService {

    private final MotoRepository motoRepository;
    private final FilialRepository filialRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @CacheEvict(value = "motos", allEntries = true)
    public MotoDTO save(MotoDTO dto) {
        Moto moto = modelMapper.map(dto, Moto.class);

        Filial filial = filialRepository.findById(dto.getFilialId())
                .orElseThrow(() -> new EntityNotFoundException("Filial não encontrada"));

        moto.setFilial(filial);

        Moto savedMoto = motoRepository.save(moto);
        return modelMapper.map(savedMoto, MotoDTO.class);
    }

    @Transactional(readOnly = true)
    @Cacheable("motos")
    public Page<MotoDTO> findAll(String modelo, Pageable pageable) {
        Page<Moto> page = motoRepository.findByModeloContainingIgnoreCase(modelo, pageable);
        return page.map(moto -> modelMapper.map(moto, MotoDTO.class));
    }

    @Transactional(readOnly = true)
    public MotoDTO findById(Long id) {
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Moto não encontrada"));
        return modelMapper.map(moto, MotoDTO.class);
    }

    @Transactional
    @CacheEvict(value = "motos", allEntries = true)
    public MotoDTO update(Long id, MotoDTO dto) {
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Moto não encontrada"));

        moto.setPlaca(dto.getPlaca());
        moto.setModelo(dto.getModelo());
        moto.setAno(dto.getAno());
        moto.setStatusAtual(dto.getStatusAtual());

        if (dto.getFilialId() != null) {
            Filial filial = filialRepository.findById(dto.getFilialId())
                    .orElseThrow(() -> new EntityNotFoundException("Filial não encontrada"));
            moto.setFilial(filial);
        }

        Moto updatedMoto = motoRepository.save(moto);
        return modelMapper.map(updatedMoto, MotoDTO.class);
    }

    @Transactional
    @CacheEvict(value = "motos", allEntries = true)
    public void delete(Long id) {
        if (!motoRepository.existsById(id)) {
            throw new EntityNotFoundException("Moto não encontrada");
        }
        motoRepository.deleteById(id);
    }
}