package br.com.banco.controller;

import br.com.banco.dto.ContaDto;
import br.com.banco.model.Conta;
import br.com.banco.service.ContaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@CrossOrigin
@RestController
@Tag(name = "Conta")
public class ContaController implements ContaInterfaceController {

    @Autowired
    ContaService contaService;

    @Override
    public ResponseEntity<Conta> create(ContaDto contaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contaService.criar(contaDto));
    }

    @Override
    public ResponseEntity<Conta> encontrarPeloId(int id) {
        return ResponseEntity.status(HttpStatus.OK).body(contaService.encontrarPeloId(id));
    }

    @Override
    public ResponseEntity<Conta> encontrarPeloNome(String nomeResponsavel) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(contaService.encontrarPeloNome(nomeResponsavel));
    }

    @Override
    public ResponseEntity<Conta> atualizar(int id, ContaDto contaDto) {
        return ResponseEntity.status(HttpStatus.OK).body(contaService.atualizar(id, contaDto));
    }

    @Override
    public ResponseEntity<Object> delete(int id) {
        contaService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
