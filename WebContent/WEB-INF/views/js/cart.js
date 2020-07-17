function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
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
        let new_product = productId + ':' + q;

        // Check if cookie existed
        myCart = getCookie('cart').split(',');
        if(q > 0) {
            if(myCart == '') {
                alert("YOU CART IS CREATED");
    
                // add New product to cart
                document.cookie = setCookie("cart", new_product);
                console.log('Add new product to cart: [' + new_product + ']');
            } else {
                if(myCart.includes(new_product)) {
                    alert('THIS PRODUCT iS ADDED TO YOUR CART');
                } else {
                    myCart = myCart + ',' + new_product;
                    alert('Add to CART SUCCESSFULLY');
                    setCookie('cart', myCart);
                }
            }
        } else {
            alert("BUY AT LEAST 1 PRODUCTS");
        }
    });
})(jQuery);