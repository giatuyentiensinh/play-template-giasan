package models;

import play.Application;
import play.GlobalSettings;

public class Global extends GlobalSettings{

	@Override
	public void onStart(Application arg0) {
		super.onStart(arg0);
		ModuleMongo.initClient();
	}
	
	@Override
	public void onStop(Application arg0) {
		super.onStop(arg0);
		ModuleMongo.destroyClient();
	}
}
