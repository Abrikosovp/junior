package ru.shifu.chess.exceptions;

public class FigureNotFoundException extends RuntimeException {
    public FigureNotFoundException(String massage) {
        super(massage);
    }
}
