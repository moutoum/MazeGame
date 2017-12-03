package com.mazegame.game;

public class DestructionPowerCapsule extends Item {

    public static final String ITEM_NAME = "Destruction power capsule";

    public DestructionPowerCapsule() {
        super(ITEM_NAME);
    }

    @Override
    public void use(Player player) {
        player.setPower(new DestructionPower());
    }
}
