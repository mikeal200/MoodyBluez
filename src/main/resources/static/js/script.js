const date = new Date();

var opt = {
    autoOpen: false,
    modal: false,
    width: 550,
    height:650,
    title: 'Details'
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
            document.querySelector(".today").addEventListener("click", () => {
                console.log("date clicked");
                openDialog(document.querySelector(".today").innerHTML);
                renderCalendar();
            });
        }
        else if(i < 10){
            document.querySelector(".\\3" + i).addEventListener("click", () => {
                console.log("date clicked");
                renderCalendar();
            });
        }
        else if(i >= 10 && i < 20) {
            j = i - 10
            document.querySelector(".\\31 " + j).addEventListener("click", () => {
                console.log("date clicked");
                renderCalendar();
            });
        }
        else if(i >= 20 && i < 30) {
            j = i - 20
            document.querySelector(".\\32 " + j).addEventListener("click", () => {
                console.log("date clicked");
                renderCalendar();
            });
        }
        else if(i >= 30) {
            j = i - 30
            document.querySelector(".\\33 " + j).addEventListener("click", () => {
                console.log("date clicked");
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
    var sel_month = date.getUTCMonth().toString();
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
    renderCalendar();
});