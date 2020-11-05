package com.xp.rps;

public class RPS {
    public static Result play(Throw p1, Throw p2) {
        if (p1.equals(p2)) {
            return Result.DRAW;
        }
        return Result.P2_WINS;
    }
}
