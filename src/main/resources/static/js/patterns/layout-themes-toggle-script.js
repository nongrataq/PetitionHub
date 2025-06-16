const themeSelect = document.getElementById('themeSelect');
const themeToggle = document.getElementById('themeToggle');
const themeIcon   = document.getElementById('themeIcon');
const root        = document.documentElement;

function applyTheme(palette, mode) {
  root.classList.remove('light-theme','dark-theme','classic','robot','pastel','modern','blue','bright','silver');
  root.classList.add(mode + '-theme', palette);
  themeIcon.className = mode === 'dark'
  ? 'bi bi-sun-fill'
  : 'bi bi-moon-stars-fill';
}

const savedPalette = localStorage.getItem('theme') || 'blue';
const savedMode    = localStorage.getItem('mode')  || 'light';
themeSelect.value = savedPalette;
applyTheme(savedPalette, savedMode);

themeSelect.addEventListener('change', () => {
  const newPalette = themeSelect.value;
  const mode = root.classList.contains('dark-theme') ? 'dark' : 'light';
  localStorage.setItem('theme', newPalette);
  applyTheme(newPalette, mode);
});

themeToggle.addEventListener('click', () => {
  const isLight = root.classList.contains('light-theme');
  const newMode = isLight ? 'dark' : 'light';
  const palette = themeSelect.value;
  localStorage.setItem('mode', newMode);
  applyTheme(palette, newMode);
});
