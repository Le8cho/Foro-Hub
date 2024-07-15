package com.challengeAlura.ForoHub.domain.topico.service;

import com.challengeAlura.ForoHub.domain.topico.Topico;
import com.challengeAlura.ForoHub.domain.topico.TopicoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EliminarTopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Transactional
    public void eliminar(Long id){
        //buscamos el id :D
        var topicoOptional = topicoRepository.findById(id);
        if (!topicoOptional.isPresent()) {
            throw new EntityNotFoundException(); //error al no encontrar una entidad
        }
        topicoRepository.deleteById(id);
    }

}
