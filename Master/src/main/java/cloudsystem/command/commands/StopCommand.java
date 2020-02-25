package cloudsystem.command.commands;

import cloudsystem.Main;
import cloudsystem.command.listener.Command;

import java.sql.SQLException;

public class StopCommand implements Command {

    @Override
    public void execute(String[] args) throws SQLException {
        Main.getSqlManager().close();
        System.exit(0);
    }
}
