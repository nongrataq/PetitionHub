<#macro main title="PetitionHub">
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>${title}</title>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq"
                crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7"
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <style>
            /* ===========================
                        Основа
            =========================== */
            :root {
                --bg-color: #F9F9F9;
                --card-bg: #FFFFFF;
                --accent-start: #9A8C98;
                --accent-end: #C9ADA7;
                --shadow: rgba(0, 0, 0, 0.1);
                --nav-height: 70px;
                --footer-height: 80px;
            }

            html, body {
                height: 100%;
            }

* { margin: 0; padding: 0; box-sizing: border-box; }
            body {
                position: relative;
                min-height: 100vh;
                font-family: system-ui, sans-serif;
                color: var(--text-color);
                background-color: var(--bg-color);
                padding-top: var(--nav-height);
                padding-bottom: var(--footer-height);
            }

/* ===========================
       Canvas под фон
    =========================== */
            #network-bg {
                position: fixed;
                top: 0; left: 0;
                width: 100%; height: 100%;
                z-index: -1;
            }

/* ===========================
       Навигационная панель
    =========================== */
            nav {
                position: fixed;
                top: 0; left: 0;
                width: 100%; height: var(--nav-height);
                background: linear-gradient(90deg, var(--accent-start), var(--accent-end));
                display: flex;
                align-items: center;
                padding: 0 1rem;
                z-index: 10;
                box-shadow: 0 2px 6px var(--shadow);
            }
            nav .navbar-brand { color: #fff; font-weight: 600; text-decoration: none; }

/* ===========================
       Контент
    =========================== */
            main {
                flex: 1 0 auto; /* Растягивает основной контент */
                padding-bottom: 20px; /* Отступ от футера */
                margin: 1rem auto;
                max-width: 1200px;
            }

/* ===========================
       Футер
    =========================== */
            footer {
                position: fixed;
                bottom: 0; left: 0;
                width: 100%; height: var(--footer-height);
                background: linear-gradient(90deg, var(--accent-end), var(--accent-start));
                display: flex;
                align-items: center;
                justify-content: center;
                color: #fff;
                font-size: 0.9rem;
                z-index: 10;
                box-shadow: 0 -2px 6px var(--shadow);
            }

            .btn-outline-light {
                transform: translateX(20px);
            }
        </style>

    </head>
    <body>
    <canvas id="network-bg"></canvas>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top shadow-sm">
        <div class="container-fluid">
            <!-- Лого -->
            <div class="d-flex align-items-center">
                <div class="search">
                    <form class="d-flex me-3" action="/search" method="get">
                        <label>
                            <input class="form-control me-2" type="search" name="query" placeholder="Поиск петиций">
                        </label>
                        <button class="btn btn-outline-light" type="submit">Найти</button>
                    </form>
                </div>
                <#if isAuthenticated>
                    <form action="/logout" method="post" class="me-2">
                        <button type="submit" class="btn btn-outline-light">Выйти</button>
                    </form>
                    <a href="/profile" class="btn btn-outline-light">
                        <i class="bi bi-person-circle"></i> Профиль
                    </a>
                <#else>
                    <form action="/auth/sign-in" method="get">
                        <button type="submit" class="btn btn-outline-light">Войти</button>
                    </form>
                </#if>
            </div>

            <a class="navbar-brand" href="/">
                <i class="bi bi-megaphone fs-4"></i> PetitionHub
            </a>
        </div>
    </nav>

    <main class="container mt-4">
        <#nested>
    </main>

    <footer class="bg-dark text-white mt-5 py-4">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <h5><i class="bi bi-megaphone"></i> PetitionHub</h5>
                    <!--TODO ЦВЕТ TEXT-INFO-->
                    <p class="text-info">Платформа для создания и поддержки петиций</p>
                </div>
                <div class="col-md-6 text-end">
                    <p class="mb-0">Контакты: saidFrontendkin@gmail.com</p>
                    <p class="mb-0">© 2025 Все права защищены</p>
                </div>
            </div>
        </div>
    </footer>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        /* ===========================
                Сеть графов
        =========================== */
        const canvas = document.getElementById('network-bg');
        const ctx = canvas.getContext('2d');
        let w, h, nodes = [], lines = [];

        function init() {
          resize();
          createNodes(80);
          connectNodes(120);
          animate();
        }
        function resize() {
          w = canvas.width = window.innerWidth;
          h = canvas.height = window.innerHeight;
        }
        window.addEventListener('resize', resize);

        function createNodes(count) {
          nodes = [];
          for (let i = 0; i < count; i++) {
            nodes.push({
              x: Math.random() * w,
              y: Math.random() * h,
              vx: (Math.random()-0.5)*0.3,
              vy: (Math.random()-0.5)*0.3
            });
          }
        }

        function connectNodes(maxDist) {
          lines = [];
          for (let i = 0; i < nodes.length; i++) {
            for (let j = i+1; j < nodes.length; j++) {
              const dx = nodes[i].x - nodes[j].x;
              const dy = nodes[i].y - nodes[j].y;
              if (dx*dx + dy*dy < maxDist*maxDist) {
                lines.push([i,j]);
              }
            }
          }
        }

        function animate() {
          ctx.clearRect(0,0,w,h);
          nodes.forEach(n => {
            n.x += n.vx; n.y += n.vy;
            if (n.x<0||n.x>w) n.vx *= -1;
            if (n.y<0||n.y>h) n.vy *= -1;
          });
          ctx.strokeStyle = 'rgba(176, 190, 197, 0.7)';
          lines.forEach(([i,j]) => {
            const a = nodes[i], b = nodes[j];
            ctx.beginPath();
            ctx.moveTo(a.x,a.y);
            ctx.lineTo(b.x,b.y);
            ctx.stroke();
          });
          ctx.fillStyle = 'rgba(84, 110, 122, 0.9)';
          nodes.forEach(n => {
            ctx.beginPath();
            ctx.arc(n.x,n.y,2,0,2*Math.PI);
            ctx.fill();
          });
          requestAnimationFrame(animate);
        }

        init();
    </script>
    </body>
    </html>
</#macro>