package ru.shifu.nonblocking;

public class OptimisticException extends RuntimeException {
    public OptimisticException(String massage) {
        super(massage);
    }
}
