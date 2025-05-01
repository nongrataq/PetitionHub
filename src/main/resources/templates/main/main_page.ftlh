<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Подключение Bootstrap CSS (не убирать ни в коем случае и не менять ниче, где написано Bootstrap) -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Подключение иконок Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <title>Petition Platform</title>
    <style>
        /* css стили */
        :root {
            --main-bg-color: #f8f9fa;
            --secondary-color: #6c757d;
        }

        body {
            background-color: var(--main-bg-color);
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        .card {
            transition: transform 0.2s;
            background-color: white;
            border: 1px solid rgba(0, 0, 0, .125);
        }

        .card:hover {
            transform: translateY(-5px);
        }

        .navbar-brand {
            font-weight: 600;
            letter-spacing: 0.5px;
        }

        main {
            margin-top: 80px;
        }
    </style>
</head>
<body>

<!-- Верхняя плашка сс навигацией -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top shadow-sm">
    <div class="container-fluid">
        <!-- Лого -->
        <div class="d-flex align-items-center">
            <!-- Поиск -->
            <form class="d-flex me-3" action="/search" method="get">
                <label>
                    <input class="form-control me-2" type="search" name="query" placeholder="Поиск петиций">
                </label>
                <button class="btn btn-outline-light" type="submit">Найти</button>
            </form>

            <!-- Кнопки авторизации-->
            <!-- Если пользователь вошел -->
            <#if user??>
                <form action="/logout" method="post" class="me-2">
                    <button type="submit" class="btn btn-outline-light">Выйти</button>
                </form>
                <a href="/profile" class="btn btn-outline-light">
                    <i class="bi bi-person-circle"></i>
                </a>
            <#else>
                <!-- Если пользователь не вошел -->
                <form action="/auth/sign-in" method="get">
                    <button type="submit" class="btn btn-outline-light">Войти</button>
                </form>
            </#if>
        </div>

        <!-- Правая часть верхней плашки -->
        <a class="navbar-brand" href="/">
            <i class="bi bi-megaphone fs-4"></i> PetitionHub
        </a>
    </div>
</nav>


<main class="container">
    <!-- Кнопка создания петиции -->
    <div class="text-center my-5 py-4">
        <#if user??>
            <a href="/petitions" class="btn btn-lg btn-secondary px-5">
                Создать петицию
            </a>
        <#else>
            <form action="/auth/sign-in" method="get">
                <button type="submit" class="btn btn-lg btn-secondary px-5">
                    Создать петицию
                </button>
            </form>
        </#if>
    </div>
    <!-- ДОБАВИТЬ ВОЗМОЖНОСТЬ ЗАЙТИ НА ИНФУ О ПЕТИЦИИ-->
    <!-- Типа лента петиций, но это хардкод -->
    <!--СЮДА ДОБАВЛЯТЬ ПОСЛЕДНИЕ ДОБАВЛЕННЫЕ ПЕТИЦИИ-->
    <h3 class="mb-4">Недавние петиции</h3>
    <div class="row g-4">
        <!-- Петиция 1 -->
        <div class="col-md-4">
            <div class="card h-100 shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">Числануть Игоря</h5>
                    <p class="card-text text-muted">Игорь не ходит на пары. Требуем его числануть...</p>
                    <div class="d-flex justify-content-between text-muted small">
                        <span>1,321 подписей</span>
                        <span>20.04.2025</span>
                    </div>
                </div>
            </div>
        </div>

        <!-- Петиция 2 -->
        <div class="col-md-4">
            <div class="card h-100 shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">Числануть Саида</h5>
                    <p class="card-text text-muted">Необходимо срочно ...</p>
                    <div class="d-flex justify-content-between text-muted small">
                        <span>3,248 подписей</span>
                        <span>18.04.2025</span>
                    </div>
                </div>
            </div>
        </div>

        <!-- Петиция 3 -->
        <div class="col-md-4">
            <div class="card h-100 shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">Гусева Федора Андреевича в сеньоры</h5>
                    <p class="card-text text-muted">Срочно!...</p>
                    <div class="d-flex justify-content-between text-muted small">
                        <span>90 подписей</span>
                        <span>15.04.2025</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<!-- Нижняя плашка -->
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

<!-- Подключение Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>