package com.linaresBarboza.denunciaservice.repository;

import com.linaresBarboza.denunciaservice.entity.Denuncia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {
    Page<Denuncia> findByDniContainingIgnoreCase(Pageable pageable, String dni);
    List<Denuncia> findByDni(String dni); // Nuevo método para buscar por ciudadano
}