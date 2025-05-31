<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="Petition Creating"
styles='<link rel="stylesheet" href="/css/petitions/create-petition-style.css">'>

    <form action="/create-petition" method="post" novalidate class="card p-4 mb-4" id="petition-form"
          onsubmit="document.getElementById('submit-button').disabled = true;">

        <!-- Название -->
        <div class="mb-3">
            <label for="title" class="form-label">Название</label>
            <input type="text"
                   class="form-control ${(errors?? && errors?filter(e -> e.field == 'title')?size == 0)?then('is-valid', '')}
                   ${(errors?? && errors?filter(e -> e.field == 'title')?size > 0)?then('is-invalid', '')}"
                   id="title"
                   name="title"
                   value="${(petitionDto.title)!''?trim}">
            <#if errors?? && errors?has_content>
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
                    class="form-control ${(errors?? && errors?filter(e -> e.field = 'description')?size == 0)?then('is-valid', '')}
                    ${(errors?? && errors?filter(e -> e.field == 'description')?size > 0)?then('is-invalid', '')}"
                    id="description"
                    name="description"
                    rows="5">${(petitionDto.description)!''?trim}</textarea>
            <#if errors?? && errors?has_content>
                <#list errors as error>
                    <#if error.field == 'description'>
                        <div class="invalid-feedback">
                            ${error.defaultMessage}
                        </div>
                    </#if>
                </#list>
            </#if>
        </div>

        <!--Тэг-->
        <div class="mb-3">
            <label class="form-label">Тег</label>

            <div class="d-flex gap-3 mb-3">
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="tagOption" id="tagOptionList" value="list"
                           checked>
                    <label class="form-check-label" for="tagOptionList">Выбрать из списка</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="tagOption" id="tagOptionCustom" value="custom">
                    <label class="form-check-label" for="tagOptionCustom">Свой вариант</label>
                </div>
            </div>

            <div class="tag-input-container position-relative" style="min-height: 42px;">
                <!-- Select input -->
                <div id="tagListContainer" class="tag-input">
                    <select class="form-control  ${selectedTag!''}"
                            id="tagList"
                            name="tagList"
                            required>
                        <option value="" selected disabled>Выберите тег</option>
                        <option value="Экология">Экология</option>
                        <option value="Образование">Образование</option>
                        <option value="Транспорт">Транспорт</option>
                        <option value="Медицина">Медицина</option>
                        <option value="Права_человека">Права человека</option>
                        <option value="Экономика">Экономика</option>
                        <option value="Культура">Культура</option>
                        <option value="Спорт">Спорт</option>
                        <option value="Технологии">Технологии</option>
                    </select>
                </div>

                <!-- Custom input -->
                <div id="tagNameContainer" class="tag-input"
                     style="display: none; position: absolute; top: 0; left: 0; right: 0;">
                    <input type="text"
                           class="form-control ${(errors?? && errors?filter(e -> e.field == 'tagName')?size == 0)?then('is-valid', '')}
                           ${(errors?? && errors?filter(e -> e.field == 'tagName')?size > 0)?then('is-invalid', '')}"
                           id="tagName"
                           name="tagName"
                           placeholder="Введите свой тег"
                    >
                    <#if errors?? && errors?has_content>
                        <#list errors as error>
                            <#if error.field == 'tagName'>
                                <div class="invalid-feedback">
                                    ${error.defaultMessage}
                                </div>
                            </#if>
                        </#list>
                    </#if>
                </div>
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

                // Обработка переключения между вариантами тегов с анимацией
                const tagListContainer = document.getElementById('tagListContainer');
                const tagNameContainer = document.getElementById('tagNameContainer');
                const tagList = document.getElementById('tagList');
                const tagName = document.getElementById('tagName');

                document.querySelectorAll('input[name="tagOption"]').forEach(radio => {
                    radio.addEventListener('change', function () {
                        if (this.value === 'list') {
                            // Плавное переключение на список
                            tagNameContainer.style.opacity = '0';
                            setTimeout(() => {
                                tagNameContainer.style.display = 'none';
                                tagListContainer.style.display = 'block';
                                setTimeout(() => {
                                    tagListContainer.style.opacity = '1';
                                }, 10);
                            }, 300);

                            tagList.disabled = false;
                            tagName.disabled = true;
                            tagName.value = '';
                        } else {
                            // Плавное переключение на кастомный ввод
                            tagListContainer.style.opacity = '0';
                            setTimeout(() => {
                                tagListContainer.style.display = 'none';
                                tagNameContainer.style.display = 'block';
                                setTimeout(() => {
                                    tagNameContainer.style.opacity = '1';
                                }, 10);
                            }, 300);

                            tagList.disabled = true;
                            tagList.value = '';
                            tagName.disabled = false;
                        }
                    });
                });

                // Инициализация стилей для анимации
                tagListContainer.style.opacity = '1';
                tagListContainer.style.transition = 'opacity 0.3s ease';
                tagNameContainer.style.opacity = '0';
                tagNameContainer.style.transition = 'opacity 0.3s ease';
            </script>
            <style>
                .tag-input {
                    transition: opacity 0.3s ease;
                }
            </style>
        </#if>
    </form>

</@layout.main>