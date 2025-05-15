<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="Petition Creating">

<style>
    /* ===========================
            Карточка создания
    =========================== */
    .card {
        background: linear-gradient(90deg, var(--accent-end), var(--accent-start));
        color: var(--text-color);
        border: none;
        border-radius: 8px;
        box-shadow: 0 6px 16px rgba(0, 0, 0, 0.7);
    }

    /* ===========================
            Кнопки
    =========================== */
    .btn-primary {
        background: linear-gradient(90deg, var(--accent-start-reversed), var(--accent-end-reversed));
        color: #fff;
        padding: .95rem 3.5rem;
        border: none;
        border-radius: 8px;
        font-weight: 500;
        position: relative;
        text-decoration: none;
        font-size: 1.25rem;
        box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3);
        transition: box-shadow 0.3s ease;
        display: inline-block;
    }

    .btn-primary:hover {
        box-shadow: 0 8px 18px rgba(0, 0, 0, 0.5);
    }

    /* ===========================
            Стиль поля ввода при фокусе
    =========================== */
    .form-control:focus {
        background-color: var(--card-bg) !important;
        color: var(--text-color) !important;
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