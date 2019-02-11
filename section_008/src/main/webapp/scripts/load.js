function getPersons() {
    $.ajax({
        url: "json",
        method: "get",
        complete: function (data) {
            var result = "";
            var persons = JSON.parse(data.responseText);
            for (var i = 0; i < persons.length; i++) {
                result += "<tr>"
                    + "<td>" + persons[i].firstname + "</td>"
                    + "<td>" + persons[i].secondname + "</td>"
                    + "<td>" + persons[i].sex + "</td>"
                    + "<td>" + persons[i].description + "</td>"
                    +"</tr>"
            }
            var table = document.getElementById("personstable");
            table.innerHTML = result;
        }
    });
}

function sendPerson() {
    var fname = $('#fname');
    var lname = $('#lname');
    var desc = $('#desc');
    var sex = $('input[name="sex"]:checked');
    var arr = [fname, lname, desc];

    if (validate(arr, sex)) {
        var person = {
            "firstname": $('#fname').val(),
            "secondname": $('#lname').val(),
            "sex": $('input[name="sex"]:checked').val(),
            "description": $('#desc').val()}

        $.ajax({
            url: "json",
            method: "post",
            data: JSON.stringify(person),
            contentType: "text/json; charset=utf-8",
            error: function (message) {
                console.log(message);
            },
            complete: function () {
                getPersons()
            },
            success: function (data) {
                console.log(data);
            }
        });
    }
}

function validate(arr, sex) {
    var result = true;
    var message = '';

    $(arr).each(function() {
        if ($(this).val() === "") {
            message += $(this).attr('placeholder') + '\n';
        }
    });

    if (sex.length === 0) {
        result = false;
        message += 'Choose your gender';
    }

    if (!result) {
        alert(message);
    }
    return result;
}