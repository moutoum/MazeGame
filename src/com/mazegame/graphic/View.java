package com.mazegame.graphic;

import java.awt.event.KeyListener;

abstract class View implements IView, KeyListener {

     GameViewManager gm;
    protected boolean isTransparent = false;
    protected boolean isUpdated = true;
    protected boolean isKeyImplemented = true;

    View(GameViewManager gm) {
        this.gm = gm;
    }
    void drop() {
        gm.pop();
    }
}
