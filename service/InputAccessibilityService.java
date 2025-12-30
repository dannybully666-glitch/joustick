public class InputAccessibilityService extends AccessibilityService {

    static InputAccessibilityService instance;

    @Override
    protected void onServiceConnected() {
        instance = this;
    }

    public static void sendKey(int keyCode) {
        if (instance == null) return;

        instance.performGlobalAction(keyCode);
    }

    public static void releaseAll() {}
}
