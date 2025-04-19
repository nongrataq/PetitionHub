<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Вход в систему</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .auth-container {
            max-width: 400px;
            margin: 50px auto;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            background: white;
        }

        body {
            background-color: #f8f9fa;
        }

        .form-floating {
            margin-bottom: 1.5rem;
        }
    </style>
</head>
<body>
<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="Вход в систему">
    <div class="container">
        <div class="auth-container">
            <h2 class="text-center mb-4">Вход в PetitionsHub</h2>
            <form action="/auth/sign-in" method="post">
                <#if RequestParameters.error??>
                    <div class="alert alert-danger">
                        Неверный логин или пароль!
                    </div>
                </#if>
                <div class="form-floating">
                    <input type="text" class="form-control" id="username" name="username" placeholder="Логин" required>
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
                    <p class="text-muted">Нет аккаунта? <a href="/auth/sign-up" class="text-decoration-none">Зарегистрируйтесь</a>
                    </p>
                </div>
            </form>
        </div>
    </div>
</@layout.main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>