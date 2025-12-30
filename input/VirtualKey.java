public enum VirtualKey {
    LEFT(KeyEvent.KEYCODE_DPAD_LEFT),
    RIGHT(KeyEvent.KEYCODE_DPAD_RIGHT);

    public final int keyCode;

    VirtualKey(int code) {
        keyCode = code;
    }
}
