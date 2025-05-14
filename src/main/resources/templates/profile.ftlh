<#import "patterns/layout.ftlh" as layout>
<@layout.main title="Profile">
    <h2 class="text-primary h3">Ваши петиции: </h2><br>

<#-- Сообщение об успешном создании петиции -->
    <#if petitionDto??>
        <div class="alert alert-success mb-3">
            Петиция "${petitionDto.title}" успешно создана!
        </div>
    </#if>

<#-- Список петиций -->
    <#if petitions?? && petitions?has_content>
        <#list petitions as p>
            <div class="card mb-3">
                <div class="card-header collapsed">
                    Петиция
                </div>
                <div class="card-body">
                    <h5 class="card-title">Название: ${p.title}</h5>
                    <p class="card-text">Описание: ${p.description}</p>
                </div>
                <div class="card-footer text-muted mb-3">
                    <#-- Дата будет работать после добавления поля createdAt в PetitionEntity -->
                    <#if p.createdAt??>
                        Дата создания: ${p.createdAt?datetime}
                    <#else>
                        Дата не указана
                    </#if>
                </div>
            </div>
        </#list>
    <#else>
        <div class="alert alert-info">
            <h3>Пока петиций нет!</h3>
        </div>
    </#if>
</@layout.main>