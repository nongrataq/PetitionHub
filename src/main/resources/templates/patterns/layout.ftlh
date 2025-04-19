<#macro main title="PetitionHub">
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <title>${title}</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
    <a href="/" class="home-btn">🏠 Главная</a>

    <#nested>

    <footer>© 2025 PetitionHub</footer>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
    </html>
</#macro>