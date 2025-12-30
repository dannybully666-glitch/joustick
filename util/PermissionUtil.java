public class PermissionUtil {

    public static boolean canDrawOverlays(Context c) {
        return Settings.canDrawOverlays(c);
    }

    public static void requestOverlay(Activity a) {
        Intent i = new Intent(
            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
            Uri.parse("package:" + a.getPackageName())
        );
        a.startActivity(i);
    }
}
