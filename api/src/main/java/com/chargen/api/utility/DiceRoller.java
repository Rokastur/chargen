package com.chargen.api.utility;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Random;

public class DiceRoller {

    private static final Logger logger = LoggerFactory.getLogger(DiceRoller.class);

    private final Random random = new Random();

    public int[] roll(int diceSides, int diceToRoll, boolean dropLowest) {
        int[] rollResults = new int[diceToRoll];
        for (int i = 0; i < diceToRoll; i++) {
            int roll = random.nextInt(diceSides) + 1;
            rollResults[i] = roll;
        }

        logger.info("Dice roll results for diceSides: {}, diceToRoll: {}, dropLowest (before drop): {} are: {} ",
                diceSides, diceToRoll, dropLowest, Arrays.toString(rollResults));

        if (dropLowest && diceToRoll > 1) {
            Arrays.sort(rollResults);
            rollResults =  Arrays.copyOfRange(rollResults, 1, diceToRoll);
            logger.info("Dice roll results for diceSides: {}, diceToRoll: {}, dropLowest (after drop): {} are: {} ",
                    diceSides, diceToRoll, dropLowest, Arrays.toString(rollResults));

        }

        return rollResults;
    }
}
