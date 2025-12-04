package com.wallacen.agendador_tarefas.infrastructure.repository;

import com.wallacen.agendador_tarefas.infrastructure.entity.Tarefas;
import com.wallacen.agendador_tarefas.infrastructure.enums.StatusNotificacao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TarefasRepository extends MongoRepository<Tarefas, String> {

    List<Tarefas> findByDataEventoBetweenAndStatusNotificacao(LocalDateTime dataInicial, LocalDateTime dataFinal, StatusNotificacao statusNotificacao);
    List<Tarefas> findByEmailUsuario(String emailUsuario);
}
