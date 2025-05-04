<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="Profile">
    <p class="text-primary h3">Аккаунт: ${user}</p>
    <h2>Ваши петиции: </h2><br>
    <#if petitions??>
        <#list petitions as p>
            <div class="card">
                <div class="card-header collapsed">
                    Петиция
                </div>
                <div class="card-body">
                    <h5 class="card-title">Название: ${p.title}</h5>
                    <p class="card-text">Описание: ${p.description}</p>
                </div>
                <div class="card-footer text-muted mb-3">
                    Добавить дату добавления
                </div>
            </div>
        </#list>
    <#else>
        <h3>Пока петиций нет!</h3>
    </#if>
</@layout.main>
