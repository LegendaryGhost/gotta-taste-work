package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Step {
    
    private int id;
    private int idRecipe = 1;
    private int number = 1;
    private String instruction = "";

    public Step() {}

    public Step(int id) {
        this.id = id;
    }

    public Step(int idRecipe, int number, String instruction) {
        this.idRecipe = idRecipe;
        this.number = number;
        this.instruction = instruction;
    }

    public Step(int id, int idRecipe, int number, String instruction) {
        this.id = id;
        this.idRecipe = idRecipe;
        this.number = number;
        this.instruction = instruction;
    }

    public static ArrayList<Step> all() throws Exception {
        ArrayList<Step> steps = new ArrayList<Step>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getPostgesConnection();
            statement = connection.prepareStatement(
                "SELECT * FROM step"
            );
            resultSet = statement.executeQuery();

            int id;
            int idRecipe;
            int number;
            String instruction;
            while (resultSet.next()) {
                id = resultSet.getInt("id_step");
                idRecipe = resultSet.getInt("id_recipe");
                number = resultSet.getInt("step_number");
                instruction = resultSet.getString("instruction");

                steps.add(
                    new Step(id, idRecipe, number, instruction)
                );
            }
        } catch (Exception e) {
            throw e;
        } finally {
            resultSet.close();
            statement.close();
            connection.close();
        }

        return steps;
    }

    public void find() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getPostgesConnection();
            statement = connection.prepareStatement(
                "SELECT * FROM step"
                + " WHERE id_step = ?"
            );
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                idRecipe = resultSet.getInt("id_recipe");
                number = resultSet.getInt("step_number");
                instruction = resultSet.getString("instruction");
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
                "INSERT INTO step(id_recipe, step_number, instruction)"
                + " VALUES (?, ?, ?)"
            );
            statement.setInt(1, idRecipe);
            statement.setInt(2, number);
            statement.setString(3, instruction);
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
                "UPDATE step"
                + " SET id_recipe = ?, step_number = ?, instruction = ?"
                + " WHERE id_step = ?"
            );
            statement.setInt(1, idRecipe);
            statement.setInt(2, number);
            statement.setString(3, instruction);
            statement.setInt(4, id);
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
                "DELETE FROM step"
                + " WHERE id_step = ?"
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
    
    public int getIdRecipe() {
        return idRecipe;
    }
    
    public void setIdRecipe(int idRecipe) {
        this.idRecipe = idRecipe;
    }
    
    public int getNumber() {
        return number;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
    
    public String getInstruction() {
        return instruction;
    }

    public String getInstructionExcerpt() {
        return instruction.length() < 21 ? instruction : instruction.substring(0, 21) + "...";
    }
    
    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    @Override
    public String toString() {
        return "Step [id=" + id + ", idRecipe=" + idRecipe + ", number=" + number + ", instruction=" + instruction
                + "]";
    }

}
