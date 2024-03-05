package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Ingredient {

    private int id;
    private String name = "";
    private String unit = "";

    public Ingredient() {}
    
    public Ingredient(int id) {
        this.id = id;
    }

    public Ingredient(String name, String unit) {
        this.name = name;
        this.unit = unit;
    }

    public Ingredient(int id, String name, String unit) {
        this.id = id;
        this.name = name;
        this.unit = unit;
    }

    public static ArrayList<Ingredient> all() throws Exception {
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getPostgesConnection();
            statement = connection.prepareStatement(
                "SELECT * FROM ingredient"
            );
            resultSet = statement.executeQuery();

            int id;
            String name;
            String unit;
            while (resultSet.next()) {
                id = resultSet.getInt("id_ingredient");
                name = resultSet.getString("ingredient_name");
                unit = resultSet.getString("unit");

                ingredients.add(
                    new Ingredient(id, name, unit)
                );
            }
        } catch (Exception e) {
            throw e;
        } finally {
            resultSet.close();
            statement.close();
            connection.close();
        }

        return ingredients;
    }

    public static ArrayList<Ingredient> search(
        String searchName,
        String searchUnit
    ) throws Exception {
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getPostgesConnection();

            // Start building the SQL query
            StringBuilder sql = new StringBuilder("SELECT * FROM ingredient");
            sql.append(" WHERE ingredient_name ILIKE ?");
            sql.append(" AND unit ILIKE ?");

            statement = connection.prepareStatement(
                sql.toString()
            );

            // Set the search parameters
            int paramIndex = 1;
            statement.setString(paramIndex, "%" + searchName + "%");
            paramIndex++;
            statement.setString(paramIndex, "%" + searchUnit + "%");
            paramIndex++;

            resultSet = statement.executeQuery();

            int id;
            String name;
            String unit;
            while (resultSet.next()) {
                id = resultSet.getInt("id_ingredient");
                name = resultSet.getString("ingredient_name");
                unit = resultSet.getString("unit");

                ingredients.add(
                    new Ingredient(id, name, unit)
                );
            }
        } catch (Exception e) {
            throw e;
        } finally {
            resultSet.close();
            statement.close();
            connection.close();
        }

        return ingredients;
    }

    public void find() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getPostgesConnection();
            statement = connection.prepareStatement(
                "SELECT * FROM ingredient"
                + " WHERE id_ingredient = ?"
            );
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                name = resultSet.getString("ingredient_name");
                unit = resultSet.getString("unit");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            resultSet.close();
            statement.close();
            connection.close();
        }
    }

    public void create() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnection.getPostgesConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(
                "INSERT INTO ingredient(ingredient_name, unit)"
                + " VALUES (?, ?)"
            );
            statement.setString(1, name);
            statement.setString(2, unit);
            statement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            statement.close();
            connection.close();
        }
    }

    public void update() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnection.getPostgesConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(
                "UPDATE ingredient"
                + " SET ingredient_name = ?, unit = ?"
                + " WHERE id_ingredient = ?"
            );
            statement.setString(1, name);
            statement.setString(2, unit);
            statement.setInt(3, id);
            statement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            statement.close();
            connection.close();
        }
    }

    public void delete() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnection.getPostgesConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(
                "DELETE FROM ingredient"
                + " WHERE id_ingredient = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            statement.close();
            connection.close();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Ingredient [id=" + id + ", name=" + name + ", unit=" + unit + "]";
    }
  
}
