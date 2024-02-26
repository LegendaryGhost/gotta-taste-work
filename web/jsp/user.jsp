<%@ page contentType="text/html; charset=UTF-8" %>
<li class="nav-item navbar-dropdown dropdown-user dropdown">
<a class="nav-link dropdown-toggle hide-arrow" href="javascript:void(0);" data-bs-toggle="dropdown">
<div
    class="avatar <% if(connected) { %>avatar-online<% } %>"
>
    <i class="bx bx-user-circle w-100 h-100 fs-10"></i>
</div>
</a>
<ul class="dropdown-menu dropdown-menu-end">
<% if(connected) { %>
    <li>
        <a class="dropdown-item" href="login">
            <i class="bx bx-log-out me-2"></i>
            <span class="align-middle">Me déconnecter</span>
        </a>
    </li>
<% } else { %>
    <li>
        <a class="dropdown-item" href="login.jsp">
            <i class="bx bx-log-in me-2"></i>
            <span class="align-middle">Me connecter</span>
        </a>
    </li>
<% } %>
</ul>
</li>