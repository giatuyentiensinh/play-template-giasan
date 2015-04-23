


import models.ConnectMongo;
import play.Application;
import play.GlobalSettings;

public class Global extends GlobalSettings{

	@Override
	public void onStart(Application app) {
		super.onStart(app);
		ConnectMongo.connect();
	}
	
	@Override
	public void onStop(Application app) {
		super.onStop(app);
		ConnectMongo.disconnect();
	}
}
