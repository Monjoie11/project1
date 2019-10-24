function showPassword() {
  let x = document.getElementById("employee-password");
  if (x.type === "password") {
    x.type = "text";
  } else {
    x.type = "password";
  }
}
