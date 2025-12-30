public class FloatingJoystickView extends FrameLayout {

    private JoystickLogic logic = new JoystickLogic();

    public FloatingJoystickView(Context context) {
        super(context);
        inflate(context, R.layout.overlay_joystick, this);

        setOnTouchListener((v, event) -> {
            logic.onTouch(event);
            return true;
        });
    }
}
