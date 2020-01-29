% Yousef Al-Shinnawi
% Monday, November 12, 2018
% EGN-5-1812
% Programming and Problem Solving in MATLAB
%
% This is the code for the Second Checkpoint

clear, clc, close all

%% This is where the Board is Built and variables are initialized

board = zeros(6,7)
player1 = 1;
player2 = 2;
turn = 0;

%% This draws the array of numbers to the game board
figure('Name','Gameboard','NumberTitle','off')

ImageBoard = zeros(6,7,3);
ImageBoard(:,:,:) = 1;
imagesc(ImageBoard);
hold on
for i = 1:7
    for j = 1:6
        a = rectangle('Position', [i-0.5 j-0.5 1 1], 'FaceColor', [0.9, 0.8, 0], 'EdgeColor', [0.9, 0.8, 0]);
        b = rectangle('Position', [i-0.5 j-0.5 1 1], 'Curvature', [1 1],'FaceColor', [1,1,1]);
    end
end

%% This is when the players choose their positions on the board

while turn < 42
    x = 6;
    y = 0;
    while (y > 7 || y < 1)
    	y = input('Please enter the column you would like to drop your puck\n');
        if (y > 7 || y < 1)
                disp('Please enter a correct value for the 6x7 grid')
                continue;
        end
        for i = 1:6
            if board(i,y) ~= 0
                x = i - 1;
                if x == 0
                    disp('That column is full. ');
                    y = 0;
                    x = 6;
                    break;
                end
                break;
            end
        end
    end
 %% This simulates the character's puck being sent to the bottom of the column just as it is in connect 4
 % It also makes sure that a correct value is inserted, if not it repeats

%% This determines whether the first or second player goes, and adds a turn to the total turn counter 
% to end the while loop once it hits 42 turns

    if (rem(turn,2)) == 0
        board(x,y) = player1
        turn = turn + 1;
    else 
        board(x,y) = player2
        turn = turn + 1;
    end
    
%% Redrawing the board with pieces in place  

if board(x,y) == player1
    ImageBoard(x,y,1) = .8;
    ImageBoard(x,y,2) = 0;
    ImageBoard(x,y,3) = 0;
elseif board(x,y) == player2
    ImageBoard(x,y,1) = 0;
    ImageBoard(x,y,2) = 0;
    ImageBoard(x,y,3) = 0;
end
imagesc(ImageBoard);
hold on
for i = 1:7
    for j = 1:6
        a = rectangle('Position', [i-0.5 j-0.5 1 1], 'FaceColor', [0.9, 0.8, 0], 'EdgeColor', [0.9, 0.8, 0]);
        x_0 = ImageBoard(j,i,1);
        y_0 = ImageBoard(j,i,2);
        z_0 = ImageBoard(j,i,3);
        color = [x_0,y_0,z_0];
        c = rectangle('Position', [i-0.5 j-0.5 1 1], 'Curvature', [1 1], 'FaceColor', color);
    end
end


%% This large bit of code checks whether player1 or player2 has won the game by checking the board
% horizontally, diagonally, and vertically.

    for i = 1:6
        for j = 1:7
            if j < 5
                if board(i,j) == player1 && board(i,j+1) == player1 && board(i,j+2) == player1 && board(i,j+3) == player1
                    disp('Player 1 Wins!')
                    player1 = player1 + 1;
                    turn = 42;
                elseif board(i,j) == player2 && board(i,j+1) == player2 && board(i,j+2) == player2 && board(i,j+3) == player2
                    disp('Player 2 Wins!')
                    player2 = player2 + 1;
                    turn = 42;
                end
            end
            if i < 4
                if board(i,j) == player1 && board(i+1,j) == player1 && board(i+2,j) == player1 && board(i+3,j) == player1
                    disp('Player 1 Wins!')
                    player1 = player1 + 1;
                    turn = 42;
                elseif board(i,j) == player2 && board(i+1,j) == player2 && board(i+2,j) == player2 && board(i+3,j) == player2
                    disp('Player 2 Wins!')
                    player2 = player2 + 1;
                    turn = 42;
                end
            end
            if i > 3 && j < 5
                if board(i,j) == player1 && board(i-1,j+1) == player1 && board(i-2,j+2) == player1 && board(i-3,j+3) == player1
                    disp('Player 1 Wins!')
                    player1 = player1 + 1;
                    turn = 42;
                elseif board(i,j) == player2 && board(i-1,j+1) == player2 && board(i-2,j+2) == player2 && board(i-3,j+3) == player2
                    disp('Player 2 Wins!')
                    player2 = player2 + 1;
                    turn = 42;
                end
            end
            if i > 3 && j > 3
                if board(i,j) == player1 && board(i-1,j-1) == player1 && board(i-2,j-2) == player1 && board(i-3,j-3) == player1
                    disp('Player 1 Wins!')
                    player1 = player1 + 1;
                    turn = 42;
                elseif board(i,j) == player2 && board(i-1,j-1) == player2 && board(i-2,j-2) == player2 && board(i-3,j-3) == player2
                    disp('Player 2 Wins!')
                    player2 = player2 + 1;
                    turn = 42;
                end
            end
        end
    end
end

%% The Winning Output Text

if player1 == 2
    text(0.85,3.5, 'Player 1 Wins!','Color',[0 0 0.75],'FontSize',45)
elseif player2 == 3
    text(0.85,3.5, 'Player 2 Wins!','Color',[0 0 0.75],'FontSize',45)
elseif turn == 42
    text(0.85,3.5, 'No One Wins!','Color',[0 0 0.75],'FontSize',45)
end


