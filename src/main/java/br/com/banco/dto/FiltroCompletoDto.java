package br.com.banco.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class FiltroCompletoDto {
    private int idConta;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private String nomeOperadorTransacao;
}
