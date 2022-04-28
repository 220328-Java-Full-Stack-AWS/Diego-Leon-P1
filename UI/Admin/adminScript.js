let reimbURl = "http://localhost:8080/Diego-Leon-P1/Reimbursement";
let userURl = "http://localhost:8080/Diego-Leon-P1/Reimbursement";



async function getRequests(status) {

    let response = await fetch(
        reimbURl,
        {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                'Accept': 'application/json',
                'authToken': localStorage.getItem('authToken'),
                'case': "2",
                'status': status
            },
        }
    );

    return response;
}

async function processRequest(reimbId, status) {

    let response = await fetch(
        reimbURl,
        {
            method: "PUT",
            headers: {
                'Content-Type': "application/json",
                'Accept': 'application/json',
                'authToken': localStorage.getItem('authToken'),
                'case': 2,
                'reimbursement': reimbId,
                'status': status
            },
        }
    );
    return response;
}
