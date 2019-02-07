package ru.shifu.userstorage.persistent;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shifu.userstorage.models.Role;
import ru.shifu.userstorage.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class implements the user storage in DateBase.
 * Class is based on the Singleton pattern.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.3$
 * @since 0.1
 * 24.01.2019
 */
public class DbStore implements Store {

    private static final Config CONF = Config.getInstance();

    private static final Logger LOGGER = LoggerFactory.getLogger(DbStore.class);

    private static final BasicDataSource SOURCE = new BasicDataSource();

    private static final DbStore INSTANCE = new DbStore();

    public DbStore() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl(CONF.getValue("get.url"));
        SOURCE.setUsername(CONF.getValue("get.name"));
        SOURCE.setPassword(CONF.getValue("get.password"));
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        this.prepareStructure();
    }

    /**
     * Returns instance.
     *
     * @return instance of the class.
     */
    public static DbStore getInstance() {
        return INSTANCE;
    }

    /**
     * Adds user to the database.
     *
     * @param user to be added.
     * @return true, if added.
     */
    @Override
    public boolean add(User user) {
        Boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(CONF.getValue("get.add"))) {
            st.setString(1, user.getName());
            st.setString(3, user.getPassword());
            st.setString(2, user.getLogin());
            st.setString(4, user.getRole().name());
            st.setString(5, user.getEmail());
            st.setDate(6, new Date(user.getCreateDate().getTime()));
            st.executeUpdate();
            result = true;
        } catch (Exception e) {
            LOGGER.error("User ADD ERROR!", e);
        }
        return result;
    }

    /**
     * Updates existed user.
     *
     * @param user to be updated.
     * @return true if updated.
     */
    @Override
    public boolean update(User user) {
        Boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(CONF.getValue("get.update"))) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getPassword());
            st.setString(4, user.getRole().name());
            st.setString(5, user.getEmail());
            st.setLong(6, Long.parseLong(user.getId()));
            st.executeUpdate();
            result = true;
        } catch (Exception e) {
            LOGGER.error("User update ERROR!", e);
        }
        return result;
    }

    /**
     * Delete user from database by id.
     *
     * @param user to be deleted.
     * @return true, if deleted.
     */
    @Override
    public boolean delete(User user) {
        Boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(CONF.getValue("get.delete"))) {
            st.setLong(1, Long.parseLong(user.getId()));
            st.executeUpdate();
            result = true;
        } catch (Exception e) {
            LOGGER.error("User delete ERROR!", e);
        }
        return result;
    }

    /**
     * Finds all users in the database.
     * @return list of all users in the database.
     */
    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(CONF.getValue("get.findAll"))) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(createUser(rs));
            }
        } catch (Exception e) {

            LOGGER.error("User find all ERROR!", e);
        }
        return result;
    }

    /**
     * Finds single user in the database.
     * @param id to search.
     * @return found user.
     */
    @Override
    public User findById(String id) {
        User result = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(CONF.getValue("get.findById"))) {
            st.setLong(1, Long.parseLong(id));

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result = createUser(rs);
            }
        } catch (Exception e) {
            LOGGER.error("User find by id ERROR!", e);
        }
        return result;
    }

    /**
     * Finds single user in the database.
     * @param user to search.
     * @return found user.
     */
    @Override
    public boolean validate(User user) {
        boolean result = true;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(CONF.getValue("get.valid"))) {
            st.setString(1, user.getLogin());
            st.setString(2, user.getEmail());
            ResultSet rs = st.executeQuery();
            while (rs.next() && !rs.getString("id").equals(user.getId())) {
                result = false;
            }
        } catch (Exception e) {
            LOGGER.error("User validate id , login, email ERROR!", e);
        }
        return result;
    }

    /**
     * Delete user from database.
     *
     * @return true, if deleted.
     */
    @Override
    public boolean fullDelete() {
        Boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(CONF.getValue("get.fullDelete"))) {
            st.executeUpdate();
            result = true;
        } catch (Exception e) {
            LOGGER.error("User full delete ERROR!", e);
        }
        return result;
    }

    /**
     * Create user.
     *
     * @param rs resultSet from statement.
     * @return item.
     * @throws SQLException If an error occurs.
     */
    private User createUser(ResultSet rs) throws SQLException {
        String id = rs.getString("id");
        String name = rs.getString("name");
        String login = rs.getString("login");
        String email = rs.getString("email");
        Date data = rs.getDate("created");
        String password = rs.getString("password");
        Role role = Role.valueOf(rs.getString("role"));
        return new User(id, name, login, password, role, email, data);
    }

    /**
     * Prepares structure of the table at first start.
     */
    private void prepareStructure() {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     CONF.getValue("get.crTable"))) {

            st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error creating structure", e);
        }
    }
}