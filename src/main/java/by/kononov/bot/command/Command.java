package by.kononov.bot.command;

@FunctionalInterface
public interface Command{
    String execute(String name, String info);
}
