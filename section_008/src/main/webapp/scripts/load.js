function validate() {
    var result = true;
    var fname = $('#fname');
    var lname = $('#lname');
    var desc = $('#desc');
    var sex = $('input[name="sex"]:checked');

    var arr = [fname, lname, desc];
    var message = '';

    $(arr).each(function() {
        if ($(this).val() === "") {
            message += $(this).attr('placeholder') + '\n';
            return result;
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