 // Afficher/Masquer mot de passe
    function togglePasswordVisibility(button) {
    const input = button.closest('.input-group').querySelector('input');
    if (input.type === 'password') {
    input.type = 'text';
    button.innerHTML = '<i class="fa fa-eye-slash"></i>';
} else {
    input.type = 'password';
    button.innerHTML = '<i class="fa fa-eye"></i>';
}
}

    // Validation des formulaires
    (function () {
    'use strict';
    window.addEventListener('load', function () {
    const forms = document.getElementsByClassName('needs-validation');

    Array.prototype.forEach.call(forms, function (form) {
    form.addEventListener('submit', function (event) {

    // Chercher les noms des champs de mot de passe
    const passFields = [
{ pass: 'password', confirm: 'confirmPassword' },     // bureau
{ pass: 'Apassw', confirm: 'confirmPassword' },       // avocat
{ pass: 'Spassw', confirm: 'confirmPassword' },       // secretaire
{ pass: 'Cpassw', confirm: 'CconfirmPassword' }       // client
    ];

    passFields.forEach(field => {
    const passInput = form.querySelector(`input[name="${field.pass}"]`);
    const confirmInput = form.querySelector(`input[name="${field.confirm}"]`);

    if (passInput && confirmInput) {
    if (passInput.value !== confirmInput.value) {
    confirmInput.classList.add('is-invalid');
    event.preventDefault();
    event.stopPropagation();
} else {
    confirmInput.classList.remove('is-invalid');
}
}
});

    if (!form.checkValidity()) {
    event.preventDefault();
    event.stopPropagation();
}

    form.classList.add('was-validated');
}, false);
});
}, false);
})();

    // Mise à jour du nom du fichier sélectionné
    document.querySelectorAll('.custom-file-input').forEach(input => {
    input.addEventListener('change', function () {
        const fileName = this.files[0] ? this.files[0].name : 'اضافة صورة';
        this.nextElementSibling.textContent = fileName;
    });
});