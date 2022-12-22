package br.com.banco.controller;

import br.com.banco.dto.*;
import br.com.banco.model.Transferencia;
import br.com.banco.service.TransferenciaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@Tag(name = "Transferência")
public class TransferenciaController implements TransferenciaInterfaceController {

    @Autowired
    TransferenciaService transferenciaService;

    @Override
    public ResponseEntity<Transferencia> create(TransferenciaDto transferenciaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transferenciaService.criar(transferenciaDto));
    }

    @Override
    public ResponseEntity<Transferencia> encontrarTransferenciaPeloId(int id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(transferenciaService.encontrarTransferenciasPeloId(id));
    }

    @Override
    public ResponseEntity<List<Transferencia>> mostrarTransferenciasPeloIdConta(int id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(transferenciaService.encontrarTransferenciasPeloIdConta(id));
    }

    @Override
    public ResponseEntity<List<Transferencia>> encontrarPeloNomeOperador(
            OperadorDto operadorDto
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(transferenciaService.encontrarPeloNomeOperador(operadorDto));
    }

    @Override
    public ResponseEntity<List<Transferencia>> filtrarPeloPeriodoTempo(
            PeriodoDto periodoDto
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(transferenciaService.filtrarPeloPeriodoTempo(periodoDto));
    }

    @Override
    public ResponseEntity<List<Transferencia>> filtrarPeloTempoEOperador(
            FiltroCompletoDto filtroDto
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(transferenciaService.filtrarPeloTempoEOperador(filtroDto));
    }

    @Override
    public ResponseEntity<Object> deletePeloId(int id) {
        transferenciaService.deletarTransferencia(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
