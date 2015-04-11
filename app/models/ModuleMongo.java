package models;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import play.Configuration;
import play.Play;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class ModuleMongo {

	private static MongoClient client = null;

	protected static void initClient() {
		String host = getConfig().getString("mongo.host");
		int port = getConfig().getInt("mongo.port");
		try {
			client = new MongoClient(host, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	protected static void destroyClient() {
		if (client != null)
			client.close();
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
