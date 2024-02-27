<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="dao.Recipe, dao.Category, java.util.ArrayList" %>
<% Recipe recipe = (Recipe)request.getAttribute("recipe"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recette</title>

    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="assets/img/favicon/favicon.ico" />

    <!-- Icons. Uncomment required icon fonts -->
    <link rel="stylesheet" href="assets/vendor/fonts/boxicons.css" />

    <!-- Core CSS -->
    <link rel="stylesheet" href="assets/vendor/css/core.css" class="template-customizer-core-css" />
    <link rel="stylesheet" href="assets/vendor/css/theme-default.css" class="template-customizer-theme-css" />
    <link rel="stylesheet" href="assets/css/demo.css" />

    <!-- Vendors CSS -->
    <link rel="stylesheet" href="assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css" />

    <link rel="stylesheet" href="assets/vendor/libs/apex-charts/apex-charts.css" />

    <!-- Page CSS -->
    <link rel="stylesheet" href="assets/css/style.css">

    <!-- Helpers -->
    <script src="assets/vendor/js/helpers.js"></script>

    <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
    <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
    <script src="assets/js/config.js"></script>
</head>
<body>
    <!-- Layout wrapper -->
    <div class="layout-wrapper layout-content-navbar">
        <div class="layout-container">
            <aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme">
                <!-- App brand -->
                <div class="app-brand demo">
                    <a href="recipe" class="app-brand-link">
                      <span class="app-brand-logo demo">
                        <img width="25" src="assets/img/favicon/book.png" alt="Gotta taste logo">
                      </span>
                      <span class="app-brand-text demo menu-text fw-bolder ms-2">Gotta taste</span>
                    </a>
        
                    <a href="javascript:void(0);" class="layout-menu-toggle menu-link text-large ms-auto d-block d-xl-none">
                      <i class="bx bx-chevron-left bx-sm align-middle"></i>
                    </a>
                  </div>
                <!-- / App brand -->

              <div class="menu-inner-shadow"></div>

              <ul class="menu-inner py-1">
                    <!-- Recipe -->
                    <li class="menu-item active">
                        <a href="recipe" class="menu-link">
                            <i class="menu-icon tf-icons bx bx-book"></i>
                            <div data-i18n="Recipies">Recettes</div>
                        </a>
                    </li>
                    
                    <!-- Category -->
                    <li class="menu-item">
                        <a href="category" class="menu-link">
                            <i class="menu-icon tf-icons bx bx-category"></i>
                            <div data-i18n="Categories">Catégories</div>
                        </a>
                    </li>

                    <!-- Ingredient -->
                    <li class="menu-item">
                        <a href="ingredient" class="menu-link">
                            <i class="menu-icon tf-icons bx bx-dish"></i>
                            <div data-i18n="Ingredients">Ingrédients</div>
                        </a>
                    </li>

                    <!-- Step -->
                    <li class="menu-item">
                        <a href="step" class="menu-link">
                            <i class="menu-icon tf-icons bx bx-book-open"></i>
                            <div data-i18n="Steps">Etapes</div>
                        </a>
                    </li>
              </ul>
            </aside>

            <!-- Layout container -->
            <div class="layout-page">
                <!-- Navbar -->
                <nav
                    class="layout-navbar container-xxl navbar navbar-expand-xl navbar-detached align-items-center bg-navbar-theme"
                    id="layout-navbar"
                >
                    <div class="layout-menu-toggle navbar-nav align-items-xl-center me-3 me-xl-0 d-xl-none">
                        <a class="nav-item nav-link px-0 me-xl-4" href="javascript:void(0)">
                            <i class="bx bx-menu bx-sm"></i>
                        </a>
                    </div>

                    <div class="navbar-nav-right d-flex align-items-center" id="navbar-collapse">
                        

                        <ul class="navbar-nav flex-row align-items-center ms-auto">

                        <!-- User -->
                        <li class="nav-item navbar-dropdown dropdown-user dropdown">
                            <a class="nav-link dropdown-toggle hide-arrow" href="javascript:void(0);" data-bs-toggle="dropdown">
                            <div class="avatar avatar-online">
                                <img src="assets/img/avatars/1.png" alt class="w-px-40 h-auto rounded-circle" />
                            </div>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end">
                            <li>
                                <a class="dropdown-item" href="#">
                                <div class="d-flex">
                                    <div class="flex-shrink-0 me-3">
                                    <div class="avatar avatar-online">
                                        <img src="assets/img/avatars/1.png" alt class="w-px-40 h-auto rounded-circle" />
                                    </div>
                                    </div>
                                    <div class="flex-grow-1">
                                    <span class="fw-semibold d-block">John Doe</span>
                                    <small class="text-muted">Admin</small>
                                    </div>
                                </div>
                                </a>
                            </li>
                            <li>
                                <div class="dropdown-divider"></div>
                            </li>
                            <li>
                                <a class="dropdown-item" href="auth-login-basic.html">
                                <i class="bx bx-power-off me-2"></i>
                                <span class="align-middle">Log Out</span>
                                </a>
                            </li>
                            </ul>
                        </li>
                        <!--/ User -->
                        </ul>
                    </div>
                </nav>
                <!-- / Navbar -->

                <!-- Content wrapper -->
                <div class="content-wrapper">
                    <!-- Content -->
                    <div class="container-xxl flex-grow-1 container-p-y">
                        <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Formulaire /</span> Recette</h4>

                        <div class="row">
                            <div class="col-lg-6 mx-auto">
                                <div class="card mb-4">
                                    <div class="card-header d-flex justify-content-between align-items-center">
                                        <h5 class="mb-0">Recette</h5>
                                    </div>
                                    <div class="card-body">
                                        <form
                                          method="POST"
                                          action="recipe"
                                        >
                                            <input type="hidden" name="action" value="<%= request.getAttribute("action") %>">
                                            <input type="hidden" name="idRecipe" value="<%= recipe.getId() %>">
                                            <div class="mb-3">
                                              <label class="form-label" for="recipeTitle">Titre</label>
                                              <input value="<%= recipe.getTitle() %>" name="recipeTitle" type="text" class="form-control" id="recipeTitle" placeholder="Titre" />
                                            </div>
                                            <div class="mb-3">
                                              <label for="recipeDescription" class="form-label">Description</label>
                                              <textarea name="recipeDescription" class="form-control" id="recipeDescription" rows="3"><%= recipe.getDescription() %></textarea>
                                            </div>
                                            <div class="mb-3">
                                              <label for="recipeIdCategory" class="form-label">Catégorie</label>
                                              <select name="recipeIdCategory" id="recipeIdCategory" class="form-select">
                                                <%  for(Category category : (ArrayList<Category>)request.getAttribute("categories")) { %>
                                                  <option
                                                    value="<%= category.getId() %>"
                                                    <% if(category.getId() == recipe.getIdCategory()) out.println("selected"); %>
                                                  >
                                                    <%= category.getName() %>
                                                  </option>
                                                <% } %>
                                              </select>
                                            </div>
                                            <div class="mb-3">
                                              <div class="d-flex justify-content-between">
                                                <label for="recipeCookTime" class="form-label">Temps de préparation</label>
                                                <small>heure:minute</small>
                                              </div>
                                              <input value="<%= recipe.getFormattedCookTime() %>" name="recipeCookTime" class="form-control" type="time" id="recipeCookTime">
                                            </div>
                                            <div class="mb-3">
                                              <label class="form-label" for="recipeCreator">Créé par</label>
                                              <input value="<%= recipe.getCreatedBy() %>" name="recipeCreator" type="text" class="form-control" id="recipeCreator" placeholder="Nom du créateur" />
                                            </div>
                                            <div class="mb-3">
                                              <label class="form-label" for="recipeCreationDate">Date de création</label>
                                              <input value="<%= recipe.getFormattedCreatedDate() %>" name="recipeCreationDate" type="date" class="form-control" id="recipeCreationDate" />
                                            </div>
                                            <% if(request.getAttribute("action").equals("create")) { %>
                                              <button type="submit" class="btn btn-success">Ajouter</button>
                                            <% } else { %>
                                              <button type="submit" class="btn btn-primary">Modifier</button>
                                            <% } %>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- / Content -->
                </div>
                <!-- / Content wrapper -->
            </div>
            <!-- / Layout container -->
        </div>
    </div>
    <!-- / Layout wrapper -->

    <!-- Core JS -->
    <!-- build:js assets/vendor/js/core.js -->
    <script src="assets/vendor/libs/jquery/jquery.js"></script>
    <script src="assets/vendor/libs/popper/popper.js"></script>
    <script src="assets/vendor/js/bootstrap.js"></script>
    <script src="assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

    <script src="assets/vendor/js/menu.js"></script>
    <!-- endbuild -->

    <!-- Vendors JS -->
    <script src="assets/vendor/libs/apex-charts/apexcharts.js"></script>

    <!-- Main JS -->
    <script src="assets/js/main.js"></script>

    <!-- Page JS -->
    <script src="assets/js/dashboards-analytics.js"></script>
</body>
</html>