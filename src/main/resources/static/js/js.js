let url = 'http://localhost:8080/user/document/1';
async function start(a) {
    fetch('url')
        .then(response => response.json())        // получаем ответ в виде промиса
        .then(data => {console.log(data)})
    alert(a);
    // .catch(error => console.error(error))
}

document.querySelector("#submit").onclick = function(){
    // alert("Вы нажали на кнопку");
    start("Zaebal");
}


// async function start() {
//   fetch('url', {
//     mode: "no-cors",
//     headers: {
//       'Content-Type': 'application/json',
//       'Accept': 'application/ json',
//       'User-agent': 'Chrome/64.0 My Own Agent' }})
//   .then(response => response.json())        // получаем ответ в виде промиса
//   .then(data => {
//       console.log(data)                          // выводим данные в консоль
//     })
//     // .catch(error => console.error(error))
//  }

//   fetch('http://localhost:8080/user/document/', {
//                       method: "GET",
//                       mode: "no-cors"
//                   })
//                   .then(response => response.json())
//                   .then(data => {
//                     console.log(data)
//                   })