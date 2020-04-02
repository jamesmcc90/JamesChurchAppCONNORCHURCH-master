package com.connorchurch.james.churchapp.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.PaymentMethod;
import com.braintreepayments.api.dropin.DropInActivity;
import com.braintreepayments.api.dropin.DropInRequest;
import com.braintreepayments.api.dropin.DropInResult;
import com.braintreepayments.api.exceptions.BraintreeError;
import com.braintreepayments.api.exceptions.ErrorWithResponse;
import com.braintreepayments.api.exceptions.InvalidArgumentException;
import com.braintreepayments.api.interfaces.HttpResponseCallback;
import com.braintreepayments.api.internal.HttpClient;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.cardinalcommerce.shared.userinterfaces.ProgressDialog;
import com.connorchurch.james.churchapp.R;

import java.util.HashMap;
import java.util.Map;

public class DonateActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1234;
    String API_GET_TOKEN="http://192.168.1.86:8080/braintree/main.php";
    String API_CHECKOUT="http://192.168.1.86:8080/braintree/checkout.php";

    String token,amount;
    HashMap<String,String> paramsHash;
    Button btn_pay;
    EditText edit_amount;

   // View mDialog = findViewById(R.id.progressBar2);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textView = findViewById(R.id.textView32);
        String text = "**At the moment, this feature is experimental, using a sandbox environment. It may change or be removed entirely in the future. Please DO NOT use this for donations right now, as it is still undergoing development/testing.**";
        SpannableString ss = new SpannableString(text);

        ForegroundColorSpan fcsRed = new ForegroundColorSpan(Color.RED);

        ss.setSpan(fcsRed, 135, 141, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new UnderlineSpan(), 135, 141, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ss);

        edit_amount= findViewById(R.id.edt_amount);
        btn_pay= findViewById(R.id.btn_donate);
        //group_payment=(LinearLayout)findViewById(R.id.payment_group);

        new getToken().execute();

        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitPayment();
            }
        });

    }

    private void submitPayment(){
        String payValue=edit_amount.getText().toString();
        if(!payValue.isEmpty())
        {
            DropInRequest dropInRequest=new DropInRequest().clientToken(token);
            startActivityForResult(dropInRequest.getIntent(this),REQUEST_CODE);
        }
        else
            Toast.makeText(this, "Enter a valid amount for donation.", Toast.LENGTH_SHORT).show();

    }

    private void sendPayments(){
        RequestQueue queue = Volley.newRequestQueue(DonateActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_CHECKOUT,
                response -> {
                    if(response.contains("Successful")){
                        Toast.makeText(DonateActivity.this, "Payment Success", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(DonateActivity.this, "Payment Failed", Toast.LENGTH_SHORT).show();
                    }
                    Log.d("Response",response);
                }, error -> Log.d("Err",error.toString()))
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if(paramsHash==null)
                    return null;
                Map<String,String> params=new HashMap<>();
                for(String key:paramsHash.keySet())
                {
                    params.put(key,paramsHash.get(key));
                }
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("Content-type","application/x-www-form-urlencoded");
                return params;
            }
        };
        RetryPolicy mRetryPolicy=new DefaultRetryPolicy(0,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(mRetryPolicy);
        queue.add(stringRequest);
    }

    private class getToken extends AsyncTask {
       // ProgressDialog mDialog;

        @Override
        protected Object doInBackground(Object[] objects) {
            HttpClient client=new HttpClient();
            client.get(API_GET_TOKEN, new HttpResponseCallback() {
                @Override
                public void success(final String responseBody) {
                    //mDialog.dismiss();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            token=responseBody;
                        }
                    });
                }

                @Override
                public void failure(Exception exception) {
                    //mDialog.dismiss();
                    Log.d("Err",exception.toString());
                }
            });
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //mDialog.show();
        }

        @Override
        protected void onPostExecute(Object o){
            super.onPostExecute(o);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                DropInResult result = data.getParcelableExtra(DropInResult.EXTRA_DROP_IN_RESULT);
                PaymentMethodNonce nonce = result.getPaymentMethodNonce();
                String strNounce = nonce.getNonce();
                if (!edit_amount.getText().toString().isEmpty()) {
                    amount = edit_amount.getText().toString();
                    paramsHash = new HashMap<>();
                    paramsHash.put("amount", amount);
                    paramsHash.put("nonce", strNounce);

                    sendPayments();
                } else {
                    Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show();
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "User canceled", Toast.LENGTH_SHORT).show();
            } else {
                Exception error = (Exception) data.getSerializableExtra(DropInActivity.EXTRA_ERROR);
                Log.d("Err", error.toString());
            }
        }
    }

    @Override
    public void onBackPressed(){
        Intent first = new Intent(DonateActivity.this, MainActivity.class);
        startActivity(first);

    }

}
