<#import "../patterns/layout.ftlh" as layout>
<@layout.main title= "Petition: ${current_petition.title}">
    <div class="card">
        <div class="card-header">
            <h5 class="card-title">Заголовок: ${current_petition.title}</h5>
        </div>
        <div class="card-body">
            <p class="card-text">Описание: ${current_petition.description}</p>
        </div>
        <div class="card-footer">
            <!--вот тут сделать ссылочкой на чужой профиль-->
            <p class="text-muted">Автор: ${current_petition.authorUsername}</p>
        </div>
    </div>

</@layout.main>