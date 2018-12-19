package ru.shifu.magnit;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * A wrapper class containing a list of database items for converting a database to XML.
 *
 * @author Pavel Abrikosov(abrikosovp@mail.ru)
 * @version 0.1$
 * @since 0.1
 * 18.12.2018
 */
@XmlRootElement(name = "entries")
public class Entries {

    @XmlElement(name = "entry")
    public List<Entry> entry;
}
