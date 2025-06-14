<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="Search"
styles='<link rel="stylesheet" href="/css/petitions/search-style.css">'>

    <div class="container mt-5">

        <div class="d-flex justify-content-center mb-4">
            <div style="width: 100%; max-width: 720px;">

                <!-- Поисковая строка -->
                <div id="searchInputWrapper" class="d-flex align-items-stretch" style="gap: 0.5rem;">
                    <input type="text"
                           id="searchInput"
                           class="form-control form-control-lg input-gradient-border flex-grow-1"
                           placeholder="Найдите петицию по теме...">
                    <button id="searchBtn"
                            class="btn btn-primary btn-lg d-flex align-items-center px-4"
                            style="font-size: 1.25rem; font-weight: 500;"
                            type="button">
                        <i class="bi bi-search me-2"></i> Найти
                    </button>
                    <ul class="autocomplete-list list-group" id="autocompleteList"></ul>
                </div>
                <!-- Быстрые фильтры -->
                <div class="quick-tags mt-3" style="width: 100%;">
                    <div class="d-flex align-items-center" style="gap: 0.5rem; flex-wrap: wrap; width: 100%;">
                        <a href="/search?tag=Экология" class="btn btn-primary flex-grow-1">🌍 Экология</a>
                        <a href="/search?tag=Образование" class="btn btn-primary flex-grow-1">🎓 Образование</a>
                        <a href="/search?tag=Транспорт" class="btn btn-primary flex-grow-1">🚇 Транспорт</a>
                        <a href="/search?tag=Медицина" class="btn btn-primary flex-grow-1">🏥 Медицина</a>

                        <div class="dropdown">
                            <button class="btn btn-primary dropdown-toggle d-flex align-items-center"
                                    type="button" id="moreFiltersBtn" data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="bi bi-chat-square-dots me-1"></i> Другие
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="moreFiltersBtn">
                                <li><a class="dropdown-item" href="/search?tag=Права человека">✊ Права человека</a></li>
                                <li><a class="dropdown-item" href="/search?tag=Экономика">💰 Экономика</a></li>
                                <li><a class="dropdown-item" href="/search?tag=Культура">🎭 Культура</a></li>
                                <li><a class="dropdown-item" href="/search?tag=Спорт">🏅 Спорт</a></li>
                                <li><a class="dropdown-item" href="/search?tag=Технологии">💻 Технологии</a></li>
                                <li><a class="dropdown-item" href="/search?tag=Другое">❓ Другое</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Результаты поиска -->
        <div id="resultsContainer" class="row gy-4">
            <#if petitions?has_content>
                <#list petitions as petition>
                    <div class="col-md-4">
                        <div class="card h-100">
                            <div class="card-body">
                                <h5 class="card-title petition-title">
                                    <a href="/specific-petition/${petition.id}">${petition.title}</a>
                                </h5>
                                <p class="card-text text-muted description-truncate">
                                    ${petition.description}</p>
                                <span>
                                <#if petition.avatarId?has_content>
                                    <img src="/image/${petition.avatarId}" alt="Аватар" class="avatar-image me-1 mb-2">
                                <#else>
                                    <i class="bi bi-person-circle text-danger me-1"></i>
                                </#if>
                                <a href="profile/${petition.authorUsername}" class="text-muted">${petition.authorUsername}</a>
                                </span>
                                <div class="d-flex justify-content-between text-muted small">
                                    <span>
                                        <i class="bi bi-people text-success"></i>
                                        <#assign count = petition.countOfSignatures>
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
                                    <span><i class="bi bi-calendar-event me-1 text-primary"></i>${petition.formattedDateOfCreation}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </#list>
            <#else>
                <div class="col-12">
                    <div class="alert alert-info">
                        <#if query?has_content>
                            По запросу ${query} ничего не найдено.
                        <#elseif tag?has_content>
                            По запросу ${tag} ничего не найдено.
                        <#else> Введите поисковый запрос
                        </#if>
                    </div>
                </div>
            </#if>
        </div>
    </div>

    <script src="/js/petitions/search-searching-script.js"></script>

</@layout.main>