package com.studrecord.StudRecord;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DbConfig {

    private static String dbUrl;
    private static String dbUser;
    private static String dbPassword;

    static {
        Dotenv dotenv = Dotenv.load();  // loads .env from project root
        dbUrl = dotenv.get("DB_URL");
        dbUser = dotenv.get("DB_USER");
        dbPassword = dotenv.get("DB_PASSWORD");
    }

    public static String getDbUrl() { return dbUrl; }
    public static String getDbUser() { return dbUser; }
    public static String getDbPassword() { return dbPassword; }
}
