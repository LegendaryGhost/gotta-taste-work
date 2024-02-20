-- Insert into the 'category' table
INSERT INTO category (category_name) VALUES
    ('Appetizers'), ('Main Courses'), ('Desserts');

-- Insert into the 'gotta_taste_user' table
INSERT INTO gotta_taste_user (firstname, lastname, email, user_password) VALUES  
    ('John', 'Doe', 'john.doe@example.com', 'password123'),
    ('Jane', 'Smith', 'jane.smith@example.com', 'password456');
    ('Tiarintsoa', 'Mbolatsiory', 'tiarintsoa@gmail.com', '12345678');

-- Insert into the 'ingredient' table
INSERT INTO ingredient (ingredient_name, unit) VALUES  
    ('Tomatoes', 'grams'),
    ('Basil', 'leaves'),
    ('Sugar', 'grams'),
    ('Flour', 'grams');

-- Insert into the 'recipe' table
INSERT INTO recipe (title, recipe_description, id_category, cook_time, created_by) VALUES  
    ('Tomato Basil Pizza', 'A delicious pizza with fresh tomatoes and basil.',  1, '00:30:00', 'John Doe'),
    ('Chocolate Cake', 'A moist and rich chocolate cake.',  3, '01:00:00', 'Jane Smith');

-- Insert into the 'recipe_ingredient' table
INSERT INTO recipe_ingredient (id_recipe, id_ingredient, quantity) VALUES  
    (1,  1,  200),
    (1,  2,  5),
    (2,  3,  200),
    (2,  4,  250);

-- Insert into the 'step' table
INSERT INTO step (id_recipe, step_number, instruction) VALUES  
    (1,  1, 'Preheat the oven to  200°C.'),
    (1,  2, 'Prepare the pizza dough.'),
    (2,  1, 'Preheat the oven to  180°C.'),
    (2,  2, 'Prepare the cake mix according to the instructions on the package.');
