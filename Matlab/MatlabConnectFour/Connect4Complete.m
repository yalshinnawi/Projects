% Yousef Al-Shinnawi
% Monday, November 12, 2018
% EGN-5-1812
% Programming and Problem Solving in MATLAB
%
% This is the code for the Final Project

clear, clc, close all

%% This is where the Board is Built and variables are initialized

board = zeros(6,7)  %initializing and displaying the playing board
player1 = 1;    %Assigning a value to player 1
player2 = 2;    %Assigning a value to player 2
turn = 0;   %Setting the number of turns to 0

%% This draws the array of numbers to the game board and creates the RGB
% matrix.
figure('Name','Gameboard','NumberTitle','off');     %Labeling the name of the plot pop-up for the gameboard

ImageBoard = zeros(6,7,3);  %Creating an RGB multidimensional matrix
ImageBoard(:,:,:) = 1;  %Setting all the RGB values to 1 so that it is a plain white screen
imagesc(ImageBoard);    %Displaying the RGB matrix to the plot
hold on     %holding the plot so that the rest can be displayed
for i = 1:7     %A double for loop that goes through every row/column combination on the board
    for j = 1:6
        a = rectangle('Position', [i-0.5 j-0.5 1 1], 'FaceColor', [0.9, 0.8, 0], 'EdgeColor', [0.9, 0.8, 0]);   %Creating yellow squares on the board
        b = rectangle('Position', [i-0.5 j-0.5 1 1], 'Curvature', [1 1],'FaceColor', [1,1,1]);  %Overlapping those yellow squares with white circles to give the effect of the gameboard
    end
end

%% This is when the players choose their positions on the board

while turn < 42     %The large while loop that begins the game
    x = 6;  %Setting the row value to the last value so that the piece automatically falls to the bottom of the gameboard
    y = 0;  %Initializing the y value out of bounds so that the next while loop begins
    
 %% This simulates the character's puck being sent to the bottom of the column just as it is in connect 4
 % It also takes user input, and makes sure that there is a correct input
 % when a wrong value is entered and when a column is full.
    while (y > 7 || y < 1)  
    	y = input('Please enter the column you would like to drop your puck\n');    %Asking the user for the column number
        if (y > 7 || y < 1)
                disp('Please enter a correct value for the 6x7 grid')   
                continue;   %Returining to the beginning of the loop
        end
        for i = 1:6     %loop to check the capacity of a column
            if board(i,y) ~= 0  %Checking if the board row/column already has a value
                x = i - 1;  %assigning the row value right after the next
                if x == 0   %Determines if a column is full because there will be no space left
                    disp('That column is full. ');
                    y = 0;  %reinitializing the y variable for the loop starts again
                    x = 6;  %also reinitialzing the x variable because at the moment it is equal to 0
                    break;  %breaking the for loop and moving on
                end
                break;  %breaking the for loop and moving on
            end
        end
    end

%% This determines whether the first or second player goes, and adds a turn to the total turn counter 
% to end the while loop once it hits 42 turns

    if (rem(turn,2)) == 0   %checking if a turn is an even number
        board(x,y) = player1    %assigning the board coordinates to the player1 value
        turn = turn + 1;    %Adding a turn 
    else 
        board(x,y) = player2    %assigning the board coordinates to the player2 value if the turn is an odd number
        turn = turn + 1;    %Adding 1 to the total turns
    end
    
%% Redrawing the board with pieces in place assigning a color to the piece placed

    if board(x,y) == player1    %Determining if the row/column in this turn is a player1 chip
        ImageBoard(x,y,1) = .8;     %Setting the color of that board piece using the RGB matrix and setting it to red
        ImageBoard(x,y,2) = 0;
        ImageBoard(x,y,3) = 0;
    elseif board(x,y) == player2    %Determining if the row/column in this turn is a player2 chip
        ImageBoard(x,y,1) = 0;  %Setting the color of that board piece using the RGB matrix and setting it to black
        ImageBoard(x,y,2) = 0;
        ImageBoard(x,y,3) = 0;
    end
    imagesc(ImageBoard);    %redrawing the plot
    hold on     %holding the plot
    for i = 1:7     %A for loop much like in the beginning to go to each row/column on the plot/board and place shapes
        for j = 1:6
            a = rectangle('Position', [i-0.5 j-0.5 1 1], 'FaceColor', [0.9, 0.8, 0], 'EdgeColor', [0.9, 0.8, 0]);   %This places a yellow rectangle and removes the imagesc() colors
            x_0 = ImageBoard(j,i,1);    %To bring back the imagesc() colors, the appropriate colors from the RGB matrix are assigned to these values and are placed into the color matrix to be used in the circle function
            y_0 = ImageBoard(j,i,2);
            z_0 = ImageBoard(j,i,3);
            color = [x_0,y_0,z_0];
            c = rectangle('Position', [i-0.5 j-0.5 1 1], 'Curvature', [1 1], 'FaceColor', color);   %Creating a circle using the color matrix with appropraite colors for each row/column on the board
       end
    end


