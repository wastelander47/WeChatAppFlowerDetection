package flower.flower;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class CameraActivity extends Activity implements OnClickListener {
    private CameraActivity mCamera;
    private SurfaceView mSurfaceView;
    private SurfaceHolder mHolder;
    private CameraCallback mCallback;
    private Button mTakePicButton;
    private Button mSwitchButton;
    private Button mflashButton;

    private boolean saved = true;
    private boolean isFrontCamera;

    public static final int MESSAGE_SVAE_SUCCESS = 0;
    public static final int MESSAGE_SVAE_FAILURE = 1;

    private final int FLASH_MODE_AUTO = 0;
    private final int FLASH_MODE_ON = 1;
    private final int FLASH_MODE_OFF = 2;
    private int mFlashMode = 0;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            saved = true;
            switch (msg.what) {
            case MESSAGE_SVAE_SUCCESS:
                Toast.makeText(CameraActivity.this, "保存成功", Toast.LENGTH_SHORT)
                        .show();
                break;
            case MESSAGE_SVAE_FAILURE:
                Toast.makeText(CameraActivity.this, "保存失败", Toast.LENGTH_SHORT)
                        .show();
                break;
            }
        };
    };
    private SeekBar mZoomBar;
    private View mZoomLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initView();

    }

    private void initView() {

        mTakePicButton = (Button) findViewById(R.id.camera_take_btn);
        mTakePicButton.setOnClickListener(this);
        mSwitchButton = (Button) findViewById(R.id.camera_switch_btn);
        mSwitchButton.setOnClickListener(this);
        mflashButton = (Button) findViewById(R.id.flashMode);
        mflashButton.setOnClickListener(this);

        mZoomLayout = findViewById(R.id.zoomLayout);
        mZoomBar = (SeekBar) findViewById(R.id.seekBar1);
        mZoomBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                    boolean fromUser) {
                mCallback.setZoom(progress);
            }
        });

        initSurfaceView();
    }

    private void initSurfaceView() {
        mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView1);
        mHolder = mSurfaceView.getHolder();
        mCallback = new CameraCallback(this);
        mHolder.addCallback(mCallback);
        mSurfaceView.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP
                        && !isFrontCamera) {// 前置摄像头取消触摸自动聚焦功能
                    View view = findViewById(R.id.RelativeLayout1);
                    mCallback.autoFocus(view, event);
                }

                return true;
            }
        });

        // 判断是否支持前置摄像头
        int cameras = mCallback.getNumberOfCameras();
        if (cameras <= 1) {
            mSwitchButton.setVisibility(View.GONE);
        }

        // 是否支持闪关灯
        if (!mCallback.isSupportedFlashMode()) {
            mflashButton.setVisibility(View.GONE);
        }

        if (mCallback.isSupportedZoom()) {
            mZoomBar.setMax(mCallback.getMaxZoom());
        } else {
            mZoomBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.camera_take_btn:
            if (saved) {
                saved = false;
                mCallback.takePicture(mHandler);
            }
            break;
        case R.id.camera_switch_btn:
            isFrontCamera = !isFrontCamera;
            if (isFrontCamera) {
                mSwitchButton.setText("打开后摄像头");
                mflashButton.setVisibility(View.GONE);
                mZoomLayout.setVisibility(View.GONE);
            } else {
                mSwitchButton.setText("打开前摄像头");
                mflashButton.setVisibility(View.VISIBLE);
                mZoomLayout.setVisibility(View.VISIBLE);
            }
            mCallback.switchCamera(mSurfaceView, isFrontCamera);
            break;
        case R.id.flashMode:
            mFlashMode = (mFlashMode + 1) % 3;
            switch (mFlashMode) {
            case FLASH_MODE_AUTO:
                mflashButton.setText("flash_auto");
                break;
            case FLASH_MODE_ON:
                mflashButton.setText("flash_on");
                break;
            case FLASH_MODE_OFF:
                mflashButton.setText("flash_off");
                break;

            default:
                break;
            }
            mCallback.SetFlashMode(mFlashMode);
            break;

        default:
            break;
        }

    }

}