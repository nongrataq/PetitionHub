function quickSearch(query) {
    if (query && query.trim()) {
        window.location.href = `/search?query=${encodeURIComponent(query.trim())}`;
    }
}

document.addEventListener('DOMContentLoaded', function() {
    const searchBtn = document.getElementById('searchBtn');
    const searchInput = document.getElementById('searchInput');

    function handleSearch() {
        const query = searchInput.value.trim();
        if (query) {
            window.location.href = `/search?query=${encodeURIComponent(query)}`;
        } else {
            // Дополнительная обработка пустого запроса (опционально)
            searchInput.focus();
            // Можно добавить визуальную индикацию ошибки
            searchInput.classList.add('is-invalid');
            setTimeout(() => searchInput.classList.remove('is-invalid'), 2000);
        }
    }

    // Обработчик клика по кнопке
    searchBtn.addEventListener('click', handleSearch);

    // Обработчик нажатия Enter в поле ввода
    searchInput.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            handleSearch();
        }
    });

    /* Аватар пользователя */
    .avatar-container {
      margin: 0 auto 20px;
      width: 120px;
      height: 120px;
    }

    .avatar, .avatar-placeholder {
      width: 100%;
      height: 100%;
      border-radius: 50%;
      object-fit: cover;
      border: 3px solid var(--accent-start);
    }

    .avatar-placeholder {
      display: flex;
      align-items: center;
      justify-content: center;
      background-color: #f0f0f0;
      font-size: 3rem;
      color: var(--accent-start);
    }

    /* Имя пользователя */
    .author-name {
      color: var(--accent-start);
      margin-bottom: 1rem;
    }

    /* Статистика */
    .user-stats {
      display: flex;
      justify-content: center;
      gap: 2rem;
      margin-bottom: 1.5rem;
    }

    .user-stats p {
      display: flex;
      align-items: center;
      gap: 0.5rem;
      margin: 0;
    }

    /* Описание пользователя */
    .user-description {
      max-width: 600px;
      margin: 0 auto;
      padding: 1rem;
      background: #f8f9fa;
      border-radius: 8px;
    }

    /* Адаптация */
    @media (max-width: 576px) {
      .user-stats {
        flex-direction: column;
        gap: 0.5rem;
      }

      .avatar-container {
        width: 100px;
        height: 100px;
      }
    }
});