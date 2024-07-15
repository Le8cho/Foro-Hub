package com.challengeAlura.ForoHub.domain.topico.validaciones;

import com.challengeAlura.ForoHub.domain.topico.DatosCrearTopico;
import com.challengeAlura.ForoHub.domain.topico.Topico;

public interface ValidadorCrearTopico {

    boolean esDuplicado(String titulo, String mensaje);
}
