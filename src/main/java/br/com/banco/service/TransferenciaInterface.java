package br.com.banco.service;

import br.com.banco.dto.FiltroDto;
import br.com.banco.dto.OperadorDto;
import br.com.banco.dto.PeriodoDto;
import br.com.banco.dto.TransferenciaDto;
import br.com.banco.model.Transferencia;
import java.util.List;

public interface TransferenciaInterface {
    Transferencia criar(TransferenciaDto transferenciaDto);

    Transferencia encontrarTransferenciasPeloId(int id);

    List<Transferencia> encontrarTransferenciasPeloIdConta(int idConta);

    List<Transferencia> encontrarPeloNomeOperador(int idConta, OperadorDto operadorDto);

    List<Transferencia> filtrarPeloPeriodoTempo(int idConta, PeriodoDto periodoDto);

    List<Transferencia> filtrarPeloTempoEOperador(
            int idConta,
            FiltroDto filtroDto
    );

    void deletarTransferencia(int id);
}
