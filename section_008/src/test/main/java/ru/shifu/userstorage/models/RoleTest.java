package ru.shifu.userstorage.models;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * RoleTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.5$
 * @since 0.1
 * 02.02.2019
 */
public class RoleTest {

    @Test
    public void whenGetRoleThenResult() {
        final Role[] values = Role.values();
        assertThat(values.length, is(2));
        assertThat(Role.values()[0], is(Role.ADMIN));
        assertThat(Role.values()[1], is(Role.USER));
    }
}