package ru.shifu.magnit;


import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SumSAXParserTest {
    File cfg = new File("config.ini");
    File xmlOne = new File("storexml.xml");
    File xmlTwo = new File("newstorexml.xml");
    File xls = new File("scheme.xsl");

    @Test
    public void completeTest() {
        StoreSQL sql = new StoreSQL();
        sql.setConnection(cfg);
        sql.createStructure();
        try {
            sql.generate(100);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Entry> entry = sql.selectData();

        StoreXML storeXML = new StoreXML(this.xmlOne);
        storeXML.save(entry);
        sql.close();


        ConvertXSQT converter = new ConvertXSQT();
        try {
            converter.convert(xmlOne, xmlTwo, xls);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        SumSAXParser parser = new SumSAXParser();
        
        try {
            parser.parseSum(xmlTwo);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        long sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        assertThat(parser.getSum(), is(sum));
    }
}