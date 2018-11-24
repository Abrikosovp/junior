package ru.shifu.nonblocking;

import java.util.concurrent.ConcurrentHashMap;
/**
 * Hash.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 24.11.2018.
 **/
public class Hash {

    ConcurrentHashMap<Integer, Base> hash;

    public Hash() {
        this.hash = new ConcurrentHashMap<>();
    }

    /**
     * add.
     * @param model model.
     */
    public void add(Base model) {
        this.hash.putIfAbsent(model.getId(), model);
    }

    /**
     * Метод изменений.
     * первый пользователь изменил поле имя и второй сделал тоже самое.
     * нужно перед обновлением данных проверить.
     * что текущий пользователь не затер данные другого пользователя.
     * если данные затерты то выбросить OptimisticException
     * @param model
     */
   public void update(Base model) {
        hash.computeIfPresent(model.getId(), (key, value) -> {
           if (hash.get(key).getVersion() != model.getVersion()) {
               throw new OptimisticException("The current version of the model differs by more than one.");
           }
           model.increment();
           return model;
        });
   }

    /**
     * delete.
     * @param model model.
     */
   public void delete(Base model) {
        hash.remove(model.getId());
   }

    /**
     * get.
     * @param model model.
     * @return Base.
     */
   public Base getBase(Base model) {
        return this.hash.get(model);
   }
}
