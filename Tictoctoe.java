package com.tictoctoe;

import java.util.Scanner;

public class Tictoctoe {

    public static void main(String[] args) {

        System.out.println("==============================");
        System.out.println("       TIC-TAC-TOE");
        System.out.println("==============================");
        System.out.println("Player X vs Player O");
        System.out.println();

        Scanner sc = new Scanner(System.in);
        int gamenumber=1;
       int xwins=0;
       int owins=0;
       int draw=0;
        

        while (true) {
        	System.out.println();
        	System.out.println("========="+gamenumber+"=========");
        	char[][] board = {
                    {' ', ' ', ' '},
                    {' ', ' ', ' '},
                    {' ', ' ', ' '}
            };

            char player = 'x';

            for (int turn = 0; turn < 9; turn++) {

                printBoard(board);

                System.out.println("Player " + player + " turn");

                // Row input
                System.out.println("Enter the row");

                if (!sc.hasNextInt()) {
                    System.out.println("Please enter a number.");
                    sc.next();
                    turn--;
                    continue;
                }

                int row = sc.nextInt();

                // Column input
                System.out.println("Enter the column");

                if (!sc.hasNextInt()) {
                    System.out.println("Please enter a number.");
                    sc.next();
                    turn--;
                    continue;
                }

                int column = sc.nextInt();

                // Check valid position
                if (row > 2 || row < 0 || column > 2 || column < 0) {
                    System.out.println("Invalid position! Please enter values from 0 to 2.");
                    turn--;
                    continue;
                }

                // Check whether cell is empty
                if (board[row][column] == ' ') {
                    board[row][column] = player;
                } 
                else {
                    System.out.println("Cell already occupied! Try another position.");
                    turn--;
                    continue;
                }

                // Check winner
                if (rowcheck(board, player) ||
                    columncheck(board, player) ||
                    diagonalcheck(board, player)) {

                    printBoard(board);
                    System.out.println("Player " + player + " wins the game!");
                    if(player=='x') {
                    	xwins++;
                    }
                    else {
                    	owins++;
                    }
                    break;
                }
               
                

                // Check draw
                if (turn == 8) {
                    printBoard(board);
                    System.out.println("Game is draw!");
                    draw++;
                    break;
                }

                // Change player
                if (player == 'x') {
                    player = 'o';
                } 
                else {
                    player = 'x';
                }
            }
            System.out.println("Do you want to play again? (y/n)");
            System.out.println();
            System.out.println("========== Score ==========");
            System.out.println("X Wins : " + xwins);
            System.out.println("O Wins : " + owins);
            System.out.println("Draws  : " + draw);
            System.out.println("===========================");

            char choice = sc.next().charAt(0);
            while(choice!='y'||choice!='Y'||choice!='x'||choice!='X') {
            	System.out.println("enter a valid option y or n");
            	sc.next().charAt(0);
            }

            if(choice != 'y' && choice != 'Y') {
                System.out.println("Thanks for playing!");
                break;
            }
           
            if(checkwinner(board,player)) {
           	 printBoard(board);
           	    System.out.println("Player " + player + " wins the game!");

           	    if(player == 'x') {
           	        xwins++;
           	    }
           	    else {
           	        owins++;
           	    }

           	    break;
           }
            gamenumber++;
            player=switchPlayer(player);
            
        }
        sc.close();
        

       
    }

    static boolean rowcheck(char[][] board, char player) {

        for (int i = 0; i < 3; i++) {

            if (board[i][0] == player &&
                board[i][1] == player &&
                board[i][2] == player) {

                return true;
            }
        }

        return false;
    }

    static boolean columncheck(char[][] board, char player) {

        for (int j = 0; j < 3; j++) {

            if (board[0][j] == player &&
                board[1][j] == player &&
                board[2][j] == player) {

                return true;
            }
        }

        return false;
    }

    static boolean diagonalcheck(char[][] board, char player) {

        if (board[0][0] == player &&
            board[1][1] == player &&
            board[2][2] == player) {

            return true;
        }

        if (board[0][2] == player &&
            board[1][1] == player &&
            board[2][0] == player) {

            return true;
        }

        return false;
    }

    static void printBoard(char[][] board) {

        System.out.println("  0 1 2");
        System.out.println("-------");

        for (int i = 0; i < 3; i++) {

            System.out.print(i + "|");

            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + "|");
            }

            System.out.println();
            System.out.println("-------");
        }
    }
    static boolean checkwinner(char[][]board,char player) {
    	return rowcheck(board,player)||columncheck(board,player)||diagonalcheck(board,player);
    }
    static char switchPlayer(char player) {

        if(player == 'x') {
            return 'o';
        }
        else {
            return 'x';
        }
    }
}