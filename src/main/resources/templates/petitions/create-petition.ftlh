<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="Petition Creating"
styles='<link rel="stylesheet" href="/css/petitions/create-petition-style.css">'>

    <form action="/create-petition" method="post" enctype="multipart/form-data" novalidate class="card p-4 mb-4" id="petition-form"
          onsubmit="document.getElementById('submit-button').disabled = true;">


    <!-- Название -->
        <div class="mb-3">
            <label for="title" class="form-label">Название</label>
            <input type="text"
                   class="form-control ${(errors?? && errors?filter(e -> e.field == 'title')?size == 0 && (petitionDto.title)??)?then('is-valid', '')}
                   ${(errors?? && errors?filter(e -> e.field == 'title')?size > 0)?then('is-invalid', '')}"
                   id="title"
                   name="title"
                   value="${(petitionDto.title)!''}">
            <#if errors??>
                <#list errors as error>
                    <#if error.field == 'title'>
                        <div class="invalid-feedback">
                            ${error.defaultMessage}
                        </div>
                    </#if>
                </#list>
            </#if>
        </div>

        <!-- Описание -->
        <div class="mb-3">
            <label for="description" class="form-label">Описание</label>
            <textarea
                    class="form-control ${(errors?? && errors?filter(e -> e.field == 'description')?size == 0 && (petitionDto.description)??)?then('is-valid', '')}
                    ${(errors?? && errors?filter(e -> e.field == 'description')?size > 0)?then('is-invalid', '')}"
                    id="description"
                    name="description"
                    rows="5">${(petitionDto.description)!''}</textarea>
            <#if errors??>
                <#list errors as error>
                    <#if error.field == 'description'>
                        <div class="invalid-feedback">
                            ${error.defaultMessage}
                        </div>
                    </#if>
                </#list>
            </#if>
        </div>

        <!-- Тэг -->
        <div class="mb-3">
            <label for="tagList" class="form-label">Тег</label>
            <select class="form-select ${(errors?? && errors?filter(e -> e.field == 'tagName')?size == 0)?then('is-valid', '')}
                           ${(errors?? && errors?filter(e -> e.field == 'tagName')?size > 0)?then('is-invalid', '')}"
                    id="tagList"
                    name="tagName"
                    required>
                <option value="" selected disabled>Выберите тег</option>
                <option value="Экология">Экология</option>
                <option value="Образование">Образование</option>
                <option value="Транспорт">Транспорт</option>
                <option value="Медицина">Медицина</option>
                <option value="Права человека">Права человека</option>
                <option value="Экономика">Экономика</option>
                <option value="Культура">Культура</option>
                <option value="Спорт">Спорт</option>
                <option value="Технологии">Технологии</option>
                <option value="Другое">Другое</option>
            </select>
        </div>

        <!-- Загрузка изображений -->
        <div class="mb-3">
            <div class="mb-3">
                <label class="form-label">Изображения (макс. 3)</label>

                <input type="file" class="form-control mb-1" name="images" accept="image/*">
                <input type="file" class="form-control mb-1" name="images" accept="image/*">
                <input type="file" class="form-control mb-1" name="images" accept="image/*">

                <small class="form-text text-muted">Максимум 3 изображений. Поля необязательные.</small>
            </div>

        </div>

        <!-- Кнопка отправки -->
        <#if wait!false>
            <button type="button" id="submit-button" class="btn btn-secondary" disabled>
                Подождите <span id="cooldown-timer">${cooldownLeft!0}</span> сек.
            </button>
            <script>
                const timerElement = document.getElementById('cooldown-timer');
                let timeLeft = ${cooldownLeft!0};

                const interval = setInterval(() => {
                    timeLeft--;
                    timerElement.textContent = timeLeft;

                    if (timeLeft <= 0) {
                        clearInterval(interval);
                        const button = document.getElementById('submit-button');
                        button.disabled = false;
                        button.classList.replace('btn-secondary', 'btn-primary');
                        button.textContent = 'Создать';
                        button.type = 'submit';
                    }
                }, 1000);
            </script>
        <#else>
            <button type="submit" id="submit-button" class="btn btn-primary">Создать</button>
            <script>
                document.getElementById('petition-form').addEventListener('submit', function () {
                    const button = document.getElementById('submit-button');
                    button.disabled = true;
                    button.innerHTML = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Отправка...';
                });
            </script>
        </#if>
    </form>

</@layout.main>