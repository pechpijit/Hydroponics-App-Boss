package ac.th.ssru.it.hydroponicsapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ac.th.ssru.it.hydroponicsapp.helper.BaseActivity;
import ac.th.ssru.it.hydroponicsapp.model.DeviceModel;
import at.blogc.android.views.ExpandableTextView;
import az.plainpie.PieView;
import az.plainpie.animation.PieAngleAnimation;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.pieView1)
    PieView pieView1;
    @BindView(R.id.txtStatus1)
    TextView txtStatus1;
    @BindView(R.id.txtTagDetail1)
    TextView txtTagDetail1;
    @BindView(R.id.imgTagDetail1)
    ImageView imgTagDetail1;
    @BindView(R.id.tag1)
    LinearLayout tag1;
    @BindView(R.id.txtStatus2)
    TextView txtStatus2;
    @BindView(R.id.txtTagDetail2)
    TextView txtTagDetail2;
    @BindView(R.id.imgTagDetail2)
    ImageView imgTagDetail2;
    @BindView(R.id.tag2)
    LinearLayout tag2;
    @BindView(R.id.txtStatus3)
    TextView txtStatus3;
    @BindView(R.id.txtTagDetail3)
    TextView txtTagDetail3;
    @BindView(R.id.imgTagDetail3)
    ImageView imgTagDetail3;
    @BindView(R.id.tag3)
    LinearLayout tag3;
    @BindView(R.id.ex_detail1)
    ExpandableTextView exDetail1;
    @BindView(R.id.ex_detail2)
    ExpandableTextView exDetail2;
    @BindView(R.id.ex_detail3)
    ExpandableTextView exDetail3;
    @BindView(R.id.txtStatus4)
    TextView txtStatus4;
    @BindView(R.id.txtNitrogen)
    TextView txtNitrogen;
    @BindView(R.id.txtPhosphorus)
    TextView txtPhosphorus;
    @BindView(R.id.txtPotassium)
    TextView txtPotassium;
    @BindView(R.id.txtTagDetail4)
    TextView txtTagDetail4;
    @BindView(R.id.imgTagDetail4)
    ImageView imgTagDetail4;
    @BindView(R.id.tag4)
    LinearLayout tag4;
    @BindView(R.id.ex_detail4)
    ExpandableTextView exDetail4;

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpView();
        getValue();
    }

    private void setUpView() {
        tag1.setOnClickListener(this);
        tag2.setOnClickListener(this);
        tag3.setOnClickListener(this);
        tag4.setOnClickListener(this);

        PieAngleAnimation animation1 = new PieAngleAnimation(pieView1);
        animation1.setDuration(1500);
        pieView1.startAnimation(animation1);
        pieView1.setMaxPercentage(9);
        pieView1.setPercentageTextSize(15);

        setExp(exDetail1);
        setExp(exDetail2);
        setExp(exDetail3);
        setExp(exDetail4);

        exDetail1.setText(R.string.txtDetail1);
        exDetail2.setText(R.string.txtDetail2);
        exDetail3.setText(R.string.txtDetail3);
        exDetail4.setText(R.string.txtDetail4);
    }

    private void getValue() {
        showProgressDialog("กำลังโหลดข้อมูล");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("device").child("1");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    setValue(dataSnapshot.getValue(DeviceModel.class));
                } else {
                    dialogAlertError("Exists", "");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                dialogAlertError("", databaseError.getMessage());
                Log.d(TAG, databaseError.getMessage());
            }
        });
    }

    private void setValue(DeviceModel value) {
        txtStatus2.setText((int) value.getHumidity() + " %");
        txtStatus3.setText(value.getTemperature() + " °c");

        txtStatus4.setText((int) value.getFertility() + "%");
        txtNitrogen.setText((int) value.getNitrogen() + " PPM");
        txtPhosphorus.setText((int) value.getPhosphorus() + " PPM");
        txtPotassium.setText((int) value.getPotassium() + " PPM");

        double ph = value.getPh() / 10;
        if (ph < 3.5) {
            txtStatus1.setText("กรดรุนแรงที่สุด");
        } else if (ph >= 3.5 && ph <= 4.5) {
            txtStatus1.setText("กรดรุนแรงมาก");
        } else if (ph >= 4.6 && ph <= 5.0) {
            txtStatus1.setText("กรดจัดมาก");
        } else if (ph >= 5.1 && ph <= 5.5) {
            txtStatus1.setText("กรดจัด");
        } else if (ph >= 5.6 && ph <= 6.0) {
            txtStatus1.setText("กรดปานกลาง");
        } else if (ph >= 6.1 && ph <= 6.5) {
            txtStatus1.setText("กรดเล็กน้อย");
        } else if (ph >= 6.6 && ph <= 7.3) {
            txtStatus1.setText("กลาง");
        } else if (ph >= 7.4 && ph <= 7.8) {
            txtStatus1.setText("ด่างเล็กน้อย");
        } else if (ph >= 7.9 && ph <= 8.4) {
            txtStatus1.setText("ด่างปานกลาง");
        } else if (ph >= 8.5 && ph <= 9.0) {
            txtStatus1.setText("ด่างจัด");
        } else if (ph > 9.0) {
            txtStatus1.setText("ด่างจัดมาก");
        }

        if (ph > 0) {
            pieView1.setPercentage((float) ph);
            pieView1.setInnerText(ph + " pH");
        } else if (ph == 0) {
            pieView1.setPercentage((float) 0.1);
            pieView1.setInnerText(ph + " pH");
        }

        hideProgressDialog();
    }

    private void setExp(ExpandableTextView exp) {
        exp.setAnimationDuration(1000L);
        exp.setPadding(10, 10, 10, 10);
        exp.setInterpolator(new OvershootInterpolator());
        exp.setExpandInterpolator(new OvershootInterpolator());
        exp.setCollapseInterpolator(new OvershootInterpolator());
        exp.addOnExpandListener(new ExpandableTextView.OnExpandListener() {
            @Override
            public void onExpand(@NonNull final ExpandableTextView view) {
                Log.d(TAG, "ExpandableTextView expanded");
            }

            @Override
            public void onCollapse(@NonNull final ExpandableTextView view) {
                Log.d(TAG, "ExpandableTextView collapsed");
            }
        });
    }

    private void setEx(ExpandableTextView ex, ImageView img, TextView txt) {
        ex.toggle();
        if (ex.isExpanded()) {
            txt.setText("คำอธิบาย");
            img.animate().rotation(0).start();
            ex.collapse();
        } else {
            txt.setText("ปิด");
            img.animate().rotation(180).start();
            ex.expand();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tag1:
                setEx(exDetail1, imgTagDetail1, txtTagDetail1);
                break;
            case R.id.tag2:
                setEx(exDetail2, imgTagDetail2, txtTagDetail2);
                break;
            case R.id.tag3:
                setEx(exDetail3, imgTagDetail3, txtTagDetail3);
                break;
            case R.id.tag4:
                setEx(exDetail4, imgTagDetail4, txtTagDetail4);
                break;
        }
    }
}
