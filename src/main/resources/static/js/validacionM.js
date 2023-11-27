    const form = document.getElementById('form');
    const usuario = document.getElementById('username');
    const apellido = document.getElementById('apellido');
    const raza = document.getElementById('raza');
    const fechaInput = document.getElementById('fecha');

    form.addEventListener('submit', e => {
        e.preventDefault();
        checkInputs();
    });

    function checkInputs() {
        const usuarioValue = usuario.value.trim();
        const apellidoValue = apellido.value.trim();
        const razaValue = raza.value.trim();
        const fechaValue = fechaInput.value.trim();
        const fechaSeleccionada = new Date(fechaValue);
        const fechaActual = new Date();
        
        if (fechaSeleccionada > fechaActual) {
            setErrorFor(fechaInput, 'La fecha no puede ser superior al día actual');
        } else {
            setSuccessFor(fechaInput);
        }
        

        if (razaValue === '') {
            setErrorFor(raza, 'No puede dejar la raza en blanco');
        } else if (razaValue.length < 3 || razaValue.length > 10) {
            setErrorFor(raza, 'La raza debe tener entre 3 y 10 caracteres');
        } else {
            setSuccessFor(raza);
        }


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
