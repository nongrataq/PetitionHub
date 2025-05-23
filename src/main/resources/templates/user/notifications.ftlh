<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="Notifications"
styles='<link rel="stylesheet" href="/css/petitions/specific-petition-style.css">'>

<body>
<div class="btn-wrapper">
    <!-- TODO: сделать переход на страницу архива, если он не пуст -->
    <button class="btn btn-primary mb-4">Архив</button>
</div>

<!-- Карточка уведомлений, стилизованные под карточки specific-petition -->
<div class="card petition-card">
    <div class="card-header">
        <div class="label-block">
            <div class="label-title">Тема</div>
            <h5 class="card-title">Новая подпись!</h5>
        </div>
    </div>
    <div class="card-body">
        <div class="label-block">
            <div class="label-title">Уведомление</div>
            <p class="card-text">Вашу петицию *hyperlink* подписал *hyperlink*</p>
        </div>
    </div>
    <div class="card-footer">
    <span class="label">
        <i class="bi bi-calendar-event me-1 text-primary"></i>*Дата уведомления*
    </span>
        <div class="button-wrapper">
            <!-- TODO: отправить увед в архив при нажатии кнопки ниже -->
            <button class="btn-primary">Пометить как прочитанное</button>
        </div>
    </div>
</div>

<script src="/js/user/notifications-tooltip-script.js"></script>

</body>
</@layout.main>
