/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 18.09.13
 * Time: 22:26
 * To change this template use File | Settings | File Templates.
 */
//Prototypes - objects that used for inheritance in js. they contain all methods
//and fields of object.
//function Slider(direction){
//    this.direction = direction;
//}
//Slider.prototype.move = function(){
//    console.log('moving ' + this.direction);
//}
//
//var slider = new Slider('forward');
//slider.move();
//
//var slider2 = new Slider('backward');
//slider2.move();

function Slider(container, nav){
    this.container = container;
    this.nav = nav;
    this.imgs = container.find('img');
    this.imgWidth = this.imgs[0].width; //600px
    this.imgsLen = this.imgs.length;
    this.current = 1;
}

Slider.prototype.transition = function(coords){
    this.container.animate({
        'margin-left': coords || - (this.current * this.imgWidth)  // 2 * 600 = 120
    })
};

//var Slider = new Slider($('div.slider'), $('#slider-nav'))
//console.log(slider.container);
