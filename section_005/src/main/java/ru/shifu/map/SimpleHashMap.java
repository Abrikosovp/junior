package ru.shifu.map;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * SimpleHashMap.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 3.11.2018.
 **/
public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Node<K, V>> {
    private Node<K, V>[] table;
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    static final int MAXIMUM_CAPACITY = 1 << 30;
    static final float DEFAULT_LOAD_FACTOR = 0.50F;
    private int size;
    private int modCount;

    public SimpleHashMap() {
        this.table = new Node[DEFAULT_INITIAL_CAPACITY];
        this.size = 0;
        this.modCount = 0;
    }

    public int size() {
        return this.size;
    }
    public int sssss() {
        return this.table.length;
    }

    /**
     * Метод добавляет элемент в коллекцию.
     * @param key уникальный ключ.
     * @param value значение.
     * @return true / false
     */
    public boolean insert(K key, V value) {
        return putVal(this.hash(key), key, value);
    }

    /**
     * Метод поиск значения по ключу.
     * @param key клуникальный ключ.
     * @return значение.
     */
    public V get(K key) {
        int si = this.table.length;
        int index = (si - 1) & hash(key);
        return this.table[index] == null ? null : this.table[index].value;
    }

    /**
     * Метод уддаляет ключ и значение.
     * @param key уникальный ключ.
     * @return true/ false
     */
    public boolean delete(K key) {
        boolean result = false;
        int si = this.table.length;
        int index = (si - 1) & hash(key);
        Node<K, V> e = this.table[index];
        if (e != null && key.equals(e.key)) {
            this.table[index] = null;
            size--;
            modCount--;
            result = true;
        }
        return result;
    }

    private boolean putVal(int hash, K key, V value) {
        boolean result = false;
        int si = this.table.length;
        int index = (si - 1) & hash;
        Node<K, V> node = this.table[index];
        if (node != null && key.equals(node.key)) {
            node.value = value;
        } else if (node == null) {
            this.table[index] = new Node<>(key, value, hash);
            result = true;
            size++;
        }
        if (this.size >= si * this.DEFAULT_LOAD_FACTOR) {
            this.resize();
        }
        modCount++;
        return result;
    }

    private void resize() {
        final int oldCap = this.table.length;
        if (oldCap < this.MAXIMUM_CAPACITY) {
            int newCap = oldCap << 1;
            Node<K, V>[] newNode = new Node[newCap];
            for (int i = 0; i < this.table.length; i++) {
                Node<K, V> e = this.table[i];
                if (e != null) {
                    this.table[i] = null;
                    int newIndex = (newCap - 1) & e.hesh;
                    newNode[newIndex] = e;

                }
            }
            this.table = newNode;
        }
    }

    private int hash(K key) {
        int h = key.hashCode();
        return (key == null) ? 0 : (h) ^ (h >>> 16);
    }

    @Override
    public Iterator<Node<K, V>> iterator() {
        return new Iterator<Node<K, V>>() {
            private int sizeIter = 0;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                boolean result = false;
                for (int i = this.sizeIter; i < table.length; i++) {
                    if (size != 0 && table[i] != null) {
                        this.sizeIter = i;
                        result = true;
                        break;
                    }
                }
                return result;
            }

            @Override
            public Node<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[this.sizeIter++];
            }
        };
    }

    public static class Node<K, V> {
        final K key;
        private V value;
        final int hesh;

        public Node(K key, V value, int hesh) {
            this.key = key;
            this.value = value;
            this.hesh = hesh;
        }

        public V getValue() {
            return value;
        }
    }
}
