package light.app.desktop;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class NativeAppInfoList {
	
	class NativeAppInfo {
		public static final String APP_NAME = "appName";
		public static final String PACKAGE_NAME ="packageName";
		public static final String ICON_PATH ="iconPath";
		
	
		public String mAppName;
		public String mPackageName;
		public String mIconPath;
		
		
		@Override
		public String toString() {
			try {
				JSONObject json = new JSONObject();
				json.put(APP_NAME, this.mAppName);
				json.put(PACKAGE_NAME, this.mAppName);
				json.put(ICON_PATH, this.mAppName);
				
				return json.toString();
			}
			catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	
	public ArrayList<NativeAppInfo> mList = null;
	
	
	@Override
	public String toString() {
		try {
			JSONArray json = new JSONArray();
			for (int i = 0; i < mList.size(); i++) {
				json.put(mList.get(i).toString());
			}
			
			return json.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
