package ru.shifu.magnit;


import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StoreXMLTest {

    @Test
    public void name() {
        StoreSQL sql = new StoreSQL();
        sql.setConnection(new File("config.ini"));
        sql.createStructure();
        try {
            sql.generate(10);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Entry> list = sql.selectData();

        StoreXML xml = new StoreXML(new File("storexml.xml"));
        xml.save(list);
        sql.close();

        Entries result = new Entries();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();
            result = (Entries) un.unmarshal(new File("storexml.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        List<Entry> expected = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Entry entry = new Entry();
            entry.setField(i);
            expected.add(entry);
        }
        assertThat(result.entry, is(expected));
        assertThat(list, is(expected));
    }
}