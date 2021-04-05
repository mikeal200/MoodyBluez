
// const date = new Date();
//
// const renderCalendar = () => {
//   date.setDate(1);
//
//   const monthDays = document.querySelector(".days");
//
//   const lastDay = new Date(
//     date.getFullYear(),
//     date.getMonth() + 1,
//     0
//   ).getDate();
//
//   const prevLastDay = new Date(
//     date.getFullYear(),
//     date.getMonth(),
//     0
//   ).getDate();
//
//   const firstDayIndex = date.getDay();
//
//   const lastDayIndex = new Date(
//     date.getFullYear(),
//     date.getMonth() + 1,
//     0
//   ).getDay();
//
//   const nextDays = 7 - lastDayIndex - 1;
//
//   const months = [
//     "January",
//     "February",
//     "March",
//     "April",
//     "May",
//     "June",
//     "July",
//     "August",
//     "September",
//     "October",
//     "November",
//     "December",
//   ];
//
//   document.querySelector(".date h1").innerHTML = months[date.getMonth()];
//
//   document.querySelector(".date p").innerHTML = new Date().toDateString();
//
//   let days = "";
//
//   for (let x = firstDayIndex; x > 0; x--) {
//     days += `<div class="prev-date">${prevLastDay - x + 1}</div>`;
//   }
//
//   for (let i = 1; i <= lastDay; i++) {
//     if (
//       i === new Date().getDate() &&
//       date.getMonth() === new Date().getMonth()
//     ) {
//       days += `<div class="today">${i}</div>`;
//     } else {
//       days += `<div class="${i}">${i}</div>`;
//     }
//   }
//
//   for (let j = 1; j <= nextDays; j++) {
//     days += `<div class="next-date">${j}</div>`;
//     monthDays.innerHTML = days;
//   }
//
//   for (let i = 1; i <= lastDay; i++) {
//     if(i === new Date().getDate() &&
//         date.getMonth() === new Date().getMonth()) {
//         document.querySelector(".today").addEventListener("click", () => {
//           console.log("date clicked");
//           renderCalendar();
//         });
//     }
//     else if(i < 10){
//         document.querySelector(".\\3" + i).addEventListener("click", () => {
//           console.log("date clicked");
//           renderCalendar();
//         });
//     }
//     else if(i >= 10 && i < 20) {
//         j = i - 10
//         document.querySelector(".\\31 " + j).addEventListener("click", () => {
//           console.log("date clicked");
//           renderCalendar();
//         });
//     }
//     else if(i >= 20 && i < 30) {
//         j = i - 20
//         document.querySelector(".\\32 " + j).addEventListener("click", () => {
//             console.log("date clicked");
//             renderCalendar();
//         });
//     }
//     else if(i >= 30) {
//         j = i - 30
//         document.querySelector(".\\33 " + j).addEventListener("click", () => {
//             console.log("date clicked");
//             renderCalendar();
//         });
//     }
//
//   }
//
//
// };
//
// document.querySelector(".prev").addEventListener("click", () => {
//   date.setMonth(date.getMonth() - 1);
//   renderCalendar();
// });
//
// document.querySelector(".next").addEventListener("click", () => {
//   date.setMonth(date.getMonth() + 1);
//   renderCalendar();
// });
//
// renderCalendar();



