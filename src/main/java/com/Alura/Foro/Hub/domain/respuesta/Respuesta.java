package com.Alura.Foro.Hub.domain.respuesta;

import com.Alura.Foro.Hub.domain.topico.Topico;
import com.Alura.Foro.Hub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @ManyToOne
    private Topico topico;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime fechaCreacion;
    @ManyToOne
    private Usuario autor;
    private boolean solucion = false;
}
