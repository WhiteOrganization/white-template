function raw(URI) {
    const request = new XMLHttpRequest();
    request.open("GET", URI);
    request.send();
    request.onload = () => {
        if (request.status === 200) {
            console.log(JSON.parse(request.response));
            // document.write(request.response);
            document.getElementById("answer").innerHTML = '<b>' + JSON.parse(request.response) + '</b><br/>' + request.response + '';
        } else {
            console.error('There was an error with the request: ${request.status}');
            alert("Request response status: " + request.status);
        }
    }
}

function sendRequest() {
    var port_number= location.port;
    consumeGetEndpoint("http://localhost:"+port_number+"/api/users",
        users => {
            let rows = "";
            console.log(users);
            for (let user of users) {
                rows += `
                <tr>
                    <td>${user.first_name}</td>
                    <td>${user.email}</td>
                </tr>
            `;
            }
            document.querySelector("#usersBody").innerHTML = rows;
        });
}

function consumeGetEndpoint(URI, action) {
    fetch(URI)
        .then(response => {
            return response.json();
        }).then(action);
}

function submit() {
    var port_number= location.port;
    consumePostEndpoint("http://localhost:"+port_number+"/api/user",
        {
            first_name: document.getElementById("first_name").value,
            email: document.getElementById("email").value
        },
        response => {
            alert(response.ok ? "Saved Successfully" : "Error when saving the user");
            sendRequest();
            console.log(response);
        });
}

function consumePostEndpoint(URI, rawPayload, action) {
    fetch(URI, {
        method: "POST",
        body: JSON.stringify(rawPayload),
        headers: { "Content-type": "application/json; charset=UTF-8" }
    })
        .then(action);
}

function consumeAsyncPostEndpoint(URI, rawPayload, action) {
    (async () => {
        const rawResponse = await fetch(URI, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(rawPayload)
        });
        const content = await rawResponse.json();
        action; //this is weird
    })();
}

