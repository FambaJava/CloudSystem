package cloudsystem.api;

import cloudsystem.api.plugin.CloudPlugin;

import java.io.File;

public class CloudClassLoader {

    private ClassLoader classLoader;

    private Class<CloudPlugin> clazz;

    private CloudPlugin cloudPlugin;

    private File pluginPath;

    public CloudClassLoader() {
        this.classLoader = ClassLoader.getSystemClassLoader();
    }


    private void load(String clzzName) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        clazz = (Class<CloudPlugin>) classLoader.loadClass(clzzName);
        clazz.cast(CloudPlugin.class);
        clazz.getMethod("onEnable", CloudPlugin.class);
        clazz.newInstance().onEnable();
    }

    private void stop(String clzzName) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        clazz = (Class<CloudPlugin>) classLoader.loadClass(clzzName);
        clazz.cast(CloudPlugin.class);
        clazz.getMethod("onDisable", CloudPlugin.class);
        clazz.newInstance().onDisable();
    }

    public void loadAllClazzes() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        pluginPath = new File("Master/plugins");
        if(!pluginPath.exists())
            pluginPath.mkdir();

        File[] plugins = pluginPath.listFiles();
        if(plugins.length == 0){
            System.out.println("0 plugins loaded.");
            return;
        }
        for (int i = 0; i < plugins.length; i++) {
            load(plugins[i].getAbsolutePath());
            System.out.println(plugins[i].getName() + " is loaded.");
        }
        System.out.println(plugins.length + " plugins loaded.");
    }


    public void stopAllPlugins() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        pluginPath = new File("Master/plugins");

        File[] plugins = pluginPath.listFiles();
        assert plugins != null;
        if(plugins.length == 0){
            System.out.println("0 plugins disabled.");
            return;
        }
        for (File plugin : plugins) {
            stop(plugin.getAbsolutePath());
            System.out.println(plugin.getName() + " is disabled.");
        }
        System.out.println(plugins.length + " plugins disabled.");
    }
    /*public CloudClassLoader() throws MalformedURLException {
        this.classLoader = new URLClassLoader.newInstance(new URL[]{new URL(pluginPath.getAbsolutePath())});
    }


    private void load(String clzzName) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?> cls = classLoader.loadClass(clzzName);
        Method method = cls.getDeclaredMethod("onEnable");
        Object instance = cls.newInstance();

    }

    public void loadAllClazzes() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        pluginPath = new File("Master/plugins");
        if (!pluginPath.exists())
            pluginPath.mkdir();

        File[] plugins = pluginPath.listFiles();
        for (int i = 0; i < plugins.length; i++) {
           load(plugins[i].getAbsolutePath());
        }
    }*/
}
