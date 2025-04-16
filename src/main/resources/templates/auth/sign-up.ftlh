<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign up</title></head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<body>
<h1>Sign up</h1>
<form action="/auth/registration" method="post">
    <label>
        <input type="text" name="username">
    </label>
    <label>
        <input type="password" name="password">
    </label>
    <button type="submit">Зарегистрироваться</button>
</form>
</body>
</html>