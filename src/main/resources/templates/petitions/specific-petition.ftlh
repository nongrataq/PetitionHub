<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="Petition: ${current_petition.title}">

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
            padding: 0.5rem 2.5rem;
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

        .btn-primary:hover {
            box-shadow: 0 8px 18px rgba(0, 0, 0, 0.5);
        }

        .button-wrapper {
            text-align: center;
        }

        .label {
            font-weight: 600;
            color: var(--accent-start);
            margin-right: 0.5rem;
            user-select: none;
        }

        .label-block {
            border: 1px solid var(--accent-start);
            border-radius: 8px;
            padding: 1rem;
            margin-bottom: 1rem;
            background-color: rgba(255, 255, 255, 0.05);
        }

        .label-title {
            font-weight: bold;
            color: var(--accent-start);
            margin-bottom: 0.5rem;
            font-size: 1.1rem;
        }

        .card-title {
            color: var(--text-color);
            text-decoration: none;
            font-weight: 500;
            background-image: linear-gradient(90deg, var(--accent-start), var(--accent-end));
            background-size: 0% 2px;
            background-position: 0 100%;
            background-repeat: no-repeat;
            transition: background-size 0.3s ease;
        }

        .card-text {
            color: var(--text-color);
            white-space: pre-wrap;
        }

        .card-footer {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .author-link {
            color: var(--accent-start-reversed);
            font-weight: 500;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .author-link:hover {
            color: var(--accent-end-reversed);
            text-decoration: underline;
        }
    </style>
    <form action="/sign/${current_petition.id}" method="post">
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
              <p>количество подписей: ${current_petition.numberOfSignatures}</p>
                <p class="text-muted">
                    <span class="label">Автор:</span>
                  <a class="author-link" href="/profile/${current_petition.author.username}">${current_petition.author.username}</a>
                </p>
                <div class="button-wrapper">
                    <button class="btn-primary">Подписать петицию</button>
                </div>
            </div>
        </div>
    </form>
</@layout.main>
