<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="Profile"
styles='<link rel="stylesheet" href="/css/user/profile-style.css">'>

  <section class="user-info">
    <img src="" alt="Аватар" class="avatar">
    <h2>${authorName}</h2>
    <p>Петиций: ${count}</p>
    <p>Собранные голоса: ${countOfSub}</p>

    <p class="description"></p>
  </section>

  <button id="togglePetitionsBtn" class="btn btn-primary">Показать петиции</button>

  <section id="petitionsSection" style="display:none; margin-top: 20px;">
    <#if petitions?? && petitions?has_content>
    <#list petitions as p>
    <div class="card mb-3">
      <div class="card-header collapsed">Петиция</div>
      <div class="card-body">
        <h5 class="card-title">${p.title}</h5>
        <p class="card-text">${p.description}</p>
        <p>Подписей: ${p.numberOfSignatures}</p>  <div class="card-body">
      </div>
      <div class="card-footer text-muted mb-3">
        <#if p.formattedDateOfCreation?? && p.formattedDateOfCreation?length != 0>
          Дата создания: ${p.formattedDateOfCreation}
        <#else>
          Дата не указана
        </#if>
      </div>
  </section>
</#list>
<#else>
  <div class="alert alert-info">Петиций пока нет.</div>
</#if>
  </section>

  <script>
    const btn = document.getElementById('togglePetitionsBtn');
    const petitionsSection = document.getElementById('petitionsSection');
    let visible = false;

    btn.addEventListener('click', () => {
      visible = !visible;
      petitionsSection.style.display = visible ? 'block' : 'none';
      btn.textContent = visible ? 'Скрыть петиции' : 'Показать петиции';
    });
  </script>

</@layout.main>