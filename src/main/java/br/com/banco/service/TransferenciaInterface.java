package br.com.banco.service;

import br.com.banco.dto.TransferenciaDto;
import br.com.banco.model.Transferencia;

import java.util.List;

public interface TransferenciaInterface {
    Transferencia criar(TransferenciaDto transferenciaDto);

    List<Transferencia> encontrarTransferenciasPeloIdConta(int idConta);
}
