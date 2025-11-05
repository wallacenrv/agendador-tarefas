package com.wallacen.agendador_tarefas.infrastructure.repository;

import com.wallacen.agendador_tarefas.infrastructure.entity.Tarefas;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TarefasRepository extends MongoRepository<Tarefas, String> {

    List<Tarefas> findByDataEventoBetween(LocalDateTime dataInicial, LocalDateTime dataFinal);
    List<Tarefas> findByemailUsuario(String emailUsuario);
}
