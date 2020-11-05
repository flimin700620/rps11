package com.xp.rps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class RpsRepoTest {


    RpsRepo repo;

    @BeforeEach
    void setup() {
        repo = new InMemoryRepo();
    }


    @Test
    void createGame() {
        Game g = new Game("Ray", "James", 3, "Buy a coffee machine");
        int gameID=repo.createGame(g);

        Game g2 = repo.getGame(gameID);
        assertEquals(g.getPlayer1(), g2.getPlayer1());

        Round r1 = new Round(Throw.ROCK, Throw.PAPER, Result.P2_WINS);
        Round r2 = new Round(Throw.ROCK, Throw.PAPER, Result.P2_WINS);

        repo.addRound(g2.getId(), r1);
        repo.addRound(g2.getId(), r2);

        Game g3 = repo.getGame(g2.getId());
        assertEquals(2, g3.getRoundList().size());
        assertEquals(Result.P2_WINS, g3.getResult());
    }
}
