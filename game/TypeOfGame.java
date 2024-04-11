package game;

import java.util.Scanner;

public abstract class TypeOfGame {
    protected static String settingShape(Scanner in) {
        String shape;
        while (true) {
            System.out.println("Select board shape: Circle - \"1\" or Rectangle - \"2\"");
            shape = in.next();
            if (shape.equals("1") || shape.equals("2")) {
                return shape;
            }
            System.out.println("incorrect answer");
        }
    }

    protected static Type settingType(Scanner in, String shape) {
        Type type;
        while (true) {
            if (shape.equals("1")) {
                int diameter = 0;
                while (diameter < 2) {
                    System.out.println("Select diameter");
                    try {
                        diameter = Integer.parseInt(in.next());
                    } catch (NumberFormatException e) {
                        System.out.println("This is not a number");
                    }
                }
                int k = 0;
                while (k < 2 || k >= diameter) {
                    try {
                        System.out.println("Select number of elements in a row to win");
                        k = Integer.parseInt(in.next());
                    } catch (NumberFormatException e) {
                        System.out.println("This is not a number");
                    }
                }
                type = new Type(diameter, k);
                break;
            } else if (shape.equals("2")) {
                int m = 0, n = 0, k = 0;
                while ((k < 1 || k > m && k > n)) {
                    try {
                        System.out.println("Select m, n, k");
                        m = Integer.parseInt(in.next());
                        n = Integer.parseInt(in.next());
                        k = Integer.parseInt(in.next());
                    } catch (NumberFormatException e) {
                        System.out.println("This is not a numbers");
                    }
                }
                type = new Type(m, n, k);
                break;
            }
            System.out.println("incorrect answer");
        }
        return type;
    }
}
