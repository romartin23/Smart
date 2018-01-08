$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/getServiceConfigurationParameter?serviceConfiguration=/discovery3&key=MB_DB_HOST"
    }).then(function(data) {
       $('.greeting-id').append(data.id);
       $('.greeting-content').append(data.key);
    });
});