# ChineseCheckers
Chinese Checkers Game README

Instructions for run: 
1- Choose level (Easy, Medium, Hard).
2- Maximize output screen.
3- PLayer is green, AI is red.
4- Player starts playing.
5- Click on a green marble, possible moves highlighted, choose the possible move.

Language: Java
IDE: intelliJ
mail: marwan.aymann23@gmail.com

Heuristic Logic: 
1- Put goal points in array and pick the last point (12,16).
2- Calculate the distance between all AI marbles with the last point using euclidean distance formula, 
then get the sum of all distances, start moving untill one of AI marbles fill (12,16) point.
3- Update goal with another point from array of AI goal.
