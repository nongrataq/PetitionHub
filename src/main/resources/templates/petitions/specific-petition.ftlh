<#import "../patterns/layout.ftlh" as layout>
<@layout.main title= "Petition: ${p.title}">
    <style>
        body {
            font-family: 'Georgia', serif;
            line-height: 1.6;
            max-width: 800px;
            margin: 0 auto;
            padding: 40px 20px;
            color: #000;
            background: #fff;
        }

        .header {
            border-bottom: 1px solid #000;
            padding-bottom: 15px;
            margin-bottom: 30px;
        }

        .title {
            font-size: 28px;
            font-weight: normal;
            margin: 0 0 5px 0;
            letter-spacing: 0.5px;
        }

        .meta {
            font-size: 14px;
            color: #555;
        }

        .description {
            margin: 40px 0;
            white-space: pre-line;
            font-size: 16px;
        }

        .actions {
            margin-top: 50px;
        }

        .btn {
            display: inline-block;
            background: transparent;
            color: #000;
            padding: 8px 15px;
            text-decoration: none;
            border: 1px solid #000;
            margin-right: 10px;
            font-size: 14px;
            cursor: pointer;
        }

        .btn:hover {
            background: #000;
            color: #fff;
        }

        .signatures {
            margin-top: 60px;
            border-top: 1px solid #000;
            padding-top: 20px;
        }

        .signature-count {
            font-weight: bold;
            margin-bottom: 15px;
        }

        .error {
            font-style: italic;
            color: #555;
        }
    </style>
    <#if p??>
        <div class="header">
            <h1 class="title">${p.title}</h1>
            <div class="meta">Автор: ${p.author.username}</div>
        </div>

        <div class="description">
            ${p.description}
        </div>

        <div class="actions">
            <a href="/petitions" class="btn">← Назад</a>
            <#if error??>
                <div class="alert alert-danger">${error}</div>
            </#if>
            <button class="btn" id="sign-btn">Подписать</button>
        </div>

        <div class="signatures">
            <div class="signature-count">Подписи (0):</div>
            <div id="signatures-list">
                <!-- Будут добавляться фронтендом -->
            </div>
        </div>
    <#else>
        <div class="error">
            Петиция не найдена или была удалена.
        </div>
    </#if>
</@layout.main>