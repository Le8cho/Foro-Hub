package com.challengeAlura.ForoHub.controller;

import com.challengeAlura.ForoHub.domain.topico.*;
import com.challengeAlura.ForoHub.domain.topico.service.ActualizarTopicoService;
import com.challengeAlura.ForoHub.domain.topico.service.CrearTopicoService;
import com.challengeAlura.ForoHub.domain.topico.service.EliminarTopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private CrearTopicoService crearTopicoService;

    @Autowired
    private ActualizarTopicoService actualizarTopicoService;

    @Autowired
    private EliminarTopicoService eliminarTopicoService;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosCrearTopico datosCrearTopico) {
        var responseBody = crearTopicoService.crear(datosCrearTopico);
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping
    public Page<DatosListadoTopico> listarTopicos(
            @PageableDefault(size = 10, sort = "fecha", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        var page = topicoRepository.findAll(pageable).map(DatosListadoTopico::new);
        return page;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> obtenerDetalle(@PathVariable Long id) {
        var detallesTopico = new DatosDetalleTopico(topicoRepository.getReferenceById(id));
        return ResponseEntity.ok(detallesTopico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datos) {
        actualizarTopicoService.actualizar(id, datos);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id){
        eliminarTopicoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
