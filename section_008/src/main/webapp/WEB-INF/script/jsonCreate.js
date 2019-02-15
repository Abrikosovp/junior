$(document).ready(function () {
    $.ajax({
        url: "loc",
        method: "get",
        complete: function (data) {
            var result =  "<option name=\"countryradio\"></option>";
            var countries = JSON.parse(data.responseText);
            for (var i = 0; i < countries.length; i++) {
                result += "<option value=\""+ countries[i] + "\" name=\"countryradio\">" + countries[i] + "</option>";
            }
            document.getElementById("country").innerHTML = result;
        }
    });
});

function getCity() {
    $.ajax({
        url: "loc",
        method: "post",
        data: {"country" : $("#country").val()},
        complete: function (data) {
            var result =  "<option name=\"citiradio\"></option>";
            var cities = JSON.parse(data.responseText);
            for (var i = 0; i < cities.length; i++) {
                result += "<option value=\""+ cities[i] + "\" name=\"citiradio\">" + cities[i] + "</option>";
            }
            document.getElementById("city").innerHTML = result;
        }
    });
}