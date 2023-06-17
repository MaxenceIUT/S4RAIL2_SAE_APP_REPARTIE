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
    meteobar.addEventListener('click',clickEvent)
    index++
}
clickEvent.apply(meteobars[0])

function clickEvent(target){
    let textArea = document.querySelector(".right-e-textArea")
    let contenu = response[this.textContent]
    console.log(contenu)
    textArea.innerHTML = `<h1>${this.textContent}</h1>
    <div>
        Temperature : ${(contenu['temperature']['sol'] - 273.15).toFixed(1)}℃    
        <br/>
        Pression : le niveau de la mer : ${contenu['pression']['niveau_de_la_mer']} Pa
        <br/>
        Quantité de pluie : ${contenu['pluie']}mm
        <br/>
        humidité : 2m : ${contenu['humidite']['2m']}%
        <br/>
        Vitesse moyenne du vent : 10m : ${contenu['vent_moyen']['10m']}m/s
        <br/>
        Vitesse des rafales de vent : 10m : ${contenu['vent_rafales']['10m']}m/s
        <br/>
        Direction du vent : 10m : ${contenu['vent_direction']['10m']} degrés (mesurée dans le sens des aiguilles d'une montre à partir du nord)
        <br/>
        Isotherme zéro degré : ${contenu['iso_zero']}m 
        <br/>
        Risque de neige : ${contenu['risque_neige']}  
        <br/>
        nébulosité :
        <br/>
        &nbsp;&nbsp;&nbsp;
        - Couverture nuageuse élevée : ${contenu['nebulosite']['haute']}%
        <br/>
        &nbsp;&nbsp;&nbsp;
        - Couverture nuageuse moyenne : ${contenu['nebulosite']['moyenne']}%
        <br/>
        &nbsp;&nbsp;&nbsp;
        - Couverture nuageuse basse : ${contenu['nebulosite']['basse']}%
        <br/>
        &nbsp;&nbsp;&nbsp;
        - Couverture nuageuse totale : ${contenu['nebulosite']['totale']}%
       
    </div>
    `;
}

