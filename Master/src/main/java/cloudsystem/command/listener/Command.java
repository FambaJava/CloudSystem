package cloudsystem.command.listener;


import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;

public interface Command {

    void execute(String[] args) throws SQLException, IOException, ClassNotFoundException, JSONException, InterruptedException;
}
