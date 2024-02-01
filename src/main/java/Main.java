import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int earnings = 0; // доходы
    static int expenses = 0; // расходы
    static int operation; // выбор операции
    static int money; // сложение введёных чисел
    static int result; // итоговый расчёт
    static int compare; // сравнение налога
    static String moneyStr; // конвертация ввода из текста в число
    static String recommendation; // рекомендуемая система

    public static void main(String[] args) {
        dialogueWithUser();
    }

    // Система УСН доходы (7%)
    public static int taxEarnings(int earnings) {
        return (earnings * 7) / 100;
    }

    // Система УСН доходы - расходы (15%)
    public static int taxEarningsMinusExpenses(int earnings, int expenses) {
        int tax = (earnings - expenses) * 15 / 100;
        return Math.max(tax, 0);
    }

    public static void dialogueWithUser() {
        while (true) {
            System.out.println("\n" + """
                    Выберите операцию и введите её номер:
                    1. Добавить новый доход
                    2. добавить новый расход
                    3. Выбрать систему налогообложения
                    Для завершения введите end""");

            String input = scanner.nextLine();
            if ("end".equals(input)) {
                System.out.println("Программа завершена!");
                break;
            }
            operation = Integer.parseInt(input);

            switch (operation) {
                case 1:
                    System.out.println("\n" + "Введите сумму дохода:");
                    moneyStr = scanner.nextLine();
                    money = Integer.parseInt(moneyStr);
                    earnings += money;
                    break;
                case 2:
                    System.out.println("\n" + "Введите сумму расхода:");
                    moneyStr = scanner.nextLine();
                    money = Integer.parseInt(moneyStr);
                    expenses += money;
                    break;
                case 3:
                    if (taxEarnings(earnings) < taxEarningsMinusExpenses(earnings, expenses)) {
                        recommendation = "УСН доходы";
                        result = taxEarnings(earnings);
                        compare = taxEarningsMinusExpenses(earnings, expenses);
                    } else {
                        recommendation = "УСН доходы минус расходы";
                        result = taxEarningsMinusExpenses(earnings, expenses);
                        compare = taxEarnings(earnings);
                    }
                    System.out.println("\n" + "Мы советуем вам: " + recommendation + "\n" +
                            "Ваш налог составит: " + result + " рублей" + "\n" +
                            "Налог на другой системе: " + compare + " рублей" + "\n" +
                            "Экономия: " + (compare - result) + " рублей");
                    break;
            }
        }
    }
}