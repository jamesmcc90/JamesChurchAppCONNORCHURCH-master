package com.connorchurch.james.churchapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.exceptions.InvalidArgumentException;
import com.braintreepayments.api.models.PaymentMethodNonce;

public class PaymentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            String mAuthorization = null;
            BraintreeFragment mBraintreeFragment = BraintreeFragment.newInstance(this, mAuthorization);
            // mBraintreeFragment is ready to use!
        } catch (InvalidArgumentException e) {
            // There was an issue with your authorization string.
        }
    }

    public void onPaymentMethodNonceCreated(PaymentMethodNonce paymentMethodNonce) {
        // Send this nonce to your server
        String nonce = paymentMethodNonce.getNonce();
    }
}
