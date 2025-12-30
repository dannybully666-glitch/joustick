public class FloatingJoystickService extends Service {

    private WindowManager windowManager;
    private FloatingJoystickView joystickView;

    @Override
    public void onCreate() {
        super.onCreate();

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        joystickView = new FloatingJoystickView(this);

        WindowManager.LayoutParams params =
            new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
            );

        params.gravity = Gravity.CENTER;
        windowManager.addView(joystickView, params);
    }

    @Override
    public void onDestroy() {
        if (joystickView != null) {
            windowManager.removeView(joystickView);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
