package cloudsystem.command.commands;

import cloudsystem.Main;
import cloudsystem.command.listener.Command;

import java.sql.SQLException;

public class StopCommand implements Command {

    @Override
    public void execute(String[] args) throws SQLException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Main.getCloudClassLoader().stopAllPlugins();
        Main.getSqlManager().close();
        System.exit(0);
    }
}
