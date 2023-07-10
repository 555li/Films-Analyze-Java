window.onload = function () {
    highlightNav();
}
const loginSuccess = document.querySelector('#login-success');
loginSuccess.classList.remove('hide');
// 3秒后自动消失
setTimeout(() => {
    loginSuccess.classList.add('hide');
}, 1000);
//公告弹窗
var noticeBox = document.getElementById('notice-box');
var noticeClose = document.getElementById('notice-close');
var closeSpan = document.getElementById("close-time");
let count = 15;

noticeBox.style.display = 'block';

noticeClose.onclick = function () {
    noticeBox.style.display = 'none';
};

closeSpan.innerText = count + '秒后关闭';
const timer = setInterval(() => {
    count--;
    closeSpan.innerText = count + '秒后关闭';
    if (count === 0) {
        clearInterval(timer);
        noticeBox.style.display = 'none';
    }
}, 1000);

//设置点击a标签，a标签亮
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


// 定义需要滚动的文本列表
var textList = [
    "使用数据驱动决策，让决策更有底气！",
    "投资前，先分析数据，让决策更准确！",
    "数据助力，决策无忧！",
    "放弃猜测，拥抱数据，决策更科学！",
    "走向数据时代，走向正确决策！",
];
// 获取 marquee 元素
var marquee = document.getElementById("myMarquee");
// 定时器每隔3秒更新一次滚动文本
setInterval(function() {
    // 随机从文本列表中选择一段文本
    var randomIndex = Math.floor(Math.random() * textList.length);
    var randomText = textList[randomIndex];
    // 将 marquee 元素的文本内容更新为随机选择的文本
    marquee.innerHTML = "<i>" + randomText + "</i>";
}, 20000);

//分享按钮点击事件
// 获取分享按钮和分享渠道按钮元素
const weiboShareBtn = document.getElementById('weibo-share');
const qqShareBtn = document.getElementById('qq-share');
const douBanShareBtn = document.getElementById('douban-share');

// 定义分享渠道链接和分享文本
const shareUrl = location.href;
const shareTitle = document.title;
const weiboTitle = encodeURIComponent(`${shareTitle} ${shareUrl}`);

// 添加新浪微博分享按钮点击事件监听器
weiboShareBtn.addEventListener('click', function() {
    const urlParam = `url=${shareUrl}&title=${weiboTitle}`;
    window.open(`http://service.weibo.com/share/share.php?${urlParam}`);
});
// 添加QQ分享按钮点击事件监听器
qqShareBtn.addEventListener('click', function() {
    const urlParam = `url=${shareUrl}&title=${shareTitle}`;
    window.open(`http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?&${urlParam}`);

});
// 添加豆瓣分享按钮点击事件监听器
douBanShareBtn.addEventListener('click', function() {
    const urlParam = `url=${shareUrl}&name=${shareTitle}`;
    window.open(`http://shuo.douban.com/!service/share??&${urlParam}`);
});

//数据来源a标签绑定鼠标事件
function showDoubanTip() {
    var tip = document.createElement("div");
    tip.innerHTML = "豆瓣网址：www.douban.com";
    tip.style.position = "absolute";
    tip.style.top = (event.clientY - tip.offsetHeight - 60) + "px";
    tip.style.left = event.clientX + "px";
    tip.style.padding = "5px";
    tip.style.backgroundColor = "#fedcbd";
    tip.style.border = "1px solid #ccc";
    document.body.appendChild(tip);
}
function hideDoubanTip() {
    var tips = document.getElementsByTagName("div");
    for (var i = 0; i < tips.length; i++) {
        if (tips[i].innerHTML == "豆瓣网址：www.douban.com") {
            document.body.removeChild(tips[i]);
        }
    }
}
//微信二维码mouseover事件
function showWechatImg() {
    var tip = document.createElement("div");
    tip.innerHTML = "<img src='../img/weChatConnect.png'>";
    var img = tip.querySelector("img"); // 获取图片元素
    img.width = 90; // 设置图片宽度为90px
    img.height = 130; // 设置图片高度为130px
    tip.style.position = "absolute";
    tip.style.top = (event.clientY - tip.offsetHeight - 160) + "px";
    tip.style.left = event.clientX + "px";
    tip.style.padding = "5px";
    tip.style.backgroundColor = "#fedcbd";
    tip.style.border = "1px solid #ccc";
    document.body.appendChild(tip);
}
//微信二维码mouseout事件
function hideWechatImg() {
    var tips = document.getElementsByTagName("div");
    for (var i = 0; i < tips.length; i++) {
        if (tips[i].innerHTML.indexOf( "weChatConnect.png")>-1) {
            document.body.removeChild(tips[i]);
        }
    }
}
//QQ二维码mouseover事件
function showQQImg() {
    var tip = document.createElement("div");
    tip.innerHTML = "<img src='../img/qqChatConnect.png'>";
    var img = tip.querySelector("img"); // 获取图片元素
    img.width = 90; // 设置图片宽度为90px
    img.height = 130; // 设置图片高度为130px
    tip.style.position = "absolute";
    tip.style.top = (event.clientY - tip.offsetHeight - 160) + "px";
    tip.style.left = event.clientX + "px";
    tip.style.padding = "5px";
    tip.style.backgroundColor = "#fedcbd";
    tip.style.border = "1px solid #ccc";
    document.body.appendChild(tip);
}
//QQ二维码mouseout事件
function hideQQImg() {
    var tips = document.getElementsByTagName("div");
    for (var i = 0; i < tips.length; i++) {
        if (tips[i].innerHTML.indexOf( "qqChatConnect.png")>-1) {
            document.body.removeChild(tips[i]);
        }
    }
}













