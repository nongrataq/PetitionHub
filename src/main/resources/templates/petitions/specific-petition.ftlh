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
                <#if images?? && (images?size > 0)>
                    <div class="mt-3">
                        <div class="label-title">Изображения</div>
                        <div class="d-flex flex-wrap gap-2">
                            <#list images as img>
                                <img src="/avatar/${img.id}" style="max-width: 200px; border-radius: 5px;">
                            </#list>
                        </div>
                    </div>
                </#if>

            </div>

            <#if current_petition.tagName?has_content>
                <div class="label-block mt-3">
                    <div class="label-title">Тег</div>
                    <span class="badge bg-primary">#${current_petition.tagName}</span>
                </div>
            </#if>
        </div>
        <div class="card-footer">
            <p class="label">Количество подписей: ${current_petition.countOfSignatures}</p>

            <p class="text-muted">
                <span class="label">Автор:</span>
                <a class="author-link"
                   href="/profile/${current_petition.authorUsername}">${current_petition.authorUsername}</a>
            </p>
            <div class="button-wrapper">
                <#if hasSigned?? && hasSigned || signError??>
                    <div class="button-wrapper">
                        <p class="signed-message">${signError!'Вы уже подписали эту петицию'}</p>
                    </div>
                <#else>
                    <form action="/sign/${current_petition.id}" method="post"
                          onsubmit="this.querySelector('button').disabled = true;">
                        <button class="btn-primary">Подписать петицию</button>
                    </form>
                </#if>
            </div>
        </div>
    </div>
</@layout.main>
