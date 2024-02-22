import dao.Recipe;

public class Main {

    public static void main(String[] args) throws Exception {
        for (Recipe recipe : Recipe.all()) {
            System.out.println(recipe);
        }
    }
    
}