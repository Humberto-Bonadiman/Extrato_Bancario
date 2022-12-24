package br.com.banco.service;

import br.com.banco.dto.ContaDto;
import br.com.banco.model.Conta;

public interface ContaInterface {
    Conta criar(ContaDto contaDto);

    Conta encontrarPeloId(int id);

    Conta encontrarPeloNome(String nomeResponsavel);

    Conta atualizar(int id, ContaDto contaDto);

    void deletar(int id);
}
