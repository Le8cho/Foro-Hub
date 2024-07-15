package com.challengeAlura.ForoHub.domain.topico;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        String titulo,
        String mensaje,
        LocalDateTime fecha
) {
    public DatosRespuestaTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getFecha());
    }
}
