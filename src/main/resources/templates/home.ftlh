<#import "patterns/layout.ftlh" as layout>
<@layout.main title="Petition Platform">
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

        main {
            margin-top: 80px;
        }
    </style>
    <main class="container">
        <div class="text-center my-5 py-4">
            <form action="/petition" method="get">
                <button type="submit" class="btn btn-lg btn-secondary px-5">Создать петицию</button>
            </form>
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
</@layout.main>