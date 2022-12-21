package br.com.banco.utils;

import br.com.banco.model.Conta;
import org.jetbrains.annotations.NotNull;

public class CriaConta {
    public static @NotNull Conta criarConta() {
        Conta conta = new Conta("Teste Cria Conta");
        return conta;
    }
}
