package com.mazegame.game;

public abstract class Power {
    private long start = 0;
    private long effectDurationReload = 0;

    public Power(long effectDuration) {
        this.effectDurationReload = effectDuration;
    }

    public void use() {
        start = System.currentTimeMillis();
    }

    public boolean isEffectFinished() {
        return (start + effectDurationReload < System.currentTimeMillis());
    }

}
