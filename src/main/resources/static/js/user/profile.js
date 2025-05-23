document.addEventListener('DOMContentLoaded', function() {
  const btn = document.getElementById('togglePetitionsBtn');
  const petitionsSection = document.getElementById('petitionsSection');
  let visible = false;

  btn.addEventListener('click', () => {
    visible = !visible;
    petitionsSection.style.display = visible ? 'block' : 'none';
    btn.textContent = visible ? 'Скрыть петиции' : 'Показать петиции';
  });
});