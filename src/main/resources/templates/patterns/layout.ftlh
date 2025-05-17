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
    <link rel="stylesheet" href="/css/layout-style.css">


</head>
<body>

<canvas id="network-bg"></canvas>

<!-- Навбар -->
<nav class="navbar navbar-expand-lg fixed-top shadow-sm">
    <div class="container-fluid">

        <!-- Поиск + Авторизация -->
        <div class="d-flex align-items-center">
            <div class="search">
                <form class="d-flex me-3" action="/search" method="get">
                    <label><input class="form-control me-2" type="search" name="query" placeholder="Поиск петиций"></label>
                    <button class="btn btn-outline-light" type="submit">Найти</button>
                </form>
            </div>
            <#if isAuthenticated>
            <form action="/logout" method="post" class="me-2">
                <button type="submit" class="btn btn-outline-light">Выйти</button>
            </form>
            <a href="/profile" class="btn btn-outline-light"><i class="bi bi-person-circle"></i> Профиль</a>
            <#else>
            <form action="/auth/sign-in" method="get">
                <button type="submit" class="btn btn-outline-light">Войти</button>
            </form>
        </#if>
    </div>

    <!-- Темы + Лого -->
    <div class="d-flex align-items-center ms-auto">
        <select id="themeSelect" class="theme-select me-2">
            <option value="robot">Robot</option>
            <option value="ocean">Ocean</option>
            <option value="forest">Forest</option>
            <option value="sunset">Sunset</option>
        </select>

        <button id="themeToggle" class="btn btn-outline-light me-5">
            <i class="bi bi-moon-stars-fill" id="themeIcon"></i>
        </button>
        <a class="navbar-brand" href="/"><i class="bi bi-megaphone fs-4"></i> PetitionHub</a>
    </div>
    </div>
</nav>

<main class="container mt-4">
    <#nested>
</main>

<footer class="mt-5 py-4">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <h5><i class="bi bi-megaphone"></i> PetitionHub</h5>
                <p class="text-info">Платформа для создания и поддержки петиций</p>
            </div>
            <div class="col-md-6 text-end">
                <a href="/about-us" class="footer-contact mb-0"><i class="bi bi-info-circle me-1"></i> О нас</a>
                <p class="mb-0">© 2025 Petition Platform</p>
            </div>
        </div>
    </div>
</footer>

<!-- Canvas background script-->

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
        const rootStyles = getComputedStyle(document.documentElement);
        const lineColor = rootStyles.getPropertyValue('--line-color');
        const nodeColor = rootStyles.getPropertyValue('--node-color');

        ctx.clearRect(0, 0, w, h);
        nodes.forEach(n => {
            n.x += n.vx;
            n.y += n.vy;
            if (n.x < 0 || n.x > w) n.vx *= -1;
            if (n.y < 0 || n.y > h) n.vy *= -1;
        });

        ctx.strokeStyle = lineColor;
        lines.forEach(([i, j]) => {
            const a = nodes[i], b = nodes[j];
            ctx.beginPath();
            ctx.moveTo(a.x, a.y);
            ctx.lineTo(b.x, b.y);
            ctx.stroke();
        });

        ctx.fillStyle = nodeColor;
        nodes.forEach(n => {
            ctx.beginPath();
            ctx.arc(n.x, n.y, 2, 0, 2 * Math.PI);
            ctx.fill();
        });

        requestAnimationFrame(animate);
    }

    init();
</script>

<!-- Логика тем -->
<script>
    const themeSelect = document.getElementById('themeSelect');
    const themeToggle = document.getElementById('themeToggle');
    const themeIcon   = document.getElementById('themeIcon');
    const root        = document.documentElement;

    function applyTheme(palette, mode) {
      root.classList.remove('light-theme','dark-theme','robot','ocean','forest','sunset');
      root.classList.add(mode + '-theme', palette);
      themeIcon.className = mode === 'dark'
        ? 'bi bi-sun-fill'
        : 'bi bi-moon-stars-fill';
    }

    const savedPalette = localStorage.getItem('theme') || 'robot';
    const savedMode    = localStorage.getItem('mode')  || 'light';
    themeSelect.value = savedPalette;
    applyTheme(savedPalette, savedMode);

    themeSelect.addEventListener('change', () => {
      const newPalette = themeSelect.value;
      const mode = root.classList.contains('dark-theme') ? 'dark' : 'light';
      localStorage.setItem('theme', newPalette);
      applyTheme(newPalette, mode);
    });

    themeToggle.addEventListener('click', () => {
      const isLight = root.classList.contains('light-theme');
      const newMode = isLight ? 'dark' : 'light';
      const palette = themeSelect.value;
      localStorage.setItem('mode', newMode);
      applyTheme(palette, newMode);
    });
</script>

</body>
</html>
</#macro>
