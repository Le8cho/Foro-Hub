package com.challengeAlura.ForoHub.domain.topico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosCrearTopico(
        @NotNull
        Long idUsuario,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        String nombreCurso
)
{

}
