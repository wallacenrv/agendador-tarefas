package com.wallacen.agendador_tarefas.controller;

import com.wallacen.agendador_tarefas.business.dto.TarefaDto;
import com.wallacen.agendador_tarefas.infrastructure.enums.StatusNotificacao;
import com.wallacen.agendador_tarefas.infrastructure.service.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping("/eventos")
    public ResponseEntity <List<TarefaDto>> buscaListaDeTarefasPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
                                                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal){

        return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping()
    public ResponseEntity <List<TarefaDto>> buscaListaDeTarefasPorEmail(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefaService.buscaTarefasPorEmail(token));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTarefaPorId(@RequestParam String id){
        tarefaService.apagarTarefa(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefaDto> alteraStatusNotificacao(@RequestParam("status")StatusNotificacao status,
                                                             @RequestParam("id")String id){
        return ResponseEntity.ok(tarefaService.alterarStatus(status, id));
    }

    @PutMapping
    public ResponseEntity<TarefaDto> alterarTarefa(@RequestBody TarefaDto tarefaDto,
                                                   @RequestParam("id")String id){
        return ResponseEntity.ok(tarefaService.updateTarefas(tarefaDto, id));
    }

}
