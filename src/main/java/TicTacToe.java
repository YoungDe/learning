import com.rabbitmq.client.ConnectionFactory;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TicTacToe {

    private static String[][] board = new String[3][3];

    private static boolean hasWinner = false;

    private static boolean isCrossMove = true;

    private static boolean boardFull = false;

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Scanner sc = new Scanner(System.in);
        while (!hasWinner && !boardFull) {
            playerMove(sc);
        }
        if (boardFull) {
            System.out.println("游戏结束,平局");
        } else {
            System.out.println("游戏结束,胜利者是" + (isCrossMove ? "X" : "O"));
        }
    }

    private static void playerMove(Scanner sc) {

        String[] split;
        System.out.println("现在请" + (isCrossMove ? "X" : "O") + "放棋");
        System.out.println("在以下位置放棋");

        String index = sc.nextLine();
        split = index.split(",");
        try {
            int[] moveIndex = { Integer.parseInt(split[0]), Integer.parseInt(split[1]) };
            if (moveIndex[0] < 0 || moveIndex[0] >= 3) {
                System.out.println("非法输入");
                return;
            }
            if (moveIndex[1] < 0 || moveIndex[1] >= 3) {
                System.out.println("非法输入");
                return;
            }

            if (board[moveIndex[0]][moveIndex[1]] != null) {
                System.out.println("棋盘已经有棋子");
                return;
            }

            if (isCrossMove) {
                board[moveIndex[0]][moveIndex[1]] = "X";
                printBoard();
                checkIfEnd(moveIndex[0], moveIndex[1]);
            } else {
                board[moveIndex[0]][moveIndex[1]] = "O";
                printBoard();
                checkIfEnd(moveIndex[0], moveIndex[1]);
            }

        } catch (Exception e) {
            System.out.println("非法输入");
        }

    }

    private static void checkIfEnd(int x, int y) {
        checkCrossWin(x, y);
        checkColumnWin(x, y);
        checkRowWin(x, y);
        if (!hasWinner) {
            isCrossMove = !isCrossMove;
        }

    }

    private static void checkColumnWin(int x, int y) {
        if (x < 1) {
            int cur = x + 1;
            String next = board[cur][y];
            if (board[x][y].equals(next) && board[x][y].equals(board[cur + 1][y])) {
                hasWinner = true;
            }
        } else if (x > 1) {
            int cur = x - 1;
            String next = board[cur][y];
            if (board[x][y].equals(next) && board[x][y].equals(board[cur - 1][y])) {
                hasWinner = true;
            }
        } else {
            if (board[x][y].equals(board[x - 1][y]) && board[x][y].equals(board[x + 1][y])) {
                hasWinner = true;
            }
        }
    }

    private static void checkRowWin(int x, int y) {
        if (y < 1) {
            int cur = y + 1;
            String next = board[x][cur];
            if (board[x][y].equals(next) && board[x][y].equals(board[x][cur + 1])) {
                hasWinner = true;
            }
        } else if (y > 1) {
            int cur = y - 1;
            String next = board[x][cur];
            if (board[x][y].equals(next) && board[x][y].equals(board[x][cur - 1])) {
                hasWinner = true;
            }
        } else {
            if (board[x][y].equals(board[x][y - 1]) && board[x][y].equals(board[x][y + 1])) {
                hasWinner = true;
            }
        }
    }

    private static void checkCrossWin(int x, int y) {
        if (x < 1) {
            if (y < 1) {
                int nextX = x + 1;
                int nextY = y + 1;
                String next = board[nextX][nextY];
                if (board[x][y].equals(next) && board[x][y].equals(board[nextX + 1][nextY + 1])) {
                    hasWinner = true;
                }
            }
            if (y > 1) {
                int nextX = x + 1;
                int nextY = y - 1;
                String next = board[nextX][nextY];
                if (board[x][y].equals(next) && board[x][y].equals(board[nextX + 1][nextY - 1])) {
                    hasWinner = true;
                }
            }
        }

        if (x > 1) {
            if (y < 1) {
                int nextX = x - 1;
                int nextY = y + 1;
                String next = board[nextX][nextY];
                if (board[x][y].equals(next) && board[x][y].equals(board[nextX - 1][nextY + 1])) {
                    hasWinner = true;
                }
            }
            if (y > 1) {
                int nextX = x - 1;
                int nextY = y - 1;
                String next = board[nextX][nextY];
                if (board[x][y].equals(next) && board[x][y].equals(board[nextX - 1][nextY - 1])) {
                    hasWinner = true;
                }
            }
        }

        if (x == 1 && y == 1) {
            if (board[x][y].equals(board[x + 1][y + 1]) && board[x][y].equals(board[x - 1][y - 1])) {
                hasWinner = true;
            }
            if (board[x][y].equals(board[x + 1][y - 1]) && board[x][y].equals(board[x - 1][y + 1])) {
                hasWinner = true;
            }
        }

    }

    private static void printBoard() {
        System.out.println("移动以后棋盘布局如下:");

        boardFull = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null) {
                    boardFull = false;
                }
                System.out.print(board[i][j] == null ? "空\t" : board[i][j] + "\t");
            }
            System.out.println();
        }
    }

}
