package ru.shifu.chess.exceptions;

public class ImpossibleMoveException extends RuntimeException {
    public ImpossibleMoveException(String massage) {
        super(massage);
    }
}
