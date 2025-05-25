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
        <h3 class="mb-4">Недавние петиции</h3>
        <div class="row g-4">
            <#if petitions?has_content>
                <#list petitions as petition>
                    <div class="col-md-4">
                        <div class="card h-100">
                            <div class="card-body">
                                <h5 class="card-title petition-title">
                                    <a href="/specific-petition/${petition.id}">Проблема: ${petition.title}</a>
                                </h5>
                                <p class="card-text text-muted description-truncate">
                                    Описание: ${petition.description}</p>
                                <p>Автор: ${petition.authorUsername}</p>
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
        <#if hasNext>
            <div class="text-center mt-4">
                <form action="/load-more" method="get">
                    <input type="hidden" name="page" value="${currentPage + 1}">
                    <input type="hidden" name="size" value="6">
                    <button type="submit" class="btn btn-primary">
                        Следующая страница
                    </button>
                </form>
            </div>
        </#if>
    </div>
</@layout.main>
