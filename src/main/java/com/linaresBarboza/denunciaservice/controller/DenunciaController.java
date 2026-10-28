package com.linaresBarboza.denunciaservice.controller;

import com.linaresBarboza.denunciaservice.entity.Denuncia;
import com.linaresBarboza.denunciaservice.service.impl.DenunciaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/denuncias")
public class DenunciaController {

    @Autowired
    private DenunciaServiceImpl service;

    // Obtener denuncias (Paginadas)
    @GetMapping
    public ResponseEntity<Page<Denuncia>> findAll(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
    ){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ResponseEntity.ok(service.findAll(pageable, search));
    }

    // Obtener denuncia por ID
    @GetMapping("/{id}")
    public ResponseEntity<Denuncia> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    // Obtener denuncias de un ciudadano
    @GetMapping("/ciudadano/{dni}")
    public ResponseEntity<List<Denuncia>> findByCiudadano(@PathVariable String dni){
        return ResponseEntity.ok(service.findByDni(dni));
    }

    // Registrar denuncia
    @PostMapping
    public ResponseEntity<Denuncia> create(@RequestBody Denuncia obj){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(obj));
    }

    // Atender denuncia
    @PutMapping("/{id}")
    public ResponseEntity<Denuncia> atender(@PathVariable Long id){
        Denuncia atendida = service.atender(id);
        if (atendida != null) {
            return ResponseEntity.ok(atendida);
        }
        return ResponseEntity.notFound().build();
    }

    // Eliminar denuncia (Mantenido por buenas prácticas)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}