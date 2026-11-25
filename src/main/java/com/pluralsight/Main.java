package com.pluralsight;

import java.util.*;
import java.util.stream.Collectors;

        public class BedFrameShop {

            // Product fields:
            // name, material, size, price, stock

            private static final List<Map<String, Object>> bedFrames =
                    List.of(
                            Map.<String, Object>of(
                                    "name", "Nordic Dream",
                                    "material", "Wood",
                                    "size", "King",
                                    "price", 499.99,
                                    "stock", 10
                            ),
                            Map.<String, Object>of(
                                    "name", "Urban Steel",
                                    "material", "Metal",
                                    "size", "Queen",
                                    "price", 299.99,
                                    "stock", 5
                            ),
                            Map.<String, Object>of(
                                    "name", "Royal Comfort",
                                    "material", "Wood",
                                    "size", "King",
                                    "price", 799.99,
                                    "stock", 3
                            ),
                            Map.<String, Object>of(
                                    "name", "Minimal Sleep",
                                    "material", "Metal",
                                    "size", "Single",
                                    "price", 199.99,
                                    "stock", 0
                            ),
                            Map.<String, Object>of(
                                    "name", "Classic Oak",
                                    "material", "Wood",
                                    "size", "Queen",
                                    "price", 399.99,
                                    "stock", 8
                            )
                    );

            public static void main(String[] args) {

                Scanner scanner = new Scanner(System.in);
                int choice;

                do {
                    System.out.println("\n================================");
                    System.out.println("       BEDFRAME BOUTIQUE");
                    System.out.println("================================");
                    System.out.println("1. Find wooden bedframes");
                    System.out.println("2. Find bedframes under €500");
                    System.out.println("3. Count available bedframes");
                    System.out.println("4. Display all products");
                    System.out.println("5. Sort bedframes by price");
                    System.out.println("6. Display product names only");
                    System.out.println("7. Calculate total inventory value");
                    System.out.println("8. Find the most expensive bedframe");
                    System.out.println("9. Search bedframes by size");
                    System.out.println("10. Find bedframes with low stock");
                    System.out.println("0. Exit");
                    System.out.println("================================");
                    System.out.print("Choose an option: ");

                    choice = scanner.nextInt();

                    switch (choice) {
                        case 1 -> findWoodenBedFrames();
                        case 2 -> findAffordableBedFrames();
                        case 3 -> countAvailableBedFrames();
                        case 4 -> displayAllBedFrames();
                        case 5 -> sortBedFramesByPrice();
                        case 6 -> displayProductNames();
                        case 7 -> calculateInventoryValue();
                        case 8 -> findMostExpensiveBedFrame();
                        case 9 -> searchBySize(scanner);
                        case 10 -> findLowStockBedFrames();
                        case 0 -> System.out.println("Thank you for visiting!");
                        default -> System.out.println("Invalid option!");
                    }

                } while (choice != 0);

                scanner.close();
            }

            // Helper methods to access product information

            private static String name(Map<String, Object> product) {
                return (String) product.get("name");
            }

            private static String material(Map<String, Object> product) {
                return (String) product.get("material");
            }

            private static String size(Map<String, Object> product) {
                return (String) product.get("size");
            }

            private static double price(Map<String, Object> product) {
                return (double) product.get("price");
            }

            private static int stock(Map<String, Object> product) {
                return (int) product.get("stock");
            }

            private static void printProduct(Map<String, Object> product) {
                System.out.printf(
                        "%-20s | %-6s | %-6s | €%.2f | Stock: %d%n",
                        name(product),
                        material(product),
                        size(product),
                        price(product),
                        stock(product)
                );
            }

            // EXAMPLE 1: FILTER
            // Find all wooden bedframes

            private static void findWoodenBedFrames() {

                System.out.println("\n--- Wooden Bedframes ---");

                bedFrames.stream()
                        .filter(product -> material(product).equals("Wood"))
                        .forEach(BedFrameShop::printProduct);
            }

            // EXAMPLE 2: FILTER
            // Find bedframes costing less than €500

            private static void findAffordableBedFrames() {

                System.out.println("\n--- Bedframes Under €500 ---");

                bedFrames.stream()
                        .filter(product -> price(product) < 500)
                        .forEach(BedFrameShop::printProduct);
            }

            // EXAMPLE 3: COUNT
            // Count products that are in stock

            private static void countAvailableBedFrames() {

                long availableProducts = bedFrames.stream()
                        .filter(product -> stock(product) > 0)
                        .count();

                System.out.println("\nAvailable products: " + availableProducts);
            }

            // EXAMPLE 4: FOREACH
            // Display every product

            private static void displayAllBedFrames() {

                System.out.println("\n--- All Bedframes ---");

                bedFrames.stream()
                        .forEach(BedFrameShop::printProduct);
            }

            // EXAMPLE 5: SORTED
            // Sort products from cheapest to most expensive

            private static void sortBedFramesByPrice() {

                System.out.println("\n--- Bedframes Sorted by Price ---");

                bedFrames.stream()
                        .sorted(Comparator.comparingDouble(
                                product -> price(product)
                        ))
                        .forEach(BedFrameShop::printProduct);
            }

            // EXAMPLE 6: MAP
            // Extract only the product names

            private static void displayProductNames() {

                System.out.println("\n--- Product Names ---");

                List<String> productNames = bedFrames.stream()
                        .map(product -> name(product))
                        .collect(Collectors.toList());

                productNames.forEach(System.out::println);
            }

            // EXAMPLE 7: REDUCE
            // Calculate the total value of all inventory

            private static void calculateInventoryValue() {

                double totalValue = bedFrames.stream()
                        .map(product -> price(product) * stock(product))
                        .reduce(0.0, Double::sum);

                System.out.printf(
                        "%nTotal inventory value: €%.2f%n",
                        totalValue
                );
            }

            // EXAMPLE 8: MAX
            // Find the most expensive bedframe

            private static void findMostExpensiveBedFrame() {

                System.out.println("\n--- Most Expensive Bedframe ---");

                bedFrames.stream()
                        .max(Comparator.comparingDouble(
                                product -> price(product)
                        ))
                        .ifPresent(BedFrameShop::printProduct);
            }

            // EXAMPLE 9: FILTER
            // Search for bedframes by size

            private static void searchBySize(Scanner scanner) {

                System.out.print("\nEnter size (Single, Queen, King): ");
                String requestedSize = scanner.next();

                System.out.println("\n--- Search Results ---");

                bedFrames.stream()
                        .filter(product ->
                                size(product).equalsIgnoreCase(requestedSize)
                        )
                        .forEach(BedFrameShop::printProduct);
            }

            // EXAMPLE 10: FILTER + SORTED
            // Find products with fewer than 5 items in stock

            private static void findLowStockBedFrames() {

                System.out.println("\n--- Low Stock Products ---");

                bedFrames.stream()
                        .filter(product -> stock(product) < 5)
                        .sorted(Comparator.comparingInt(
                                product -> stock(product)
                        ))
                        .forEach(BedFrameShop::printProduct);
            }
        }

