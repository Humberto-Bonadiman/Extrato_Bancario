package br.com.banco.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OperadorDto {
    private int idConta;
    private String nomeOperadorTransacao;
}
