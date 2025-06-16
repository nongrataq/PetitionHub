<#macro main title="PetitionHub" styles="">

    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>${title}</title>

        <!-- Bootstrap + Icons -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq"
                crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7"
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <!-- Стили -->
        <link rel="stylesheet" href="/css/patterns/layout-style.css">
        ${styles!?no_esc}
    </head>

    <body>

    <canvas id="network-bg"></canvas>

    <!-- Навбар -->
    <nav class="navbar navbar-expand-lg fixed-top shadow-sm">
        <div class="container-fluid">

            <!-- Поиск + Авторизация + Уведы -->
            <div class="d-flex align-items-center">
                <div class="search">
                    <a href="/search" class="btn btn-outline-light me-2"><span>Поиск петиций</span></a>
                </div>
                <#if isAuthenticated>
                    <form action="/logout" method="post" class="me-2">
                        <button type="submit" class="btn btn-outline-light">Выйти</button>
                    </form>
                    <a href="/profile" class="btn btn-outline-light me-2"><i class="bi bi-person-circle"></i>
                        Профиль</a>
                    <!-- TODO: Сделать проверку: если есть непрочитанные уведы, то иконку с красным кружком -->
                  <div class="notification-btn me-2 position-relative">
                    <a href="/notifications" class="btn btn-outline-light"><i class="bi bi-bell"></i></a>
                    <#if hasNotification>
                      <span class="notification-badge">!</span>
                    </#if>
                  </div>
                <#else>
                    <form action="/auth/sign-in" method="get">
                        <button type="submit" class="btn btn-outline-light">Войти</button>
                    </form>
                </#if>
            </div>

            <!-- Темы + Лого -->
            <div class="d-flex align-items-center me-2">

                <div class="theme-dropdown">
                    <select id="themeSelect"
                            class="theme-select dropdown-toggle me-2"
                            onchange="onThemeChange(this.value)">
                        <option value="" disabled selected hidden>Темы</option>
                        <!-- <option value="classic">Для Богдана</option>
                        <option value="robot">Робот</option> -->
                        <option value="pastel">Нежный</option>
                        <option value="modern">Современный</option>
                        <option value="blue">Строгий синий</option>
                        <option value="bright">Яркий</option>
                        <option value="silver">Серебро</option>
                    </select>
                </div>

                <button id="themeToggle" class="btn btn-outline-light me-5">
                    <i class="bi bi-moon-stars-fill" id="themeIcon"></i>
                </button>
                <a class="navbar-brand" href="/"><i class="bi bi-megaphone fs-4"></i> PetitionHub</a>
            </div>
        </div>
    </nav>

    <main class="container mt-4">
        <#nested>
    </main>

    <footer class="mt-5 py-4">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <h5><i class="bi bi-megaphone"></i> PetitionHub</h5>
                    <p class="text-info">Платформа для создания и поддержки петиций</p>
                </div>
                <div class="col-md-6 text-end">
                    <a href="/about-us" class="footer-contact mb-0"><i class="bi bi-info-circle me-1"></i> О нас</a>
                    <p class="mb-0">© 2025 Petition Platform</p>
                </div>
            </div>
        </div>
    </footer>

    <!-- Canvas background script-->
    <script src="/js/patterns/layout-graphs-script.js"></script>

    <!-- Логика тем -->
    <script src="/js/patterns/layout-themes-toggle-script.js"></script>

    </body>
    </html>
</#macro>
