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

		if ("getNativeAppList".equals(action)) {
			result = getNativeAppList();
		}

		return result;
	}

	private PluginResult getNativeAppList() {
		NativeAppInfoList list = new NativeAppInfoList(this.nuwa.getContext());
		return new PluginResult(PluginResult.Status.OK, list.toString());
	}

}
