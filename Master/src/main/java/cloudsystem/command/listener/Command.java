package cloudsystem.command.listener;

import java.sql.SQLException;

public interface Command {

    void execute(String[] args) throws InterruptedException, SQLException;
}
