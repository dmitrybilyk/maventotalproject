package com.learn.bases.bloh;
import java.util.*;
/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 09.12.12
 * Time: 13:31
 * To change this template use File | Settings | File Templates.
 */
public class _DestroyObsoleteObjects {
}

 class Stack {
    private Object[] elements;
    private int size = 0;
    public Stack(int initialCapacity) {
        this.elements = new Object[initialCapacity];
    }
    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }
    public Object pop() {
        if (size==0)
            throw new EmptyStackException();
        Object result = elements[--size];
        elements[size] = null; // Eliminate obsolete reference
        return result;
    }


    /**
     * Убедимся в том, что в стеке есть место хотя бы еще
     * для одного элемента. Каждый раз, когда
     * нужно увеличить массив, удваиваем его емкость.
     */
    private void ensureCapacity() {
        if (elements.length == size) {
            Object[] oldElements = elements;
            elements = new Object[2 * elements.length + 1];
            System.arraycopy(oldElements, 0, elements, 0, size);
        } }
    public static void main(String[] args) {
        Stack s = new Stack(0);
        for (int i=0; i<args.length; i++)
            s.push(args[i]);
        for (int i=0; i<args.length; i++)
            System.out.println(s.pop());
    } }
