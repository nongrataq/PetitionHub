<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="Notifications"
styles='<link rel="stylesheet" href="/css/petitions/specific-petition-style.css">'>

    <body>
    <div class="btn-wrapper">
        <!-- TODO: сделать переход на страницу архива, если он не пуст -->
        <a href="/archive" class="btn btn-primary mb-4">Архив</a>
    </div>
    <#if notifications?has_content>
        <#list notifications as notification>
            <!-- Карточка уведомлений, стилизованные под карточки specific-petition -->
            <div class="card petition-card">
                <div class="card-body">
                    <div class="label-block">
                        <div class="label-title">Уведомление</div>
                        Вашу петицию <a class="card-text" href="/specific-petition/${notification.petitionId}">${notification.petitionTitle}</a> подписал <a class="card-text" href="/profile/${notification.senderName}"> ${notification.senderName}</a>
                    </div>
                </div>
                <div class="card-footer">
                    <span class="label">
                        <i class="bi bi-calendar-event me-1 text-primary"></i>
                        ${notification.formattedDate}
                    </span>
                    <div class="button-wrapper">
                        <!-- TODO: отправить увед в архив при нажатии кнопки ниже -->
                        <form action="/notifications/${notification.id}" method="post">
                            <button type="submit" class="btn-primary">Пометить как прочитанное</button>
                        </form>
                    </div>
                </div>
            </div>
        </#list>
        <#else> Уведомлений нет
    </#if>

    <script src="/js/user/notifications-tooltip-script.js"></script>

    </body>
</@layout.main>
