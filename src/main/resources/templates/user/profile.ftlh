<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="Профиль"
styles='<link rel="stylesheet" href="/css/user/profile-style.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">'>

    <div class="profile-container">
        <div class="profile-header">
            <div class="profile-icon">
                <i class="bi bi-person-circle"></i>
            </div>
            <h2 class="profile-name">${authorName}</h2>
        </div>

        <div class="profile-stats">
            <div class="stat-item">
                <span class="stat-label">Петиций: </span>
                <span class="stat-number">${petitions?size}</span>
            </div>
            <div class="stat-item">
                <span class="stat-label">Голосов: </span>
                <span class="stat-number">${countOfSub}</span>
            </div>
        </div>

        <!--добавить описание профиля-->
        <#if description??>
            <div class="profile-description">
                <p>Профиль</p>
            </div>
        </#if>

        <div class="profile-actions">
            <button id="togglePetitionsBtn" class="btn btn-primary">
                <i class="bi bi-list"></i>
                <span>Показать петиции</span>
            </button>
            <#if authorName == principal>
                <a href="/create-petition" class="btn btn-transparent">
                    <i class="bi bi-plus-lg"></i>
                    <span>Создать петицию</span>
                </a>
            </#if>
        </div>
    </div>

    <section id="petitionsSection" style="display:none; margin-top: 20px;">
        <#if petitions?? && petitions?has_content>
            <div class="petitions-grid">
                <#list petitions as p>
                    <div class="petition-card">
                        <div class="petition-content">
                            <h3 class="petition-title">Проблема: ${p.title}</h3>
                            <p class="petition-description">Описание: ${p.description}</p>
                            <div class="petition-meta">
                                <span class="petition-signatures">
                                    <i class="bi bi-people text-success"></i>
                                    <#assign count = p.countOfSignatures>
                                    <#assign lastDigit = count % 10>
                                    <#assign lastTwoDigits = count % 100>

                                    <#if lastTwoDigits gte 11 && lastTwoDigits lte 14>
                                        ${count} подписей
                                    <#elseif lastDigit == 1>
                                        ${count} подпись
                                    <#elseif lastDigit gte 2 && lastDigit lte 4>
                                        ${count} подписи
                                    <#else>
                                        ${count} подписей
                                    </#if>
                                </span>
                                <span class="petition-date">
                                    <i class="bi bi-calendar"></i>
                                    <#if p.formattedDateOfCreation?? && p.formattedDateOfCreation?length != 0>
                                        ${p.formattedDateOfCreation}
                                    <#else>
                                        Дата не указана
                                    </#if>
                                </span>
                            </div>
                        </div>
                        <a href="/specific-petition/${p.id}" class="petition-link">Подробнее</a>
                    </div>
                </#list>
            </div>
        <#else>
            <div class="alert alert-info">Петиций пока нет.</div>
        </#if>
    </section>

    <script src="/js/user/profile.js"></script>

</@layout.main>