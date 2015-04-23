package models;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import play.Configuration;
import play.Logger;
import play.Play;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class ConnectMongo {

	private static MongoClient client = null;

	public static void connect() {
		String host = getConfig().getString("mongo.host");
		int port = getConfig().getInt("mongo.port");
		try {
			client = new MongoClient(host, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			Logger.error("Mongodb not connection");
		}
	}

	public static void disconnect() {
		if (client != null)
			client.close();
	}

	protected static DBCollection getCollection(String dbname, String colname) {
		DB db = client.getDB(dbname);
		return db.getCollection(colname);
	}
	
	
	public static List<DBObject> getDocument(String dbname, String colname) {
		DBCollection collection = getCollection(dbname, colname);

		List<DBObject> array = collection.find().toArray();
		return array;		
	}

	public static Map<String, Set<String>> getCollections() {
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();

		List<String> list = client.getDatabaseNames();
		list.forEach(dbname -> {
			DB db = client.getDB(dbname);
			Set<String> collections = db.getCollectionNames();
			map.put(dbname, collections);
		});

		return map;
	}

	public static Configuration getConfig() {
		return Play.application().configuration();
	}

}
