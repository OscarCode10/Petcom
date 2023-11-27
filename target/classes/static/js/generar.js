const generarPswd = document.getElementById("generarPswd");
const pswd = document.getElementById("password");

generarPswd.addEventListener("click", () => {
  const generatedPassword = generatePassword(8);
  pswd.value = generatedPassword
});

let generatePassword = (length) => {
  const charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+";
  let password = "";

  for (let i = 0; i < length; i++) {
    const randomIndex = Math.floor(Math.random() * charset.length);
    password += charset[randomIndex];
  }
  return password;
}

const showPswd = document.getElementById("showPswd");

showPswd.addEventListener('change', () => {
  pswd.type = showPswd.checked ? "text" : "password";
});