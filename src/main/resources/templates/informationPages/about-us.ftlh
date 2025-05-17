<#import "../patterns/layout.ftlh" as layout>
<@layout.main title="About Us"
styles='<link rel="stylesheet" href="/css/informationPages/about-us-style.css">'>

<div class="card about-card">
  <h2 class="section-title">О нас</h2>
  <p class="about-text">
    Добро пожаловать на наш сайт петиций!

    Мы — команда энтузиастов, стремящихся сделать общество лучше с помощью цифровых инициатив.
    Наша цель — дать каждому голос, который будет услышан.

    Мы верим, что даже одна подпись может изменить многое.
  </p>

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
