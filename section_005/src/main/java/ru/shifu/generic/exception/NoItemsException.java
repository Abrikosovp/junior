package ru.shifu.generic.exception;
/**
 * NoItemsException.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 27.10.2018.
 **/
public class NoItemsException extends Exception {
    public NoItemsException(String massage) {
        super(massage);
    }
}
