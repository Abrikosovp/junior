package ru.shifu.magnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;


/**
 * The class converts the List of SQLite database items to XML.
 *
 *  @author Pavel Abrikosov(abrikosovp@mail.ru)
 *  @version 0.1$
 *  @since 0.1
 *  18.12.2018
 */
public class StoreXML {
    /**
     * File to save.
     */
    private File target;

    public StoreXML(File target) {
        this.target = target;
    }

    /**
     * The method saves the list of database elements to XML.
     * @param list of items to convert.
     */
    public void save(List<Entry> list) {
        System.out.println("Database conversion to XML started.");
       Entries entries = new Entries();
       entries.entry = list;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class, Entry.class);
            Marshaller mar = jaxbContext.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            mar.marshal(entries, this.target);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        System.out.println("Database conversion to XML ended.");
    }
}
