package com.example.android.justjava;
import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void increament(View view) {
        if(quantity>=0 && quantity<=100) {
            quantity++;
        }
    displayQuantity(quantity);

}
    public void decreament(View view) {
        if(quantity>0 && quantity<=100) {
            quantity--;
        }
        displayQuantity(quantity);
}
    public void submitOrder(View view) {
       EditText text = (EditText)findViewById(R.id.name_field);
        String value=text.getText().toString();
        CheckBox whippedCreamCheckBox=(CheckBox) findViewById(R.id.whipped_cream_checkbox);
      boolean haswhippedCream=whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
       int price= calculatePrice(haswhippedCream,hasChocolate);
        String Pricemessage=createOrderSummary(value,price,haswhippedCream,hasChocolate);


        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "just java order for "+value);
        intent.putExtra(Intent.EXTRA_TEXT,Pricemessage);

        if(intent.resolveActivity(getPackageManager())!=null)
        {
            startActivity(intent);
        }


    }

    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(boolean addWhippedCream,boolean addChoclate) {
       int basePrice=5;
       if(addWhippedCream)
       {
           basePrice+=1;
       }
       if(addChoclate)
       {
           basePrice+=2;
       }

        return quantity*basePrice;
    }

    private String createOrderSummary(String val,int price,boolean addWhippedCream,boolean addChoclate)
    {
        String PriceMessage="Name :"+val;
        PriceMessage+="\nAdd whipped cream ? "+addWhippedCream;
        PriceMessage+="\nAdd Choclate cream ? "+addChoclate;
        PriceMessage=PriceMessage+"\nQuantity : "+quantity;
        PriceMessage=PriceMessage+"\nTotal : $"+price;
        PriceMessage=PriceMessage+"\nThank You!";
        return PriceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }



}