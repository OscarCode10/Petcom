document.addEventListener('DOMContentLoaded', function() {
  // 1. Acceder al formulario
  let form = document.getElementById("form")
  let numeroDoc = document.querySelector("#numeroDoc .feedback")
  let nombre = document.querySelector("#nombre .feedback")
  let apellido = document.querySelector("#apellido .feedback")
  let telefono = document.querySelector("#telefono .feedback")
  let correo = document.querySelector("#correo .feedback")
  let clave = document.querySelector("#clave .feedback")
  let fecha = document.querySelector("#fecha .feedback")
  
  //2. reglas de validación
  const $num = /^[0-9]{1,15}/
  const $text = /^[A-Za-zñÑáÁ]{10,50}/
  const $email = /\S+@\S+\.\S+/
  const $name = /[A-Za-zñÑáÁ]{3,15}/
  const $lastName = /[A-Za-zñÑáÁ]{6,15}/
  const $contra = /[A-Za-zñÑáÁ]{3,15}/
  
  //3. Validación antes de enviar al formulario
  //Numero Doc
  form.noDoc.addEventListener('input', (e) => {
    e.preventDefault();
    if ($num.test(e.target.value)) {
      form.noDoc.setAttribute("class", "sucess")
      numeroDoc.style.setProperty("visibility", "hidden")
      numeroDoc.style.setProperty("opacity", "0")
    }
    else {
      form.noDoc.setAttribute("class", "error")
      numeroDoc.textContent = "Solo pueden ir números"
      numeroDoc.style.setProperty("visibility", "visible")
      numeroDoc.style.setProperty("opacity", "1")
    }
  })
  
  //Nombre
  form.name.addEventListener('input', (e) => {
    e.preventDefault();
    if ($text.test(e.target.value)) {
      form.name.setAttribute("class", "sucess")
      nombre.style.setProperty("visibility", "hidden")
      nombre.style.setProperty("opacity", "0")
    }
    else {
      form.name.setAttribute("class", "error")
      nombre.textContent = "Solo pueden ir números"
      nombre.style.setProperty("visibility", "visible")
      nombre.style.setProperty("opacity", "1")
    }
  })
  
  //Apellido
  form.lastName.addEventListener('input', (e) => {
    e.preventDefault();
    if ($text.test(e.target.value)) {
      form.lastName.setAttribute("class", "sucess")
      apellido.style.setProperty("visibility", "hidden")
      apellido.style.setProperty("opacity", "0")
    }
    else {
      form.lastName.setAttribute("class", "error")
      apellido.textContent = "Solo pueden ir números"
      apellido.style.setProperty("visibility", "visible")
      apellido.style.setProperty("opacity", "1")
    }
  })
  
  //Teléfono
  form.tel.addEventListener('input', (e) => {
    e.preventDefault()
    if ($num.test(e.target.value)) {
      form.tel.setAttribute("class", "sucess")
      telefono.style.setProperty("visibility", "hidden")
      telefono.style.setProperty("opacity", "0")
    }
    else {
      form.tel.setAttribute("class", "error")
      telefono.textContent = "Solo pueden ir números"
      telefono.style.setProperty("visibility", "visible")
      telefono.style.setProperty("opacity", "1")
    }
  })
  
  //Correo
  form.email.addEventListener('input', (e) => {
    e.preventDefault();
    if ($email.test(e.target.value)) {
      form.email.setAttribute("class", "sucess")
      correo.style.setProperty("visibility", "hidden")
      correo.style.setProperty("opacity", "0")
    }
    else {
      form.email.setAttribute("class", "error")
      correo.textContent = "Solo pueden ir números"
      correo.style.setProperty("visibility", "visible")
      correo.style.setProperty("opacity", "1")
    }
  })
  
  //Contraseña
  form.pswd.addEventListener('input', (e) => {
    e.preventDefault()
    if ($contra.test(e.target.value)) {
      form.pswd.setAttribute("class", "sucess")
      clave.style.setProperty("visibility", "hidden")
      clave.style.setProperty("opacity", "0")
    }
    else {
      form.pswd.setAttribute("class", "error")
      clave.textContent = "Solo pueden ir números"
      clave.style.setProperty("visibility", "visible")
      clave.style.setProperty("opacity", "1")
    }
  })
  
  // //4. Validación de campos vacíos
  // form.addEventListener("submit", (e) => {
  //   e.preventDefault() //Detener propagación
  
  
  //   if (noDoc == null || noDoc == 0) {
  //     alert(`Debe ingresar un número de documento`)
  //     frmvalidacion.noDoc.focus()
  //     frmvalidacion.noDoc.setAttribute("class", "error")
  //   }
  //   else {
  //     frmvalidacion.submit()//Reanudar el envio del formulario
  //     alert(`Los datos fueron enviados`)
  //   }
  // })
});
