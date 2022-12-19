package br.com.banco.service;

import br.com.banco.dto.ContaDto;
import br.com.banco.model.Conta;

public interface ContaInterface {
    Conta criar(ContaDto contaDto);

    Conta encontrarPeloId(Long id);

    Conta atualizar(Long id, ContaDto contaDto);

    void deletar(Long id);
}
