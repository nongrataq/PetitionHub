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
                <span class="stat-number">${count}</span>
                <span class="stat-label">Петиций</span>
            </div>
            <div class="stat-item">
                <span class="stat-number">${countOfSub}</span>
                <span class="stat-label">Голосов</span>
            </div>
        </div>

        <#if description??>
            <div class="profile-description">
                <p>Профиль</p>
            </div>
        </#if>

        <div class="profile-actions">
            <button id="togglePetitionsBtn" class="btn btn-primary">
                <i class="bi bi-list"></i>
                <span>Мои петиции</span>
            </button>
            <a href="/create-petition" class="btn btn-transparent">
                <i class="bi bi-plus-lg"></i>
                <span>Создать петицию</span>
            </a>
        </div>
    </div>

    <section id="petitionsSection" style="display:none; margin-top: 20px;">
        <#if petitions?? && petitions?has_content>
            <div class="petitions-grid">
                <#list petitions as p>
                    <div class="petition-card">
                        <div class="petition-content">
                            <h3 class="petition-title">${p.title}</h3>
                            <p class="petition-description">${p.description}</p>
                            <div class="petition-meta">
                    <span class="petition-signatures">
                        <i class="bi bi-people-fill"></i> ${p.countOfSignatures} подписей
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