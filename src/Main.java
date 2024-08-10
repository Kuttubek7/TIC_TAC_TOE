import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // Получаем имена игроков
        System.out.println("Игрок 1, Как вас зовут?");
        String p1 = in.nextLine();
        System.out.println("Игрок 2, Как вас зовут");
        String p2 = in.nextLine();

        // Создаем поле 3 на 3
        char[][] board = new char[3][3];
        // Заполняем наше поле
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }

        // Для отслеживания за тем, чья сейчас очередь
        boolean isPlayer1 = true;

        // следите за тем, закончилась ли игра
        boolean gameEnded = false;

        while (!gameEnded) {
            // Вызываем метод drawBoard для принта нашей доски
            drawBoard(board);

            char symbol = ' ';
            if (isPlayer1) {
                symbol = 'x';
            } else {
                symbol = 'o';
            }

            // распечатайте ход игроков
            if (isPlayer1) {
                System.out.println("Очередь: " + p1 + "(x)");
            } else {
                System.out.println("Очередь: " + p2 + "(o)");
            }

            // row and col variables
            int row = 0;
            int col = 0;

            while (true) {
                System.out.println("Введите строку (0, 1 или 2):");
                row = in.nextInt();
                System.out.println("Введите столбцы (0, 1 или 2):");
                col = in.nextInt();

                // проверьте, допустимы ли значения row и col
                if (row < 0 || col < 0 || row > 2 || col > 2) {
                    // вход в row и col запрещен
                    System.out.println("Вход в строке или в столбце запрещен");
                } else if (board[row][col] != '-') {
                    System.out.println("кто-то уже сделал свой ход туда");
                } else {
                    // row и col валидный
                    break;
                }
            }

            // установка позиции на игровом поле в соответствии с символом игрока
            board[row][col] = symbol;

            // Проверяем кто выиграл
            if (hasWon(board) == 'x') {
                // pl1 win
                System.out.println(p1 + " Поздравляем, вы выиграли!");
                gameEnded = true;
            } else if (hasWon(board) == 'o') {
                // p2 win
                System.out.println(p2 + " Поздравляем, вы выиграли!");
                gameEnded = true;
            } else {
                // ничья, никто не выиграл
                if (hasTied(board)) {
                    System.out.println("Ничья!");
                    gameEnded = true;
                } else {
                    // игра продолжается
                    isPlayer1 = !isPlayer1;
                }
            }
        }

        // принтуем финальный табоицу
        drawBoard(board);
    }


    // Метод принтует нашу доску
    public static void drawBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public static char hasWon(char[][] board) {
        // строки
        for (int i = 0; i < 3; i++) {
            if(board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
                return board[i][0];
            }
        }

        // столбцы
        for (int i = 0; i < 3; i++) {
            if(board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != '-') {
                return board[0][i];
            }
        }

        // диагонально
        if(board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
            return board[0][0];
        }
        if(board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] != '-') {
            return board[2][0];
        }

        // Никто не выиграл
        return '-';
    }

    // Проверка полноты таблицы
    public static boolean hasTied(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}