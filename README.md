# sudoku_solver

RUNTIME: 

If we look at the recursive function, we loop through twice: n times 
So, if we have two loops that take n times to loop through, we get n * n. 
The number limit is 9 --> we cannot enter a number more than 9 or less than 1, so each time we are checking the number, we are checking a maximum of 9 times. 
Therefore, runtime is O(9^(n * n))

