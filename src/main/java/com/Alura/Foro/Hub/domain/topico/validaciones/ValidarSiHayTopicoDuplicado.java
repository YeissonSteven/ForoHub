package com.Alura.Foro.Hub.domain.topico.validaciones;

import com.Alura.Foro.Hub.domain.ValidacionTopico;
import com.Alura.Foro.Hub.domain.topico.AgregarTopico;
import com.Alura.Foro.Hub.domain.topico.Topico;
import com.Alura.Foro.Hub.domain.topico.TopicoRepository;
import com.Alura.Foro.Hub.domain.topico.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarSiHayTopicoDuplicado implements ValidadorDeTopicos {

    @Autowired
    TopicoRepository topicoRepository;

    public void validar(AgregarTopico datos) {
        Topico mensajeBuscado = topicoRepository.findByMensaje(datos.mensaje());
        if (mensajeBuscado != null && mensajeBuscado.getMensaje().equals(datos.mensaje()) && mensajeBuscado.getTitulo().equals(datos.titulo())) {
            throw new ValidacionTopico("ya existe un topico con el mismo titulo y mensaje");
        }



    }
}
