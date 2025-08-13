package com.Alura.Foro.Hub.domain.topico;

import com.Alura.Foro.Hub.curso.Curso;
import com.Alura.Foro.Hub.domain.respuesta.Respuesta;
import com.Alura.Foro.Hub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @Column(name="fechaDeCreacion")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime fechaDeCreacion;
    private boolean status = true;
    @ManyToOne
    private Usuario autor;
    @ManyToOne
    private Curso curso;
    @OneToMany(mappedBy = "topico")
    private List<Respuesta> respuestas;

    public Topico(String titulo, String mensaje, LocalDateTime fechaDeCreacion, Usuario autor, Curso curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaDeCreacion = fechaDeCreacion;
        this.autor = autor;
        this.curso = curso;
    }
}
