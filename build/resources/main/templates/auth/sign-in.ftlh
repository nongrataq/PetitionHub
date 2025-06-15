<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="Sign In"
styles='<link rel="stylesheet" href="/css/auth/sign-in-style.css">'>

    <div class="container">
        <div class="auth-wrapper">
            <h2 class="text-center mb-4">Вход в PetitionHub</h2>
            <#if message??>
                <div class="alert-success-custom">
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
                        <p class="text-muted">Нет аккаунта? <a href="/auth/sign-up" class="text-decoration-none">Зарегистрируйтесь</a>
                        </p>
                    </div>
                </div>
            </form>
        </div>
    </div>
</@layout.main>
