package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RecipeIngredient {
    
    private int idRecipe;
    private int idIngredient;
    private double quantity;
    
    public RecipeIngredient(int idRecipe) {
        this.idRecipe = idRecipe;
    }

    public void deleteFromIdRecipe() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnection.getPostgesConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(
                "DELETE FROM recipe_ingredient"
                + " WHERE id_recipe = ?"
            );
            statement.setInt(1, idRecipe);
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

    public int getIdRecipe() {
        return idRecipe;
    }
    
    public void setIdRecipe(int idRecipe) {
        this.idRecipe = idRecipe;
    }
    
    public int getIdIngredient() {
        return idIngredient;
    }
    
    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }
    
    public double getQuantity() {
        return quantity;
    }
    
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

}
