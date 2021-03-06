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

package org.cardai.simplegame.playingstrategy;

import java.util.List;

import org.cardai.game.card.Card;
import org.cardai.game.play.PlayingStrategy;
import org.cardai.game.Hand;

public class PlayFirstCard extends PlayingStrategy {

    /**
     * PlayFistCard plays the first card of the hand.
     */

    public static String getLabel() {
        return "first-card";
    }

    @Override
    public Card play(Hand hand, List<Card> playedCards) {
        return hand.getCards().get(0);
    }
}
