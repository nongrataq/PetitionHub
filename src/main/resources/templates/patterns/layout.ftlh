<#macro main title="PetitionHub">
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>${title}</title>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq"
                crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7"
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <style>
            html, body {
                height: 100%;
            }

            body {
                display: flex;
                flex-direction: column;
                padding-top: 56px; /* Для фиксированного navbar */
            }

            main {
                flex: 1 0 auto; /* Растягивает основной контент */
                padding-bottom: 20px; /* Отступ от футера */
            }

            footer {
                flex-shrink: 0; /* Фиксирует футер */
                background-color: #343a40;
                color: white;
            }

            .btn-outline-light {
                transform: translateX(20px);
            }
        </style>
    </head>
    <body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top shadow-sm">
        <div class="container-fluid">
            <!-- Лого -->
            <div class="d-flex align-items-center">
                <div class="search">
                    <form class="d-flex me-3" action="/search" method="get">
                        <label>
                            <input class="form-control me-2" type="search" name="query" placeholder="Поиск петиций">
                        </label>
                        <button class="btn btn-outline-light" type="submit">Найти</button>
                    </form>
                </div>
                <#if user??>
                    <form action="/logout" method="post" class="me-2">
                        <button type="submit" class="btn btn-outline-light">Выйти</button>
                    </form>
                    <a href="/profile" class="btn btn-outline-light">
                        <i class="bi bi-person-circle"></i> Профиль
                    </a>
                <#else>
                    <!-- Если пользователь не вошел -->
                    <form action="/auth/sign-in" method="get">
                        <button type="submit" class="btn btn-outline-light">Войти</button>
                    </form>
                </#if>
            </div>

            <a class="navbar-brand" href="/">
                <i class="bi bi-megaphone fs-4"></i> PetitionHub
            </a>
        </div>
    </nav>

    <main class="container mt-4">
        <#nested>
    </main>

    <footer class="bg-dark text-white mt-5 py-4">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <h5><i class="bi bi-megaphone"></i> PetitionHub</h5>
                    <!--TODO ЦВЕТ TEXT-INFO-->
                    <p class="text-info">Платформа для создания и поддержки петиций</p>
                </div>
                <div class="col-md-6 text-end">
                    <p class="mb-0">Контакты: saidFrontendkin@gmail.com</p>
                    <p class="mb-0">© 2025 Все права защищены</p>
                </div>
            </div>
        </div>
    </footer>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
    </html>
</#macro>