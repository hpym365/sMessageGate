package com.senyint.handler;

import java.util.Map;

import com.senyint.entity.Config;
import com.senyint.entity.DataStore;

public class BaseHandler implements Handler {

	public Config getConfig(DataStore dataStore) {
		Config config = (Config) dataStore.get(this.toString());
		return config;
	}

	@Override
	public void execute(DataStore dataStore) {
		// TODO Auto-generated method stub
	}

}
