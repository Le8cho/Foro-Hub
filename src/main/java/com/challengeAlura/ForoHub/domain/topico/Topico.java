package com.challengeAlura.ForoHub.domain.topico;


import com.challengeAlura.ForoHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titulo;

    private String mensaje;

    @Column(name = "fecha_creacion")
    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario usuario;

    private String nombreCurso;

    public Topico(DatosCrearTopico datosCrearTopico,Usuario usuario) {
        this.titulo = datosCrearTopico.titulo();
        this.mensaje = datosCrearTopico.mensaje();
        this.fecha = LocalDateTime.now();
        this.nombreCurso = datosCrearTopico.nombreCurso();
        this.status = Status.Abierto;
        this.usuario = usuario;
    }
}
