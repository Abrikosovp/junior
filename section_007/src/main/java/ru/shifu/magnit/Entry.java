package ru.shifu.magnit;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The database item class for converting a database to XML.
 *  @author Pavel Abrikosov(abrikosovp@mail.ru)
 *  @version 0.1$
 *  @since 0.1
 *  18.12.2018
 */
@XmlRootElement
public class Entry {

    private int field;

    public int getField() {
        return field;
    }

    @XmlElement
    public void setField(int field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object obj) {
        boolean valid = false;
        if (obj != null) {
            if (this == obj) {
                valid = true;
            }
            if (!valid && getClass() == obj.getClass()) {
                Entry entry = (Entry) obj;
                valid = this.field == entry.field;
            }
        }
        return valid;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
