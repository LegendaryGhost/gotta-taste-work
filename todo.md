# Back-end
## Database design
### Tables
- [x] gotta_taste_user (id_user, firstname, lastname, email, user_password)
- [x] category (id_category, category_name)
- [x] recipe (id_recipe, title, recipe_description, id_category, cook_time, created_by, created_date)
- [x] ingredient (id_ingredient, ingredient_name, unit)
- [x] recipe_ingredient (id_reipe, id_ingredient, quantity)
- [x] step (id_step, id_recipe, step_number, instruction)

### Fake data
- [x] Fill tables with realistic data

## DAO
### User
- [x] Method create
- [x] Method findByEmailAndPassword

### Category
- [x] Method all
- [x] Method find
- [x] Method create
- [x] Method update
- [x] Method delete

### Recipe
- [x] Method all
- [x] Method find
- [x] Method create
- [x] Method update
- [x] Method delete

### Step
- [x] Method all
- [x] Method find
- [x] Method create
- [x] Method update
- [x] Method delete

### Ingredient
- [x] Method all
- [x] Method find
- [x] Method create
- [x] Method update
- [x] Method delete

## Servlet
### LoginServlet
- [ ] GET: Allow users to login

### CategoryServlet
- [ ] GET:
    - [ ] Show category list
    - [ ] Delete a category
- [ ] POSt:
    - [ ] Add a new category
    - [ ] Update a category

### CategoryFormServlet
- [ ] GET:
    - [ ] Add action: give an empty form
    - [ ] Update action: fill the form with the data to update

### IngredientServlet
- [ ] GET:
    - [ ] Show ingredient list
    - [ ] Delete a ingredient
- [ ] POSt:
    - [ ] Add a new ingredient
    - [ ] Update a ingredient

### IngredientFormServlet
- [ ] GET:
    - [ ] Add action: give an empty form
    - [ ] Update action: fill the form with the data to update

### RecipeServlet
- [ ] GET:
    - [x] Show recipe list
    - [ ] Delete a recipe
- [ ] POSt:
    - [ ] Add a new recipe
    - [ ] Update a recipe

### RecipeFormServlet
- [ ] GET:
    - [ ] Add action: give an empty form
    - [ ] Update action: fill the form with the data to update

### StepServlet
- [ ] GET:
    - [ ] Show step list
    - [ ] Delete a step
- [ ] POSt:
    - [ ] Add a new step
    - [ ] Update a step

### StepFormServlet
- [ ] GET:
    - [ ] Add action: give an empty form
    - [ ] Update action: fill the form with the data to update

### XML webapp
- [ ] Configure the web.xml file
- [ ] Add servlets into web.xml

# Front-end
## HTML
### Login
- [x] Login form

### Category
- [ ] Category table
- [ ] Category form

### Ingredient
- [ ] Ingredient table
- [ ] Ingredient form

### Recipe
- [x] Recipe table
- [ ] Recipe form

### Step
- [ ] Step table
- [ ] Step form

## JSP (Java Server Pages)
- [ ] Convert HTML to JSP
    - [ ] Category table
    - [ ] Category form
    - [ ] Ingredient table
    - [ ] Ingredient form
    - [ ] Recipe table
    - [ ] Recipe form
    - [ ] Steps table
    - [ ] Steps form
