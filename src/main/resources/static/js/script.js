document.addEventListener('DOMContentLoaded', function () {
  const loginTab = document.getElementById('loginTab');
  const registerTab = document.getElementById('registerTab');
  const loginContent = document.getElementById('loginContent');
  const registerContent = document.getElementById('registerContent');

  loginTab.addEventListener('click', function () {
    loginTab.classList.add('active');
    registerTab.classList.remove('active');
    loginContent.classList.add('active');
    registerContent.classList.remove('active');
  });

  registerTab.addEventListener('click', function () {
    registerTab.classList.add('active');
    loginTab.classList.remove('active');
    registerContent.classList.add('active');
    loginContent.classList.remove('active');
  });
});
