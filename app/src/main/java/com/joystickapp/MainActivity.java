public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_start).setOnClickListener(v -> {
            if (!PermissionUtil.canDrawOverlays(this)) {
                PermissionUtil.requestOverlay(this);
                return;
            }

            startService(new Intent(this, FloatingJoystickService.class));
        });
    }
}
