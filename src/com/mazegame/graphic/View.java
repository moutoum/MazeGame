package com.mazegame.graphic;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;

abstract class View implements KeyListener {

    private static GameViewManager gm = new GameViewManager();

    private View parent = null;
    private ArrayList<View> children = new ArrayList<>();

    protected boolean isTransparent = false;
    protected boolean isUpdated = true;
    protected boolean isKeyImplemented = true;

    public static GameViewManager getGm() {
        return gm;
    }

    public static void CreateWithView(View first) {
        if (gm.isEmpty()) {
            first.setParent(null);
            gm.add(first);
        }
    }

    public View getParent() {
        return parent;
    }


    public final void addView(View view) {
        view.setParent(this);
        gm.add(view);
        children.add(view);
    }

    public void delete() {
        ArrayList<View> childrenCopy = (ArrayList<View>) children.clone();
        for (View v : childrenCopy) {
            v.delete();
            gm.remove(v);
        }
        children.clear();
        if (parent != null) {
            parent.children.remove(this);
        }
        gm.remove(this);
    }

    private void setParent(View parent) {
        this.parent = parent;
    }

    abstract public void draw(Graphics2D graphics);
    abstract public void update();
}
