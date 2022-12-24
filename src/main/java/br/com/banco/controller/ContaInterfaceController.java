package br.com.banco.controller;

import br.com.banco.dto.ContaDto;
import br.com.banco.model.Conta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RequestMapping("/conta")
public interface ContaInterfaceController {

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criar conta",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Conta.class)) }),
            @ApiResponse(responseCode = "400", description = "Formato incorreto",
                    content = @Content)})
    @Operation(summary = "Criar conta")
    ResponseEntity<Conta> create(@RequestBody ContaDto contaDto);

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrar conta pelo id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Conta.class)) }),
            @ApiResponse(responseCode = "404", description = "Conta não encontrada",
                    content = @Content)})
    @Operation(summary = "Encontrar conta pelo id")
    ResponseEntity<Conta> encontrarPeloId(@PathVariable int id);

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrar conta pelo nome responsável",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Conta.class)) }),
            @ApiResponse(responseCode = "404", description = "Conta não encontrada",
                    content = @Content)})
    @Operation(summary = "Encontrar conta pelo nome responsável")
    ResponseEntity<Conta> encontrarPeloNome(@PathParam("nome-responsavel") String nomeResponsavel);

    @PatchMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Conta.class)) }),
            @ApiResponse(responseCode = "400", description = "Solicitação inválida",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Conta não encontrada",
                    content = @Content)})
    @Operation(summary = "Atualizar conta pelo id")
    ResponseEntity<Conta> atualizar(
            @PathVariable int id,
            @RequestBody ContaDto contaDto
    );

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Conta não encontrada",
                    content = @Content)})
    @Operation(summary = "Deletar conta pelo id")
    ResponseEntity<Object> delete(@PathVariable int id);
}
