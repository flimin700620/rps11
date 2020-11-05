package com.xp.rps;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RPSTest {

    @Test
    void RockvsPaper() {
        Result r = RPS.play(Throw.ROCK, Throw.PAPER);
        assertEquals(Result.P2_WINS, r);
    }

    @Test
    void RockvsRock() {
        Result r = RPS.play(Throw.ROCK, Throw.ROCK);
        assertEquals(Result.DRAW, r);
    }
}
