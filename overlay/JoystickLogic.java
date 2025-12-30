public class JoystickLogic {

    public void onTouch(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();

        if (e.getAction() == MotionEvent.ACTION_MOVE) {
            if (x < 50) {
                KeyMapper.press(VirtualKey.LEFT);
            } else if (x > 150) {
                KeyMapper.press(VirtualKey.RIGHT);
            }
        }

        if (e.getAction() == MotionEvent.ACTION_UP) {
            KeyMapper.releaseAll();
        }
    }
}
