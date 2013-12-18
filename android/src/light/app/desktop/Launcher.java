package light.app.desktop;

import org.json.JSONArray;
import org.json.JSONException;

import com.baidu.sumeru.nuwa.api.Plugin;
import com.baidu.sumeru.nuwa.api.PluginResult;

public class Launcher extends Plugin {

	@Override
	public PluginResult execute(String action, JSONArray args, String callbackId)
			throws JSONException {
		
		PluginResult result = null;
		
		if ("biubiubiu".equals(action)) {
			result = new PluginResult(PluginResult.Status.OK, "hehehe");
		}
		
		return result;
	}

}
