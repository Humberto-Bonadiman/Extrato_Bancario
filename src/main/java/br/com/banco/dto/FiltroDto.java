package br.com.banco.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class FiltroDto {
    private String nomeOperadorTransacao;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
}
