$(function(){
    // Attach the fancytree widget to an existing <div id="tree"> element
    // and pass the tree options as an argument to the fancytree() function:
    $("#tree").fancytree({
      extensions: ["filter"],
      quicksearch: true,
      source: {
		     url: "/greeting",
 		 cache: false
      },
      filter: {
        autoApply: true,   // Re-apply last filter if lazy data is loaded
        autoExpand: false, // Expand all branches that contain matches while filtered
        counter: true,     // Show a badge with number of matching child nodes near parent icons
        fuzzy: false,      // Match single characters in order, e.g. 'fb' will match 'FooBar'
        hideExpandedCounter: true,  // Hide counter badge if parent is expanded
        hideExpanders: false,       // Hide expanders if all child nodes are hidden by filter
        highlight: true,   // Highlight matches by wrapping inside <mark> tags
        leavesOnly: false, // Match end nodes only
        nodata: true,      // Display a 'no data' status node if result is empty
        mode: "dimm"       // Grayout unmatched nodes (pass "hide" to remove unmatched node instead)
      },
      activate: function(event, data) {
    	 if (data.node.key.includes("----")) {
	         loadData(data.node.key);
    	 } else {
    		 cleanEditorData();
    	 }
      },
      focus: function(event, data) {
    	  //A
      },
      lazyLoad: function(event, data) {
        data.result = {url: "ajax-sub2.json"}
      }
    // }).on("keydown", function(e){
    //   var c = String.fromCharCode(e.which);
    //   if( c === "F" && e.ctrlKey ) {
    //     $("input[name=search]").focus();
    //   }
    });
    var tree = $("#tree").fancytree("getTree");

    /*
     * Event handlers for our little demo interface
     */
    $("input[name=search]").keyup(function(e){
      var n,
        tree = $.ui.fancytree.getTree(),
        args = "autoApply autoExpand fuzzy hideExpanders highlight leavesOnly nodata".split(" "),
        opts = {},
        filterFunc = $("#branchMode").is(":checked") ? tree.filterBranches : tree.filterNodes,
        match = $(this).val();

      $.each(args, function(i, o) {
        opts[o] = $("#" + o).is(":checked");
      });
      opts.mode = $("#hideMode").is(":checked") ? "hide" : "dimm";

      if(e && e.which === $.ui.keyCode.ESCAPE || $.trim(match) === ""){
        $("button#btnResetSearch").click();
        return;
      }
      if($("#regex").is(":checked")) {
        // Pass function to perform match
        n = filterFunc.call(tree, function(node) {
          return new RegExp(match, "i").test(node.title);
        }, opts);
      } else {
        // Pass a string to perform case insensitive matching
        n = filterFunc.call(tree, match, opts);
      }
      $("button#btnResetSearch").attr("disabled", false);
      $("span#matches").text("(" + n + " matches)");
    }).focus();

    $("button#btnResetSearch").click(function(e){
      $("input[name=search]").val("");
      $("span#matches").text("");
      tree.clearFilter();
    }).attr("disabled", true);

    $("fieldset input:checkbox").change(function(e){
        var id = $(this).attr("id"),
          flag = $(this).is(":checked");

        // Some options can only be set with general filter options (not method args):
        switch( id ){
        case "counter":
        case "hideExpandedCounter":
          tree.options.filter[id] = flag;
          break;
        }
        tree.clearFilter();
        $("input[name=search]").keyup();
    });
  });