package main.java;

import main.java.storage.DatabaseManager;

public final class TurtleMD {

    private static DatabaseManager dbManager;
    private static TurtleMD instance;

    public TurtleMD(){
        dbManager = new DatabaseManager();
        instance = this;
    }

    public static DatabaseManager getDbManager(){
        return dbManager;
    }

    public static TurtleMD getMain(){
        return instance;
    }
}
