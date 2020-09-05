/* Group: Aaron Brion, Angela Richards, Kassy Pak
 *
 * Requirements:
 *      - must accept a partially filled Sudoku board from user (81 inputs)
 *      - generate completely filled board according to back tracking
 *      - must print correctly-generated Sudoku board
 * */

import java.util.Scanner;

public class Sudoku {
    Scanner input = new Scanner(System.in);
    private final int[][] arr;
    private final int end;

    public Sudoku() {
        arr = new int[9][9];
        end = arr.length;
        enterBoard();
    }

    /* allows users to input values sudoku board */
    private void enterBoard() {
        int num;

        for (int row=0; row<end; row++) {
            for (int column=0; column<end; column++) {
                do {
                    printBoard();
                    System.out.print("Please input a num (0 for blank or 1-9)\nfor ROW " + row + " COLUMN " + column + " : ");
                    num = input.nextInt();

                } while ( num!=0 && !isValid(num, row, column) );
                arr[row][column] = num;
            }
        }
        printBoard(); // prints before
        solveBoard(arr, end);
        printBoard(); // prints after
    }

    /* checks if the input is a valid number and valid for the cell. */
    private boolean isValid(int userNum, int currRow, int currCol) {

        /* if the user input is not 0-9, return invalid */
        if(userNum<1 || userNum>9) {
            System.out.println("ONLY INPUT NUMBERS 0-9");
            return false;
        }
        if (!checkColumns(userNum, currRow)) {
            return false;
        }
        if (!checkRows(userNum, currCol)) {
            return false;
        }
        if (!checkSubGrid(userNum, currRow, currCol)) {
            System.out.println("CONFLICT IN SUBGRID");
            return false;
        }
        /* inputs valid num in cell and increments row/column respectively */
        return true;
    }

    private boolean checkColumns(int userNum, int currRow) {
        /* checks for conflict in column */
        for (int column=0; column<end; column++) {
            /* loops through current row's columns, return false if invalid num.*/
            if (arr[currRow][column] == userNum && arr[currRow][column]!=0) {
                return false;
            }
        }
        return true;
    }

    private boolean checkRows(int userNum, int currCol) {
        /* checks for conflict in row */
        for (int row=0; row<end; row++){
            /* loops through current column's rows, return false if invalid num.*/
            if (arr[row][currCol] == userNum && arr[row][currCol]!=0) {
                return false;
            }
        }
        return true;
    }

    private boolean checkSubGrid(int userNum, int currRow, int currCol) {
        int sqrt = (int)Math.sqrt(arr.length);
        // the rows and columns of the subgrid are checked
        int row_subgrid = currRow - currRow % sqrt; //
        int col_subgrid = currCol - currCol % sqrt;


        for(int r = row_subgrid; r < row_subgrid + sqrt; r++){ // we start going by 3's --> the row starts at the first subgrid
            for(int c = col_subgrid; c < col_subgrid + sqrt; c++){ // same with the columns
                if(arr[r][c] == userNum){ // if anything is found in the rows or columns of the subgrid
                    return false; // we cannot place the number there
                }
            }
        }
        return true;
    }

    /* solves sudoku board recursively */
    public boolean solveBoard(int[][]board, int N) {
        int row = -1;
        int col = -1;
        // checks if theres a 0
        boolean empty = true;

        // it's not empty if there is a 0 --> therefore we can add
        for(int r = 0; r < N; r++){
            for(int c = 0; c < N; c++) {
                if (board[r][c] == 0) {
                    row = r;
                    col = c;

                    empty = false;
                    break;
                }
            }
            // if there is not a zero, just continue
            if(!empty){
                break;
            }
        }

        if(empty){
            return true;
        }

        // RECURSIVELY CALLED
        // If the placement is valid, we add the number to the cell
        // Tf the placement is valid and it is possible to solve, return true
        for(int num = 1; num <= N; num++){
            if(isValid(num, row, col)){
                board[row][col] = num;
                if(solveBoard(board, N)){
                    return true;
                }
            }
            // otherwise, it stays empty
            else{
                board[row][col] = 0;
            }
        }
        return false;

}

    /* displays sudoku board */
    public void printBoard() {
        System.out.println("---------------------------");

        for (int row=0; row<arr.length; row++) {
            /* prints the | on the left side for each new row */
            System.out.print(" | ");

            for (int column=0; column<arr.length; column+=3) {
                /* prints each 3 column numbers for the current row, and then separates at the end with | */
                System.out.print(arr[row][column] + " " + arr[row][column+1] + " " + arr[row][column+2] + " " + "| ");
            }

            /* makes a new line after one row of 9 numbers are filled */
            System.out.println();
            if(row%3==2) {
                /* adds a border bracket */
                System.out.println("---------------------------");
            }
        }
    }

    public static void main(String[] args) {
        Sudoku game = new Sudoku();
    }

}
