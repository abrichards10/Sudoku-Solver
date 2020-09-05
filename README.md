# sudoku_solver

Correction Courtesy of Kassy :) 

solveBoard = N* N + N * N * (N * N) = N^2 + N^4
isValid = (N columns + N rows + N subgrid) or N

Time Complexity = N^4
Space Complexity = N * N (arr var) + (N * N from solveBoard) = N^2
