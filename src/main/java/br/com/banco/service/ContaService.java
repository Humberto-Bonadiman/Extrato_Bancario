package br.com.banco.service;

import br.com.banco.dto.ContaDto;
import br.com.banco.model.Conta;
import br.com.banco.repository.ContaRepository;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService implements ContaInterface {

    @Autowired
    private ContaRepository contaRepository;

    @Override
    public Conta criar(@NotNull ContaDto contaDto) {
        Conta conta = new Conta(contaDto.getNomeResponsavel());
        contaRepository.save(conta);
        return conta;
    }

    @Override
    public Conta encontrarPeloId(Long id) {
        return encontrarContaPeloId(id);
    }

    @Override
    public Conta atualizar(Long id, @NotNull ContaDto contaDto) {
        Conta conta = encontrarContaPeloId(id);
        conta.setNomeResponsavel(contaDto.getNomeResponsavel());
        contaRepository.save(conta);
        return conta;
    }

    @Override
    public void deletar(Long id) {
        contaRepository.deleteById(id);
    }

    private @NotNull Conta encontrarContaPeloId(Long id) {
        Optional<Conta> contaValida = contaRepository.findById(id);
        /**
         * if (contaValida.isEmpty()) {
         *             throw new ExcecaoContaNaoRegistrada();
         *         }
         */
        return contaValida.get();
    }
}
