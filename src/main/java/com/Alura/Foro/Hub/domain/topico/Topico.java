package com.Alura.Foro.Hub.domain.topico;

import com.Alura.Foro.Hub.domain.ValidacionTopico;
import com.Alura.Foro.Hub.domain.curso.Curso;
import com.Alura.Foro.Hub.domain.respuesta.Respuesta;
import com.Alura.Foro.Hub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    private Boolean status = true;
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

    public void ActualizarTopico(DatosActualizarTopico datos){
        if (datos.titulo() != null && datos.titulo() != "") {
            this.titulo = datos.titulo();
        }
        if (datos.mensaje() != null && datos.mensaje() != "") {
            this.mensaje = datos.mensaje();
        }
        if (datos.nombreCurso() != null && datos.nombreCurso() != "") {
            TopicoService  topicoService = new TopicoService();
            this.curso = topicoService.BuscarCrusoPornombre(datos.nombreCurso());
        }
    }
}
