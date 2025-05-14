<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="Petition Creating">

<style>
    .card {
        background-color: var(--card-bg);
        color: var(--text-color);
        border: none;
        box-shadow: var(--shadow);
        border-radius: 8px;
    }

    .btn-primary {
        background: linear-gradient(90deg, var(--accent-start), var(--accent-end));
        color: #fff;
        padding: .95rem 3.5rem;
        border: none;
        border-radius: 8px;
        font-weight: 500;
        position: relative;
        transition: transform 0.3s ease, box-shadow 0.3s ease;
        text-decoration: none;
        font-size: 1.25rem;
        box-shadow: 0 6px 8px rgba(0, 0, 0, 0.2);
        display: inline-block;
    }
</style>

<form action="/create-petition" method="post" class="card p-4 mb-4">
    <#if errors??>
    <#list errors as error>
    <div class="alert alert-danger">
        ${error.defaultMessage}
    </div>
</#list>
</#if>
<div class="mb-3">
    <label for="title" class="form-label">Название</label>
    <input type="text" class="form-control" id="title" name="title" required>
</div>
<div class="mb-3">
    <label for="description" class="form-label">Описание</label>
    <textarea class="form-control" id="description" name="description" rows="5" required></textarea>
</div>
<button type="submit" class="btn btn-primary">Создать</button>
</form>
</@layout.main>