package com.Alura.Foro.Hub.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record AgregarTopico(
        Long usuarioId,
        @NotBlank String mensaje,
        @NotBlank String nombreCurso,
        @NotBlank String titulo
) {
}
