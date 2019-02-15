package ru.shifu.userstorage.logic;

import ru.shifu.userstorage.models.PersonalData;
import ru.shifu.userstorage.models.Role;
import ru.shifu.userstorage.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ValidateStub implements Validate  {

    public ValidateStub() { }

    /**
     * Instance of storage class.
     */
    private final Map<String, User> store = new HashMap<>();

    /**
     * Actions storage.
     */
    private final Map<Action.Type, Function<User, String>> dispatch = new HashMap<>();

    private static ValidateStub instance;
    /**
     * Only one instance of this class will be created.
     * @return instance of class.
     */
    public static Validate getInstance() {
        if (instance == null) {
            synchronized (ValidateService.class) {
                if (instance == null) {
                    instance = new ValidateStub().init();

                }
            }
        }
        return instance;
    }
    /**
     * Add new User to storage.
     * @return message to logic layout.
     */
    private Function<User, String> add() {
        return user -> {
            String result = "User already exists";
            if (this.store.put(user.getId(), user) == null) {
                result = String.format("User with id: %s was added.", user.getId());
            }
            return result;
        };
    }

    /**
     * Removes user by id.
     * @return message to logic layout.
     */
    private Function<User, String> delete() {
        return user -> {
            String result = "User already exists";
            if (this.store.remove(user.getId()) != null) {
                result = String.format("User with id: %s was delete.", user.getId());
            }
            return result;
        };
    }

    /**
     * Changes user if it exists.
     * @return message to logic layout.
     */
    private Function<User, String> update() {
        return user -> {
            String result = "User already exists";
            if (this.store.replace(user.getId(), user) != null) {
                result = String.format("User with id: %s was update.", user.getId());
            }
            return result;
        };
    }

    /**
     * Init dispatcher.
     * @return current object.
     */
    public ValidateStub init() {
        this.load(Action.Type.ADD, this.add());
        this.load(Action.Type.DELETE, this.delete());
        this.load(Action.Type.UPDATE, this.update());
        doAction(Action.Type.ADD, new User("1", "root", "root", Role.ADMIN, new PersonalData("Pavel", "abriksovp@mail.ru", "Russia", "Moscow")));
        return this;
    }
    /**
     * Load handlers for actions.
     * @param type action key.
     * @param handle Handler.
     */
    private void load(Action.Type type, Function<User, String> handle) {
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
        List<User> result = new ArrayList<>();
        result.addAll(store.values());
        return result;
    }
    /**
     * Find id user in storage.
     * @return list of all users.
     */
    public User findById(String id) {
        return store.get(String.valueOf(id));
    }

    /**
     * The method checks if the user exists in the store.
     * @param login to search.
     * @param password to search.
     * @return id if exists else -1;
     */
    public long isRegistered(String login, String password) {
        long id = -1;
        for (User user : this.findAll()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                id = Long.parseLong(user.getId());
                break;
            }
        }
        return id;
    }

    @Override
    public boolean fullDelete() {
        boolean result = false;
        if (!this.store.isEmpty()) {
            this.store.clear();
            result = true;
        }
        return result;
    }

    @Override
    public List<String> getCountries() {
        return null;
    }

    @Override
    public List<String> getCities(String country) {
        return null;
    }
}
