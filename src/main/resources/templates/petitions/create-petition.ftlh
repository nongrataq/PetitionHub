<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="Petition Creating"
styles='<link rel="stylesheet" href="/css/petitions/create-petition-style.css">'>

<form action="/create-petition" method="post" class="card p-4 mb-4">
  <#if errors??>
  <#list errors as error>
  <div class="alert alert-danger">
    ${error.defaultMessage}
  </div>
  </#list>
  </#if>
  <div class="mb-3">
    <label for="title" class="form-label">Название</label>
    <input type="text" class="form-control" id="title" name="title" required>
  </div>
  <div class="mb-3">
    <label for="description" class="form-label">Описание</label>
    <textarea class="form-control" id="description" name="description" rows="5" required></textarea>
  </div>
  <button type="submit" class="btn btn-primary">Создать</button>
</form>
</@layout.main>
