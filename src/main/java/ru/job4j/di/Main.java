package ru.job4j.di;

public class Main {

    public static void main(String[] args) {

        Context context = new Context();
        context.register(Store.class);
        context.register(ConsoleInput.class);
        context.register(StartUI.class);

        StartUI ui = context.get(StartUI.class);
        ui.add("Ivan Ivanov");
        ui.print();
    }
}
