package com.linaresBarboza.denunciaservice.service;

import com.linaresBarboza.denunciaservice.entity.Denuncia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DenunciaService {
    Page<Denuncia> findAll(Pageable pageable, String search);
    Denuncia findById(Long id);
    Denuncia create(Denuncia obj);
    Denuncia atender(Long id); // Cambiado para cumplir el requerimiento de "Atender denuncia"
    List<Denuncia> findByDni(String dni); // Nuevo requerimiento
    void delete(Long id);
}