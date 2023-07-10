function getTime() {
    var date = new Date();
    var year = date.getFullYear();
    var month = (date.getMonth() + 1).toString().padStart(2, '0');
    var day = date.getDate().toString().padStart(2, '0');
    var hours = date.getHours().toString().padStart(2, '0');
    var minutes = date.getMinutes().toString().padStart(2, '0');
    var seconds = date.getSeconds().toString().padStart(2, '0');
    var timeString = year + '-' + month + '-' + day + ' ' +
        hours + ':' + minutes + ':' + seconds;
    return timeString;
}

setInterval(function () {
    document.getElementById('time').innerHTML = getTime();
}, 1000);