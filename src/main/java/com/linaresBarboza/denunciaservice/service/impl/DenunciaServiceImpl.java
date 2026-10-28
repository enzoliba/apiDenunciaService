package com.linaresBarboza.denunciaservice.service.impl;

import com.linaresBarboza.denunciaservice.entity.Denuncia;
import com.linaresBarboza.denunciaservice.repository.DenunciaRepository;
import com.linaresBarboza.denunciaservice.service.DenunciaService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DenunciaServiceImpl implements DenunciaService {

    @Autowired
    private DenunciaRepository repository;

    @Transactional(readOnly = true)
    @Override
    public Page<Denuncia> findAll(Pageable pageable, String search) {
        if (search == null){
            return repository.findAll(pageable);
        } else {
            return repository.findByDniContainingIgnoreCase(pageable, search);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Denuncia findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Denuncia> findByDni(String dni) {
        return repository.findByDni(dni);
    }

    @Transactional
    @Override
    public Denuncia create(Denuncia obj) {
        obj.setEstado(1); // Garantiza que el estado inicie en 1 al registrar
        return repository.save(obj);
    }

    @Transactional
    @Override
    public Denuncia atender(Long id) {
        Denuncia denuncia = this.findById(id);
        if (denuncia != null) {
            denuncia.setEstado(0); // Cambia el estado a 0 (Atendida por serenazgo)
            return repository.save(denuncia);
        }
        return null;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}