package com.goo32v2.cooldict.data.dtos;

/**
 * Created on 25-Jun-16. (c) CoolDict
 */

// TODO: 06-Jul-16 create more abstract method and change model name
public class ModelDTO<MODEL, CALLBACK> {
    private MODEL model;
    private CALLBACK callback;

    public ModelDTO(MODEL model, CALLBACK callback) {
        this.model = model;
        this.callback = callback;
    }

    public MODEL getModel() {
        return model;
    }

    public void setModel(MODEL model) {
        this.model = model;
    }

    public CALLBACK getCallback() {
        return callback;
    }

    public void setCallback(CALLBACK callback) {
        this.callback = callback;
    }
}
