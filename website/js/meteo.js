let response = await fetch('https://www.infoclimat.fr/public-api/gfs/json?_ll=48.67103,6.15083&_auth=ARsDFFIsBCZRfFtsD3lSe1Q8ADUPeVRzBHgFZgtuAH1UMQNgUTNcPlU5VClSfVZkUn8AYVxmVW0Eb1I2WylSLgFgA25SNwRuUT1bPw83UnlUeAB9DzFUcwR4BWMLYwBhVCkDb1EzXCBVOFQoUmNWZlJnAH9cfFVsBGRSPVs1UjEBZwNkUjIEYVE6WyYPIFJjVGUAZg9mVD4EbwVhCzMAMFQzA2JRMlw5VThUKFJiVmtSZQBpXGtVbwRlUjVbKVIuARsDFFIsBCZRfFtsD3lSe1QyAD4PZA%3D%3D&_c=19f3aa7d766b6ba91191c8be71dd1ab2')
response = await response.json()
// console.log(response)

let currentDate = new Date()
const year = currentDate.getFullYear()
const month = `${currentDate.getMonth() + 1}`.length > 1 ? `${currentDate.getMonth() + 1}` : `0${currentDate.getMonth() + 1}`
const days = currentDate.getDate()
const hours = `${currentDate.getHours()}`.length > 1 ? `${currentDate.getHours()}` : `0${currentDate.getHours()}`
currentDate = `${year}-${month}-${days} ${hours}:00:00`
// console.log(currentDate)

let list_date = []
for(let date in response){
    if(date.length === 19 && date > currentDate){
        list_date.push(date)
        // console.log(date)
    }
}

list_date.length = 8
console.log(list_date)

let meteobars = document.querySelectorAll(".element-bar");

let index = 0;
for(let meteobar of meteobars){
    meteobar.textContent = list_date[index]
    index++
}