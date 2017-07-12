/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava; 
 *
 */

package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method displays the given price on the screen.
    */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText myName = (EditText) findViewById(R.id.name);
        String userName = myName.getText().toString();

        boolean isChecked = ((CheckBox)findViewById(R.id.WhippedCreamCheckBox)).isChecked();
        boolean isChocolateChecked = ((CheckBox)findViewById(R.id.ChocolateCheckBox)).isChecked();

        int price = quantity * 5;

        if(isChecked){
            price += quantity;
        }
        if(isChocolateChecked){
            price += quantity * 2;
        }

        String priceMessage = "Name: " + userName +
                "\nQuantity: " + quantity +
                "\nAdd whipped cream: " + isChecked +
                "\nAdd Chocolate: " + isChocolateChecked +
                "\nTotal: $" + price +
                "\nThank you";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order for " + userName);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage );
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        displayMessage(priceMessage);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    //method for when plus button clicked
    public void increment(View view){
        if(quantity <= 100)
            quantity++;
        display(quantity);
    }

    //method when minus button clicked
    public void decrement(View view){
        if(quantity != 1)
            quantity--;
        display(quantity);
    }
}