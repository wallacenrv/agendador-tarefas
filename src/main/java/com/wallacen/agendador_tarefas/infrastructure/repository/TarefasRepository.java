package com.wallacen.agendador_tarefas.infrastructure.repository;

import com.wallacen.agendador_tarefas.infrastructure.entity.Tarefas;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefasRepository extends MongoRepository<Tarefas, String> {
}
