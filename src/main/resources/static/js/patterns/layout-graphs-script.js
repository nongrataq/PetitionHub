const canvas = document.getElementById('network-bg');
const ctx = canvas.getContext('2d');
let w, h, nodes = [], lines = [];

function init() {
  resize();
  createNodes(80);
  connectNodes(120);
  animate();
}

function resize() {
  w = canvas.width = window.innerWidth;
  h = canvas.height = window.innerHeight;
}

window.addEventListener('resize', resize);

function createNodes(count) {
  nodes = [];
  for (let i = 0; i < count; i++) {
    nodes.push({
      x: Math.random() * w,
      y: Math.random() * h,
      vx: (Math.random() - 0.5) * 0.3,
      vy: (Math.random() - 0.5) * 0.3
    });
  }
}

function connectNodes(maxDist) {
  lines = [];
  for (let i = 0; i < nodes.length; i++) {
    for (let j = i + 1; j < nodes.length; j++) {
      const dx = nodes[i].x - nodes[j].x;
      const dy = nodes[i].y - nodes[j].y;
      if (dx * dx + dy * dy < maxDist * maxDist) {
        lines.push([i, j]);
      }
    }
  }
}

function animate() {
  const rootStyles = getComputedStyle(document.documentElement);
  const lineColor = rootStyles.getPropertyValue('--line-color');
  const nodeColor = rootStyles.getPropertyValue('--node-color');

  ctx.clearRect(0, 0, w, h);
  nodes.forEach(n => {
    n.x += n.vx;
    n.y += n.vy;
    if (n.x < 0 || n.x > w) n.vx *= -1;
    if (n.y < 0 || n.y > h) n.vy *= -1;
  });

  ctx.strokeStyle = lineColor;
  lines.forEach(([i, j]) => {
    const a = nodes[i], b = nodes[j];
    ctx.beginPath();
    ctx.moveTo(a.x, a.y);
    ctx.lineTo(b.x, b.y);
    ctx.stroke();
  });

  ctx.fillStyle = nodeColor;
  nodes.forEach(n => {
    ctx.beginPath();
    ctx.arc(n.x, n.y, 2, 0, 2 * Math.PI);
    ctx.fill();
  });

  requestAnimationFrame(animate);
}

init();
