package ru.shifu.tracker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TrackerSQL implements ITracker, AutoCloseable {

    Connection conn = null;

    public TrackerSQL() {
        getConnection();
        initializationTable();
    }

    /**
     * Метод подключения к серверу базы данных.
     */
    private void getConnection() {
        String uml = "jdbc:postgresql://localhost:5432/items";
        String name = "root";
        String password = "root";
        try {
            this.conn = DriverManager.getConnection(uml, name, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод создает таблицу если она еще не созданна.
     */
    private void initializationTable() {
        try (PreparedStatement ps = this.conn.prepareStatement(
                "CREATE table if not exists items (id serial primary key, name character varying(2000), description text)")) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Мотод добавляет в бд.
     * @param item item.
     * @return item.
     */
    @Override
    public Item add(Item item) {
            try (PreparedStatement st = this.conn.prepareStatement("insert into items(name, description) values(?, ?) returning id")) {
                st.setString(1, item.getName());
                st.setString(2, item.getDescription());
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    item.setId(rs.getInt(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return item;
    }

    /**
     * Метод ищет заявку в БД по ID.
     * @param id item.
     * @return item.
     */
    @Override
    public Item findById(int id) {
        Item item = null;
        try (PreparedStatement ps = this.conn.prepareStatement("select * from items where id = ? ")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                item = new Item(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Метод редактирует item в бд.
     * @param id id item.
     * @param item item.
     * @return true and false.
     */
    @Override
    public boolean replace(int id, Item item) {
        boolean result = false;
        try (PreparedStatement ps = this.conn.prepareStatement("UPDATE items SET name = ?, description = ? WHERE id = ?")) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getName());
            ps.setInt(3, id);
            ps.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод удаления item из бд.
     * @param id id item.
     * @return true and false.
     */
    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (PreparedStatement ps = this.conn.prepareStatement("DELETE FROM items where id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод дастает все items из бд.
     * @return список всех items.
     */
    @Override
    public List<Item> getAll() {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM items")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new Item(rs.getString("name"), rs.getString("description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод дастает все items по Name из бд.
     * @param key name.
     * @return список всех items.
     */
    @Override
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM items where name = ?")) {
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new Item(rs.getString("name"), rs.getString("description"), rs.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * Close resources.
     * @throws Exception exception.
     */
    @Override
    public void close() throws Exception {
        if (this.conn != null) {
            conn.close();
        }
    }
}
