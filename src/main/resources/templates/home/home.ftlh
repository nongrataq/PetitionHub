<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="Petition Platform"
styles='<link rel="stylesheet" href="/css/home/home-style.css">'>

    <!-- Навигация -->
    <nav>
        <a href="/" class="navbar-brand">Petition Platform</a>
    </nav>

    <!-- Основной контент -->
    <div class="container">

        <!-- Кнопка создания новой петиции -->
        <div class="text-center my-5 py-4">
            <div class="btn-wrapper">
                <a href="/create-petition" class="btn-primary btn-lg"><span>Создать петицию</span></a>
            </div>
        </div>

        <!-- Заголовок секции -->
        <h3 class="mb-4">Популярные петиции</h3>
        <div class="row g-4">
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
                                    <i class="bi bi-person-circle text-danger"></i>
                                    <span class="text-muted"> ${petition.authorUsername}</span>
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
                                    <span>
                                        <i class="bi bi-calendar-event me-1 text-primary"></i>${petition.formattedDateOfCreation}
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </#list>
            <#else>
                Пока петиций нет!
            </#if>
        </div>
        <!-- ... предыдущий код остается без изменений до кнопок навигации ... -->

        <div class="d-flex justify-content-between align-items-center mt-4">
            <#if hasPrevious>
                <form action="/load-more" method="get" class="me-2" onsubmit="saveScrollPosition()">
                    <input type="hidden" name="page" value="${currentPage - 1}">
                    <input type="hidden" name="size" value="6">
                    <button type="submit" class="btn btn-outline-primary rounded-circle page-toggle-btn">
                        <i class="bi bi-chevron-left text-muted"></i>
                    </button>
                </form>
            <#else>
                <div></div>
            </#if>

            <#if hasNext>
                <form action="/load-more" method="get" class="ms-2" onsubmit="saveScrollPosition()">
                    <input type="hidden" name="page" value="${currentPage + 1}">
                    <input type="hidden" name="size" value="6">
                    <button type="submit" class="btn btn-outline-primary rounded-circle page-toggle-btn">
                        <i class="bi bi-chevron-right text-muted"></i>
                    </button>
                </form>
            </#if>
        </div>

        <script src="js/home/scroll-position.js"></script>
    </div>
</@layout.main>
