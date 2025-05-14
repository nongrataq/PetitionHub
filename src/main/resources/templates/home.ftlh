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
            box-shadow: 0 6px 16px var(--shadow);
        }

        .card::before {
            content: '';
            display: block;
            height: 4px;
            background: linear-gradient(90deg, var(--accent-start), var(--accent-end));
        }

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
        }

.btn-primary:hover {
  transform: translateY(-5px) scale(1.05);
  box-shadow: 0 12px 20px rgba(0, 0, 0, 0.5);
}

.btn-primary:hover span {
  display: inline-block;
  animation: wiggle 0.4s ease-in-out;
}

@keyframes wiggle {
  0%   { transform: translateX(0); }
  25%  { transform: translateX(-2px); }
  50%  { transform: translateX(2px); }
  75%  { transform: translateX(-1px); }
  100% { transform: translateX(0); }
}

        /* ===========================
           Ссылки
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
        <div class="text-center my-5 py-4">
            <a href="/create-petition" class="btn-primary btn-lg"><span>Создать петицию</span></a>
        </div>

        <h3 class="mb-4">Недавние петиции</h3>
        <!--TODO: конкретное id, не 1, 2, 3-->
        <div class="row g-4">
            <!-- Петиция 1 -->
            <div class="col-md-4">
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-title">
                            <a href="/petitions/1">Числануть Богдана</a>
                        </h5>
                        <p class="card-text text-muted">Нужно числануть Богдана, т.к. он...</p>
                        <div class="d-flex justify-content-between text-muted small">
                            <span>
                                <i class="bi bi-check-circle me-1 text-success"></i>786 подписей
                            </span>
                            <span>
                                <i class="bi bi-calendar-event me-1 text-primary"></i>19.03.2025
                            </span>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Петиция 2 -->
            <div class="col-md-4">
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-title">
                            <a href="/petitions/2">Гусева Федора Андреевича в сеньоры</a>
                        </h5>
                        <p class="card-text text-muted">Легенда java, поэтому надо скорее...</p>
                        <div class="d-flex justify-content-between text-muted small">
                            <span>
                                <i class="bi bi-check-circle me-1 text-success"></i>7,890 подписей
                            </span>
                            <span>
                                <i class="bi bi-calendar-event me-1 text-primary"></i>01.01.2024
                            </span>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Петиция 3 -->
            <div class="col-md-4">
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-title">
                            <a href="/petitions/3">Отобрать общагу у Игоря</a>
                        </h5>
                        <p class="card-text text-muted">Нужно скорее отобрать общагу у Игоря, пока...</p>
                        <div class="d-flex justify-content-between text-muted small">
                            <span>
                                <i class="bi bi-check-circle me-1 text-success"></i>91 подписей
                            </span>
                            <span>
                                <i class="bi bi-calendar-event me-1 text-primary"></i>30.02.2025
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
</@layout.main>
