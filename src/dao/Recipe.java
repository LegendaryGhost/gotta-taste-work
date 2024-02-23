package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Recipe {
    
    private int id;
    private String title;
    private String description;
    private int idCategory;
    private LocalTime cookTime;
    private String createdBy;
    private LocalDate createdDate;

    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Recipe(int id) {
        this.id = id;
    }

    public Recipe(String title, String description, int idCategory, LocalTime cookTime, String createdBy,
            LocalDate createdDate) {
        this.title = title;
        this.description = description;
        this.idCategory = idCategory;
        this.cookTime = cookTime;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public Recipe(int id, String title, String description, int idCategory, LocalTime cookTime, String createdBy,
            LocalDate createdDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.idCategory = idCategory;
        this.cookTime = cookTime;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public static ArrayList<Recipe> all() throws Exception {
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getPostgesConnection();
            statement = connection.prepareStatement(
                "SELECT * FROM recipe"
            );
            resultSet = statement.executeQuery();

            int id;
            String title;
            String description;
            int idCategory;
            LocalTime cookTime;
            String createdBy;
            LocalDate createdDate;
            while (resultSet.next()) {
                id = resultSet.getInt("id_recipe");
                title = resultSet.getString("title");
                description = resultSet.getString("recipe_description");
                idCategory = resultSet.getInt("id_category");
                cookTime = resultSet.getTime("cook_time").toLocalTime();
                createdBy = resultSet.getString("created_by");
                createdDate = resultSet.getDate("created_date").toLocalDate();

                recipes.add(
                    new Recipe(id, title, description, idCategory, cookTime, createdBy, createdDate)
                );
            }
        } catch (Exception e) {
            throw e;
        } finally {
            resultSet.close();
            statement.close();
            connection.close();
        }

        return recipes;
    }

    public void find() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getPostgesConnection();
            statement = connection.prepareStatement(
                "SELECT * FROM recipe"
                + " WHERE id_recipe = ?"
            );
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                id = resultSet.getInt("id_recipe");
                title = resultSet.getString("title");
                description = resultSet.getString("recipe_description");
                idCategory = resultSet.getInt("id_category");
                cookTime = resultSet.getTime("cook_time").toLocalTime();
                createdBy = resultSet.getString("created_by");
                createdDate = resultSet.getDate("created_date").toLocalDate();
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
                "INSERT INTO recipe(title, recipe_description, id_category, cook_time, created_by, created_date)"
                + " VALUES (?, ?, ?, ?, ?, ?)"
            );
            statement.setString(1, title);
            statement.setString(2, description);
            statement.setInt(3, idCategory);
            statement.setDate(4, Date.valueOf(createdDate));
            statement.setString(5, createdBy);
            statement.setDate(6, Date.valueOf(createdDate));
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
                "UPDATE recipe"
                + " SET title = ?, recipe_description = ?, id_category = ?, cook_time = ?, created_by = ?, created_date = ?"
                + " WHERE id_recipe = ?"
            );
            statement.setString(1, title);
            statement.setString(2, description);
            statement.setInt(3, idCategory);
            statement.setDate(4, Date.valueOf(createdDate));
            statement.setString(5, createdBy);
            statement.setDate(6, Date.valueOf(createdDate));
            statement.setInt(7, id);
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
                "DELETE FROM recipe"
                + " WHERE id_recipe = ?"
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
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }

    public String getDescriptionExcerpt() {
        return description.length() < 21 ? description : description.substring(0, 21) + "...";
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getIdCategory() {
        return idCategory;
    }
    
    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
    
    public LocalTime getCookTime() {
        return cookTime;
    }

    public String getFormattedCookTime() {
        return cookTime.format(timeFormatter);
    }
    
    public void setCookTime(LocalTime cookTime) {
        this.cookTime = cookTime;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public LocalDate getCreatedDate() {
        return createdDate;
    }
    
    public String getFormattedCreatedDate() {
        return createdDate.format(dateFormatter);
    }
    
    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Recipe [id=" + id + ", title=" + title + ", description=" + description + ", idCategory=" + idCategory
                + ", cookTime=" + cookTime + ", createdBy=" + createdBy + ", createdDate=" + createdDate + "]";
    }

}
