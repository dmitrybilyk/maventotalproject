$(function(){
    $("input").on('customEvent',function(){
        $("p").text("hello");
    });
    $("input").on('click',function(){
        $(this).trigger('customEvent');
    });
});