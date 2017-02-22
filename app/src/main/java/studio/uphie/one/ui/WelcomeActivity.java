package studio.uphie.one.ui;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import studio.uphie.one.R;
import studio.uphie.one.abs.AbsBaseActivity;

/**
 * Created by beforenight on 2016/3/24.
 * Email: beforenight@163.com
 */
public class WelcomeActivity extends AbsBaseActivity
{

    @Bind(R.id.dv_welcome)
    SimpleDraweeView dvWelcome;
    @Bind(R.id.text_countdown)
    TextView textCountdown;

    private int count = 3;
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            if (msg.what == 0)
            {
                if (count > 0)
                {
                    textCountdown.setText(count + "");
                    count--;
                    sendEmptyMessageDelayed(0, 1000);
                }
                else
                {
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    finish();
                }
            }
        }
    };

    @Override
    public int getLayoutId()
    {
        return R.layout.activity_welcome;
    }

    @Override
    public void init()
    {
        DraweeController draweeController = Fresco.newDraweeControllerBuilder().setAutoPlayAnimations(true).setUri(Uri.parse("asset://studio.uphie.one/welcome.gif")).build();
        dvWelcome.setController(draweeController);

        textCountdown.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //必须移除Message,否则Handler会继续执行，导致空指针异常
                handler.removeMessages(0);
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        });

        handler.sendEmptyMessageDelayed(0, 1000);
    }


}
