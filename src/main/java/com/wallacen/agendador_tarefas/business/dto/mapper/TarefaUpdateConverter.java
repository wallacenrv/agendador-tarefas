package com.wallacen.agendador_tarefas.business.dto.mapper;

import com.wallacen.agendador_tarefas.business.dto.TarefaDto;
import com.wallacen.agendador_tarefas.infrastructure.entity.Tarefas;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE) // ignore todos os valoress nulos
public interface TarefaUpdateConverter {

    // essa anotacao @MappingTarget diz que caso o TarefaDto tarefaDto venha nulo, preencha com o Tarefas tarefas
    void updateTarefas(TarefaDto tarefaDto, @MappingTarget Tarefas tarefas);
}
