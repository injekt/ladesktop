package light.app.desktop;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Environment;

public class NativeAppInfoList {

	class NativeAppInfo {
		public static final String APP_NAME = "appName";
		public static final String PACKAGE_NAME ="packageName";
		public static final String ICON_PATH ="iconPath";

		public String mAppName;
		public String mPackageName;
		public String mIconPath;

		public NativeAppInfo(String appName, String packageName, String iconPath) {
			mAppName = appName;
			mPackageName = packageName;
			mIconPath = iconPath;
		}

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

	private static final String ICON_PATH = Environment.getExternalStorageDirectory().toString()
			+ "/zatzIcons";
	private ArrayList<NativeAppInfo> mList = null;

	public NativeAppInfoList(Context ctx) {
	    File fileParent = new File(ICON_PATH);
	    if (!fileParent.exists()) {
	 	    fileParent.mkdirs();
	    }

	    getAllAppInfo(ctx);
	}

	private void getAllAppInfo(Context ctx) {
		PackageManager pm = ctx.getPackageManager();
		List<PackageInfo> packages = pm.getInstalledPackages(0);

		for (int i = 0; i < packages.size(); i++) {
			PackageInfo packageInfo = packages.get(i);

			// Only display the non-system application info
			if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
				String label = packageInfo.applicationInfo.loadLabel(pm).toString();
				String packageName = packageInfo.packageName;
				String iconPath = ICON_PATH + "/" + label + ".png";
				BitmapToPng(drawableToBitmap(packageInfo.applicationInfo.loadIcon(pm)), iconPath);

				mList.add(new NativeAppInfo(label, packageName, iconPath));
			}
		}
	}

	public Bitmap drawableToBitmap(Drawable drawable) {

        Bitmap bitmap = Bitmap.createBitmap(
                                drawable.getIntrinsicWidth(),
                                drawable.getIntrinsicHeight(),
                                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                		: Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
	}

	private void BitmapToPng(Bitmap bitmap, String filePath) {
		try {
			   File file = new File(filePath);
		       FileOutputStream out = new FileOutputStream(file);
		       bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
		} catch (Exception e) {
		       e.printStackTrace();
		}
	}

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
