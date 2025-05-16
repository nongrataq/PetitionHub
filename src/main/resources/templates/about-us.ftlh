<#import "patterns/layout.ftlh" as layout>
<@layout.main title="About Us">

<style>
    .about-card {
      margin: 2rem auto;
      max-width: 800px;
      padding: 1.5rem;
    }

    .section-title {
      font-size: 1.75rem;
      font-weight: 600;
      margin-bottom: 1rem;
      color: var(--accent-start);
      text-align: center;
    }

    .about-text {
      color: var(--text-color);
      line-height: 1.6;
      white-space: pre-wrap;
    }

    .team-section {
      display: flex;
      flex-wrap: wrap;
      gap: 1rem;
      justify-content: center;
      margin-top: 2rem;
    }

    .team-member {
      background: var(--card-bg);
      border: 2px solid transparent;
      background-image: linear-gradient(var(--card-bg), var(--card-bg)),
                        linear-gradient(90deg, var(--accent-start), var(--accent-end));
      background-origin: padding-box, border-box;
      background-clip: padding-box, border-box;
      border-radius: 8px;
      padding: 1rem;
      width: 220px;
      box-shadow: 0 2px 6px var(--shadow);
      transition: transform 0.3s ease;
    }

    .team-member:hover {
      transform: translateY(-4px);
    }

    .member-name {
      font-weight: 600;
      color: var(--accent-start);
      margin-bottom: 0.5rem;
      text-align: center;
    }

    .member-role {
      color: var(--text-color);
      font-size: 0.95rem;
      text-align: center;
    }

    .team-link {
      text-decoration: none;
      display: block;
      transition: transform 0.3s ease;
    }

    .team-link:hover {
      transform: scale(1.02);
    }
</style>

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
                <div class="member-role">Никто</div>
            </div>
        </a>

        <a class="team-link" href="https://github.com/ReZorDos" target="_blank">
            <div class="team-member">
                <div class="member-name">Булат Тимуршин</div>
                <div class="member-role">Никто</div>
            </div>
        </a>

        <a class="team-link" href="https://github.com" target="_blank">
        <div class="team-member">
            <div class="member-name">Олег Грачев</div>
            <div class="member-role">Никто</div>
        </div>
        </a>
    </div>
</div>

</@layout.main>
