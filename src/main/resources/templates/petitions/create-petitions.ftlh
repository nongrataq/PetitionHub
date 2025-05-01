<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Petitions</title>
</head>
<body>
<form action="/create-petition" method="post" class="card p-4 mb-4">
    <div class="mb-3">
        <label for="title" class="form-label">Название</label>
        <input type="text" class="form-control" id="title" name="title" required>
    </div>
    <div class="mb-3">
        <label for="description" class="form-label">Описание</label>
        <textarea class="form-control" id="description" name="description" rows="4" required></textarea>
    </div>
    <button type="submit" class="btn btn-primary">Создать</button>
    <#if error??>
        <div class="alert alert-danger">${error}</div>
    </#if>
</form>
</body>
</html>