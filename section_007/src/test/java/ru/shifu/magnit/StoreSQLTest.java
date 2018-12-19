package ru.shifu.magnit;

import org.junit.Test;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StoreSQLTest {

    @Test
    public void whenAddTenElementsAndSelectThanTrue() {
        StoreSQL sql = new StoreSQL();
        sql.setConnection(new File("config.ini"));
        sql.createStructure();
        try {
            sql.generate(10);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Entry> result = sql.selectData();
        sql.close();

        List<Entry> expected = new ArrayList<>();
        for (int index = 1; index <= 10; index++) {
            Entry toAdd = new Entry();
            toAdd.setField(index);
            expected.add(toAdd);
        }
        assertThat(result, is(expected));
    }
}