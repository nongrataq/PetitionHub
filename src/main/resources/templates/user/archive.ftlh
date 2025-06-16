<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="Archive"
styles='<link rel="stylesheet" href="/css/user/archive-style.css">'>

    <div class="notifications-body">
    <div class="header-wrapper">
        <h1 class="archive-title">Архив уведомлений</h1>
        <a class="btn btn-primary" href="/notifications">&#8592; Назад к уведомлениям</a>
    </div>

    <#if readNotifications?has_content>
        <#list readNotifications as notification>
            <div class="card petition-card shadow">
                <div class="card-body">
                    <div class="label-block">
                        <div class="label-title">Прочитанное уведомление</div>
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
                <div class="card-footer">
                    <span class="label text-muted">
                        <i class="bi bi-calendar-event me-1 text-primary"></i>
                        ${notification.formattedDate}
                    </span>
                </div>
            </div>
        </#list>
    <#else>
        <div class="empty-message">Архив пуст</div>
    </#if>
    </div>
</@layout.main>
