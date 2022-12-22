package br.com.banco.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RetornoFiltroCompleto {
    private int idConta;
    private String dataInicial;
    private String dataFinal;
    private String nomeOperadorTransacao;
}
