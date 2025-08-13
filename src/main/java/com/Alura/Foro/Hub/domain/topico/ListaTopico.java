package com.Alura.Foro.Hub.domain.topico;
import java.time.LocalDateTime;

public record ListaTopico(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean status,
        String autor,
        String nombreCurso

) {
    public ListaTopico (Topico topico){
        this(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}
