package br.com.banco.service;

import br.com.banco.dto.*;
import br.com.banco.model.Transferencia;
import java.util.List;

public interface TransferenciaInterface {
    Transferencia criar(TransferenciaDto transferenciaDto);

    Transferencia encontrarTransferenciasPeloId(int id);

    List<Transferencia> encontrarTransferenciasPeloIdConta(int idConta);

    List<Transferencia> encontrarPeloNomeOperador(OperadorDto operadorDto);

    List<Transferencia> filtrarPeloPeriodoTempo(PeriodoDto periodoDto);

    List<Transferencia> filtrarPeloTempoEOperador(
            FiltroCompletoDto filtroDto
    );

    void deletarTransferencia(int id);
}
