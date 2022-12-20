package br.com.banco.service;

import br.com.banco.dto.TransferenciaDto;
import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransferenciaService implements TransferenciaInterface {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Override
    public Transferencia criar(@NotNull TransferenciaDto transferenciaDto) {
        Conta conta = contaRepository.findById(transferenciaDto.getContaId()).get();
        Transferencia transferencia = new Transferencia(
                transferenciaDto.getDataTransferencia(),
                transferenciaDto.getValor(),
                transferenciaDto.getTipo(),
                transferenciaDto.getNomeOperadorTransacao(),
                transferenciaDto.getContaId(),
                conta
        );
        conta.addAttendance(transferencia);
        transferenciaRepository.save(transferencia);
        return transferencia;
    }

    @Override
    public List<Transferencia> encontrarTransferenciasPeloIdConta(int idConta) {
        return transferenciaRepository.findAllByContaId(idConta);
    }
}
