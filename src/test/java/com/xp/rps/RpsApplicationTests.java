package com.xp.rps;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RpsApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	void play() {
		ResponseEntity<Round> response = restTemplate.postForEntity("/play",new Round(Throw.ROCK, Throw.PAPER), Round.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(Result.P2_WINS, response.getBody().getResult());

	}

	@Test
	void createGame() {
		ResponseEntity<Integer> response = restTemplate.postForEntity("/game", new Game("Raymond","James",3,"Buy a cup of coffee"), Integer.class);
		int gameId = response.getBody();
		//http://localhost:8080/game/1
		ResponseEntity<Game> response2 = restTemplate.getForEntity("/game/"+gameId,Game.class);
		Game g = response2.getBody();
		assertEquals("Raymond", g.getPlayer1());

	}


}