function refresh(){
    var table_obj = $('#table');
    var sel_year = parseInt($('#sel_year').value());
    var sel_month = parseInt($('#sel_month').value());

    var month_day = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

    var total_day = month_day[sel_month-1];
    if(sel_month==2){
        if(sel_year%400==0||(sel_year%4==0&&sel_year%100!=0)){
            total_day = 29;
        }
    }

    var dateObj = new Date();
    dateObj.setFullYear(sel_year);
    dateObj.setMonth(sel_month);
    dateObj.setDate(1);
    var beginWeek = dateObj.getDay();
    var rowNum = (beginWeek+total_day)/7+((beginWeek+total_day)%7==0?0:1);

    var showData = new Array(rowNum*7);
    for(var i=0;i<total_day;i++){
        showData[i+beginWeek] = i+1;
    }

    var html_str = '';
    for(var i=0;i<rowNum;i++){
        html_str += '<tr>';
        for(var j=i*7;j<(i+1)*7;j++){
            if(showData[j]==undefined){
                html_str += '<td></td>'
            }
            else{
                html_str += '<td><a href="javascript:void(0);" οnclick="openDialog('+showData[j].toString()+')">'+showData[j].toString()+'</a></td>'
            }
        }
        html_str += '</tr>';
    }
    table_obj.innerHTML = html_str;
};

function refreshSel(){
    sel_obj = $('#sel_year');
    sel_month_obj = $('#sel_month');
    var date_obj = new Date();
    var year = date_obj.getFullYear();
    var month = date_obj.getMonth();

    var html_str = '';
    for(var i=year-3;i<=year;i++){
        if(i==year){
            html_str+='<option value="'+String(i)+'" selected="selected">';
        }
        else{
            html_str+='<option value="'+String(i)+'">';
        }

        html_str+=String(i);
        html_str+='</option>';
    }
    sel_obj.innerHTML = html_str;

    html_str = '';
    for(var i=1;i<=12;i++){
        if(i==month){
            html_str+='<option value="'+String(i)+'" selected="selected">';
        }
        else{
            html_str+='<option value="'+String(i)+'">';
        }

        html_str+=String(i);
        html_str+='</option>';
    }
    sel_month_obj.innerHTML = html_str;

    sel_obj.onchange = function(){
        refresh();
    };

    sel_month_obj.onchange = function(){
        refresh();
    };
};

function openDialog(day){
    var sel_year = $('#sel_year').value();
    var sel_month = $('#sel_month').value();
    $.get('/entry/'+sel_year+'/'+sel_month+'/'+day.toString(),function (data){
        if(data==undefined){
            $('#entryid').val('');
        }
        else{
            var jdata = JSON.stringify(data);
            $('#entryid').val(data.entryid.toString());
        }
        $( "#dialog" ).dialog( "open" );
    });

}

function closeDialog(){
    $( "#dialog" ).dialog({ autoOpen: false });
}

function isValidDate(dateString) {
    var regEx = /^\d{4}-\d{2}-\d{2}$/;
    if(!dateString.match(regEx)) return false;  // Invalid format
    var d = new Date(dateString);
    var dNum = d.getTime();
    if(!dNum && dNum !== 0) return false; // NaN value, Invalid date
    return d.toISOString().slice(0,10) === dateString;
}

function submitEntry(){
    var date = $('#date').value();
    var mood = $('#mood').value();
    var description = $('#description').value();
    var id = $("#entryid").value();

    if(isValidDate(date)==false){
        alter('error date format');
        return;
    }

    if(mood==undefined||mood==""){
        alter('error mood selection');
        return;
    }

    if(description==undefined||description==""){
        alter('error description');
        return;
    }

    var data = {date:date, moodid:parseInt(mood),description:description, userid:1};
    if(id!=undefined&&id!=""){
        data['entityid'] = id;
    }


    $.ajax({
        url: '/echo/html/',
        type: 'PUT',
        data: JSON.stringify(),
        success: function(data) {
            closeDialog();
        }
    });
}

function initDialog(){
    $.get("/mood",function (data){
        var jdata = JSON.parse(data);
        var html_var = '';
        for(var i=0;i<jdata.length;i++){
            if(i==0){
                html_var+='<option value="'+jdata[i].moodid+'" selected="selected">'+jdata[i].description+'</option>';
            }
            else{
                html_var+='<option value="'+jdata[i].moodid+'">'+jdata[i].description+'</option>';
            }
        }
        $('#mood').innerHTML = html_var;
    });
}

function init(){
    closeDialog();
    refreshSel();
    refresh();
};


