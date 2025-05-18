<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="Petition: ${current_petition.title}"
styles='<link rel="stylesheet" href="/css/petitions/specific-petition-style.css">'>

<!-- Карточка петиции -->
<div class="card petition-card">
  <div class="card-header">
    <div class="label-block">
      <div class="label-title">Заголовок</div>
      <h5 class="card-title">${current_petition.title}</h5>
    </div>
  </div>
  <div class="card-body">
    <div class="label-block">
      <div class="label-title">Описание</div>
      <p class="card-text">${current_petition.description}</p>
    </div>
  </div>
  <div class="card-footer">
    <p class="label">Количество подписей: ${current_petition.getSignatureCount()}</p>
    <p class="text-muted">
      <span class="label">Автор:</span>
      <a class="author-link" href="/profile/${current_petition.author.username}">${current_petition.author.username}</a>
    </p>
    <div class="button-wrapper">
      <#if hasSigned?? && hasSigned>
      <p class="signed-message">Вы уже подписали эту петицию</p>
      <#else>
      <form action="/sign/${current_petition.id}" method="post">
        <button class="btn-primary">Подписать петицию</button>
      </form>
      </#if>
    </div>
  </div>
</div>
</@layout.main>