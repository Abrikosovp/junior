package ru.shifu.magnit;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
/**
 * SAX parser class.
 *
 * @author Pavel Abrikosov(abrikosovp@mail.ru)
 * @version 0.1$
 * @since 0.1
 * 18.12.2018
 */
public class SumSAXParser {

    private long sum;

    public void parseSum(File file) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        FieldCounter counter = new FieldCounter();
        parser.parse(file, counter);
        sum = counter.getSum();
        System.out.println(String.format("Sum of all field's is %s", sum));
    }

    public long getSum() {
        return sum;
    }
}
