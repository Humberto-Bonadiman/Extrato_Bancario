package br.com.banco.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TransferenciaDto {
    private LocalDate dataTransferencia;
    private double valor;
    private String tipo;
    private String nomeOperadorTransacao;
    private int contaId;
}
