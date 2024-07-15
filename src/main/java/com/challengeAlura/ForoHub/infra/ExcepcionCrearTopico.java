package com.challengeAlura.ForoHub.infra;

public class ExcepcionCrearTopico extends RuntimeException {
    public ExcepcionCrearTopico(String mensajeExcepcion){
        super(mensajeExcepcion);
    }
}
