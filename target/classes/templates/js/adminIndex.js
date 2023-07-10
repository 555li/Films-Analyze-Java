//修改修改密码标签的href属性
link="/pwdUpdate/"+$("#emailSpan").text()
$("#updatePwd").attr("href", link);

window.onload = function () {
    highlightNav();
}
const loginSuccess = document.querySelector('#login-success');
loginSuccess.classList.remove('hide');
// 3秒后自动消失
setTimeout(() => {
    loginSuccess.classList.add('hide');
}, 1000);

function highlightNav() {
    // 获取所有的a标签
    var navLinks = document.querySelectorAll('.nav-link');

    // 为每个a标签添加点击事件
    navLinks.forEach(function (link) {
        link.addEventListener('click', function () {
            // 移除所有已经高亮的a标签的高亮样式
            navLinks.forEach(function (link) {
                link.classList.remove('active');
            });

            // 给当前点击的a标签添加高亮样式
            this.classList.add('active');
        });
    });
}

function showWechatQRCode() {
    // 创建一个模态框
    var modal = document.createElement('div');
    modal.classList.add('modal');

    // 创建一个关闭按钮
    var closeButton = document.createElement('button');
    closeButton.classList.add('close-button');
    closeButton.textContent = '关闭';
    closeButton.addEventListener('click', function () {
        document.body.removeChild(modal);
    });

    // 创建一个二维码图片
    var qrCode = document.createElement('img');
    qrCode.src = './img/weChatConnect.png';
    qrCode.style.maxWidth = '300px';

    // 将关闭按钮和二维码图片添加到模态框中
    modal.appendChild(closeButton);
    modal.appendChild(qrCode);

    // 将模态框添加到页面中
    document.body.appendChild(modal);
}