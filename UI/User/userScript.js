let authServiceURl = "http://localhost:8080/Diego-Leon-P1/Reimbursement";



async function submitNewRequest(newRequest) {

    let response = await fetch(
        authServiceURl,
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                'Accept': 'application/json',
                'authToken': localStorage.getItem('authToken'),

            },
            body: JSON.stringify(newRequest)
        }
    );

    return response;
}


async function getPendingRequests() {

    let response = await fetch(
        authServiceURl,
        {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                'Accept': 'application/json',
                'authToken': localStorage.getItem('authToken'),
                'case': 1
            },
        }
    );

    return response;
}
