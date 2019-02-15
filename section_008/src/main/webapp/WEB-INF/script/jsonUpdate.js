$(document).ready(function () {
    var country = $('#country').val();
    $.ajax({
        url: "loc",
        method: "get",
        complete: function (data) {
            var result =  "<option name=\"countryradio\"></option>";
            var countries = JSON.parse(data.responseText);
            for (var i = 0; i < countries.length; i++) {
                var selected = country == countries[i] ? "selected" : "";
                result += "<option " + selected +" value=\""
                    + countries[i] + "\" name=\"countryradio\">" + countries[i] + "</option>"
            }
            document.getElementById("country").innerHTML = result;
        }
    });
    fillCity();
});
function fillCity() {
    var country = $('#country').val();
    var city = $('#city').val();
    $.ajax({
        url: "loc",
        method: "post",
        data: {"country" : country },
        complete: function (data) {
            var result =  "<option name=\"citiradio\"></option>";
            var cities = JSON.parse(data.responseText);
            for (var i = 0; i < cities.length; i++) {
                var selected = city == cities[i] ? "selected" : "";
                result += "<option " + selected + " value=\""
                    + cities[i] + "\" name=\"citiradio\">" + cities[i] + "</option>";
            }
            document.getElementById("city").innerHTML = result;
        }
    });
}
function getCityByCountry() {
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