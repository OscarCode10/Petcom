function validarFormulario() {
    var tipoDocumentoUsu = document.getElementById("tipoDocumentoUsu").value;
    var numeroDocumento = document.getElementById("numerodocumento").value;
    var nombre = document.getElementById("nombre").value;
    var apellido = document.getElementById("apellido").value;
    var telefono = document.getElementById("telefono").value;
    var correoElectronico = document.getElementById("email").value;
    var password = document.getElementById("clave").value;
    var rolEstado = document.getElementById("rolEstado").value;
    var rol = document.getElementById("rol").value;

    // Validar campos obligatorios
    if (!tipoDocumentoUsu || !numeroDocumento || !nombre || !apellido || !telefono || !correoElectronico || !password || !rolEstado || !rol) {
      alert("Todos los campos son obligatorios.");
      return false;
    }

    /*Bloque TipoDocUsu*/

    //Validar que se elija un tipo de documento

    if(tipoDocumentoUsu == null){
        alert("Elija un tipo de documento");
        return false;
    }

    /*Fin bloque TipoDocUsu*/

    /*Bloque numero documento*/

    // Validar longitud del número de documento

    if (numeroDocumento.length == 10) {
      alert("El número de documento debe tener al menos 5 dígitos.");
      return false;
    }

    /*Fin bloque numero documento*/
    

    // Validar caracteres alfanuméricos para nombre y apellido
    var alphanumericRegex = /^[a-zA-Z0-9\s]+$/;
    if (!alphanumericRegex.test(nombre) || !alphanumericRegex.test(apellido)) {
      alert("El nombre y el apellido deben contener caracteres alfanuméricos.");
      return false;
    }


    // Validar dominio de correo electrónico
    var emailRegex = /^[a-zA-Z0-9._%+-]+@misena\.edu\.co$/;
    if (!emailRegex.test(correoElectronico)) {
      alert("El correo electrónico debe tener el dominio @misena.edu.co");
      return false;
    }

    // Validar contraseña
    var passwordRegex = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\W).{10,}/;
    if (!passwordRegex.test(password)) {
      alert("La contraseña debe contener al menos una letra mayúscula, una letra minúscula, un número, un carácter especial y ser de al menos 10 caracteres.");
      return false;
    }

    // Si todas las validaciones pasan, el formulario es válido
    alert("Formulario enviado exitosamente.");
    return true;
  }

  console.log("asjkhdajs")


  //onsubmit="return validarFormulario()"