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


async function deletingRequest(reimbID) {

    let response = await fetch(
        authServiceURl,
        {
            method: "DELETE",
            headers: {
                reimbursement: reimbID
            },
        }
    );

    return response;
}


async function getRequestbyID(reimbID) {

    let response = await fetch(
        authServiceURl,
        {
            method: "GET",
            headers: {
                case: 3,
                reimbursement: reimbID
            },
        }
    );

    return response;
}

async function updateRequest(updatedRequest) {

    let response = await fetch(
        authServiceURl,
        {
            method: "PUT",
            headers: {
                'Content-Type': "application/json",
                'Accept': 'application/json',
                'authToken': localStorage.getItem('authToken'),
                'reimbursement': localStorage.getItem('localID'),
            },
            body: JSON.stringify(updatedRequest)
        }

    );
    localStorage.removeItem('localID')
    return response;
}
