package com.mazegame.game;

public class BoostSpeed extends Item {

    public static final String BOOST_SPEED_NAME = "Boost Speed";

    public BoostSpeed() {
        super(BOOST_SPEED_NAME);
    }

    @Override
    public void use(Player player) {
        player.setVelocity(player.getVelocity() + 0.005f);
    }
}
