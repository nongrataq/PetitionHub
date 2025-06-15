<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="Профиль"
styles='<link rel="stylesheet" href="/css/user/profile-style.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">'>

    <div class="profile-container">
        <div class="profile-avatar-container">
            <div class="profile-avatar">
                <#if user.avatar??>
                    <img src="/avatar/${user.avatar.id}" alt="Аватар" class="avatar-image">
                <#else>
                    <div class="avatar-placeholder">
                        <i class="bi bi-person-circle"></i>
                    </div>
                </#if>

                <#if user.username == principal>
                    <form action="/avatar" method="post" enctype="multipart/form-data" class="avatar-form">
                        <input type="file" name="file" id="avatarInput" accept="image/*" hidden>
                        <label for="avatarInput" class="avatar-upload-btn" title="Сменить фото">
                            <i class="bi bi-camera"></i>
                        </label>
                        <button type="submit" class="avatar-submit-btn d-none" id="avatarSubmit" title="Сохранить">
                            <i class="bi bi-check"></i>
                        </button>
                    </form>
                </#if>
            </div>
        </div>
        <h2 class="profile-name">${user.username}</h2>

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

        <!-- TODO: добавить описание профиля-->
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
            <#if user.username == principal>
                <a href="/create-petition" class="btn btn-primary reversed-gradient">
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
                            <h3 class="petition-title">${p.title}</h3>
                            <p class="petition-description">${p.description}</p>
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
    <script>
        // Показываем кнопку "Сохранить" при выборе файла
        document.getElementById('avatarInput').addEventListener('change', function () {
            if (this.files.length > 0) {
                document.getElementById('avatarSubmit').classList.remove('d-none');
            }
        });
    </script>

    <script src="/js/user/profile.js"></script>

</@layout.main>