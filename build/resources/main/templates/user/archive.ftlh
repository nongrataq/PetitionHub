<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="Notifications"
styles='<link rel="stylesheet" href="/css/user/notifications-style.css">'>

<H1>АРХИВ</H1>

    <a class="btn-primary" href="/notifications">Вернуться назад -></a>

<#if readNotifications?has_content>
    <#list readNotifications as notification>
        <div class="card petition-card">
            <div class="card-body">
                <div class="label-block">
                    <div class="label-title">Уведомление</div>
                    Вашу петицию <a class="card-text"
                                    href="/specific-petition/${notification.petitionId}">${notification.petitionTitle}</a>
                    подписал <a class="card-text"
                                href="/profile/${notification.senderName}"> ${notification.senderName}</a>
                </div>
            </div>
            <div class="card-footer">
                    <span class="label">
                        <i class="bi bi-calendar-event me-1 text-primary"></i>
                        ${notification.formattedDate}
                    </span>
            </div>
        </div>
    </#list>
<#else> Пустой архив
</#if>
</@layout.main>