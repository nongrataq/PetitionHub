<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<h1>REGISTRATION</h1>
<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="Регистрация">
    <form action="/auth/registration" method="post">
        <label>
            <input type="text" name="username" value="username" class="form-control" placeholder="input username">
        </label>
        <label>
            <input type="password" name="password" value="password" class="form-control" placeholder="input password">
        </label>
        <input type="submit" value="Создать аккаунт">
        <#if message??>
            <div class="alert alert-danger">
                ${message}
            </div>
        </#if>
    </form>
</@layout.main>
</body>
</html>