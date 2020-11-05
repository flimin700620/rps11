package com.xp.rps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RpsController {

    @Autowired
    RpsRepo repo;


    @GetMapping("/")
    String hello() {
        return "Hello MS!";
    }

   @PostMapping("/play")
   Round play(@RequestBody Round round) {
        Result result = RPS.play(round.getThrow1(), round.getThrow2());
        round.setResult(result);
        return round;
   }

   @PostMapping("/game")
   int createGame(@RequestBody  Game game) {
       return repo.createGame(game);
   }

   @GetMapping("/game/{id}")
   Game getGame(@PathVariable  int id) {
        return repo.getGame(id);
   }

    @PostMapping("/game/{id}/round")
    int playRound(@PathVariable int id, @RequestBody Round round) {
        Result playResult = RPS.play(round.getThrow1(), round.getThrow2());
        round.setResult(playResult);
        int roundID = repo.addRound(id, round);
        return roundID;
    }

}
