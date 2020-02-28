package cloudsystem.command.commands;

import cloudsystem.Main;
import cloudsystem.command.listener.Command;
import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;


public class RestartCommand implements Command {

    @Override
    public void execute(String[] args) throws SQLException, IOException, ClassNotFoundException, JSONException, InterruptedException {
        System.out.println("Restart the cloud...");
        Main.stop();
        System.out.println("Loading Library's...");
        //START THE CLOUD
        Main.start();
        System.out.println("Cloud is successfully restarted.");
    }
}
