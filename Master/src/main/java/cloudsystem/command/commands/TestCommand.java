package cloudsystem.command.commands;


import cloudsystem.command.listener.Command;

public class TestCommand implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            for (String arg : args) {
                System.out.println(arg);
            }
        } else
            System.out.println("you need to type in more args then one.");
    }
}
