import java.sql.SQLException;

import dao.DBConnection;
import dao.User;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        User tiarintsoa = new User("Tiarintsoa", "Mbolatsiory", "tiarintsoa@gmail.com", "12345678");
        tiarintsoa.create(DBConnection.getPostgesConnection());
    }
    
}