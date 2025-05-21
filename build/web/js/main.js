const bar = document.getElementById('bar');
const close = document.getElementById('close');
const nav = document.getElementById('navbar');

if (bar) {
    bar.addEventListener('click', () => {
        nav.classList.add('active');
    })
}

if (close) {
    close.addEventListener('click', () => {
        nav.classList.remove('active');
    })
}

const menuToggle = document.getElementById('menu-toggle');
const navbar = document.getElementById('navbar');
menuToggle.addEventListener('click', function () {
    navbar.classList.toggle('show');
});
// Đóng menu khi click ra ngoài (tùy chọn)
document.addEventListener('click', function (e) {
    if (!navbar.contains(e.target) && !menuToggle.contains(e.target)) {
        navbar.classList.remove('show');
    }
});