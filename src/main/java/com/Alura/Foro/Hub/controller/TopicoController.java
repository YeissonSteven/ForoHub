package com.Alura.Foro.Hub.controller;

import com.Alura.Foro.Hub.domain.topico.AgregarTopico;
import com.Alura.Foro.Hub.domain.topico.Topico;
import com.Alura.Foro.Hub.domain.topico.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("topicos")
public class TopicoController {
    @Autowired
    TopicoService servicio;

    @PostMapping
    @Transactional
    public ResponseEntity agregarTopico(@RequestBody @Valid AgregarTopico datos) {
        Topico topicoCreado = servicio.convertirATopico(datos);
        return new ResponseEntity<>(datos, HttpStatus.CREATED);
    }
}
