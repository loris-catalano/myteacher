/*document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('login-form');
    form.addEventListener('submit', function(event) {

        event.preventDefault();



        const formData = new FormData(form);
        formData.get("email")

        const email = formData.get("email")
        const password = formData.get("password")
        const role = formData.get("role")

        const base64Credentials = btoa(`${email}:${password}`);

        const options = {
            method: 'GET',
            headers: {
                'Authorization': `Basic ${base64Credentials}`
            }
        }

        console.log(role)

        if (role === 'ROLE_STUDENT') {
            console.log("prova2")

            fetch('/student/homepage.html', options)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok ' + response.statusText);
                    }
                    window.location.pathname='/student/homepage.html'

                })
                .then(data => {
                    console.log(data);
                })
                .catch(error => {
                    console.error(error);
                });
        }


    })
});*/