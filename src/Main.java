import dao.Category;
import dao.Ingredient;

public class Main {

    public static void main(String[] args) throws Exception {
        for (Ingredient ingredient : Ingredient.all()) {
            System.out.println(ingredient);
        }
        for (Category category : Category.all()) {
            System.out.println(category);
        }
    }
    
}