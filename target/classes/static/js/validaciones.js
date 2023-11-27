const form = document.getElementById('form');
const usuario = document.getElementById('username');
const email = document.getElementById('email');
const password = document.getElementById('password');
const password2 = document.getElementById('password2');
const numeroDocumento = document.getElementById('numeroDocumento');
const apellido = document.getElementById('apellido');
const telefono = document.getElementById('telefono');
const fechaInput = document.getElementById('fecha');
const fechaInputS = document.getElementById('fechaS');
const razaInput = document.getElementById('raza');

form.addEventListener('submit', e => {
    e.preventDefault();
    checkInputs();
    return ;
});

function checkInputs() {
    const usuarioValue = usuario.value.trim();
    const emailValue = email.value.trim();
    const passwordValue = password.value.trim();
    const numeroDocumentoValue = numeroDocumento.value.trim();
    const apellidoValue = apellido.value.trim();
    const telefonoValue = telefono.value.trim();

    if (usuarioValue === '') {
        setErrorFor(usuario, 'No puede dejar el usuario en blanco');
    } else if (usuarioValue.length < 3) {
        setErrorFor(usuario, 'El usuario debe tener al menos 3 caracteres');
    } else if (!isValidInput(usuarioValue)) {
        setErrorFor(usuario, 'El usuario no puede contener caracteres especiales');
    } else {
        setSuccessFor(usuario);
    }

    if (apellidoValue === '') {
        setErrorFor(apellido, 'No puede dejar el apellido en blanco');
    } else if (apellidoValue.length < 3) {
        setErrorFor(apellido, 'El apellido debe tener al menos 3 caracteres');
    } else if (!isValidInput(apellidoValue)) {
        setErrorFor(apellido, 'El apellido no puede contener caracteres especiales');
    } else {
        setSuccessFor(apellido);
    }

    if (emailValue === '') {
        setErrorFor(email, 'No puede dejar el email en blanco');
    } else if (!isEmail(emailValue)) {
        setErrorFor(email, 'No ingresó un email válido');
    } else {
        setSuccessFor(email);
    }

    if (passwordValue === '') {
        setErrorFor(password, 'La contraseña no debe estar en blanco.');
    } else {
        setSuccessFor(password);
    }

    if (numeroDocumentoValue === '') {
        setErrorFor(numeroDocumento, 'No puede dejar el número de documento en blanco');
    } else {
        setSuccessFor(numeroDocumento);
    }

    if (telefonoValue === '') {
        setErrorFor(telefono, 'No puede dejar el teléfono en blanco');
    } else if (!isValidPhoneNumber(telefonoValue)) {
        setErrorFor(telefono, 'El número de teléfono debe tener entre 5 y 10 dígitos');
    } else {
        setSuccessFor(telefono);
    }
}

function setErrorFor(input, message) {
    const formControl = input.parentElement;
    const small = formControl.querySelector('small');
    formControl.className = 'form-control error';
    small.innerText = message;
}

function setSuccessFor(input) {
    const formControl = input.parentElement;
    formControl.className = 'form-control success';
}

function isEmail(email) {
    return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email);
}

function isValidPhoneNumber(phoneNumber) {
    return /^\d{5,10}$/.test(phoneNumber);
}

function isValidInput(input) {
    // Esta expresión regular permite letras y números, pero no caracteres especiales.
    return /^[a-zA-Z0-9]+$/.test(input);
}
