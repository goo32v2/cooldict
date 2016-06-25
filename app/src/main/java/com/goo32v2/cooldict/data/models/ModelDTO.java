package com.goo32v2.cooldict.data.models;

/**
 * Created on 25-Jun-16. (c) CoolDict
 */

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
