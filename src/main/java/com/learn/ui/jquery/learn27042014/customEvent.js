$(function(){
    $("input").on('customEvent',function(){
        $("p").text("hello");
    });
    $("input").on('click',function(){
        $(this).trigger('customEvent');
    });

    $('input#submitButton').on('myEvent', function(event){
        event.preventDefault;
        alert("my event is working");
    })
    $('input#submitButton').on('click',  function(){
        $(this).trigger('myEvent')
    })
});