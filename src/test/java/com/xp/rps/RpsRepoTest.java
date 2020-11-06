package com.xp.rps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Import(JDBCRepo.class) //Somehow @JdbcTest not able to load @Repository, Import to force it happen
@Transactional //Default with @JDBCTest This will cause each test rollback after testing, also rollback the beforeTestRun.sql
public class RpsRepoTest {


    @Autowired
    RpsRepo repo;

    @BeforeEach
    void setup() {
        //repo = new InMemoryRepo();
    }


    @Test
    //@Rollback(value = false)
    void createGame() {
        Game g = new Game("Ray", "James", 3, "Buy a coffee machine");
        int gameID=repo.createGame(g);

        Game g2 = repo.getGame(gameID);
        System.out.println("=============>"+g2.getId());
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
