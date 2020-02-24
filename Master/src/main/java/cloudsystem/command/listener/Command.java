package cloudsystem.command.listener;

public interface Command {

        void execute(String[] args) throws InterruptedException;
}
