CREATE DATABASE gotta-taste;

\c gottat-taste;

CREATE TABLE gotta_taste_user (
    id_user INT PRIMARY KEY AUTO_INCREMENT,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    user_password VARCHAR(100) NOT NULL
);

CREATE TABLE category (
    id_category INT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(255) NOT NULL
);

CREATE TABLE recipe (
    id_recipe INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    recipe_description TEXT,
    id_category INT NOT NULL,
    cook_time TIME NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    created_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_category) REFERENCES category(id_category)
);

CREATE TABLE ingredient (
    id_ingredient INT PRIMARY KEY AUTO_INCREMENT,
    ingredient_name VARCHAR(255) NOT NULL,
    unit VARCHAR(50) NOT NULL -- For example, grams, milliliters, teaspoons, etc.
);

CREATE TABLE recipe_ingredient (
    id_recipe INT,
    id_ingredient INT,
    quantity DECIMAL(10,2), -- To store the amount needed for each recipe
    PRIMARY KEY (id_recipe, id_ingredient),
    FOREIGN KEY (id_recipe) REFERENCES recipe(id_recipe),
    FOREIGN KEY (id_ingredient) REFERENCES ingredient(id_ingredient)
);

CREATE TABLE step (
    id_step INT PRIMARY KEY AUTO_INCREMENT,
    id_recipe INT NOT NULL,
    step_number INT NOT NULL UNIQUE,
    snstruction TEXT NOT NULL,
    FOREIGN KEY (id_recipe) REFERENCES recipe(id_recipe)
);
