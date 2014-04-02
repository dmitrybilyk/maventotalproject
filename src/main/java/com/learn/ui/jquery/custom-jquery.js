/**
 * Created with IntelliJ IDEA.
 * User: USER
 * Date: 15.09.13
 * Time: 9:21
 * To change this template use File | Settings | File Templates.
 */
$(function(){
    $('ul.green').parent('.par').css('color', 'yellow');
//    console.log($('ul').parents())
//    console.log($('ul').closest())
})

$(function(){
    var link = $('link');
    $("button").click(function(){
        var $this = $(this),
            buttonName = $this.data('file');

        $this
            .siblings('button')
            .removeAttr('disabled')
            .end()
            .attr('disabled', 'disabled');
            link.attr('href', buttonName +'.css');
    })
})

$(function(){
    var dd = $('dd');
    dd.filter(':nth-child(n+4)').addClass('hide');

    $('dl').on('mouseenter', 'dt', function(){
        var $this = $(this);
        $this
            .next()
            .show()
            .siblings('dd')
            .slideUp(300);
    })

    $('h2').on('click', function(){
        $(this).clone(true).appendTo('body');
    })


    //lesson7

    $('article').append("Hello from my room");
    $('h1').after("After h1");

    $("<h1></h1>", {
        text: "hi from java script77",
        class: 'myClass'
    }).insertAfter('h1');

    $('p#pafter').eq(0).after($('h1.myClass'));

})



