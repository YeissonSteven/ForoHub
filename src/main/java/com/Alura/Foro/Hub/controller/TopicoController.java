package com.Alura.Foro.Hub.controller;

import com.Alura.Foro.Hub.domain.ValidacionTopico;
import com.Alura.Foro.Hub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("topicos")
public class TopicoController {
    @Autowired
    TopicoService servicio;

    @Autowired
    TopicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity agregarTopico(@RequestBody @Valid AgregarTopico datos) {
        Topico topicoCreado = servicio.convertirATopico(datos);
        return new ResponseEntity<>(datos, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity listarTopicos(@PageableDefault(size=10, sort= {"fechaDeCreacion"}, direction = Sort.Direction.ASC) Pageable paginacion) {
        var lista = repository.findAll(paginacion).map(ListaTopico::new);

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity detallarTopico(@PathVariable Long id){
        var topico = repository.findById(id);
        if (topico.isPresent()){
            ListaTopico dto = new ListaTopico(topico.get());
            return ResponseEntity.ok(dto);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Topico no encontrado");
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@PathVariable Long id,@RequestBody DatosActualizarTopico datos){
        var topico = repository.getReferenceById(id);
        topico.ActualizarTopico(datos);
        return ResponseEntity.ok(new ListaTopico(topico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        var topico = repository.findById(id);
        if (topico.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.ok(new ListaTopico(topico.get()));
        }
        throw new ValidacionTopico("El topico el cual deseas eliminar no exite");
    }

}
