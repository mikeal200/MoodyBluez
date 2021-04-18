const date = new Date();
var id = null;

var opt = {
    autoOpen: false,
    modal: false,
    width: 500,
    height: 360,
    title: 'Moody'
};

const renderCalendar = () => {
    date.setDate(1);
    const monthDays = document.querySelector(".days");
    const lastDay = new Date(
        date.getFullYear(),
        date.getMonth() + 1,
        0
    ).getDate();
    const prevLastDay = new Date(
        date.getFullYear(),
        date.getMonth(),
        0
    ).getDate();
    const firstDayIndex = date.getDay();
    const lastDayIndex = new Date(
        date.getFullYear(),
        date.getMonth() + 1,
        0
    ).getDay();
    const nextDays = 7 - lastDayIndex - 1;
    const months = [
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December",
    ];
    document.querySelector(".date h1").innerHTML = months[date.getMonth()];
    document.querySelector(".date p").innerHTML = new Date().toDateString();
    let days = "";
    for (let x = firstDayIndex; x > 0; x--) {
        days += `<div class="prev-date">${prevLastDay - x + 1}</div>`;
    }
    for (let i = 1; i <= lastDay; i++) {
        if (
            i === new Date().getDate() &&
            date.getMonth() === new Date().getMonth()
        ) {
            days += `<div class="today">${i}</div>`;
        } else {
            days += `<div class="${i}">${i}</div>`;
        }
    }
    if(nextDays > 0) {
        for (let j = 1; j <= nextDays; j++) {
            days += `<div class="next-date">${j}</div>`;
            monthDays.innerHTML = days;
        }
    }
    else {
        monthDays.innerHTML = days;
    }
    for (let i = 1; i <= lastDay; i++) {
        if(i === new Date().getDate() &&
            date.getMonth() === new Date().getMonth()) {
            document.querySelector(".today").addEventListener("click", (e) => {
                openDialog(document.querySelector(".today").innerHTML);
                renderCalendar();
            });
        }
        else if(i < 10){
            document.querySelector(".\\3" + i).addEventListener("click", (e) => {
                openDialog(e.target.innerText);
                renderCalendar();
            });
        }
        else if(i >= 10 && i < 20) {
            j = i - 10
            document.querySelector(".\\31 " + j).addEventListener("click", (e) => {
                openDialog(e.target.innerText);
                renderCalendar();
            });
        }
        else if(i >= 20 && i < 30) {
            j = i - 20
            document.querySelector(".\\32 " + j).addEventListener("click", (e) => {
                openDialog(e.target.innerText);
                renderCalendar();
            });
        }
        else if(i >= 30) {
            j = i - 30
            document.querySelector(".\\33 " + j).addEventListener("click", (e) => {
                openDialog(e.target.innerText);
                renderCalendar();
            });
        }
    }
};

function initDialog(){
    $.get("/mood",function (data){
        var html_var = '';
        for(var i=0;i<data.length;i++){
            if(i==0){
                html_var+='<option value="'+data[i].moodId+'" selected="selected">'+data[i].description+'</option>';
            }
            else{
                html_var+='<option value="'+data[i].moodId+'">'+data[i].description+'</option>';
            }
        }
        $('#mood').html(html_var);
    });
}

function openDialog(day){
    var sel_year = date.getUTCFullYear().toString();
    var sel_month = date.getUTCMonth() + 1;
    sel_month = sel_month.toString();

    $.get('entry/'+sel_year+'/'+sel_month+'/'+day.toString(),function (data){
        if(data==undefined||data==""){
            $('#entryid').val('');
            $('#date').val(sel_year+'-'+sel_month.padStart(2,'0')+'-'+day.toString().padStart(2,'0'));
            $('#description').val('');
        }
        else{
            id = data.entryId;
            $('#date').val(data.date);
            $('#mood').val(data.moodId.toString());
            $('#description').val(data.description);
        }
        $(document).ready(function() {
            $("#dialog").dialog(opt).dialog("open");
        });
    });

}

function submitEntry(){
    let date = $('#date').val();
    var mood = $('#mood').val();
    var description = $('#description').val();

    if(isValidDate(date)==false){
        alert('error date format');
        return;
    }

    if(mood==undefined||mood==""){
        alert('error mood selection');
        return;
    }

    if(description==undefined||description==""){
        alert('error description');
        return;
    }

    date = new Date($('#date').val());
    date = new Date(date.getTime() + date.getTimezoneOffset() * 60000);
    var d = {date:date, moodId:mood, description:description, entryId:id};

    $.ajax({
        url:'/entry',
        type:'PUT',
        data:JSON.stringify(d),
        contentType: "application/json",
        success:function (data){
            closeDialog();
        }
    });
}

function closeDialog(){
    $( "#dialog" ).dialog({ autoOpen: false,width:'500px' });
}

function isValidDate(dateString) {
    var regEx = /^\d{4}-\d{2}-\d{2}$/;
    if(!dateString.match(regEx)) return false;  // Invalid format
    var d = new Date(dateString);
    var dNum = d.getUTCMilliseconds();
    if(!dNum && dNum !== 0) return false; // NaN value, Invalid date
    return d.toISOString().slice(0,10) === dateString;
}

document.querySelector(".prev").addEventListener("click", () => {
    date.setMonth(date.getMonth() - 1);
    renderCalendar();
});
document.querySelector(".next").addEventListener("click", () => {
    date.setMonth(date.getMonth() + 1);
    renderCalendar();
});

$(document).ready(function(){
    initDialog();
    closeDialog();
    renderCalendar();
});

document.getElementById("xlogin").onclick = function() {
    var xLogout = document.getElementById("xlogout");
    xLogout.style.display = "block";
    loginLogout();
}

function loginLogout() {
    var xLogin = document.getElementById("xlogin");
    var xLogout = document.getElementById("xlogout");
    var xRegister = document.getElementById("xregister");
    if (xLogout.style.visibility === "hidden") {
        xLogin.style.visibility = "hidden";
        xRegister.style.visibility = "hidden";
        xLogout.style.visibility = "visible";
    } else {
        xLogin.style.visibility = "visible";
        xRegister.style.visibility = "visible";
        xLogout.style.visibility = "hidden";
    }
}
