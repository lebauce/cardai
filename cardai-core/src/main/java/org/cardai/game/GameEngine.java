/* Cardai - A card game engine
 * Copyright (C) 2014 Thomas Génin
 *
 * This file is part of Cardai.
 *
 * Cardai is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * Cardai is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses/>.
 */

package org.cardai.game;

import org.cardai.exception.UnexpectedSituationException;
import org.cardai.game.card.Card;

import java.util.List;

public class GameEngine {

    private Game         game;
    private GameHand     gamehand;
    private List<Player> players;
    private Deck         deck;


    public GameEngine(Game game, List<Player> players) {
        this.game     = game;
        this.players  = players;
        this.gamehand = null; // At start, no game hand initialized
        this.deck     = new Deck(game);
    }

    /**
     * Plays the whole hand, which means plays as long as a player can play a card
     */
    private void playHand() {
        while (! (gamehand.getReferencePosition() == null)) {
            Card c = players.get(gamehand.getReferencePosition()).play(gamehand.getPlayedCards());
            gamehand.play(c);
            gamehand.setReferencePosition(game.nextPlayer(gamehand));
        }
    }

    /**
     * Deal the deck, set hands and run the game
     * @param gamehand
     * @throws UnexpectedSituationException
     */
    public void run(int numOfHands) throws UnexpectedSituationException {
        for (int i = 0; i < numOfHands; i++) {
            RandomSeed.setSeed(i); //TODO improve this
            for (int j = 0; j < game.getNumOfPlayers(); j++) {
                prepareHand(j);
                playHand();
                game.register(gamehand);
            }
            deck.shuffle();
        }
        game.analyse();
    }


    private void prepareHand(int dealer) throws UnexpectedSituationException {
        RandomSeed.reset();
        this.gamehand = new GameHand(game.getNumOfPlayers(), dealer);
        // TODO dealer and first player are not the same player in real card games
        List<List<Card>> deal = game.deal(deck.getCards(), dealer);
        gamehand.setReferencePosition(dealer);
        gamehand.setHands(deal);
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setHand(gamehand.getHands().get(i));
        }
    }
}
