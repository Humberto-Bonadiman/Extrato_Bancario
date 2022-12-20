package br.com.banco.controller;

import br.com.banco.dto.ContaDto;
import br.com.banco.dto.TransferenciaDto;
import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/transferencia")
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

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrar Transferências pelo id da conta",
            content = { @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Transferencia.class)))})
    })
    @Operation(summary = "Mostrar todas as transferências pelo id da conta")
    ResponseEntity<List<Transferencia>> mostrarTransferenciasPeloIdConta(@RequestParam int id);
}
