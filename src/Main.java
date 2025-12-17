import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Point> points = new ArrayList<>();

        try {
            File file = new File("src/points.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                double x = scanner.nextDouble();
                double y = scanner.nextDouble();
                points.add(new Point(x, y));
            }
            scanner.close();

        } catch (Exception e) {
            System.out.println("File error: " + e.getMessage());
            return;
        }

        Shape shape = new Shape(points);

        System.out.println("Perimeter: " + shape.getPerimeter());
        System.out.println("Longest side: " + shape.getLongestSide());
        System.out.println("Average side: " + shape.getAverageSide());
    }
}
