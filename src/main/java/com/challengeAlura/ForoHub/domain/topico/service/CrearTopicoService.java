package com.challengeAlura.ForoHub.domain.topico.service;

import com.challengeAlura.ForoHub.domain.topico.DatosCrearTopico;
import com.challengeAlura.ForoHub.domain.topico.DatosRespuestaTopico;
import com.challengeAlura.ForoHub.domain.topico.Topico;
import com.challengeAlura.ForoHub.domain.topico.TopicoRepository;
import com.challengeAlura.ForoHub.domain.topico.validaciones.ValidadorCrearTopico;
import com.challengeAlura.ForoHub.domain.usuario.Usuario;
import com.challengeAlura.ForoHub.domain.usuario.UsuarioRepository;
import com.challengeAlura.ForoHub.infra.ExcepcionCrearTopico;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrearTopicoService {

    @Autowired
    private ValidadorCrearTopico validadorCrearTopico;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository userRepository;

    @Transactional
    public DatosRespuestaTopico crear(DatosCrearTopico datosCrearTopico) {

        //verificamos si ya existe un topico con el mismo titulo y mensaje
        var duplicado = validadorCrearTopico.esDuplicado(datosCrearTopico.titulo(), datosCrearTopico.mensaje());

        //verificamos si existe ese usuario
        if(!userRepository.existsById(datosCrearTopico.idUsuario())){
            throw new ExcepcionCrearTopico("Usuario no encontrado");
        }

        //Si no es duplicado y existe usuario
        var usuario = userRepository.getById(datosCrearTopico.idUsuario());

        var topico = new Topico(datosCrearTopico,usuario);

        topicoRepository.save(topico);

        return new DatosRespuestaTopico(topico);
    }
}
