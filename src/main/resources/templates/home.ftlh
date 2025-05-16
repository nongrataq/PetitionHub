<#import "patterns/layout.ftlh" as layout>
<@layout.main title="Petition Platform">

<style>
    /* ===========================
       Карточки с градиент‑бордером
    =========================== */
    .card {
        position: relative;
        background: var(--card-bg);
        border-radius: 8px;
        box-shadow: 0 2px 6px var(--shadow);
        overflow: hidden;
        transition: transform 0.3s ease, box-shadow 0.3s ease;
        opacity: 0;
        transform: translateY(12px);
        animation: fadeInUp 0.6s forwards;
        border: 2px solid transparent;
        background-image: linear-gradient(var(--card-bg), var(--card-bg)),
                          linear-gradient(90deg, var(--accent-start), var(--accent-end));
        background-origin: padding-box, border-box;
        background-clip: padding-box, border-box;
    }

    .card:hover {
        transform: translateY(-6px) scale(1.02);
        box-shadow: 0 6px 16px rgba(0, 0, 0, 0.7);
    }

    .card::before {
        content: '';
        display: block;
        height: 4px;
        background: linear-gradient(90deg, var(--accent-start), var(--accent-end));
    }

    /* Анимация появления карточек */
    @keyframes fadeInUp {
        to {
            opacity: 1;
            transform: translateY(0) scale(1);
        }
    }

    /* ===========================
       Кнопки
    =========================== */
    .btn-primary {
        background: linear-gradient(90deg, var(--accent-start), var(--accent-end));
        color: #fff;
        padding: .95rem 3.5rem;
        border: none;
        border-radius: 8px;
        font-weight: 500;
        position: relative;
        transition: transform 0.3s ease, box-shadow 0.3s ease;
        text-decoration: none;
        font-size: 1.25rem;
        box-shadow: 0 6px 8px rgba(0, 0, 0, 0.2);
        display: inline-block;
        opacity: 0;
        transform: translateY(12px);
        animation: fadeInUp 0.6s forwards;
    }

    /* Эффекты при наведении на кнопку */
    .btn-primary:hover {
        transform: translateY(-5px) scale(1.05);
        box-shadow: 0 12px 20px rgba(0, 0, 0, 0.7);
    }

    .btn-primary:hover span {
        display: inline-block;
        animation: wiggle 0.4s ease-in-out;
    }

    /* Анимация "встряски" текста */
    @keyframes wiggle {
        0%   { transform: translateX(0); }
        25%  { transform: translateX(-2px); }
        50%  { transform: translateX(2px); }
        75%  { transform: translateX(-1px); }
        100% { transform: translateX(0); }
    }

    /* ===========================
       Ссылки в заголовках карточек
    =========================== */
    .card-title a {
        color: inherit;
        text-decoration: none;
        font-weight: 500;
        background-image: linear-gradient(90deg, var(--accent-start), var(--accent-end));
        background-size: 0% 2px;
        background-position: 0 100%;
        background-repeat: no-repeat;
        transition: background-size 0.3s ease;
    }

    .petition-title a {
      color: var(--accent-start-reversed) !important;
    }

    .card-title a:hover {
        background-size: 100% 2px;
    }
</style>

<!-- Навигация -->
<nav>
    <a href="/" class="navbar-brand">Petition Platform</a>
</nav>

<!-- Основной контент -->
<main class="container">

    <!-- Кнопка создания новой петиции -->
    <div class="text-center my-5 py-4">
        <a href="/create-petition" class="btn-primary btn-lg"><span>Создать петицию</span></a>
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
                                <p class="card-text text-muted">${petition.description}</p>
                                <div class="d-flex justify-content-between text-muted small">
                            <span>
                                <i class="bi bi-check-circle me-1 text-success"></i>1 подписей
                            </span>
                                    <span>
                                <i class="bi bi-calendar-event me-1 text-primary"></i>${petition.dateOfCreation}
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
