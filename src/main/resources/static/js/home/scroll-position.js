// Сохраняем позицию скролла перед отправкой формы
function saveScrollPosition() {
    sessionStorage.setItem('scrollPosition', window.scrollY);
}

// Восстанавливаем позицию скролла после загрузки страницы
document.addEventListener('DOMContentLoaded', function () {
    const scrollPosition = sessionStorage.getItem('scrollPosition');
    if (scrollPosition) {
        window.scrollTo(0, parseInt(scrollPosition));
        sessionStorage.removeItem('scrollPosition');
    }
});
