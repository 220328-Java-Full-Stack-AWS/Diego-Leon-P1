let reimbServiceURl = "http://localhost:8080/Diego-Leon-P1/Reimbursement";



async function submitNewRequest(newRequest) {

    let response = await fetch(
        reimbServiceURl,
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                'Accept': 'application/json',
                'authToken': localStorage.getItem('authToken'),
                'case': 1

            },
            body: JSON.stringify(newRequest)
        }
    );

    return response;
}


async function getPendingRequests() {

    let response = await fetch(
        reimbServiceURl,
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
        reimbServiceURl,
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
        reimbServiceURl,
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
        reimbServiceURl,
        {
            method: "PUT",
            headers: {
                'Content-Type': "application/json",
                'Accept': 'application/json',
                'authToken': localStorage.getItem('authToken'),
                'reimbursement': localStorage.getItem('localID'),
                'case': "1"
            },
            body: JSON.stringify(updatedRequest)
        }

    );
    localStorage.removeItem('localID')
    return response;
}

function logOutUser() {

    localStorage.clear();
    window.location.href = "../index.html";
}
