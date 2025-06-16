<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="About Us"
styles='<link rel="stylesheet" href="/css/informationPages/about-us-style.css">'>

<div class="card about-card">
  <h2 class="section-title">О нас</h2>

  <div class="about-content">
    <h5>О PetitionHub</h5>
    <p class="about-text">PetitionHub — это современная цифровая платформа для создания и продвижения общественных инициатив.</p>

    <h5>Наша миссия</h5>
    <p class="about-text">Создать прозрачную и удобную среду для гражданской активности, где:</p>
    <ul class="about-list">
      <li>Любая значимая идея получает шанс на реализацию</li>
      <li>Процесс поддержки петиций прост и безопасен</li>
      <li>Результаты общественных инициатив отслеживаются и документируются</li>
    </ul>

    <h5>Технологии для перемен</h5>
    <ul class="about-list checkmarks">
      <li>Веб-технологии нового поколения</li>
      <li>Защищенные механизмы верификации</li>
      <li>Удобные инструменты аналитики</li>
    </ul>
  </div>

  <div class="team-section">
    <a class="team-link" href="https://github.com/ssadam16" target="_blank">
      <div class="team-member">
        <div class="member-name">Саид Файзуллин</div>
        <div class="member-role">Frontend разработчик</div>
      </div>
    </a>

    <a class="team-link" href="https://github.com/nongrataq" target="_blank">
      <div class="team-member">
        <div class="member-name">Богдан Кудрявцев</div>
        <div class="member-role">Backend разработчик</div>
      </div>
    </a>

    <a class="team-link" href="https://github.com/Novice2311" target="_blank">
      <div class="team-member">
        <div class="member-name">Игорь Добрица</div>
        <div class="member-role">Backend разработчик</div>
      </div>
    </a>

    <a class="team-link" href="https://github.com/ReZorDos" target="_blank">
      <div class="team-member">
        <div class="member-name">Булат Тимуршин</div>
        <div class="member-role">Backend разработчик</div>
      </div>
    </a>

    <a class="team-link" href="https://github.com/OERook" target="_blank">
      <div class="team-member">
        <div class="member-name">Олег Грачев</div>
        <div class="member-role">Frontend разработчик</div>
      </div>
    </a>
  </div>
</div>

</@layout.main>
