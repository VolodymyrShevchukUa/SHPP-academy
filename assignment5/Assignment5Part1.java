package com.shpp.p2p.cs.vshevchuk.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.util.*;

public class Assignment5Part1 extends TextProgram {

    HashSet<Character> vowelsHashSet = new HashSet<>(Arrays.asList(charAsObject("AEIOUYaeiouy")));  // all vowels
    Character e = 'e';

    @Override
    public void run() {
            syllablesInWord(readLine("input your word"));
    }

    /**YOU CAN STOP PROGRAM, IF WRITE WORD "STOP"
     * characterList - char arrays converted to charList
     * @param word is our word,which we are inputting
     */
    private void syllablesInWord(String word) {
        List<Character> characterList = new ArrayList<>(Arrays.asList(charAsObject(word)));
        checkSynopsis(characterList,word);
    }

    /**
     * @return Character[]Array
     */
    private Character[] charAsObject(String word) {
        Character[] characters = new Character[word.length()];
        int i = 0;
        for (char c : word.toCharArray()) {
            characters[i++] = c;
        }
        return characters;
    }

    private void checkSynopsis(List<Character> characterList, String word) {
        if (word.equals("STOP")){
            return;
        }
        int totalVowelsCounter = 0; //vowels counter
        int counterOfCountVowelsInARow = 0;
        int counterOfCurrentSymbol = 0;
        for (Character c : characterList) {
            if (!vowelsHashSet.contains(c)){
                counterOfCountVowelsInARow = 0;
            }
            counterOfCurrentSymbol++;
            //check for "e" in last symbol
            if (c == e && counterOfCurrentSymbol > characterList.size() - 1) {
                break;
            }else
                // check for vowels in a row
            if (vowelsHashSet.contains(c)) {
                ++counterOfCountVowelsInARow;
                if (counterOfCountVowelsInARow >= 2) {
                    continue;
                }
                totalVowelsCounter++;
            }
        }
        if (totalVowelsCounter == 0) {
            totalVowelsCounter = 1;
        }
        System.out.println(word +  ": " + totalVowelsCounter + " syllable");
        syllablesInWord(readLine("input your word"));

    }
}


