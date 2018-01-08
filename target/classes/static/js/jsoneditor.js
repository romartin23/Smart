var options = {
	 mode: 'code',
	 modes: ['code', 'form', 'text', 'tree', 'view'], // allowed modes
};


// create the editor
var editor = new JSONEditor(document.getElementById('jsoneditor'), options);

$(document).ready(function () {
	
    $("#search-form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });

});

function fire_ajax_submit() {

    var data = editor.getText();

    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: "http://localhost:8080/putMemoryConcept",
        data: data,
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            $("#btn-search").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

            }
        });

    }
 
	function loadData(elementByGlobalIdentifier) {
	  	$.ajax({
	  	        url: "http://localhost:8080/getMemoryConcept?elementGlobalIdentifier="+elementByGlobalIdentifier
	    }).then(function(data) {
	    	editor.set(data,"serviceConfiguration");
	    });
    }
	
	function cleanEditorData() {
	    	editor.setText("");
    }
