<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="Sign Up">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6 col-lg-4">
                <div class="card shadow-sm">
                    <div class="card-body p-4">
                        <h2 class="text-center mb-4">Регистрация</h2>
                        <form action="/auth/registration" method="post">
                            <#if error??>
                                <div class="alert alert-danger">
                                    <p class="error">${error}</p>
                                </div>
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
                                <button type="submit" class="btn btn-dark btn-lg">Создать</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@layout.main>