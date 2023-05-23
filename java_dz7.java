import java.util.*;

public class java_dz7 {
    private String model;
    private int ram;
    private int storage;
    private String operatingSystem;
    private String color;

    public java_dz7(String model, int ram, int storage, String operatingSystem, String color) {
        this.model = model;
        this.ram = ram;
        this.storage = storage;
        this.operatingSystem = operatingSystem;
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getColor() {
        return color;
    }

    public static void main(String[] args) {
        Set<java_dz7> notebooks = new HashSet<>();
        notebooks.add(new java_dz7("Model 1", 8, 256, "Windows", "Silver"));
        notebooks.add(new java_dz7("Model 2", 16, 512, "MacOS", "Space Gray"));
        notebooks.add(new java_dz7("Model 3", 16, 512, "Windows", "Black"));
        notebooks.add(new java_dz7("Model 4", 8, 256, "MacOS", "Silver"));

        filterNotebooks(notebooks);
    }

    public static void filterNotebooks(Set<java_dz7> notebooks) {
        Map<Integer, Object> filters = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите количество критериев фильтрации (максимум 3): ");
        int numCriteria = scanner.nextInt();

        if (numCriteria < 1 || numCriteria > 3) {
            System.out.println("Некорректное количество критериев.");
            return;
        }

        for (int i = 0; i < numCriteria; i++) {
            System.out.println("Выберите критерий фильтрации:");
            System.out.println("1 - ОЗУ");
            System.out.println("2 - Объем ЖД");
            System.out.println("3 - Операционная система");
            System.out.println("4 - Цвет");

            System.out.print("Введите номер критерия: ");
            int criteria = scanner.nextInt();

            System.out.print("Введите минимальное значение для выбранного критерия: ");
            Object value;
            if (criteria == 1 || criteria == 2) {
                value = scanner.nextInt();
            } else {
                scanner.nextLine(); // Сбросить символ новой строки после ввода числа
                value = scanner.nextLine();
            }

            filters.put(criteria, value);
        }

        for (java_dz7 notebook : notebooks) {
            int matchCount = 0;

            for (Map.Entry<Integer, Object> filter : filters.entrySet()) {
                int criteria = filter.getKey();
                Object value = filter.getValue();

                switch (criteria) {
                    case 1:
                        if (notebook.getRam() >= (int) value) {
                            matchCount++;
                        }
                        break;
                    case 2:
                        if (notebook.getStorage() >= (int) value) {
                            matchCount++;
                        }
                        break;
                    case 3:
                        if (notebook.getOperatingSystem().equals(value)) {
                            matchCount++;
                        }
                        break;
                    case 4:
                        if (notebook.getColor().equals(value)) {
                            matchCount++;
                        }
                        break;
                    default:
                        System.out.println("Некорректный критерий фильтрации: " + criteria);
                        return;
                }
            }

            if (matchCount == filters.size()) {
                System.out.println("Подходящий ноутбук: " + notebook.getModel());
            }
        }
    }
}

// Я здесь ради Python, ваша джава разбивает моё сердечко (Т_Т)