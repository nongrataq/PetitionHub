<#macro main title="PetitionHub">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${title}</title>

    <!-- Bootstrap + Icons -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq"
            crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

    <style>
        /* ===========================
                Цветовая палитра
        =========================== */
        :root {
            --nav-height: 70px;
            --footer-height: 80px;
            --shadow: rgba(0, 0, 0, 0.5);
            --text-color: #1a1a1a;
        }

        :root.light-theme {
            --bg-color: #F9F9F9;
            --card-bg: #FFFFFF;
            --accent-start: #9A8C98;
            --accent-end: #C9ADA7;
            --accent-start-reversed: #6E636B;
            --accent-end-reversed: #927C77;
            --text-color: #1a1a1a;
        }

        :root.dark-theme {
            --bg-color: #1C1B1E;
            --card-bg: #2A272D;
            --accent-start: #6E636B;
            --accent-end: #927C77;
            --accent-start-reversed: #9A8C98;
            --accent-end-reversed: #C9ADA7;
            --text-color: #F0F0F0;
        }

        /* ===========================
                Базовые правки Bootstrap
        =========================== */
        .text-muted,
        .card-title a {
            color: var(--text-color) !important;
        }

        html, body {
            height: 100%;
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            font-family: system-ui, sans-serif;
            color: var(--text-color);
            background-color: var(--bg-color);
            padding-top: var(--nav-height);
            transition: background-color 0.8s ease, color 0.8s ease;
        }

        /* ===========================
                Canvas с фоном
        =========================== */
        #network-bg {
            position: fixed;
            top: 0; left: 0;
            width: 100%; height: 100%;
            z-index: -1;
        }

        /* ===========================
                Навигация
        =========================== */
        nav {
            position: fixed;
            top: 0; left: 0;
            width: 100%;
            height: var(--nav-height);
            background: linear-gradient(90deg, var(--accent-start), var(--accent-end));
            display: flex;
            align-items: center;
            padding: 0 1rem;
            z-index: 10;
            box-shadow: 0 2px 6px var(--shadow);
            transition: background-color 0.8s ease, color 0.8s ease;
        }

        nav .navbar-brand {
            color: #fff;
            font-weight: 600;
            text-decoration: none;
        }

        /* ===========================
                Контент
        =========================== */
        main {
            flex: 1;
            margin: 1rem auto;
            max-width: 1200px;
        }

        /* ===========================
                Футер
        =========================== */
        footer {
            height: var(--footer-height);
            background: linear-gradient(90deg, var(--accent-end), var(--accent-start));
            display: flex;
            align-items: center;
            justify-content: center;
            color: #fff;
            font-size: 0.9rem;
            z-index: 10;
            box-shadow: 0 -2px 6px var(--shadow);
            transition: background-color 0.8s ease, color 0.8s ease;
        }

        .footer-contact {
            color: #FFFFFF;
            text-decoration: none;
            transition: color 0.8s ease;
        }

        .footer-contact:hover {
            color: #C0C0C0;
            text-decoration: underline;
        }

        .text-info {
            color: #FFFFFF !important;
            text-decoration: none;
            transition: color 0.8s ease;
        }

        .btn-outline-light {
            transform: translateX(20px);
        }

        /* ===========================
                Поле поиска
        =========================== */
        .form-control {
            background-color: var(--card-bg);
            color: var(--text-color);
            border-radius: 8px;
            box-shadow: 0 2px 6px var(--shadow);
        }

        .form-control::placeholder {
            color: var(--text-color);
            opacity: 0.7;
            transition: color 0.8s ease;
        }
    </style>
</head>
<body>

<canvas id="network-bg"></canvas>

<!-- Навбар -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top shadow-sm">
    <div class="container-fluid">

        <!-- Поиск + Авторизация -->
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

    <!-- Тема + Лого -->
    <div class="d-flex align-items-center">
        <button id="themeToggle" class="btn btn-outline-light me-5">
            <i class="bi" id="themeIcon"></i>
        </button>
        <a class="navbar-brand" href="/">
            <i class="bi bi-megaphone fs-4"></i> PetitionHub
        </a>
    </div>
    </div>
</nav>

<!-- Контент -->
<main class="container mt-4">
    <#nested>
</main>

<!-- Футер -->
<footer class="bg-dark text-white mt-5 py-4">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <h5><i class="bi bi-megaphone"></i> PetitionHub</h5>
                <p class="text-info">Платформа для создания и поддержки петиций</p>
            </div>
            <div class="col-md-6 text-end">
                <a href="https://github.com/ssadam16" class="footer-contact mb-0">Контакты: saidFrontendkin@gmail.com</a>
                <p class="mb-0">© 2025 Petition Platform</p>
            </div>
        </div>
    </div>
</footer>

<!-- ===========================
        Canvas background
=========================== -->
<script>
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
                vx: (Math.random() - 0.5) * 0.3,
                vy: (Math.random() - 0.5) * 0.3
            });
        }
    }

    function connectNodes(maxDist) {
        lines = [];
        for (let i = 0; i < nodes.length; i++) {
            for (let j = i + 1; j < nodes.length; j++) {
                const dx = nodes[i].x - nodes[j].x;
                const dy = nodes[i].y - nodes[j].y;
                if (dx * dx + dy * dy < maxDist * maxDist) {
                    lines.push([i, j]);
                }
            }
        }
    }

    function animate() {
        ctx.clearRect(0, 0, w, h);
        nodes.forEach(n => {
            n.x += n.vx;
            n.y += n.vy;
            if (n.x < 0 || n.x > w) n.vx *= -1;
            if (n.y < 0 || n.y > h) n.vy *= -1;
        });

        ctx.strokeStyle = 'rgba(176, 190, 197, 0.7)';
        lines.forEach(([i, j]) => {
            const a = nodes[i], b = nodes[j];
            ctx.beginPath();
            ctx.moveTo(a.x, a.y);
            ctx.lineTo(b.x, b.y);
            ctx.stroke();
        });

        ctx.fillStyle = 'rgba(84, 110, 122, 0.9)';
        nodes.forEach(n => {
            ctx.beginPath();
            ctx.arc(n.x, n.y, 2, 0, 2 * Math.PI);
            ctx.fill();
        });

        requestAnimationFrame(animate);
    }

    init();
</script>

<!-- ===========================
        Темы (светлая/тёмная)
=========================== -->
<script>
    const themeToggle = document.getElementById('themeToggle');
    const themeIcon = document.getElementById('themeIcon');
    const root = document.documentElement;

    function setTheme(theme) {
        root.classList.remove('light-theme', 'dark-theme');
        root.classList.add(theme + '-theme');
        localStorage.setItem('theme', theme);
        updateIcon(theme);
    }

    function updateIcon(theme) {
        themeIcon.className = theme === 'dark' ? 'bi bi-sun-fill' : 'bi bi-moon-stars-fill';
    }

    const savedTheme = localStorage.getItem('theme') || 'light';
    setTheme(savedTheme);

    themeToggle.addEventListener('click', () => {
        const newTheme = root.classList.contains('light-theme') ? 'dark' : 'light';
        setTheme(newTheme);
    });
</script>

</body>
</html>
</#macro>
