package cloudsystem.datas;


import cloudsystem.Main;

public class AdminKeyHandler {

    private static String adminKey;

    public static String getAdminKey() throws InterruptedException {
        if (adminKey == null) {
            Main.getConnectionManager().wakeTSUpAndRenewTheAdminKey();
            Thread.sleep(1000);
        }
        return adminKey;
    }

    public static void setAdminKey(String adminKey) {
        AdminKeyHandler.adminKey = adminKey;
    }
}
