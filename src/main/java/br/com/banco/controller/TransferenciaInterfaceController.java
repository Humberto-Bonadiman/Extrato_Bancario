package br.com.banco.controller;

import br.com.banco.dto.*;
import br.com.banco.model.Transferencia;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(value = "/transferencia")
public interface TransferenciaInterfaceController {

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criar transferência",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Transferencia.class)) }),
            @ApiResponse(responseCode = "400", description = "Formato incorreto",
                    content = @Content)})
    @Operation(summary = "Criar transferência")
    ResponseEntity<Transferencia> create(@RequestBody TransferenciaDto transferenciaDto);

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrar Transferências pelo id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Transferencia.class))})
    })
    @Operation(summary = "Mostrar transferência pelo id")
    ResponseEntity<Transferencia> encontrarTransferenciaPeloId(@PathVariable int id);

    @GetMapping("/id-conta/{contaId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrar Transferências pelo id da conta",
            content = { @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Transferencia.class)))})
    })
    @Operation(summary = "Mostrar todas as transferências pelo id da conta")
    ResponseEntity<List<Transferencia>> mostrarTransferenciasPeloIdConta(@PathVariable("contaId") int contaId);

    @PostMapping("/operador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Encontrar Transferências pelo id da conta e nome do operador",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Transferencia.class)))})
    })
    @Operation(summary = "Mostrar todas as transferências pelo id da conta e nome do operador")
    ResponseEntity<List<Transferencia>> encontrarPeloNomeOperador(
            @RequestBody OperadorDto operadorDto
    );

    @PostMapping("/periodo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Encontrar Transferências pelo id da conta e período de tempo",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Transferencia.class)))})
    })
    @Operation(summary = "Mostrar todas as transferências pelo id da conta e período de tempo")
    ResponseEntity<List<Transferencia>> filtrarPeloPeriodoTempo(
            @RequestBody PeriodoDto periodoDto
    );

    @PostMapping("/operador/periodo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Encontrar Transferências pelo id da conta e período de tempo",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Transferencia.class)))})
    })
    @Operation(summary = "Mostrar todas as transferências pelo id da conta e período de tempo")
    ResponseEntity<List<Transferencia>> filtrarPeloTempoEOperador(
            @RequestBody FiltroCompletoDto filtroDto
    );

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", content = @Content)})
    ResponseEntity<Object> deletePeloId(@PathVariable int id);
}
