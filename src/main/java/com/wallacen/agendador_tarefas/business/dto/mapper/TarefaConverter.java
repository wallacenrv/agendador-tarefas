package com.wallacen.agendador_tarefas.business.dto.mapper;

import com.wallacen.agendador_tarefas.business.dto.TarefaDto;
import com.wallacen.agendador_tarefas.infrastructure.entity.Tarefas;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "dataEvento", target = "dataEvento")
    @Mapping(source = "dataCriacao", target = "dataCriacao")
    Tarefas paraTarefa(TarefaDto tarefaDto);
    TarefaDto paraTarefaDto(Tarefas tarefa);
    List<TarefaDto> paraListTarefaDto(List<Tarefas> tarefas);
    List<Tarefas> paraListTarefa(List<TarefaDto> tarefaDto);
}
