package ru.shifu.chess.exceptions;

public class OccupiedWayException extends RuntimeException {
    public OccupiedWayException(String message) {
        super(message);
    }
}
