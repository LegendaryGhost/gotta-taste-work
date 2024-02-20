import dao.User;

public class Main {

    public static void main(String[] args) throws Exception {
        User tiarintsoa = new User("tiarintsoa@gmail.com", "12345678");
        tiarintsoa.findByEmailAndPassword();
        System.out.println(tiarintsoa);
    }
    
}