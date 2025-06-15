<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="Sign Up"
styles='<link rel="stylesheet" href="/css/auth/sign-in-style.css">'>

<div class="container">
    <div class="auth-wrapper">
        <h2 class="text-center mb-4">Регистрация в PetitionHub</h2>

        <#if message??>
        <div class="alert-success-custom">
            <p>${message}</p>
        </div>
    </#if>

    <#if userAlreadyExist??>
    <div class="alert alert-danger">
        <p>${userAlreadyExist}</p>
    </div>
</#if>

<form action="/auth/sign-up" method="post" novalidate>
    <div class="auth-container">
        <#if RequestParameters.error??>
        <div class="alert alert-danger">
            <p>${error}</p>
        </div>
    </#if>

    <div class="form-floating">
        <input type="text"
               class="form-control ${(isFormSubmitted?? && fieldErrors?? && fieldErrors?filter(e -> e.field == 'username')?size == 0)?then('is-valid', '')}
                                ${(fieldErrors?? && fieldErrors?filter(e -> e.field == 'username')?size>0)?then('is-invalid', '')}"
               id="username"
               name="username"
               placeholder="Логин"
               value="${(signUpDto.username)!''}"
               required>
        <label for="username">Логин</label>
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

<div class="form-floating">
    <input type="password"
           class="form-control ${(isFormSubmitted?? && fieldErrors?? && fieldErrors?filter(e -> e.field == 'password')?size == 0)?then('is-valid', '')}
                                ${(fieldErrors?? && fieldErrors?filter(e -> e.field == 'password')?size>0)?then('is-invalid', '')}"
           id="password"
           name="password"
           placeholder="Пароль"
           value="${(signUpDto.password)!''}"
           required>
    <label for="password">Пароль</label>
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

<div class="d-grid gap-2 mb-3">
    <button type="submit" class="btn btn-primary btn-lg">Зарегистрироваться</button>
</div>

<div class="text-center">
    <p class="text-muted">Уже есть аккаунт? <a href="/auth/login" class="text-decoration-none">Войдите</a></p>
</div>
</div>
</form>
</div>
</div>
</@layout.main>