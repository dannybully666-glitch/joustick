public class KeyMapper {

    public static void press(VirtualKey key) {
        InputAccessibilityService.sendKey(key.keyCode);
    }

    public static void releaseAll() {
        InputAccessibilityService.releaseAll();
    }
}
