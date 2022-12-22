package br.com.banco.exception.messages;

public class ContaNaoEncontradaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ContaNaoEncontradaException() {
        super("Conta não encontrada");
    }
}
