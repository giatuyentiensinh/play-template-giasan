


import models.ModuleMongo;
import play.Application;
import play.GlobalSettings;

public class Global extends GlobalSettings{

	@Override
	public void onStart(Application app) {
		super.onStart(app);
		ModuleMongo.connect();
	}
	
	@Override
	public void onStop(Application app) {
		super.onStop(app);
		ModuleMongo.disconnect();
	}
}
