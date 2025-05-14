<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="Sign Up">
<style>
    /* ===========================
       Кнопки
    =========================== */
    .btn-primary {
            background: linear-gradient(90deg, var(--accent-start), var(--accent-end));
            color: #fff;
            border: none;
        }

    .card {
        background-color: var(--card-bg);
        color: var(--text-color);
        border: none;
        box-shadow: var(--shadow);
        border-radius: 8px;
    }

    .form-floating label {
        color: var(--text-color);
        transition: color 0.8s ease;
    }

    .form-control:focus {
        background-color: var(--card-bg) !important;
        color: var(--text-color) !important;
    }
</style>

    <div class="container">

        <div class="row justify-content-center">
            <div class="col-md-6 col-lg-4">
                <div class="card shadow-sm">
                    <div class="card-body p-4">
                        <h2 class="text-center mb-4">Регистрация</h2>
                        <form action="/auth/sign-up" method="post">
                            <#if errors??>
                                <#list errors as error>
                                    <div class="alert alert-danger">Ошибка: ${error.defaultMessage}</div>
                                    <br>
                                </#list>
                            </#if>
                            <#if userAlreadyExist??>
                                <div class="alert alert-danger">Ошибка: ${userAlreadyExist}</div>
                                <br>
                            </#if>
                            <div class="form-floating">
                                <input type="text" class="mb-3 form-control" id="username" name="username"
                                       placeholder="Логин"
                                       required>
                                <label for="username">Логин</label>
                            </div>
                            <div class="form-floating">
                                <input type="password" class="mb-3 form-control" id="password" name="password"
                                       placeholder="Пароль"
                                       required>
                                <label for="password">Пароль</label>
                            </div>
                            <div class="d-grid gap-2 mb-3">
                                <button type="submit" class="btn btn-primary btn-lg">Создать</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@layout.main>