%% This large bit of code checks whether player1 or player2 has won the game by checking the board
% horizontally, diagonally, and vertically.

    for i = 1:6
        for j = 1:7
            %% This checks the board pieces horizontally
            if j < 5    %Setting initial boundaries so that the following if statements don't go out of boundaries
                if board(i,j) == player1 && board(i,j+1) == player1 && board(i,j+2) == player1 && board(i,j+3) == player1
                    disp('Player 1 Wins!')
                    player1 = player1 + 1;  %adding one to the the player 1 value so that it can be used in a later if statement
                    turn = 42;  %Setting the number of turns to 42 to end the beginning while loop
                elseif board(i,j) == player2 && board(i,j+1) == player2 && board(i,j+2) == player2 && board(i,j+3) == player2
                    disp('Player 2 Wins!')
                    player2 = player2 + 1;  %adding one to the the player 2 value so that it can be used in a later if statement
                    turn = 42;  %Setting the number of turns to 42 to end the beginning while loop
                end
            end
            %% This checks the board pieces vertically
            if i < 4    %Setting initial boundaries so that the following if statements don't go out of boundaries
                if board(i,j) == player1 && board(i+1,j) == player1 && board(i+2,j) == player1 && board(i+3,j) == player1
                    disp('Player 1 Wins!')
                    player1 = player1 + 1;  %adding one to the the player 1 value so that it can be used in a later if statement
                    turn = 42;  %Setting the number of turns to 42 to end the beginning while loop
                elseif board(i,j) == player2 && board(i+1,j) == player2 && board(i+2,j) == player2 && board(i+3,j) == player2
                    disp('Player 2 Wins!')
                    player2 = player2 + 1;  %adding one to the the player 2 value so that it can be used in a later if statement
                    turn = 42;  %Setting the number of turns to 42 to end the beginning while loop
                end
            end
            %% This checks the board pieces right-diagonally
            if i > 3 && j < 5   %Setting initial boundaries so that the following if statements don't go out of boundaries
                if board(i,j) == player1 && board(i-1,j+1) == player1 && board(i-2,j+2) == player1 && board(i-3,j+3) == player1
                    disp('Player 1 Wins!')
                    player1 = player1 + 1;  %adding one to the the player 1 value so that it can be used in a later if statement
                    turn = 42;  %Setting the number of turns to 42 to end the beginning while loop
                elseif board(i,j) == player2 && board(i-1,j+1) == player2 && board(i-2,j+2) == player2 && board(i-3,j+3) == player2
                    disp('Player 2 Wins!')
                    player2 = player2 + 1;  %adding one to the the player 2 value so that it can be used in a later if statement
                    turn = 42;  %Setting the number of turns to 42 to end the beginning while loop
                end
            end
            %% This checks the board pieces left-diagonally
            if i > 3 && j > 3   %Setting initial boundaries so that the following if statements don't go out of boundaries
                if board(i,j) == player1 && board(i-1,j-1) == player1 && board(i-2,j-2) == player1 && board(i-3,j-3) == player1
                    disp('Player 1 Wins!')
                    player1 = player1 + 1;  %adding one to the the player 1 value so that it can be used in a later if statement
                    turn = 42;  %Setting the number of turns to 42 to end the beginning while loop
                elseif board(i,j) == player2 && board(i-1,j-1) == player2 && board(i-2,j-2) == player2 && board(i-3,j-3) == player2
                    disp('Player 2 Wins!')
                    player2 = player2 + 1;  %adding one to the the player 2 value so that it can be used in a later if statement
                    turn = 42;  %Setting the number of turns to 42 to end the beginning while loop
                end
            end
        end
    end
end

%% The End Game Output Text

if player1 == 2     %This uses the information determined in the horizontal/vertical/diagonal checker and displays winning or losing text
    text(0.85,3.5, 'Player 1 Wins!','Color',[0 0 0.75],'FontSize',45)
elseif player2 == 3
    text(0.85,3.5, 'Player 2 Wins!','Color',[0 0 0.75],'FontSize',45)
elseif turn == 42
    text(0.85,3.5, 'No One Wins!','Color',[0 0 0.75],'FontSize',45)
end


