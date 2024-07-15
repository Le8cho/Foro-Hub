package com.challengeAlura.ForoHub.domain.topico.service;

import com.challengeAlura.ForoHub.domain.topico.DatosActualizarTopico;
import com.challengeAlura.ForoHub.domain.topico.TopicoRepository;
import com.challengeAlura.ForoHub.domain.topico.validaciones.ValidacionTopicoDuplicado;
import com.challengeAlura.ForoHub.domain.topico.validaciones.ValidadorCrearTopico;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActualizarTopicoService {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private ValidacionTopicoDuplicado validacionTopicoDuplicado;

    @Transactional
    public void actualizar(Long id, DatosActualizarTopico datos) {
        //buscamos el id :D
        var topicoOptional = topicoRepository.findById(id);
        if (!topicoOptional.isPresent()) {
            throw new EntityNotFoundException(); //error al no encontrar una entidad
        }
        //si lo tenemos ahora verifiquemos que el topico al actualizar no sea duplicado
        validacionTopicoDuplicado.esDuplicado(datos.titulo(), datos.mensaje());

        //si no hay problema actualizamos el topico
        var topico = topicoOptional.get();

        if(datos.titulo() != null){
            topico.setTitulo(datos.titulo());
        }
        if(datos.mensaje() != null){
            topico.setMensaje(datos.mensaje());
        }
        if(datos.nombreCurso() != null){
            topico.setNombreCurso(datos.nombreCurso());
        }
    }

}
