let authServiceURl = "http://localhost:8080/Diego-Leon-P1/auth";

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