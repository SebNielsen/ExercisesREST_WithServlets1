var id;
$(document).ready(function() {
     $.ajax({
        method: "GET",
        url: "http://localhost:8080/RestExcersice1/api/quote/random",
        success: function (data) {
            console.log(data);
            id = data.id;
            $('#quote').text(data.quote);
        },
        error: function (err) {
            console.log(err);
        }
    });
});

$('#randomQuote').on('click',function(){
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/RestExcersice1/api/quote/random",
        success: function (data) {
            console.log(data);
            $('#quote').val(data.quote);
        },
        error: function (err) {
            console.log(err);
        }
    });
});

$('#newQuote').on('click', function(e){
    e.preventDefault();
    
    var dataObject = {quote: 'hej'};
    $.ajax({
        url: "http://localhost:8080/RestExcersice1/api/quote",
        type: "POST",
        data: JSON.stringify(dataObject),
        contentType: "application/json",
    }); 
});


$('#editQuote').on('click', function(e){
    e.preventDefault();
    
    var dataObject = {quote: $('#quote').val()};
    $.ajax({
        url: "http://localhost:8080/RestExcersice1/api/quote/"+id,
        type: "PUT",
        data: JSON.stringify(dataObject),
        contentType: "application/json",
    }); 
});

$('#removeQuote').on('click', function(e){
     e.preventDefault();
    $.ajax({
        url: "http://localhost:8080/RestExcersice1/api/quote/"+id,
        type: "DELETE",
    }); 
});


