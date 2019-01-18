package ru.shifu.userstorage.logic;

import ru.shifu.userstorage.persistent.MemoryStore;
import ru.shifu.userstorage.persistent.Store;
import ru.shifu.userstorage.persistent.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
/**
 * Class for data validates.
 * Logic layout.
 * Checks data for compliance with conditions.
 * Based on Singleton pattern and Dispatch patter by Petr Arsentev (parsentev@uandex.ru).
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.1$
 * @since 0.1
 * 18.01.2019
 */
public class ValidateService {

    public ValidateService() { }

    /**
     * Instance of storage class.
     */
    private final Store store = MemoryStore.getInstance();

    /**
     * Actions storage.
     */
    private final Map<Action.Type, Function<User, String>> dispatch = new HashMap<>();

    private static ValidateService instance;
    /**
     * Only one instance of this class will be created.
     * @return instance of class.
     */
    public static ValidateService getInstance() {
        if (instance == null) {
            synchronized (ValidateService.class) {
                if (instance == null) {
                    instance = new ValidateService();
                }
            }
        }
        return instance;
    }
    /**
     * Add new User to storage.
     * @return message to logic layout.
     */
    public Function<User, String> add() {
        return user -> {
            String result = "User already exists";
            if (!store.add(user)) {
                result = String.format("User with id: %s was added.", user.getId());
            }
            return result;
        };
    }

    /**
     * Removes user by id.
     * @return message to logic layout.
     */
    public Function<User, String> delete() {
        return user -> {
            String result = "User already exists";
            if (store.delete(user)) {
                result = String.format("User with id: %s was delete.", user.getId());
            }
            return result;
        };
    }

    /**
     * Changes user if it exists.
     * @return message to logic layout.
     */
    public Function<User, String> update() {
        return user -> {
            String result = "User already exists";
            if (store.update(user)) {
                result = String.format("User with id: %s was update.", user.getId());
            }
            return result;
        };
    }

    /**
     * Init dispatcher.
     * @return current object.
     */
    public ValidateService init() {
        this.load(Action.Type.ADD, this.add());
        this.load(Action.Type.UPDATE, this.update());
        this.load(Action.Type.DELETE, this.delete());
        return this;
    }
    /**
     * Load handlers for actions.
     * @param type action key.
     * @param handle Handler.
     */
    public void load(Action.Type type, Function<User, String> handle) {
        this.dispatch.put(type, handle);
    }

    /**
     *
     * @param action to do with user.
     * @param user for action.
     * @return report on the work done.
     */
    public String doAction(final Action.Type action, final User user) {
        return this.dispatch.get(action).apply(user);
    }

    /**
     * Find all users in storage.
     * @return list of all users.
     */
    public List<User> findAll() {
        return store.findAll();
    }
}
