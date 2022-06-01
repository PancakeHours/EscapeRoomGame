# EscapeRoomGame
Completed project as required. 
Some changes/improvements to the desired final product were made:
1. Traps can now be manually checked for on all adjacent squares, but would take up a step to create a tradeoff between a higher score and player safety.
2. Total score is now calculated by taking the old score and subtracting the number of steps taken to complete the escape room, incentivizing players to be efficient.
3. An attempt was made at allowing user-inputted prize, trap, and wall numbers, but this proved to be too difficult when working with the Rectangle arrays to prevent
duplicate objects.
4. Movement from place to place now triggers traps if they aren't checked for and sprung, which decreases score by trapVal.
5. Jumps are 2 squares to any direction.
