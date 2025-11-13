package com.wallacen.agendador_tarefas.infrastructure.service;

import com.wallacen.agendador_tarefas.business.dto.TarefaDto;
import com.wallacen.agendador_tarefas.business.dto.mapper.TarefaConverter;
import com.wallacen.agendador_tarefas.business.dto.mapper.TarefaUpdateConverter;
import com.wallacen.agendador_tarefas.infrastructure.entity.Tarefas;
import com.wallacen.agendador_tarefas.infrastructure.enums.StatusNotificacao;
import com.wallacen.agendador_tarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.wallacen.agendador_tarefas.infrastructure.repository.TarefasRepository;
import com.wallacen.agendador_tarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefasRepository tarefasRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefaDto gravarTarefa(String token, TarefaDto tarefaDto){

        String email = jwtUtil.extractUsername(token.substring(7));
        tarefaDto.setDataCriacao(LocalDateTime.now());
        tarefaDto.setStatusNotificacao(StatusNotificacao.PENDENTE);
        //tarefaDto.setEmailUsuario(email);
        tarefaDto.setEmailUsuario(tarefaDto.getEmailUsuario());
        Tarefas tarefa = tarefaConverter.paraTarefa(tarefaDto);
        return tarefaConverter.paraTarefaDto(
                tarefasRepository.save(tarefa));
    }

public List<TarefaDto> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal){
    return tarefaConverter.paraListTarefaDto(tarefasRepository.findByDataEventoBetweenAndStatusNotificacao(dataInicial, dataFinal, StatusNotificacao.PENDENTE));
}

    public List<TarefaDto> buscaTarefasPorEmail(String token){
        String email = jwtUtil.extractUsername(token.substring(7));
        return tarefaConverter.paraListTarefaDto(tarefasRepository.findByemailUsuario(email));
    }

    public void apagarTarefa(String id){
        try{
            tarefasRepository.deleteById(id);
        }catch (Exception e){
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id " + e.getCause());
        }
    }

    public TarefaDto alterarStatus(StatusNotificacao status, String id){
        try {
            Tarefas tarefas = tarefasRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada : " + id));
                    tarefas.setStatusNotificacao(status);
            return tarefaConverter.paraTarefaDto(tarefasRepository.save(tarefas));

        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa  : " + e.getCause());
        }
    }

    public TarefaDto updateTarefas(TarefaDto tarefaDto, String id){
        try {
            Tarefas tarefas = tarefasRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada : " + id));
            tarefaUpdateConverter.updateTarefas(tarefaDto, tarefas);
            tarefasRepository.save(tarefas);
            tarefaConverter.paraTarefaDto(tarefas);
            return tarefaDto;
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa  : " + e.getCause());
        }
    }


}
