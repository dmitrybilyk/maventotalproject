package com.learn.spring.springinaction.methodsubstitution;

/**
 * Created by dmitry on 03.05.14.
 */
public interface MagicBox {
    String getContents();

    void setMagicBox(MagicBoxImpl magicBox);

    void setMagicWords(String magicWords);

    Magician getMagician();
}
