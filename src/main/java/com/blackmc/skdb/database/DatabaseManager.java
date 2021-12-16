package com.blackmc.skdb.database;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DatabaseManager {

    private static ArrayList<Class<?>> annotatedClasses = new ArrayList<Class<?>>();

    public static Session database;

    public static boolean registerDatabaseConnection(DatabaseConfiguration config) {
        
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
            .applySetting("hibernate.connection.url", config.getUrl())
            .applySetting("hibernate.connection.username", config.getUsername())
            .applySetting("hibernate.connection.password", config.getPassword())
            .applySetting("hibernate.connection.driver_class", config.getDriver())
            .applySetting("hibernate.dialect", config.getDialect())
            .build();

        MetadataSources sources = new MetadataSources(standardRegistry);
        for(Class<?> clazz : annotatedClasses) {
            sources.addAnnotatedClass(clazz);
        }
        Metadata metadata = sources.buildMetadata();

        SessionFactory sessionFactory = metadata.buildSessionFactory();
        Session session = sessionFactory.openSession();

        database = session;

        return session != null;

    }

    public static void registerClass(Class<?> clazz) {
        if(!annotatedClasses.contains(clazz)) annotatedClasses.add(clazz);
    }

}