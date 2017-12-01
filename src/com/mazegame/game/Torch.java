package com.mazegame.game;

public class Torch extends Item {

    public static final String TORCH_NAME = "Torch";

    public Torch() {
        super(TORCH_NAME);
    }

    @Override
    public void use(Player player) {
        player.setStrengthLight(player.getStrengthLight() + 1);
    }
}
