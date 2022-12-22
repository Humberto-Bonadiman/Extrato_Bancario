package br.com.banco.service;

import br.com.banco.dto.ContaDto;
import br.com.banco.exception.messages.ContaNaoEncontradaException;
import br.com.banco.model.Conta;
import br.com.banco.repository.ContaRepository;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService implements ContaInterface {

    @Autowired
    static
    ContaRepository contaRepository;

    @Override
    public Conta criar(@NotNull ContaDto contaDto) {
        Conta conta = new Conta(contaDto.getNomeResponsavel());
        contaRepository.save(conta);
        return conta;
    }

    @Override
    public Conta encontrarPeloId(int id) {
        return encontrarContaPeloId(id);
    }

    @Override
    public Conta atualizar(int id, @NotNull ContaDto contaDto) {
        Conta conta = encontrarContaPeloId(id);
        conta.setNomeResponsavel(contaDto.getNomeResponsavel());
        contaRepository.save(conta);
        return conta;
    }

    @Override
    public void deletar(int id) {
        contaRepository.deleteById(id);
    }

    public static @NotNull Conta encontrarContaPeloId(int id) {
        Optional<Conta> contaValida = contaRepository.findById(id);
        if (contaValida.isEmpty()) {
            throw new ContaNaoEncontradaException();
        }
        return contaValida.get();
    }
}
