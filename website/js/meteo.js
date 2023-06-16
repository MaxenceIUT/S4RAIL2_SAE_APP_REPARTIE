async function getListMeteoJson(){
    let response = await fetch('https://www.infoclimat.fr/public-api/gfs/json?_ll=48.67103,6.15083&_auth=ARsDFFIsBCZRfFtsD3lSe1Q8ADUPeVRzBHgFZgtuAH1UMQNgUTNcPlU5VClSfVZkUn8AYVxmVW0Eb1I2WylSLgFgA25SNwRuUT1bPw83UnlUeAB9DzFUcwR4BWMLYwBhVCkDb1EzXCBVOFQoUmNWZlJnAH9cfFVsBGRSPVs1UjEBZwNkUjIEYVE6WyYPIFJjVGUAZg9mVD4EbwVhCzMAMFQzA2JRMlw5VThUKFJiVmtSZQBpXGtVbwRlUjVbKVIuARsDFFIsBCZRfFtsD3lSe1QyAD4PZA%3D%3D&_c=19f3aa7d766b6ba91191c8be71dd1ab2')
    response = await response.json()
    // console.log(response)


    for(let date in response){
        if(date.length == 19){
            console.log(date);
        }
    }

    const currentDate = new Date()
    const year = currentDate.getFullYear()
    // const month = `${currentDate.getMonth() + 1}`.length > 1 ? `${currentDate.getMonth() + 1}`

    const days = currentDate.getDate()
    console.log(days);





}

getListMeteoJson()