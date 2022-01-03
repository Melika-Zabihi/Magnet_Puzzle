package com.company;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;

public class Utility {
    int rowNumber;
    int columnNumber;
    int[] posRowNumbers;
    int[] negRowNumbers;
    int[] posColNumbers;
    int[] negColNumbers;

    public Utility(int rowNumber, int columnNumber, int[] negRowNumbers,
                   int[] posRowNumbers, int[] negColNumbers, int[] posColNumbers) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.posRowNumbers = posRowNumbers;
        this.negRowNumbers = negRowNumbers;
        this.posColNumbers = posColNumbers;
        this.negColNumbers = negColNumbers;
    }

    public void AC3(ArrayList<Variable> originalVariables) {
        boolean contradiction = false;
        ArrayList<Variable> variables = new ArrayList<>();
        for (int i = 0; i < originalVariables.size(); i++) {
            variables.add(i, originalVariables.get(i));
        }

        while (variables.size() != 0 && !contradiction) {
            Variable selectedVar = variables.get(0);
            variables.remove(0);
            ArrayList<Variable> neighVars = new ArrayList<>();
            int[][] XAndYPositions = new int[8][2];
            Variable tempVar;

            if (selectedVar.getPositions()[1][0] >= 1) {
                boolean isACompleteNeighbor = false;
                if ((tempVar = getVariable(variables, selectedVar.getPositions()[1][0] - 1, selectedVar.getPositions()[1][1])) != null && !tempVar.isMagnet()) {
                    XAndYPositions[neighVars.size()][0] = 1;
                    if (tempVar.getPositions()[0][1] == selectedVar.getPositions()[1][0] - 1 && tempVar.getPositions()[0][1] == selectedVar.getPositions()[1][1])
                        XAndYPositions[neighVars.size()][1] = 0;
                    else {
                        XAndYPositions[neighVars.size()][1] = 1;
                        isACompleteNeighbor = true;
                    }
                    neighVars.add(tempVar);
                }
                if (selectedVar.isHorizontal() && !isACompleteNeighbor) {
                    if ((tempVar = getVariable(variables, selectedVar.getPositions()[0][0] - 1, selectedVar.getPositions()[0][1])) != null && !tempVar.isMagnet()) {
                        XAndYPositions[neighVars.size()][0] = 0;
                        if (tempVar.getPositions()[0][1] == selectedVar.getPositions()[0][0] - 1 && tempVar.getPositions()[0][1] == selectedVar.getPositions()[0][1])
                            XAndYPositions[neighVars.size()][1] = 0;
                        else XAndYPositions[neighVars.size()][1] = 1;
                        neighVars.add(tempVar);
                    }
                }
            }
            if (selectedVar.getPositions()[1][1] >= 1) {
                boolean isACompleteNeighbor = false;
                if ((tempVar = getVariable(variables, selectedVar.getPositions()[1][0], (selectedVar.getPositions()[1][1] - 1))) != null && !tempVar.isMagnet()) {
                    XAndYPositions[neighVars.size()][0] = 1;
                    if (tempVar.getPositions()[0][1] == selectedVar.getPositions()[1][0] && tempVar.getPositions()[0][1] == selectedVar.getPositions()[1][1] - 1)
                        XAndYPositions[neighVars.size()][1] = 0;
                    else {
                        XAndYPositions[neighVars.size()][1] = 1;
                        isACompleteNeighbor = true;
                    }
                    neighVars.add(tempVar);
                }
                if (!selectedVar.isHorizontal() && !isACompleteNeighbor) {
                    if ((tempVar = getVariable(variables, selectedVar.getPositions()[0][0], (selectedVar.getPositions()[0][1] - 1))) != null && !tempVar.isMagnet()) {
                        XAndYPositions[neighVars.size()][0] = 0;
                        if (tempVar.getPositions()[0][1] == selectedVar.getPositions()[0][0] && tempVar.getPositions()[0][1] == selectedVar.getPositions()[0][1] - 1)
                            XAndYPositions[neighVars.size()][1] = 0;
                        else XAndYPositions[neighVars.size()][1] = 1;
                        neighVars.add(tempVar);
                    }
                }
            }
            if (selectedVar.getPositions()[0][0] < rowNumber - 1) {
                boolean isACompleteNeighbor = false;
                if ((tempVar = getVariable(variables, selectedVar.getPositions()[0][0] + 1, selectedVar.getPositions()[0][1])) != null && !tempVar.isMagnet()) {
                    XAndYPositions[neighVars.size()][0] = 0;
                    if (tempVar.getPositions()[0][1] == selectedVar.getPositions()[0][0] + 1 && tempVar.getPositions()[0][1] == selectedVar.getPositions()[0][1]) {
                        XAndYPositions[neighVars.size()][1] = 0;
                        isACompleteNeighbor = true;
                    } else XAndYPositions[neighVars.size()][1] = 1;
                    neighVars.add(tempVar);
                }
                if (selectedVar.isHorizontal() && !isACompleteNeighbor) {
                    if ((tempVar = getVariable(variables, selectedVar.getPositions()[1][0] + 1, selectedVar.getPositions()[1][1])) != null && !tempVar.isMagnet()) {
                        XAndYPositions[neighVars.size()][0] = 1;
                        if (tempVar.getPositions()[0][1] == selectedVar.getPositions()[1][0] + 1 && tempVar.getPositions()[0][1] == selectedVar.getPositions()[1][1])
                            XAndYPositions[neighVars.size()][1] = 0;
                        else XAndYPositions[neighVars.size()][1] = 1;
                        neighVars.add(tempVar);
                    }
                }
            }
            if (selectedVar.getPositions()[0][1] < columnNumber - 1) {
                boolean isACompleteNeighbor = false;
                if ((tempVar = getVariable(variables, selectedVar.getPositions()[0][0], selectedVar.getPositions()[0][1] + 1)) != null && !tempVar.isMagnet()) {
                    XAndYPositions[neighVars.size()][0] = 0;
                    if (tempVar.getPositions()[0][1] == selectedVar.getPositions()[0][0] && tempVar.getPositions()[0][1] == selectedVar.getPositions()[0][1] + 1) {
                        XAndYPositions[neighVars.size()][1] = 0;
                        isACompleteNeighbor = true;
                    } else XAndYPositions[neighVars.size()][1] = 1;
                    neighVars.add(tempVar);
                }
                if (!selectedVar.isHorizontal() && !isACompleteNeighbor) {
                    if ((tempVar = getVariable(variables, selectedVar.getPositions()[1][0], selectedVar.getPositions()[1][1] + 1)) != null && !tempVar.isMagnet()) {
                        XAndYPositions[neighVars.size()][0] = 1;
                        if (tempVar.getPositions()[0][1] == selectedVar.getPositions()[1][0] && tempVar.getPositions()[0][1] == selectedVar.getPositions()[1][1] + 1)
                            XAndYPositions[neighVars.size()][1] = 0;
                        else XAndYPositions[neighVars.size()][1] = 1;
                        neighVars.add(tempVar);
                    }
                }
            }
            for (int i = 0; i < neighVars.size(); i++) {
                if (removeValues(variables, selectedVar, neighVars.get(i), XAndYPositions[i][0], XAndYPositions[i][1])) {
                    if (neighVars.get(i).getDomain()[0] == 0 && neighVars.get(i).getDomain()[1] == 0 && neighVars.get(i).getDomain()[2] == 0) {
                        contradiction = true;
                    }
                    boolean isInTheVariables = false;
                    for (Variable variable : variables) {
                        if (neighVars.get(i) == variable) {
                            isInTheVariables = true;
                            break;
                        }
                    }
                    if (!isInTheVariables) variables.add(neighVars.get(i));
                }
            }
        }
    }

    public Variable getVariable(ArrayList<Variable> variables, int y, int x) {
        for (Variable variable : variables) {
            if (variable.getPositions()[0][0] == y && variable.getPositions()[0][1] == x ||
                    variable.getPositions()[1][0] == y && variable.getPositions()[1][1] == x) {
                return variable;
            }
        }
        return null;
    }

    public boolean removeValues(ArrayList<Variable> variables, Variable X, Variable Y, int x_position, int y_position) {
        boolean[][] remove = {{false, false, false}, {false, false, false}, {false, false, false}};
        int[] tempPosRowNumbers = posRowNumbers;
        int[] tempNegRowNumbers = negRowNumbers;
        int[] tempPosColNumbers = posColNumbers;
        int[] tempNegColNumbers = negColNumbers;

        boolean removeDomain = false;
        for (int y = 0; y < 3; y++) {
            if (Y.getDomain()[y] != 1) continue;
            for (int x = 0; x <= 3; x++) {
                if (X.getDomain()[x] != 1) continue;
                remove[y][0] = removeDomain && x == 1 && X.getDomain()[0] == 1;
                remove[y][1] = removeDomain && x == 2 && X.getDomain()[1] == 1;
                remove[y][2] = removeDomain && x == 3 && X.getDomain()[2] == 1;
                if (x == 3) break;

                Y.setMagnet(false);
                X.setMagnet(false);
                removeDomain = (y == 1 && x == 1 && (y_position == 1 && x_position == 1 || y_position == 0 && x_position == 0));
                if (removeDomain) break;
                removeDomain = (y == 1 && x == 2 && (y_position == 0 && x_position == 1 || y_position == 1 && x_position == 0));
                if (removeDomain) break;
                removeDomain = (y == 2 && x == 1 && (y_position == 0 && x_position == 1 || y_position == 1 && x_position == 0));
                if (removeDomain) break;
                removeDomain = (y == 2 && x == 2 && (y_position == 1 && x_position == 1 || y_position == 0 && x_position == 0));
                if (removeDomain) break;

                Set<Integer> rowNumbers = new HashSet<>();
                rowNumbers.add(X.getPositions()[0][0]);
                rowNumbers.add(X.getPositions()[1][0]);
                rowNumbers.add(Y.getPositions()[0][0]);
                rowNumbers.add(Y.getPositions()[1][0]);
                for (Integer rowNumber : rowNumbers) {
                    int[] emptyHomes = {0, 0};
                    if (y == 1 && Y.getDomain()[1] == 1) {

                        Y.setMagnet(true);
                        tempPosRowNumbers[Y.getPositions()[0][0]] -= 1;
                        tempNegRowNumbers[Y.getPositions()[1][0]] -= 1;
                        tempNegColNumbers[Y.getPositions()[1][1]] -= 1;
                        tempPosColNumbers[Y.getPositions()[0][1]] -= 1;

                    } else if (y == 2 && Y.getDomain()[2] == 1) {

                        Y.setMagnet(true);
                        tempPosRowNumbers[Y.getPositions()[1][0]] -= 1;
                        tempNegRowNumbers[Y.getPositions()[0][0]] -= 1;
                        tempNegColNumbers[Y.getPositions()[0][1]] -= 1;
                        tempPosColNumbers[Y.getPositions()[1][1]] -= 1;
                    }
                    if (x == 1 && X.getDomain()[1] == 1) {

                        X.setMagnet(true);
                        tempPosRowNumbers[X.getPositions()[0][0]] -= 1;
                        tempNegRowNumbers[X.getPositions()[1][0]] -= 1;
                        tempNegColNumbers[X.getPositions()[1][1]] -= 1;
                        tempPosColNumbers[X.getPositions()[0][1]] -= 1;

                    } else if (x == 2 && X.getDomain()[2] == 1) {

                        X.setMagnet(true);
                        tempPosRowNumbers[X.getPositions()[1][0]] -= 1;
                        tempNegRowNumbers[X.getPositions()[0][0]] -= 1;
                        tempNegColNumbers[X.getPositions()[0][1]] -= 1;
                        tempPosColNumbers[X.getPositions()[1][1]] -= 1;
                    }
                    for (int i = 0; i < columnNumber; i++) {
                        Variable tempVar = getVariable(variables, rowNumber, i);
                        if (!tempVar.isMagnet()) {
                            if (tempVar != Y && tempVar != X) {
                                emptyHomes[0]++;
                                emptyHomes[1]++;
                            }
                            if (tempVar.getDomain()[2] == 1 && tempVar.getDomain()[1] == 1) ;
                            else if (tempVar.getPositions()[0][0] == rowNumber && tempVar.getPositions()[0][1] == i) {
                                if (tempVar.getDomain()[1] == 1) tempPosRowNumbers[rowNumber] -= 1;
                                else if (tempVar.getDomain()[2] == 1) tempNegRowNumbers[rowNumber] -= 1;
                                else {
                                    if (tempVar != Y && tempVar != X) {
                                        emptyHomes[0]++;
                                        emptyHomes[1]++;
                                    }
                                }
                            } else {
                                if (tempVar.getDomain()[2] == 1) tempPosRowNumbers[rowNumber] -= 1;
                                else if (tempVar.getDomain()[1] == 1) tempNegRowNumbers[rowNumber] -= 1;
                                else {
                                    if (tempVar != Y && tempVar != X) {
                                        emptyHomes[0]++;
                                        emptyHomes[1]++;
                                    }
                                }
                            }
                        }
                        if (tempVar.isHorizontal()) {
                            i++;
                            emptyHomes[1]++;
                        }
                    }
                    if (!(emptyHomes[0] >= tempNegRowNumbers[rowNumber] && emptyHomes[0] >= tempPosRowNumbers[rowNumber] && emptyHomes[1] >= tempNegRowNumbers[rowNumber] + tempPosRowNumbers[rowNumber])) {
                        removeDomain = true;
                        break;
                    }
                    tempPosColNumbers = posColNumbers;
                    tempNegColNumbers = negColNumbers;
                    tempPosRowNumbers = posRowNumbers;
                    tempNegRowNumbers = negRowNumbers;
                }
                Set<Integer> colNumbers = new HashSet<>();
                colNumbers.add(X.getPositions()[0][0]);
                colNumbers.add(X.getPositions()[1][0]);
                colNumbers.add(Y.getPositions()[0][0]);
                colNumbers.add(Y.getPositions()[1][0]);
                for (Integer columnNumber : colNumbers) {
                    int[] emptyHomes = {0, 0};
                    if (y == 1 && Y.getDomain()[1] == 1) {

                        Y.setMagnet(true);
                        tempPosRowNumbers[Y.getPositions()[0][0]] -= 1;
                        tempNegRowNumbers[Y.getPositions()[1][0]] -= 1;
                        tempNegColNumbers[Y.getPositions()[1][1]] -= 1;
                        tempPosColNumbers[Y.getPositions()[0][1]] -= 1;

                    } else if (y == 2 && Y.getDomain()[2] == 1) {

                        tempPosRowNumbers[Y.getPositions()[1][0]] -= 1;
                        tempNegRowNumbers[Y.getPositions()[0][0]] -= 1;
                        tempNegColNumbers[Y.getPositions()[0][1]] -= 1;
                        tempPosColNumbers[Y.getPositions()[1][1]] -= 1;
                    }
                    if (x == 1 && X.getDomain()[1] == 1) {

                        X.setMagnet(true);
                        tempPosRowNumbers[X.getPositions()[0][0]] -= 1;
                        tempNegRowNumbers[X.getPositions()[1][0]] -= 1;
                        tempNegColNumbers[X.getPositions()[1][1]] -= 1;
                        tempPosColNumbers[X.getPositions()[0][1]] -= 1;

                    } else if (x == 2 && X.getDomain()[2] == 1) {

                        tempPosRowNumbers[X.getPositions()[1][0]] -= 1;
                        tempNegRowNumbers[X.getPositions()[0][0]] -= 1;
                        tempNegColNumbers[X.getPositions()[0][1]] -= 1;
                        tempPosColNumbers[X.getPositions()[1][1]] -= 1;
                    }
                    for (int i = 0; i < rowNumber; i++) {
                        Variable tempVar = getVariable(variables, i, columnNumber);
                        if (!tempVar.isMagnet()) {
                            if (tempVar != Y && tempVar != X) {
                                emptyHomes[0]++;
                                emptyHomes[1]++;
                            }
                            if (tempVar.getDomain()[2] == 1 && tempVar.getDomain()[1] == 1) ;
                            else if (tempVar.getPositions()[0][0] == i && tempVar.getPositions()[0][1] == columnNumber) {
                                if (tempVar.getDomain()[1] == 1) tempPosColNumbers[rowNumber] -= 1;
                                else if (tempVar.getDomain()[2] == 1) tempNegColNumbers[rowNumber] -= 1;
                                else {
                                    if (tempVar != Y && tempVar != X) {
                                        emptyHomes[0]++;
                                        emptyHomes[1]++;
                                    }
                                }
                            } else {
                                if (tempVar.getDomain()[2] == 1) tempPosColNumbers[rowNumber] -= 1;
                                else if (tempVar.getDomain()[1] == 1) tempNegColNumbers[rowNumber] -= 1;
                                else {
                                    if (tempVar != Y && tempVar != X) {
                                        emptyHomes[0]++;
                                        emptyHomes[1]++;
                                    }
                                }
                            }
                        }
                        if (!tempVar.isHorizontal()) {
                            i++;
                            emptyHomes[1]++;
                        }
                    }
                    if (!removeDomain && !(emptyHomes[0] >= tempNegColNumbers[columnNumber] && emptyHomes[0] >= tempPosColNumbers[columnNumber] && emptyHomes[1] >= tempNegColNumbers[columnNumber] + tempPosRowNumbers[columnNumber])) {
                        removeDomain = true;
                        break;
                    }
                    tempPosColNumbers = posColNumbers;
                    tempNegColNumbers = negColNumbers;
                    tempPosRowNumbers = posRowNumbers;
                    tempNegRowNumbers = negRowNumbers;
                }
            }
        }
        int[] newDomain = {remove[0][0] && remove[0][1] && remove[0][2] ? 0 : 1, remove[1][0] && remove[1][1] && remove[1][2] ? 0 : 1, remove[2][0] && remove[2][1] && remove[2][2] ? 0 : 1};
        Y.setDomain(newDomain);
        return ((remove[0][0] && remove[0][1] && remove[0][2]) || (remove[1][0] && remove[1][1] && remove[1][2]) || (remove[2][0] && remove[2][1] && remove[2][2]));
    }

    public void forwardChecking(ArrayList<Variable> variables, Variable selectedVar, int selectedDomain, int[][] neighborsDomain) {
        int numberOfNeighbors = 0;
        if (selectedVar.getPositions()[1][0] >= 1) {
            boolean isCompleteNeighbors = false;
            Variable tempVar = getVariable(variables, selectedVar.getPositions()[1][0] - 1, selectedVar.getPositions()[1][1]);
            for(int i = 0; i < 3; i++) {
                neighborsDomain[numberOfNeighbors][i] = tempVar.domain[i];
            }
            numberOfNeighbors++;
            if (tempVar.getPositions()[0][0] == selectedVar.getPositions()[1][0] - 1 && tempVar.getPositions()[0][1] == selectedVar.getPositions()[1][1]) {
                if (selectedDomain == 1) tempVar.getDomain()[2] = 0;
                else if (selectedDomain == 2) tempVar.getDomain()[1] = 0;
            } else {
                isCompleteNeighbors = true;
                if (selectedDomain != 0) tempVar.getDomain()[selectedDomain] = 0;
            }

            if (selectedVar.isHorizontal() && !isCompleteNeighbors) {
                tempVar = getVariable(variables, selectedVar.getPositions()[0][0] - 1, selectedVar.getPositions()[0][1]);
                for(int i = 0; i < 3; i++) {
                    neighborsDomain[numberOfNeighbors][i] = tempVar.domain[i];
                }
                numberOfNeighbors++;
                if (tempVar.getPositions()[0][0] == selectedVar.getPositions()[0][0] - 1 && tempVar.getPositions()[0][1] == selectedVar.getPositions()[0][1]) {
                    if (selectedDomain != 0) tempVar.getDomain()[selectedDomain] = 0;
                } else {
                    if (selectedDomain == 2) tempVar.getDomain()[1] = 0;
                    else if (selectedDomain == 1) tempVar.getDomain()[2] = 0;
                }
            }
        }
        if (selectedVar.getPositions()[1][1] >= 1) {
            boolean isCompleteNeighbors = false;
            Variable tempVar = getVariable(variables, selectedVar.getPositions()[1][0], selectedVar.getPositions()[1][1] - 1);
            for(int i = 0; i < 3; i++) {
                neighborsDomain[numberOfNeighbors][i] = tempVar.domain[i];
            }
            numberOfNeighbors++;
            if (tempVar.getPositions()[0][0] == selectedVar.getPositions()[1][0] && tempVar.getPositions()[0][1] == selectedVar.getPositions()[1][1] - 1) {
                if (selectedDomain == 1) tempVar.getDomain()[2] = 0;
                else if (selectedDomain == 2) tempVar.getDomain()[1] = 0;
            } else {
                isCompleteNeighbors = true;
                if (selectedDomain != 0) tempVar.getDomain()[selectedDomain] = 0;
            }
            if (!selectedVar.isHorizontal() && !isCompleteNeighbors) {
                tempVar = getVariable(variables, selectedVar.getPositions()[0][0], selectedVar.getPositions()[0][1] - 1);
                for(int i = 0; i < 3; i++) {
                    neighborsDomain[numberOfNeighbors][i] = tempVar.domain[i];
                }
                numberOfNeighbors++;
                if (tempVar.getPositions()[0][0] == selectedVar.getPositions()[0][0] && tempVar.getPositions()[0][1] == selectedVar.getPositions()[0][1] - 1) {
                    if (selectedDomain != 0) tempVar.getDomain()[selectedDomain] = 0;
                } else {
                    if (selectedDomain == 2) tempVar.getDomain()[1] = 0;
                    else if (selectedDomain == 1) tempVar.getDomain()[2] = 0;
                }
            }
        }
        if (selectedVar.getPositions()[0][0] < rowNumber - 1) {
            boolean isCompleteNeighbors = false;
            Variable tempVar = getVariable(variables, selectedVar.getPositions()[0][0] + 1, selectedVar.getPositions()[0][1]);
            for(int i = 0; i < 3; i++) {
                neighborsDomain[numberOfNeighbors][i] = tempVar.domain[i];
            }
            numberOfNeighbors++;
            if (tempVar.getPositions()[0][0] == selectedVar.getPositions()[0][0] + 1 && tempVar.getPositions()[0][1] == selectedVar.getPositions()[0][1]) {
                if (selectedDomain != 0) tempVar.getDomain()[selectedDomain] = 0;
                isCompleteNeighbors = true;
            } else {
                if (selectedDomain == 2) tempVar.getDomain()[1] = 0;
                else if (selectedDomain == 1) tempVar.getDomain()[2] = 0;
            }
            if (selectedVar.isHorizontal() && !isCompleteNeighbors) {
                tempVar = getVariable(variables, selectedVar.getPositions()[1][0] + 1, selectedVar.getPositions()[0][1]);
                for(int i = 0; i < 3; i++) {
                    neighborsDomain[numberOfNeighbors][i] = tempVar.domain[i];
                }
                numberOfNeighbors++;
                if (tempVar.getPositions()[0][0] == selectedVar.getPositions()[1][0] + 1 && tempVar.getPositions()[0][1] == selectedVar.getPositions()[0][1]) {
                    if (selectedDomain == 1) tempVar.getDomain()[2] = 0;
                    else if (selectedDomain == 2) tempVar.getDomain()[1] = 0;
                } else {
                    if (selectedDomain != 0) tempVar.getDomain()[selectedDomain] = 0;
                }
            }
        }
        if (selectedVar.getPositions()[0][1] < columnNumber - 1) {
            boolean isCompleteNeighbors = false;
            Variable tempVar = getVariable(variables, selectedVar.getPositions()[0][0], selectedVar.getPositions()[0][1] + 1);
            for(int i = 0; i < 3; i++) {
                neighborsDomain[numberOfNeighbors][i] = tempVar.domain[i];
            }
            numberOfNeighbors++;
            if (tempVar.getPositions()[0][0] == selectedVar.getPositions()[0][0] && tempVar.getPositions()[0][1] == selectedVar.getPositions()[0][1] + 1) {
                if (selectedDomain != 0) tempVar.getDomain()[selectedDomain] = 0;
                isCompleteNeighbors = true;
            } else {
                if (selectedDomain == 2) tempVar.getDomain()[1] = 0;
                else if (selectedDomain == 1) tempVar.getDomain()[2] = 0;
            }
            if (!selectedVar.isHorizontal() && !isCompleteNeighbors) {
                tempVar = getVariable(variables, selectedVar.getPositions()[1][0], selectedVar.getPositions()[1][1] + 1);
                for(int i = 0; i < 3; i++) {
                    neighborsDomain[numberOfNeighbors][i] = tempVar.domain[i];
                }
                if (tempVar.getPositions()[0][0] == selectedVar.getPositions()[1][0] && tempVar.getPositions()[0][1] == selectedVar.getPositions()[1][1] + 1) {
                    if (selectedDomain == 2) tempVar.getDomain()[1] = 0;
                    else if (selectedDomain == 1) tempVar.getDomain()[2] = 0;
                } else {
                    if (selectedDomain != 0) tempVar.getDomain()[selectedDomain] = 0;
                }
            }
        }
            /*
        }
        Set<Integer> rowNumbers = new HashSet<>();
        rowNumbers.add(selectedVar.getPositions()[0][0]);
        rowNumbers.add(selectedVar.getPositions()[1][0]);
        for (Integer rowNumber : rowNumbers) {
            //int emptyHomes = 0;
            //ArrayList<Variable> emptyVarialbes = new ArrayList<>();
            for (int i = 0; i < columnNumber; i++) {
                Variable tempVar = getVariable(variables, rowNumber, i);
                if (!tempVar.isMagnet() && (tempVar.getDomain()[0] == 0 && tempVar.getDomain()[1] == 0 && tempVar.getDomain()[2] == 1 || tempVar.getDomain()[0] == 0 && tempVar.getDomain()[1] == 1 && tempVar.getDomain()[2] == 1)) {
                    tempVar.setMagnet(true);
                    if (tempVar.getDomain()[1] == 1) {
                        posRowNumbers[tempVar.getPositions()[0][0]] -= 1;
                        posColNumbers[tempVar.getPositions()[0][1]] -= 1;
                        negColNumbers[tempVar.getPositions()[1][1]] -= 1;
                        negRowNumbers[tempVar.getPositions()[1][0]] -= 1;
                    } else {
                        posRowNumbers[tempVar.getPositions()[1][0]] -= 1;
                        posColNumbers[tempVar.getPositions()[1][1]] -= 1;
                        negColNumbers[tempVar.getPositions()[0][1]] -= 1;
                        negRowNumbers[tempVar.getPositions()[0][0]] -= 1;
                    }
                }
//                if(!tempVar.isMagnet()){
//                    emptyHomes++;
//                    //emptyVarialbes.add(tempVar);
//                }
                if(tempVar.isHorizontal()) i++;
            }
//            if(emptyHomes == posRowNumbers[rowNumber] && negRowNumbers[rowNumber] == 0){
//                for(int j = 0; j < emptyVarialbes.size(); j++){
//                    if()
//                    int[] domain = {}
//                    emptyVarialbes.get(j).setDomain();
//                }
//            }
        }
        Set<Integer> colNumbers = new HashSet<>();
        rowNumbers.add(selectedVar.getPositions()[0][1]);
        rowNumbers.add(selectedVar.getPositions()[1][1]);
        for (Integer colNumber : colNumbers) {
            //int emptyHomes = 0;
            //ArrayList<Variable> emptyVarialbes = new ArrayList<>();
            for (int i = 0; i < rowNumber; i++) {
                Variable tempVar = getVariable(variables, colNumber, i);
                if (!tempVar.isMagnet() && (tempVar.getDomain()[0] == 0 && tempVar.getDomain()[1] == 0 && tempVar.getDomain()[2] == 1 || tempVar.getDomain()[0] == 0 && tempVar.getDomain()[1] == 1 && tempVar.getDomain()[2] == 1)) {
                    tempVar.setMagnet(true);
                    if (tempVar.getDomain()[1] == 1) {
                        posRowNumbers[tempVar.getPositions()[0][0]] -= 1;
                        posColNumbers[tempVar.getPositions()[0][1]] -= 1;
                        negColNumbers[tempVar.getPositions()[1][1]] -= 1;
                        negRowNumbers[tempVar.getPositions()[1][0]] -= 1;
                    } else {
                        posRowNumbers[tempVar.getPositions()[1][0]] -= 1;
                        posColNumbers[tempVar.getPositions()[1][1]] -= 1;
                        negColNumbers[tempVar.getPositions()[0][1]] -= 1;
                        negRowNumbers[tempVar.getPositions()[0][0]] -= 1;
                    }
                }
//                if(!tempVar.isMagnet()){
//                    emptyHomes++;
//                    //emptyVarialbes.add(tempVar);
//                }
                if(tempVar.isHorizontal()) i++;
            }
//            if(emptyHomes == posRowNumbers[rowNumber] && negRowNumbers[rowNumber] == 0){
//                for(int j = 0; j < emptyVarialbes.size(); j++){
//                    if()
//                    int[] domain = {}
//                    emptyVarialbes.get(j).setDomain();
//                }
//            }
        */

    }

    public void load_PDomain_OfVarNeigh(ArrayList<Variable> variables, Variable selectedVar, int[][] neighboursDomain) {
        int numberOfNeighbors = 0;
        if (selectedVar.getPositions()[1][0] >= 1) {
            boolean isCompleteNeighbors = false;
            Variable tempVar = getVariable(variables, selectedVar.getPositions()[1][0] - 1, selectedVar.getPositions()[1][1]);
            for(int i = 0; i < 3; i++) {
                tempVar.domain[i] = neighboursDomain[numberOfNeighbors][i];
            }
            numberOfNeighbors++;
            if (tempVar.getPositions()[1][0] == selectedVar.getPositions()[1][0] - 1 && tempVar.getPositions()[1][1] == selectedVar.getPositions()[1][1]) {
                isCompleteNeighbors = true;
            }
            if (selectedVar.isHorizontal() && !isCompleteNeighbors) {
                tempVar = getVariable(variables, selectedVar.getPositions()[0][0] - 1, selectedVar.getPositions()[0][1]);
                for(int i = 0; i < 3; i++) {
                    tempVar.domain[i] = neighboursDomain[numberOfNeighbors][i];
                }
                numberOfNeighbors++;
            }
        }
        if (selectedVar.getPositions()[1][1] >= 1) {
            boolean isCompleteNeighbors = false;
            Variable tempVar = getVariable(variables, selectedVar.getPositions()[1][0], selectedVar.getPositions()[1][1] - 1);
            for(int i = 0; i < 3; i++) {
                tempVar.domain[i] = neighboursDomain[numberOfNeighbors][i];
            }
            numberOfNeighbors++;
            if (tempVar.getPositions()[1][0] == selectedVar.getPositions()[1][0] && tempVar.getPositions()[1][1] == selectedVar.getPositions()[1][1] - 1) {
                isCompleteNeighbors = true;
            }
            if (!selectedVar.isHorizontal() && !isCompleteNeighbors) {
                tempVar = getVariable(variables, selectedVar.getPositions()[0][0], selectedVar.getPositions()[0][1] - 1);
                for(int i = 0; i < 3; i++) {
                    tempVar.domain[i] = neighboursDomain[numberOfNeighbors][i];
                }
                numberOfNeighbors++;
            }
        }
        if (selectedVar.getPositions()[0][0] < rowNumber - 1) {
            boolean isCompleteNeighbors = false;
            Variable tempVar = getVariable(variables, selectedVar.getPositions()[0][0] + 1, selectedVar.getPositions()[0][1]);
            for(int i = 0; i < 3; i++) {
                tempVar.domain[i] = neighboursDomain[numberOfNeighbors][i];
            }
            numberOfNeighbors++;
            if (tempVar.getPositions()[0][0] == selectedVar.getPositions()[0][0] + 1 && tempVar.getPositions()[0][1] == selectedVar.getPositions()[0][1]) {
                isCompleteNeighbors = true;
            }
            if (selectedVar.isHorizontal() && !isCompleteNeighbors) {
                tempVar = getVariable(variables, selectedVar.getPositions()[1][0] + 1, selectedVar.getPositions()[0][1]);
                for(int i = 0; i < 3; i++) {
                    tempVar.domain[i] = neighboursDomain[numberOfNeighbors][i];
                }
                numberOfNeighbors++;
            }
        }
        if (selectedVar.getPositions()[0][1] < columnNumber - 1) {
            boolean isCompleteNeighbors = false;
            Variable tempVar = getVariable(variables, selectedVar.getPositions()[0][0], selectedVar.getPositions()[0][1] + 1);
            for(int i = 0; i < 3; i++) {
                tempVar.domain[i] = neighboursDomain[numberOfNeighbors][i];
            }
            numberOfNeighbors++;
            if (tempVar.getPositions()[0][0] == selectedVar.getPositions()[0][0] && tempVar.getPositions()[0][1] == selectedVar.getPositions()[0][1] + 1) {
                isCompleteNeighbors = true;
            }
            if (!selectedVar.isHorizontal() && !isCompleteNeighbors) {
                tempVar = getVariable(variables, selectedVar.getPositions()[1][0], selectedVar.getPositions()[1][1] + 1);
                for(int i = 0; i < 3; i++) {
                    tempVar.domain[i] = neighboursDomain[numberOfNeighbors][i];
                }
            }
        }

    }


    public void printDomain(ArrayList<Variable> variables, Variable selectedVar) {
        System.out.println("neighbours domains of[" + selectedVar.getPositions()[0][0] + "][" + selectedVar.getPositions()[0][1] + "] are");
        if (selectedVar.getPositions()[1][0] >= 1) {
            Variable tempVar = getVariable(variables, selectedVar.getPositions()[1][0] - 1, selectedVar.getPositions()[1][1]);
            System.out.println(tempVar.getPositions()[0][0] + " " + tempVar.getPositions()[0][1] + " current domain is:" + tempVar.getDomain()[0] + " " + tempVar.getDomain()[1] + " " + tempVar.getDomain()[2]);
            if (selectedVar.isHorizontal()) {
                tempVar = getVariable(variables, selectedVar.getPositions()[0][0] - 1, selectedVar.getPositions()[0][1]);
                System.out.println(tempVar.getPositions()[0][0] + " " + tempVar.getPositions()[0][1] + " current domain is:" + tempVar.getDomain()[0] + " " + tempVar.getDomain()[1] + " " + tempVar.getDomain()[2]);
            }
        }
        if (selectedVar.getPositions()[1][1] >= 1) {
            Variable tempVar = getVariable(variables, selectedVar.getPositions()[1][0], selectedVar.getPositions()[1][1] - 1);
            System.out.println(tempVar.getPositions()[0][0] + " " + tempVar.getPositions()[0][1] + " current domain is:" + tempVar.getDomain()[0] + " " + tempVar.getDomain()[1] + " " + tempVar.getDomain()[2]);
            ///System.out.println(tempVar.getPositions()[0][0] + " " + tempVar.getPositions()[0][1] + " PDomain2 is :" + tempVar.domain[0] + " " + tempVar.domain[1] + " " + tempVar.domain[2]);
            if (!selectedVar.isHorizontal()) {
                tempVar = getVariable(variables, selectedVar.getPositions()[0][0], selectedVar.getPositions()[0][1] - 1);
                System.out.println(tempVar.getPositions()[0][0] + " " + tempVar.getPositions()[0][1] + " current domain is:" + tempVar.getDomain()[0] + " " + tempVar.getDomain()[1] + " " + tempVar.getDomain()[2]);
            }
        }
        if (selectedVar.getPositions()[0][0] < rowNumber - 1) {
            Variable tempVar = getVariable(variables, selectedVar.getPositions()[0][0] + 1, selectedVar.getPositions()[0][1]);
            System.out.println(tempVar.getPositions()[0][0] + " " + tempVar.getPositions()[0][1] + " current domain is:" + tempVar.getDomain()[0] + " " + tempVar.getDomain()[1] + " " + tempVar.getDomain()[2]);
            if (selectedVar.isHorizontal()) {
                tempVar = getVariable(variables, selectedVar.getPositions()[1][0] + 1, selectedVar.getPositions()[0][1]);
                System.out.println(tempVar.getPositions()[0][0] + " " + tempVar.getPositions()[0][1] + " current domain is:" + tempVar.getDomain()[0] + " " + tempVar.getDomain()[1] + " " + tempVar.getDomain()[2]);
            }
        }
        if (selectedVar.getPositions()[0][1] < columnNumber - 1) {
            Variable tempVar = getVariable(variables, selectedVar.getPositions()[0][0], selectedVar.getPositions()[0][1] + 1);
            System.out.println(tempVar.getPositions()[0][0] + " " + tempVar.getPositions()[0][1] + " current domain is:" + tempVar.getDomain()[0] + " " + tempVar.getDomain()[1] + " " + tempVar.getDomain()[2]);
            if (!selectedVar.isHorizontal()) {
                tempVar = getVariable(variables, selectedVar.getPositions()[1][0], selectedVar.getPositions()[1][1] + 1);
                System.out.println(tempVar.getPositions()[0][0] + " " + tempVar.getPositions()[0][1] + " current domain is:" + tempVar.getDomain()[0] + " " + tempVar.getDomain()[1] + " " + tempVar.getDomain()[2]);

            }
        }
        System.out.println("neighbors domain finished");
    }

    public Variable MRV(ArrayList<Variable> variables) {
        int min = 3;
        int index = 0;
        for (int i = 0; i < variables.size(); i++) {
            if (variables.get(i).getDomainSize() <= min) {
                index = i;
                min = variables.get(i).getDomainSize();
            }
        }
        return variables.get(index);

    }

    public ArrayList<Integer> LCV(ArrayList<Variable> variableArrayList, Variable variable) {

        Hashtable<String, Variable> variables = listToHash(variableArrayList);
        ArrayList<Integer> ordering = new ArrayList<>();
        if (variable.getDomain()[0] == 1)
            ordering.add(0);

        int count = 0;
        int min = 1000;
        int minValue = 0;
        int[][] varPos = variable.getPositions();
        int y1 = varPos[1][0]; //i
        int y2 = varPos[0][0]; //i+1 || i
        int x1 = varPos[1][1]; //j
        int x2 = varPos[0][1]; //j+1 || j
        String key = "";

        for (int i = 1; i < 3; i++) {

            if (variable.getDomain()[i] == 0)
                continue;

            if (variable.isHorizontal()) { // y1 == y2
                if (y1 > 0) { //mitune hamsaye balaii dashte bashe
                    key = (y1 - 1) + " " + x1;
                    Variable top_left_n = variables.get(key);
                    key = (y1 - 1) + " " + x2;
                    Variable top_right_n = variables.get(key);

                    //ye hamseye balayii ba 2 khune mojaver
                    if (top_left_n.equals(top_right_n)) {
                        if (variable.getDomain()[i] == top_left_n.getDomain()[i])
                            count++;
                    } else { //2 hamsaye ba yekhune mojaver
                        //top_lef_n halat amudi va ofoghish farghi nemikone
                        if (i == 1 && top_left_n.getDomain()[2] == 1)
                            count++;
                        if (i == 2 && top_left_n.getDomain()[1] == 1)
                            count++;
                        if (top_right_n.isHorizontal()) {
                            if (i == 1 && top_right_n.getDomain()[2] == 1)
                                count++;
                            if (i == 2 && top_right_n.getDomain()[1] == 1)
                                count++;
                        } else if (top_right_n.getDomain()[i] == 1)
                            count++;
                    }
                }

                if (x1 > 0) { //mitune hamsaye samte chap dashte bashe
                    key = y1 + " " + (x1 - 1);
                    Variable left_n = variables.get(key);

                    if (left_n.getOtherPositionY(key) > y1) {
                        if (left_n.getDomain()[i] == 1)
                            count++;
                    } else {
                        if (i == 1 && left_n.getDomain()[2] == 1)
                            count++;
                        if (i == 2 && left_n.getDomain()[1] == 1)
                            count++;
                    }
                }

                if (y2 < rowNumber - 1) { //mitune hamsaye paiini dashte bashe
                    key = (y2 + 1) + " " + x1;
                    Variable bottom_left_n = variables.get(key);
                    key = (y2 + 1) + " " + x2;
                    Variable bottom_right_n = variables.get(key);

                    //ye hamseye balayii ba 2 khune mojaver
                    if (bottom_left_n.equals(bottom_right_n)) {
                        if (variable.getDomain()[i] == bottom_left_n.getDomain()[i])
                            count++;
                    } else { //2 hamsaye ba yekhune mojaver
                        //bottom_right_n halat amudi va ofoghish farghi nemikone
                        if (i == 1 && bottom_right_n.getDomain()[2] == 1)
                            count++;
                        if (i == 2 && bottom_right_n.getDomain()[1] == 1)
                            count++;
                        if (bottom_left_n.isHorizontal()) {
                            if (i == 1 && bottom_left_n.getDomain()[2] == 1)
                                count++;
                            if (i == 2 && bottom_left_n.getDomain()[1] == 1)
                                count++;
                        } else if (bottom_left_n.getDomain()[i] == 1)
                            count++;
                    }
                }

                if (x2 < columnNumber - 1) { //mitune hamsaye rast dashte bashe
                    key = y1 + " " + (x2 + 1);
                    Variable right_n = variables.get(key);

                    if (right_n.getOtherPositionY(key) < y1) {
                        if (right_n.getDomain()[i] == 1)
                            count++;
                    } else {
                        if (i == 1 && right_n.getDomain()[2] == 1)
                            count++;
                        if (i == 2 && right_n.getDomain()[1] == 1)
                            count++;
                    }
                }
            } else { // variable is vertical. x1 == x2

                if (y1 > 0) { //mitune hamsaye balaii dashte bashe
                    key = (y1 - 1) + " " + x1;
                    Variable top_n = variables.get(key);

                    if (top_n.getOtherPositionX(key) > x1) {
                        if (top_n.getDomain()[i] == 1)
                            count++;
                    } else {
                        if (i == 1 && top_n.getDomain()[2] == 1)
                            count++;
                        if (i == 2 && top_n.getDomain()[1] == 1)
                            count++;
                    }
                }

                if (x1 > 0) { //mitune hamsaye samte chap dashte bashe
                    key = y1 + " " + (x1 - 1);
                    Variable left_top_n = variables.get(key);
                    key = y2 + " " + (x1 - 1);
                    Variable left_bottom_n = variables.get(key);

                    //ye hamseye samte chap ba 2 khune mojaver
                    if (left_top_n.equals(left_bottom_n)) {
                        if (variable.getDomain()[i] == left_top_n.getDomain()[i])
                            count++;
                    } else { //2 hamsaye ba yekhune mojaver
                        //lef_top_n halat amudi va ofoghish farghi nemikone
                        if (i == 1 && left_top_n.getDomain()[2] == 1)
                            count++;
                        if (i == 2 && left_top_n.getDomain()[1] == 1)
                            count++;
                        if (left_bottom_n.isHorizontal()) {
                            if (left_bottom_n.getDomain()[i] == 1)
                                count++;
                        } else {
                            if (i == 1 && left_bottom_n.getDomain()[2] == 1)
                                count++;
                            if (i == 2 && left_bottom_n.getDomain()[1] == 1)
                                count++;
                        }
                    }
                }

                if (y2 < rowNumber - 1) { //mitune hamsaye paiini dashte bashe
                    key = (y2 + 1) + " " + x1;
                    Variable bottom_n = variables.get(key);

                    if (bottom_n.getOtherPositionX(key) < x1) {
                        if (bottom_n.getDomain()[i] == 1)
                            count++;
                    } else {
                        if (i == 1 && bottom_n.getDomain()[2] == 1)
                            count++;
                        if (i == 2 && bottom_n.getDomain()[1] == 1)
                            count++;
                    }
                }

                if (x2 < columnNumber - 1) { //mitune hamsaye rast dashte bashe
                    key = y1 + " " + (x2 + 1);
                    Variable right_top_n = variables.get(key);
                    key = y2 + " " + (x2 + 1);
                    Variable right_bottom_n = variables.get(key);

                    //ye hamseye samte chap ba 2 khune mojaver
                    if (right_top_n.equals(right_bottom_n)) {
                        if (variable.getDomain()[i] == right_bottom_n.getDomain()[i])
                            count++;
                    } else { //2 hamsaye ba yekhune mojaver
                        //right_bottom_n halat amudi va ofoghish farghi nemikone
                        if (i == 1 && right_bottom_n.getDomain()[2] == 1)
                            count++;
                        if (i == 2 && right_bottom_n.getDomain()[1] == 1)
                            count++;
                        if (right_top_n.isHorizontal()) {
                            if (right_top_n.getDomain()[i] == 1)
                                count++;
                        } else {
                            if (i == 1 && right_top_n.getDomain()[2] == 1)
                                count++;
                            if (i == 2 && right_top_n.getDomain()[1] == 1)
                                count++;
                        }
                    }
                }

            }

            if (count < min) { //ba farz inke variable ba domain tohi handle shode
                min = count;
                minValue = i;
            }
            count = 0;
        }
        if (minValue != 0)
            ordering.add(minValue);
        if (minValue == 1 && variable.getDomain()[2] == 1)
            ordering.add(2);
        if (minValue == 2 && variable.getDomain()[1] == 1)
            ordering.add(1);

//        System.out.println("-----------");
//        System.out.println("["+variable.getDomain()[0]+","+variable.getDomain()[1]+","+variable.getDomain()[2]+"]");
//
//        for(int i=0; i<ordering.size(); i++)
//            System.out.print(ordering.get(i)+" ");
//        System.out.println();
//        System.out.println("-----------");

        return ordering;
    }

    //vList aval khalie, variables kole moteghayeras
    public ArrayList<Variable> CSP_BackTracking(ArrayList<Variable> vList, ArrayList<Variable> variables) {

        if (isComplete(vList))
            return vList;

//          AC3(variables);
//        if (hasEmptyDomain(variables))
//            return null;

        ArrayList<Variable> vPrimList = findOtherVariables(vList, variables);
        if (vPrimList.size() == 0) {
            ///System.out.println("returned");
            return null;
        }
        Variable var = MRV(vPrimList);
        ArrayList<Integer> ordering = LCV(variables, var);
        System.out.println("------");
        for (int i = 0; i < ordering.size(); i++) {
            System.out.println(ordering.get(i) + " ");
        }
        System.out.println("------");

        // changed
        for (int v : ordering) {
            int[][] pos = var.getPositions();
            System.out.println(v + " -->[ " + var.getDomain()[0] + "," + var.getDomain()[1] + "," + var.getDomain()[2] + "]" +
                    "-->" + pos[0][0] + " " + pos[0][1]);
            int[] preDomain = new int[3];
            System.arraycopy(var.domain, 0, preDomain, 0, 3);
            var.selectValue(v);
            vList.add(var);
            vPrimList.remove(var);
            int[][] neighboursDomain = new int[6][3];
            forwardChecking(variables, var, v, neighboursDomain);
            if (hasEmptyDomain(vPrimList)) {
                load_PDomain_OfVarNeigh(variables, var, neighboursDomain);
                vList.remove(var);
                return null;
            }
            ArrayList<Variable> result = CSP_BackTracking(vList, variables);
            if (result != null) {
                return result;
            }
            load_PDomain_OfVarNeigh(variables, var, neighboursDomain);
            System.arraycopy(preDomain, 0, var.domain, 0, 3);
            vPrimList.add(var);
            vList.remove(var);
        }
        return null;
    }

    private boolean hasEmptyDomain(ArrayList<Variable> vList) {

        for (int i = 0; i < vList.size(); i++) {
            if (vList.get(i).getDomainSize() == 0) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<Variable> findOtherVariables(ArrayList<Variable> vList, ArrayList<Variable> allVariables) {
        ArrayList<Variable> vPrimList = new ArrayList<>();
        boolean found = false;
        for (Variable variable : allVariables) {
            for (Variable var : vList) {
                /*
                int[][] variablePos = variable.getPositions();
                int[][] varPos = var.getPositions();
                variablePos[0][0] == varPos[0][0] && variablePos[0][1] == variablePos[0][1] &&
                        variablePos[1][0] == varPos[1][0] && variablePos[1][1] == varPos[1][1]

                 */
                if (variable.equals(var)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                vPrimList.add(variable);
            }
            found = false;
        }
        return vPrimList;
    }

    private boolean isComplete(ArrayList<Variable> variableArrayList) {
        Variable var;
        int rowN = 0;
        int columnN = 0;
        int rowP = 0;
        int columnP = 0;
        String value;

        if (variableArrayList.size() != (rowNumber * columnNumber) / 2)
            return false; //age hame moteghayera meghdar naagerefte bashan

        Hashtable<String, Variable> variables = listToHash(variableArrayList);
        String key;
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                key = i + " " + j;
                var = variables.get(key);
                value = var.selectedValue(i, j);
                System.out.print(value + " ");
            }
            System.out.println();
        }
        System.out.println();

        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {

                var = variables.get(i + " " + j);
                value = var.selectedValue(i, j);
                if (value == "+")
                    rowP++;
                if (value == "-")
                    rowN++;
            }
            if (posRowNumbers[i] != rowP || negRowNumbers[i] != rowN)
                return false;
            rowN = 0;
            rowP = 0;
        }
        for (int j = 0; j < columnNumber; j++) {
            for (int i = 0; i < rowNumber; i++) {

                var = variables.get(i + " " + j);
                value = var.selectedValue(i, j);
                if (value == "+")
                    columnP++;
                if (value == "-")
                    columnN++;
            }
            if (posColNumbers[j] != columnP || negColNumbers[j] != columnN)
                return false;
            columnN = 0;
            columnP = 0;
        }
        return true;
    }

    public Hashtable<String, Variable> listToHash(ArrayList<Variable> variables) {
        Hashtable<String, Variable> varHash = new Hashtable<>();

        for (Variable var : variables) {
            int[][] positions = var.getPositions();
            varHash.put(positions[0][0] + " " + positions[0][1], var);
            varHash.put(positions[1][0] + " " + positions[1][1], var);
        }
        return varHash;
    }
}