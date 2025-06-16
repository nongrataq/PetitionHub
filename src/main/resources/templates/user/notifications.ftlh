<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="Notifications"
styles='<link rel="stylesheet" href="/css/user/notifications-style.css">'>

    <div class="notifications-body">
        <div class="header-wrapper">
            <h1 class="archive-title">Уведомления</h1>
        </div>
    <div class="btn-wrapper">
        <a href="/archive" class="btn btn-primary mb-4">Перейти в архив</a>
    </div>

    <#if notifications?has_content>
        <#list notifications as notification>
            <div class="card petition-card shadow-sm">
                <div class="card-body">
                    <div class="label-block">
                        <div class="label-title">Новое уведомление</div>
                        <p style="color: var(--text-color);">
                            Вашу петицию
                            <a href="/specific-petition/${notification.petitionId}" class="link">
                                ${notification.petitionTitle}
                            </a>
                            подписал
                            <a href="/profile/${notification.senderName}" class="link">
                                ${notification.senderName}
                            </a>
                        </p>
                    </div>
                </div>
                <div class="card-footer d-flex justify-content-between align-items-center">
                    <span class="label text-muted">
                        <i class="bi bi-calendar-event me-1 text-primary"></i>
                        ${notification.formattedDate}
                    </span>
                    <form action="/notifications/${notification.id}" method="post">
                        <button type="submit" class="btn btn-sm btn-success">Прочитано</button>
                    </form>
                </div>
            </div>
        </#list>
    <#else>
        <div class="empty-message">Нет новых уведомлений</div>
    </#if>
    </div>
</@layout.main>
