package edu.ktu.lab2;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements RequestOperator.RequestOperatorListener {

    Button sendRequestButton;
    TextView title;
    TextView bodyText;
    private NumberInCircleView numberIndicator;
    ArrayList<ModelPost> publications;
    private IndicatingView indicator;
    ProgressBar progressBar;
    int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivitydesign);

        sendRequestButton = (Button) findViewById(R.id.send_request);
        sendRequestButton.setOnClickListener(requestButtonClicked);

        title = (TextView) findViewById(R.id.title);
        bodyText = (TextView) findViewById(R.id.body_text);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        numberIndicator = (NumberInCircleView) findViewById(R.id.number);
        indicator = (IndicatingView) findViewById(R.id.generated_graphic);
    }

    View.OnClickListener requestButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            progressStatus = 0;
            progressBar.setVisibility(View.VISIBLE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    setIndicatorStatus(IndicatingView.EXECUTING);
                    while (progressStatus < 100){
                        progressStatus++;
                        android.os.SystemClock.sleep(50);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setProgress(progressStatus);
                            }
                        });
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            sendRequest();
                        }
                    });
                }
            }).start();
        }
    };

    private void sendRequest(){
        RequestOperator ro = new RequestOperator();
        ro.setListener(this);
        ro.start();
    }

    public void updatePublication(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (publications != null){
                    title.setText(publications.get(0).getTitle());
                    bodyText.setText(publications.get(0).getBodyText());
                }else{
                    title.setText("");
                    bodyText.setText("");
                }
            }
        });
    }


    @Override
    public void success(ArrayList<ModelPost> publications){
        this.publications = publications;
        setIndicatorStatus(IndicatingView.SUCCESS);
        setIndicatorNumber();
        updatePublication();
    }

    @Override
    public void failed(int responseCode){
        this.publications = null;
        setIndicatorStatus(IndicatingView.FAILED);
        updatePublication();
    }

    public void setIndicatorNumber(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                numberIndicator.setNumber(publications.size());
                numberIndicator.invalidate();
            }
        });
    }

    public void setIndicatorStatus(final int status){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                indicator.setState(status);
                indicator.invalidate();
            }
        });
    }
}
