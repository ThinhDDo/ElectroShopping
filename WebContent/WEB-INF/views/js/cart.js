function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + encodeURIComponent(cvalue) + ";" + expires + ";path=/SpringMVC";
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

(function($) {
    document.getElementsByClassName('add-to-cart-btn')[0].addEventListener('click', function() {
        
        // data
        let q = document.getElementsByClassName('pro-qty')[0].value;
        let productId = document.getElementById('pro-id').value;

        // Test: Add new product
        let new_product = productId + ":" + q;

        // Check if cookie existed
        myCart = decodeURIComponent(getCookie("cart"));
        if(q > 0) {
            if(myCart == "") {
                // add New product to cart
                document.cookie = setCookie("cart", new_product);
                console.log('Add new product to cart: [' + new_product + ']');
            } else {
                if(myCart.includes(productId)) {
                    alert('THIS PRODUCT IS ADDED TO YOUR CART');
                } else {
                    myCart = myCart + "-" + new_product;
                    console.log('Add product to cart: [' + new_product + ']');
                    setCookie("cart", myCart);
                }
            }

        } else {
            alert("BUY AT LEAST 1 PRODUCTS");
        }
        
        // Reload page
        let uri = "/SpringMVC/product?pid=" + productId;
        $.ajax({
            type: "GET",
            url: uri,
            success: function (result) {
                console.log("success");
            },
            error: function (result) {
                console.log("fail");
            }
        });
        document.getElementById("gotoTop").click();
        location.reload();
    });

    // Show products added to cart (/index[Dropdown])
    $('.dropdown').on('click', function (e) {
        // console.log(getCookie("cart"));
        var mycart = decodeURIComponent(getCookie("cart"));
        console.log("My cart: " + mycart);
        if(mycart!="") {
            var httpRequest = new XMLHttpRequest();
            var url = "/SpringMVC/test";
            httpRequest.responseType = 'json';

            // check response
            httpRequest.onreadystatechange = function () {
            	if(httpRequest.status==200 && httpRequest.readyState==4) {
                    console.log("RESPONSE SUCCESSFULLY");
                    var list = httpRequest.response;
                    console.log(list[0].name);
                    console.log(list[1].name);
                    console.log(list[0].image);
                    
                    document.getElementById('product-widget-img').setAttribute("src", "img/product09.png");
                }
            }
            httpRequest.open("GET", url);
            httpRequest.send();
        } else {
            console.log("no products found!!!");
        }
	});
})(jQuery);