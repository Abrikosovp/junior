function validate() {
    var result = true;

    var message = '';

    var name = $('#name');
    var login = $('#login');
    var password = $('#password');
    var email = $('#email');

    var arr = [name, login, password, email];


    if (!checkValue(name.val())) {
        message = "The name must start with a letter" + '\n';
        var result = false;
    }


    if (!checkValue(login.val())) {
        message = "The login must start with a letter" + '\n';
        var result = false;
    }

    if (!checkPassword(password.val())) {
        message = "The password must contains 8 or more symbols or numbers" + '\n';
        var result = false;
    }

    if (!checkEmail(email.val())) {
        message = "Please enter correctly email" + '\n';
        var result = false;
    }

    $(arr).each(function () {
        if ($(this).val() === "") {
            message += $(this).attr('placeholder') + '\n';
            result = false;
        }
    });

    if (!result) {
        alert(message);
    }

    return result;
}


function checkEmail(email) {
    var regEx = /^[0-9A-Za-zА-Яа-я]{1}[-0-9A-Za-zА-Яа-я\\.]{1,}@[-0-9A-Za-zА-Яа-я\\.]{1,}.{1}[A-Za-zА-Яа-я]{2,}$/;
    return regEx.test(email);
}

function checkValue(value) {
    var regEx = /^[A-Za-zА-Яа-я]+.*/;
    return regEx.test(value);
}

function checkPassword(pass) {
    var regEx = /^[A-Za-zА-Я0-9]{8,}$/;
    return regEx.test(pass);
}
