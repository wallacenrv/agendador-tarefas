package com.wallacen.agendador_tarefas.controller;

import com.wallacen.agendador_tarefas.business.dto.TarefaDto;
import com.wallacen.agendador_tarefas.infrastructure.service.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDto> gravarTarefa(@RequestBody TarefaDto tarefaDto,
                                                  @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefaService.gravarTarefa(token, tarefaDto));
    }
}
