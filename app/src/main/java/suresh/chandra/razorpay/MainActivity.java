package suresh.chandra.razorpay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity  implements PaymentResultListener{
    Button bt_pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_pay = findViewById(R.id.bt_pay);
        String sAmount = "100";
        int amount = Math.round(Float.parseFloat(sAmount) *100);
        bt_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Checkout checkout = new Checkout();
                checkout.setKeyID("rzp_live_haRYt1bAIImDxc");
                checkout.setImage(R.drawable.ic_launcher_background);
                JSONObject object = new JSONObject();
                try {
                    object.put("name","suresh chandra");
                    object.put("description","Test payment");
                    object.put("theme.color","#0093DD");
                    object.put("currency","INR");
                    object.put("amount",amount);
                    object.put("prefill.contact","8755323795");
                    object.put("prefill.email","csuresh073@gmail.com");
                    checkout.open(MainActivity.this,object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Payment ID");
        alertDialog.setMessage(s);
        alertDialog.show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(),s, Toast.LENGTH_SHORT).show();
    }
}