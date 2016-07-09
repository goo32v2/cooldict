package com.goo32v2.cooldict.data.dtos;

/**
 * Created on 25-Jun-16. (c) CoolDict
 */

public class Pair<E, C> {
    private E element0;
    private C element1;

    public Pair(E element0, C element1) {
        this.element0 = element0;
        this.element1 = element1;
    }

    public E getElement0() {
        return element0;
    }

    public void setElement0(E element0) {
        this.element0 = element0;
    }

    public C getElement1() {
        return element1;
    }

    public void setElement1(C element1) {
        this.element1 = element1;
    }
}
