let authServiceURl = "http://localhost:8080/Diego-Leon-P1/auth";

// let userModel = {
//     firstName: fn,
//     lastName: ln,
//     email: email,
//     passord: pw
// }

// let newUser = {
//     password: document.getElementById("password").value,
//     firstName: document.getElementById("fname").value,
//     lastName: document.getElementById("lname").value,
//     email: document.getElementById("email").value
// }

async function registerNewUser(newUser) {

    let response = await fetch(
        authServiceURl,
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                'Accept': 'application/json',
                "case": "1"
            },
            body: JSON.stringify(newUser)
        }
    );

    return response;
}