<header>
    <div class="header-left"><span class="logo">Online</span>Cources</div>
    <div class="header-center">
        <ul>
            <li><a href="Courses">Cources</a></li>
        </ul>
    </div>
    <c:if test="${user.admin()}">
    <div class="header-center" style="margin-left: 1%">
        <ul>
            <li><a href="Users">Users</a></li>
        </ul>
    </div>
    </c:if>
    <div class="header-center" style="margin-left: 1%">
        <ul>
            <li><a href="Cube">Cube</a></li>
        </ul>
    </div>
    <div class="header-name">
        Hello, ${user.name}.
    </div>
    <div class="header-logout">
        <a href="Login?logout=1">Log out</a>
    </div>
</header>