package br.com.banco.exception.messages;

public class TransferenciaNaoEncontradaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TransferenciaNaoEncontradaException() {
        super("Transferência não encontrada");
    }
}
