const form = document.getElementById('form');
const consulta = document.getElementById('consulta');
const fechaInput = document.getElementById('fecha');    
const fechaInputs = document.getElementById('date');    



form.addEventListener('submit', e => {
    e.preventDefault();
    checkInputs();
});

function checkInputs() {
    
    const consultaValue = consulta.value.trim();
    const fechaValue = fechaInput.value.trim();
    const fechaValues = fechaInputs.value.trim();

    const fechaSeleccionada = new Date(fechaValue);
    const fechaSeleccionadas = new Date(fechaValues);

    const fechaActual = new Date();
    const fechaMaxima = new Date();
    fechaMaxima.setDate(fechaMaxima.getDate() + 7);

    if (consultaValue === '') {
        setErrorFor(consulta, 'No puede dejar el usuario en blanco');
    } else {
        setSuccessFor(usuario);
    }

    if (fechaSeleccionadas > fechaMaxima) {
        setErrorFor(fechaInputs, 'No puede seleccionar una fecha superior a 7 días desde hoy');
    } else {
        setSuccessFor(fechaInputs);
    }

    if (fechaSeleccionada < fechaActual) {
        setErrorFor(fechaInput, 'La fecha no puede ser inferior al día actual');
    } else {
        setSuccessFor(fechaInput);
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


function isValidInput(input) {
    // Esta expresión regular permite letras y números, pero no caracteres especiales.
    return /^[a-zA-Z0-9]+$/.test(input);
}
