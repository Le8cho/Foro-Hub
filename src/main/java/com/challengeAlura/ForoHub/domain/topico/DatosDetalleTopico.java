package com.challengeAlura.ForoHub.domain.topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        String titulo,
        String mensaje,
        LocalDateTime fechaDeCreacion,
        Status estado,
        Long idUsuario,
        String nombreCurso
) {
    public DatosDetalleTopico (Topico topico){
        this(
                topico.getTitulo(), topico.getMensaje(), topico.getFecha(),topico.getStatus(),
                topico.getUsuario().getId(), topico.getNombreCurso()
        );
    }
}
