package com.wallacen.agendador_tarefas.infrastructure.service;

import com.wallacen.agendador_tarefas.business.dto.TarefaDto;
import com.wallacen.agendador_tarefas.business.dto.mapper.TarefaConverter;
import com.wallacen.agendador_tarefas.infrastructure.client.UsuarioClient;
import com.wallacen.agendador_tarefas.infrastructure.entity.Tarefas;
import com.wallacen.agendador_tarefas.infrastructure.enums.StatusNotificacao;
import com.wallacen.agendador_tarefas.infrastructure.repository.TarefasRepository;
import com.wallacen.agendador_tarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefasRepository tarefasRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefaDto gravarTarefa(String token, TarefaDto tarefaDto){

        String email = jwtUtil.extractUsername(token.substring(7));
        tarefaDto.setDataCriacao(LocalDateTime.now());
        tarefaDto.setStatusNotificacao(StatusNotificacao.PENDENTE);
        tarefaDto.setEmailUsuario(email);


        Tarefas tarefa = tarefaConverter.paraTarefa(tarefaDto);

        return tarefaConverter.paraTarefaDto(
                tarefasRepository.save(tarefa));
    }
}
