<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="Petition Platform"
styles='<link rel="stylesheet" href="/css/home/home-style.css">'>

<!-- Навигация -->
<nav>
  <a href="/" class="navbar-brand">Petition Platform</a>
</nav>

<!-- Основной контент -->
<main class="container">

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
            <a href="/specific-petition/${petition.id}">${petition.title}</a>
          </h5>
          <p class="card-text text-muted description-truncate">${petition.description}</p>
          <div class="d-flex justify-content-between text-muted small">
            <span>
              <i class="bi bi-people text-success"></i>${petition.numberOfSignatures} подписей
            </span>
            <span>
              <i class="bi bi-calendar-event me-1 text-primary"></i>${petition.formattedDateOfCreation}
            </span>
          </div>
        </div>
      </div>
    </div>
    </#list>
    <#else> Пока петиций нет!
    </#if>
  </div>
</main>
</@layout.main>
