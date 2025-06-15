document.addEventListener('DOMContentLoaded', function() {
    const searchBtn = document.getElementById('searchBtn');
    const searchInput = document.getElementById('searchInput');

    function handleSearch() {
        const query = searchInput.value.trim();
        if (query) {
            window.location.href = `/search?query=${encodeURIComponent(query)}`;
        } else {
            searchInput.focus();
            searchInput.classList.add('is-invalid');
            setTimeout(() => searchInput.classList.remove('is-invalid'), 2000);
        }
    }

    searchBtn.addEventListener('click', handleSearch);

    searchInput.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            handleSearch();
        }
    });
});