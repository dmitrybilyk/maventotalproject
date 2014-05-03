package com.learn.spring.springinaction.methodsubstitution;

public class MagicBoxImpl implements MagicBox {
public MagicBoxImpl() {}
    @Override
public String getContents() {
    return "A beautiful assistant"; // Симпатичная ассистентка в ящике
}

    @Override
    public void setMagicBox(MagicBoxImpl magicBox) {

    }

    @Override
    public void setMagicWords(String magicWords) {

    }

    @Override
    public Magician getMagician(){
        Magician magician = new Magician();
        magician.setMagicWords("wwwwwauu");
        return magician;
    }

}