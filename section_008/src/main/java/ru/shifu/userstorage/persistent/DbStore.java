package ru.shifu.userstorage.persistent;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * The class implements the user storage in DateBase.
 * Class is based on the Singleton pattern.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.1$
 * @since 0.1
 * 24.01.2019
 */
public class DbStore implements Store {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbStore.class);

    private static final BasicDataSource SOURCE = new BasicDataSource();

    private static final DbStore INSTANCE = new DbStore();

    public DbStore() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/user_store");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("postgres");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        this.prepareStructure();
    }

    /**
     * Returns instance.
     * @return instance of the class.
     */
    public static DbStore getInstance() {
        return INSTANCE;
    }

    /**
     * Adds user to the database.
     * @param user to be added.
     * @return true, if added.
     */
    @Override
    public boolean add(User user) {
        Boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT INTO users(id, name, login, email, created) values (?, ?, ?, ?, ?)")) {
            st.setString(1, user.getId());
            st.setString(2, user.getName());
            st.setString(3, user.getLogin());
            st.setString(4, user.getEmail());
            st.setDate(5, new Date(user.getCreateDate().getTime()));
            st.executeUpdate();
            result = true;
        } catch (Exception e) {
           LOGGER.error("User ADD ERROR!", e);
        }
        return result;
    }

    /**
     * Updates existed user.
     * @param user to be updated.
     * @return true if updated.
     */
    @Override
    public boolean update(User user) {
        Boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("UPDATE users SET name=?, login=?, email=? WHERE id=?")) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setString(4, user.getId());
            st.executeUpdate();
            result = true;
        } catch (Exception e) {
            LOGGER.error("User update ERROR!", e);
        }
        return result;
    }

    /**
     * Delete user from database by id.
     * @param user to be deleted.
     * @return true, if deleted.
     */
    @Override
    public boolean delete(User user) {
        Boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("DELETE FROM users WHERE id=?")) {
            st.setString(1, user.getId());
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
             PreparedStatement st = connection.prepareStatement("SELECT * FROM users")) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String login = rs.getString("login");
                String email = rs.getString("email");
                Date data = rs.getDate("created");
                result.add(new User(id, name, login, email, data));
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
             PreparedStatement st = connection.prepareStatement("SELECT * FROM users WHERE id=?")) {
            st.setString(1, id);

             ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String login = rs.getString("login");
                Date data = rs.getDate("created");
                String email = rs.getString("email");
                result = new User(id, name, login, email, data);
            }
        } catch (Exception e) {
            LOGGER.error("User find by id ERROR!", e);
        }
        return result;
    }

    /**
     * Prepares structure of the table at first start.
     */
    private void prepareStructure() {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS users ("
                             + "id VARCHAR UNIQUE,"
                             + "name VARCHAR(50),"
                             + "login VARCHAR(50),"
                             + "email VARCHAR(50),"
                             + "created DATE\n"
                             + ");")
        ) {

            st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error creating structure", e);
        }
    }
}