package com.xp.rps;

import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;

//@Repository
public class InMemoryRepo implements RpsRepo {
    int identity=0;
    HashMap<Integer, Game> repo = new HashMap<Integer, Game>();

    @Override
    public int createGame(Game game) {
        identity++;
        game.setId(identity);
        repo.put(identity, game);
        return identity;
    }

    @Override
    public int addRound(int gameID, Round round) {
        Game g = repo.get(gameID);
        identity++;
        round.setId(identity);
        g.getRoundList().add(round);
        return identity;
    }

    @Override
    public Game getGame(int gameID) {
        return repo.get(gameID);

    }


}
