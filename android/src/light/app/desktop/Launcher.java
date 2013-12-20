package light.app.desktop;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.Intent;

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
		else if ("startApp".equals(action)) {
			String packageName = args.getString(0);
			startApp(packageName, this.nuwa.getContext());
			result = new PluginResult(PluginResult.Status.NO_RESULT);
		}

		return result;
	}

	public PluginResult getNativeAppList() {
		NativeAppInfoList list = new NativeAppInfoList(this.nuwa.getContext());
		return new PluginResult(PluginResult.Status.OK, list.toString());
	}

	public void startApp(String packageName, Context context) {
		Intent intent =  context.getPackageManager().getLaunchIntentForPackage(packageName);
	    context.startActivity(intent);
	}

}
