package ru.job4j.service;

import ru.job4j.model.*;
import java.util.*;

/**
 * Service
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.0.
 * @since 20.02.19
 */
public interface Service {

    /**
     * Add account.
     * @param accounts account.
     * @return message about operation.
     */
    String addAccount(Accounts accounts);

    /**
     * Map halls.
     * @return map halls.
     */
    Map<Integer, List<Hall>> getHalls();

    /**
     * Get hall by id.
     * @param id hall id.
     * @return Hall
     */
    Hall getHallsById(int id);
}
