package com.xp.rps;

public interface RpsRepo {
    int createGame(Game game);
    Game getGame(int gameId);
    int addRound(int gameId, Round round);
}


//Create a InMemoryRepo implements RpsRepo
//Store the object into HashMap
//Use some integer as the ID;