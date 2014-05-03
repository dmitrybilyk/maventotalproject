package com.learn.spring.springinaction.methodsubstitution;


import org.springframework.beans.factory.annotation.Configurable;

@Configurable("harry")
public class Magician implements Performer {
public Magician() {}

    @Override
public void perform() throws PerformanceException {
System.out.println(magicWords);
System.out.println("The magic box contains...");
System.out.println(magicBox.getContents()); // Исследует содержимое
}
// ящика
// внедрение
private MagicBox magicBox;
public void setMagicBox(MagicBox magicBox) {
this.magicBox = magicBox; // Внедрение волшебного ящика
}
private String magicWords;

    public MagicBox getMagicBox() {
        return magicBox;
    }

    public void setMagicWords(String magicWords) {
this.magicWords = magicWords;
}

    public String getMagicWords() {
        return magicWords;
    }
}