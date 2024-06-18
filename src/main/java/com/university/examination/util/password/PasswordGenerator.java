package com.university.examination.util.password;

import com.university.examination.exception.CustomException;

import java.security.SecureRandom;
import java.util.*;

public class PasswordGenerator {
    private final List<PasswordCharacterSet> pwSets;
    private final char[] allCharacters;
    private final int minLength;
    private final int maxLength;
    private final int presetCharacterCount;

    public PasswordGenerator(int minLength, int maxLength) {
        if (minLength < 0 || maxLength < minLength) {
            throw new CustomException("Error: Invalid length parameters");
        }
        this.minLength = minLength;
        this.maxLength = maxLength;

        List<PasswordCharacterSet> origPwSets = Arrays.asList(
                SummerCharacterSets.ALPHA_UPPER,
                SummerCharacterSets.ALPHA_LOWER,
                SummerCharacterSets.NUMERIC,
                SummerCharacterSets.SPECIAL
        );

        List<PasswordCharacterSet> pwSets = new ArrayList<>();
        int pwCharacters = 0;
        int preallocatedCharacters = 0;

        for (PasswordCharacterSet origPwSet : origPwSets) {
            pwSets.add(origPwSet);
            pwCharacters += origPwSet.getCharacters().length;
            preallocatedCharacters += origPwSet.getMinCharacters();
        }

        this.presetCharacterCount = preallocatedCharacters;
        this.pwSets = Collections.unmodifiableList(pwSets);

        if (minLength < presetCharacterCount) {
            throw new CustomException("Combined minimum lengths "
                    + presetCharacterCount
                    + " are greater than the minLength of " + minLength);
        }

        char[] allChars = new char[pwCharacters];
        int currentIndex = 0;
        for (PasswordCharacterSet pwSet : pwSets) {
            char[] chars = pwSet.getCharacters();
            System.arraycopy(chars, 0, allChars, currentIndex, chars.length);
            currentIndex += chars.length;
        }
        this.allCharacters = allChars;
    }

    public String generatePassword() {
        SecureRandom rand = new SecureRandom();
        int pwLength = minLength + rand.nextInt(maxLength - minLength + 1);
        int randomCharacterCount = pwLength - presetCharacterCount;
        List<Integer> remainingIndexes = new ArrayList<>(pwLength);
        for (int i = 0; i < pwLength; ++i) {
            remainingIndexes.add(i);
        }
        char[] pw = new char[pwLength];
        for (PasswordCharacterSet pwSet : pwSets) {
            addRandomCharacters(pw, pwSet.getCharacters(), pwSet.getMinCharacters(), remainingIndexes, rand);
        }
        addRandomCharacters(pw, allCharacters, randomCharacterCount, remainingIndexes, rand);
        return pw.toString();
    }

    private static void addRandomCharacters(char[] pw, char[] characterSet,
                                            int numCharacters, List<Integer> remainingIndexes, Random rand) {
        for (int i = 0; i < numCharacters; ++i) {
            int pwIndex = remainingIndexes.remove(rand.nextInt(remainingIndexes.size()));
            int randCharIndex = rand.nextInt(characterSet.length);
            pw[pwIndex] = characterSet[randCharIndex];
        }
    }
}
