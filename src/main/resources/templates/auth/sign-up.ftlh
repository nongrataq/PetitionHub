<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="Sign Up">

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card shadow-sm">
                    <div class="card-body p-4">
                        <h2 class="text-center mb-4">Регистрация</h2>

                        <#if userAlreadyExist??>
                            <div class="alert alert-danger mb-3">${userAlreadyExist}</div>
                        </#if>

                        <form action="/auth/sign-up" method="post" novalidate>
                            <!-- Поле логина -->
                            <div class="mb-3">
                                <label for="username" class="form-label">Логин</label>
                                <input type="text"
                                       class="form-control ${(isFormSubmitted?? && fieldErrors?? && fieldErrors?filter(e -> e.field == 'username')?size == 0)?then('is-valid', '')}
                                        ${(fieldErrors?? && fieldErrors?filter(e -> e.field == 'username')?size>0)?then('is-invalid', '')}"
                                       id="username"
                                       name="username"
                                       value="${(signUpDto.username)!''}"
                                >
                                <#if fieldErrors??>
                                    <#list fieldErrors as error>
                                        <#if error.field == 'username'>
                                            <div class="invalid-feedback">
                                                ${error.defaultMessage}
                                            </div>
                                        </#if>
                                    </#list>
                                </#if>
                            </div>

                            <div class="mb-4">
                                <label for="password" class="form-label">Пароль</label>
                                <input type="password"
                                       class="form-control ${(isFormSubmitted?? && fieldErrors?? && fieldErrors?filter(e -> e.field == 'password')?size == 0)?then('is-valid', '')}
                                        ${(fieldErrors?? && fieldErrors?filter(e -> e.field == 'password')?size>0)?then('is-invalid', '')}"
                                       id="password"
                                       name="password"
                                       value="${(signUpDto.password)!''}"
                                >
                                <#if fieldErrors??>
                                    <#list fieldErrors as error>
                                        <#if error.field == 'password'>
                                            <div class="invalid-feedback">
                                                ${error.defaultMessage}
                                            </div>
                                        </#if>
                                    </#list>
                                </#if>
                            </div>

                            <button type="submit" class="btn btn-primary w-100 py-2">Создать аккаунт</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</@layout.main>