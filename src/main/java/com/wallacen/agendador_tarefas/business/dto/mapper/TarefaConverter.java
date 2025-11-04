package com.wallacen.agendador_tarefas.business.dto.mapper;

import com.wallacen.agendador_tarefas.business.dto.TarefaDto;
import com.wallacen.agendador_tarefas.infrastructure.entity.Tarefas;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    Tarefas paraTarefa(TarefaDto tarefaDto);
    TarefaDto paraTarefaDto(Tarefas tarefa);
}
