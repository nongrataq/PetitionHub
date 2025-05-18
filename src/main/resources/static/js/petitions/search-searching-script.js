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
});