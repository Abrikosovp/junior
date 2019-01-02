package ru.shifu.parser;

import java.time.LocalDateTime;

/**
 * The class describes the vacancy.
 * Contains the name of the job and the date of publication.
 * @author Pavel Abrikosov(abrikosovp@mail.ru)
 * @version 0.1$
 * @since 0.1
 * 31.12.2018
 */
public class Vacancy {
    private final String name;
    private final String href;
    private final LocalDateTime date;

    public Vacancy(String name, String href, LocalDateTime date) {
        this.name = name;
        this.href = href;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getHref() {
        return href;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
