package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class Review {

    private int id;
    private int idUser;
    private int idRecipe = 1;
    private int rating = 1;
    private String comment = "";
    private LocalDate date;
    
    public Review() {}

    public Review(int id) {
        this.id = id;
    }

    public Review(int idUser, int idRecipe, int rating, String comment) {
        this.idUser = idUser;
        this.idRecipe = idRecipe;
        this.rating = rating;
        this.comment = comment;
    }

    public Review(int idUser, int idRecipe, int rating, String comment, LocalDate date) {
        this.idUser = idUser;
        this.idRecipe = idRecipe;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public Review(int id, int idUser, int idRecipe, int rating, String comment, LocalDate date) {
        this.id = id;
        this.idUser = idUser;
        this.idRecipe = idRecipe;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public static ArrayList<Review> all() throws Exception {
        ArrayList<Review> reviews = new ArrayList<Review>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getPostgesConnection();
            statement = connection.prepareStatement(
                "SELECT * FROM review"
            );
            resultSet = statement.executeQuery();

            int id;
            int idUser;
            int idRecipe;
            int rating;
            String comment;
            LocalDate date;
            while (resultSet.next()) {
                id = resultSet.getInt("id_review");
                idUser = resultSet.getInt("id_user");
                idRecipe = resultSet.getInt("id_recipe");
                rating = resultSet.getInt("rating");
                comment = resultSet.getString("comment");
                date = resultSet.getDate("review_date").toLocalDate();

                reviews.add(
                    new Review(id, idUser, idRecipe, rating, comment, date)
                );
            }
        } catch (Exception e) {
            throw e;
        } finally {
            resultSet.close();
            statement.close();
            connection.close();
        }

        return reviews;
    }

    public void find() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getPostgesConnection();
            statement = connection.prepareStatement(
                "SELECT * FROM review"
                + " WHERE id_review = ?"
            );
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                id = resultSet.getInt("id_review");
                idUser = resultSet.getInt("id_user");
                idRecipe = resultSet.getInt("id_recipe");
                rating = resultSet.getInt("rating");
                comment = resultSet.getString("comment");
                date = resultSet.getDate("review_date").toLocalDate();
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
                "INSERT INTO review(id_user, id_recipe, rating, comment)"
                + " VALUES (?, ?, ?, ?)"
            );
            statement.setInt(1, idUser);
            statement.setInt(2, idRecipe);
            statement.setInt(3, rating);
            statement.setString(4, comment);
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
                "UPDATE review"
                + " SET id_user = ?, id_recipe = ?, rating = ?, comment = ?"
                + " WHERE id_review = ?"
            );
            statement.setInt(1, idUser);
            statement.setInt(2, idRecipe);
            statement.setInt(3, rating);
            statement.setString(4, comment);
            statement.setInt(5, id);
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
            RecipeIngredient recipeIngredient = new RecipeIngredient(id);
            recipeIngredient.deleteFromIdRecipe();

            connection = DBConnection.getPostgesConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(
                "DELETE FROM review"
                + " WHERE id_review = ?"
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
    
    public int getIdUser() {
        return idUser;
    }
    
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    public int getIdRecipe() {
        return idRecipe;
    }
    
    public void setIdRecipe(int idRecipe) {
        this.idRecipe = idRecipe;
    }
    
    public int getRating() {
        return rating;
    }
    
    public void setRating(int rating) {
        this.rating = rating;
    }
    
    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }

    

}
