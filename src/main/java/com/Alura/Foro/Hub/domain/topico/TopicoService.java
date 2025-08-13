package com.Alura.Foro.Hub.domain.topico;

import com.Alura.Foro.Hub.curso.Curso;
import com.Alura.Foro.Hub.curso.CursoRepository;
import com.Alura.Foro.Hub.domain.topico.validaciones.ValidadorDeTopicos;
import com.Alura.Foro.Hub.domain.usuario.Usuario;
import com.Alura.Foro.Hub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    TopicoRepository topicoRepository;

    @Autowired
    List<ValidadorDeTopicos> validador;

    public Topico convertirATopico(AgregarTopico datos){
        Optional<Usuario> autor = usuarioRepository.findById(datos.usuarioId());
        Usuario usuario= null;
        if(autor.isPresent()){
            usuario = autor.get();
        }
        Curso curso = cursoRepository.findByNombre(datos.nombreCurso());
        LocalDateTime fecha = LocalDateTime.now();
        Topico topico = new Topico(datos.titulo(), datos.mensaje(), fecha, usuario, curso);
        //validacion
        validador.forEach(v -> v.validar(datos));

        return topicoRepository.save(topico);
    }


}
