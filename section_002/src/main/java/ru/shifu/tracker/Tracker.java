package ru.shifu.tracker;

import java.util.Random;
/**
 * Tracker .
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 09.10.2018.
 **/
public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private Item[] items = new Item[100];
    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;
    private static final Random RN = new Random();
    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[position++] = item;
        return item;
    }
    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }
    /**
     * Метод редактирование заявок . Метод должен заменить ячейку в массиве this.items.
     * Для этого необходимо найти ячейку в массиве по id
     * @param id идентификационный номер, по которому будем искать заявку.
     * @param item заявка которую нужно изменить.
     */
    public void replace(String id, Item item) {
        for (int i = 0; i != this.position; i++) {
            if (item.getId().equals(id)) {
                this.items[i] = item;
                break;
            }
        }
    }
    /**
     * Метод удаление заявок. даление ячейки в массиве this.items.
     * @param id для удаления необходимо найти ячейку в массиве по id.
     */
    public void delete(String id) {
        for (int index = 0; index != this.position; index++) {
            if (this.items[index].getId().equals(id)) {
                System.arraycopy(this.items, index + 1, this.items, index, this.items.length - 1 - index);
                break;
            }
        }
    }
    /**
     * Метод получение списка всех заявок.
     * @return копию массива this.items без null элементов.
     */
    public Item[] findAll() {
        Item[] result = new Item[this.position];
        for (int i = 0; i != this.position; i++) {
            if (this.items[i] != null) {
            result[i] = this.items[i];
            }
        }
        return result;
    }
    /**
     * Метод получение списка по имени.
     * @param key ключ по которому будем искать.
     * @return массив со списком собранный по имени.
     */
    public Item[] findByName(String key) {
        Item[] result = new Item[this.position];
        for (int i = 0; i != this.position; i++) {
            if (this.items[i].getName().equals(key)) {
                result[i] = this.items[i];
            }
        }
        return result;
    }
    /**
     * Метод получение заявки по id.
     * @param id id по которому будем искать.
     * @return item - заявка.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item value:this.items) {
            if (value != null && value.getId().equals(id)) {
                result = value;
            }
        }
        return result;
    }
}

