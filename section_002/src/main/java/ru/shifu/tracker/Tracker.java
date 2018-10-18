package ru.shifu.tracker;

import java.util.ArrayList;
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
    private ArrayList<Item> items = new ArrayList<>();
    /**
     * Указатель ячейки для новой заявки.
     */
    private static final Random RN = new Random();
    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
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
        Item result = null;
        for (Item value : this.items) {
            if (value.getId().equals(id)) {
               result = value;
            }
        }
        result.setName(item.getName());
        result.setDescription(item.getDescription());
        result.setCreate(item.getCreate());
    }
    /**
     * Метод удаление заявок. даление ячейки в массиве this.items.
     * @param id для удаления необходимо найти ячейку в массиве по id.
     */
    public void delete(String id) {
        Item result = null;
        for (Item value : this.items) {
            if (value.getId().equals(id)) {
                result = value;
                break;
            }
        }
        this.items.remove(result);
    }
    /**
     * Метод получение списка всех заявок.
     * @return копию массива this.items без null элементов.
     */
    public ArrayList<Item> findAll() {
      return this.items;
    }
    /**
     * Метод получение списка по имени.
     * @param key ключ по которому будем искать.
     * @return массив со списком собранный по имени.
     */
    public ArrayList<Item> findByName(String key) {
        ArrayList<Item> result = new ArrayList<>();
        for (Item value : this.items) {
            if (value.getName().equals(key)) {
                result.add(value);
                break;
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

