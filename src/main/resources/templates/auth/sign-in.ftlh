<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="Sign In">
    <style>
        .auth-container {
            max-width: 400px;
            margin: 50px auto;
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            background: white;
        }

        body {
            background-color: #f8f9fa;
        }

/* ===========================
       Кнопки
    =========================== */
        .btn-primary {
            background: linear-gradient(90deg, var(--accent-start), var(--accent-end));
            color: #fff;
            border: none;
        }

        .form-floating {
            margin-bottom: 1.5rem;
        }

        .alert-danger {
            max-width: 350px;
            margin: 0 auto 1rem auto;
            padding: 0.5rem 1rem;
            font-size: 0.9rem;
        }

    </style>
    <div class="container">
        <div class="auth-container">
            <h2 class="text-center mb-4">Вход в PetitionHub</h2>
            <#if message??>
                <div class="alert alert-success">
                    <p>${message}</p>
                </div>
            </#if>
            <form action="/auth/login" method="post">
                <div class="auth-container">
                    <#if RequestParameters.error??>
                        <div class="alert alert-danger">
                            <p>${error}</p>
                        </div>
                    </#if>
                    <div class="form-floating">
                        <input type="text" class="form-control" id="username" name="username" placeholder="Логин"
                               required>
                        <label for="username">Логин</label>
                    </div>
                    <div class="form-floating">
                        <input type="password" class="form-control" id="password" name="password" placeholder="Пароль"
                               required>
                        <label for="password">Пароль</label>
                    </div>
                    <div class="d-grid gap-2 mb-3">
                        <button type="submit" class="btn btn-primary btn-lg">Войти</button>
                    </div>
                    <div class="text-center">
                        <p class="text-muted">Нет аккаунта? <a href="/auth/sign-up" class="text-decoration-none">Зарегистрируйтесь</a></p>
                    </div>
                </div>
            </form>
        </div>
    </div>
</@layout.main>