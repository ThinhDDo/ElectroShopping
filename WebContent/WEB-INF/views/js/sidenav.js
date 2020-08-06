$(document).ready(function (){

    // Load homepage
    $.ajax({
        url: "home.html"
    })
    .done(function(reponseHTML) {
        $(".content").empty().append(reponseHTML);                
    })

    // Load page when click on menu
    $("div.sidebar > a").click(function (event) {
    	
    	// Remove class 'active' from a tag
        $("div.sidebar").children().removeClass("active");
    	
        // Get the link from href
        let link = $(this).attr("href");
        // Change to 'active'
        $(this).addClass("active");
        
        // Ajax processing
        $.ajax({
            url: link
        })
        .done(function(reponseHTML) {
            $(".content").empty().append(reponseHTML);                
        })
        .fail(function() {
            $(".content").empty().append("page404.html");
        })
        .always(function() {

        });
        return false;
    });        
});