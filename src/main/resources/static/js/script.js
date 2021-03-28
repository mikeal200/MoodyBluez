
const date = new Date();

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

  for (let j = 1; j <= nextDays; j++) {
    days += `<div class="next-date">${j}</div>`;
    monthDays.innerHTML = days;
  }

  for (let i = 1; i <= lastDay; i++) {
    if(i === new Date().getDate() &&
        date.getMonth() === new Date().getMonth()) {
        document.querySelector(".today").addEventListener("click", () => {
          console.log("date clicked");
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

document.querySelector(".prev").addEventListener("click", () => {
  date.setMonth(date.getMonth() - 1);
  renderCalendar();
});

document.querySelector(".next").addEventListener("click", () => {
  date.setMonth(date.getMonth() + 1);
  renderCalendar();
});

renderCalendar();
