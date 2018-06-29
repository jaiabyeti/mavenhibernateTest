package org.TestMavenProject.example;


import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class hibernateConnection {

	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory(){
		if(sessionFactory == null){
			try{
	            Configuration configuration = new Configuration().configure();
	            sessionFactory = configuration.buildSessionFactory();
				return sessionFactory;
			}
			catch(Exception e){
				System.out.println("Error while creating session factory : " + e);
			}
		}
		return sessionFactory;
	}
	
	public static void closeConnection(){
		getSessionFactory().close();
	}
	
}
