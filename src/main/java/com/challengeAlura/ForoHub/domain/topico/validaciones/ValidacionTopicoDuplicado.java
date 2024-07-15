package com.challengeAlura.ForoHub.domain.topico.validaciones;

import com.challengeAlura.ForoHub.domain.topico.TopicoRepository;
import com.challengeAlura.ForoHub.infra.ExcepcionCrearTopico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionTopicoDuplicado implements ValidadorCrearTopico{

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public boolean esDuplicado(String titulo, String mensaje) {
        //para verificar si existe un topico duplicado lo buscamos en la base de datos
        if (topicoRepository.existsByTituloAndMensaje(titulo, mensaje)){
            throw new ExcepcionCrearTopico("Topico Duplicado!");
        }
        else{
            return false;
        }
    }
}
