package com.vehicle.drivemehomeontime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterCustomerEmployee extends Activity
{
    private Spinner state_spinner;
    private Spinner driver_spinner;
	private boolean fieldsOK;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_customer_employee);

        addListenerOnSpinnerItemSelection();
    }

    public void addListenerOnSpinnerItemSelection()
    {
        state_spinner = (Spinner) findViewById(R.id.state_spinner_Register_Customer_Employee);
        driver_spinner = (Spinner) findViewById(R.id.driver_spinner_Register_Customer_Employee);
        
        state_spinner
                .setOnItemSelectedListener(new CustomOnItemSelectedListener());
        
        driver_spinner
                .setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }
    
    public void submitRegistration(View v)
    {
        EditText username = (EditText) findViewById(
                R.id.username_Register_Customer_Employee);
        EditText password = (EditText) findViewById(
                R.id.password_Register_Customer_Employee);
        EditText first_name = (EditText) findViewById(
                R.id.first_name_Register_Customer_Employee);
        EditText last_name = (EditText) findViewById(
                R.id.last_name_Register_Customer_Employee);
        EditText address = (EditText) findViewById(
                R.id.address_Register_Customer_Employee);
        EditText city = (EditText) findViewById(
                R.id.city_Register_Customer_Employee);
        EditText zip_code = (EditText) findViewById(
                R.id.zip_code_Register_Customer_Employee);
        EditText phone = (EditText) findViewById(
                R.id.phone_Register_Customer_Employee);
        EditText email = (EditText) findViewById(
                R.id.email_Register_Customer_Employee);
        EditText drivers_license = (EditText) findViewById(
                R.id.drivers_license_Register_Customer_Employee);
        
    	boolean fieldsOK= validate(new EditText[]{username, password, first_name
			, last_name, address, city, zip_code, phone, email, drivers_license});
    	boolean lengthOK = unpwLength(new EditText[] {username, password});
    	boolean zipOK = zipLength(zip_code);
    	boolean phoneOK = phoneLength(phone);
        switch(v.getId())
        {
            case R.id.submit_button_Register_Customer_Employee:
            	if (fieldsOK == true && lengthOK == true && zipOK == true && phoneOK == true)
            	{
            		Log.i("register", "success");
            		new RegisterAccount().execute();
            		finish();
            	}
            	else
            	{
            		Log.i("register", "fail");
            	}
                break;
        }
    }
    
//    private boolean checker(EditText fields[] )
//    {        
//    	if (fields == true && lengthOK == true)
//    	{
//			Log.i("register", "no problem");
//			return true;
//		}
//		else 
//		{
//			Log.i("register error", "blank field");
//			return false;
//		}
//    }
    
    //checks to see if all the fields are filled
    private boolean validate(EditText[] fields)
    {
        for(int i=0; i<fields.length; i++)
        {
            EditText currentField=fields[i];
            if(currentField.getText().toString().length()<=0)
            {
                return false;
            }
        }
        return true;
    }
    
    //checks to see if username and password are at least 8 characters long
    private boolean unpwLength(EditText[] unPw)
    {
        for(int i=0; i<unPw.length; i++)
        {
            EditText currentField=unPw[i];
            if(currentField.getText().toString().length()<8)
            {
                return false;
            }
        }
        return true;
    }
    
    //returns true if user entered 5 numbers into zipcode
    private boolean zipLength(EditText zip)
    {
    	EditText zipCode = zip;
    	if (zipCode.length() == 5)
    	{
    		return true;
    	}
    	else return false;
    }
    
    private boolean phoneLength(EditText phone)
    {
    	EditText phoneNum = phone;
    	if (phoneNum.length() == 10)
    	{
    		return true;
    	}
    	else return false;
    }

    public class CustomOnItemSelectedListener implements
            OnItemSelectedListener
    {
        @Override
        public void onItemSelected(AdapterView<?> parent,
                View view, int pos, long id)
        {
            // TODO Auto-generated method stub
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0)
        {
            // TODO Auto-generated method stub
        }
    }
    
    public class RegisterAccount extends AsyncTask<Void,Void,Void>
    {
//    	private RegisterCustomerEmployee rce;
        EditText username = (EditText) findViewById(
                R.id.username_Register_Customer_Employee);
        EditText password = (EditText) findViewById(
                R.id.password_Register_Customer_Employee);
        EditText first_name = (EditText) findViewById(
                R.id.first_name_Register_Customer_Employee);
        EditText last_name = (EditText) findViewById(
                R.id.last_name_Register_Customer_Employee);
        EditText address = (EditText) findViewById(
                R.id.address_Register_Customer_Employee);
        EditText city = (EditText) findViewById(
                R.id.city_Register_Customer_Employee);
        EditText zip_code = (EditText) findViewById(
                R.id.zip_code_Register_Customer_Employee);
        EditText phone = (EditText) findViewById(
                R.id.phone_Register_Customer_Employee);
        EditText email = (EditText) findViewById(
                R.id.email_Register_Customer_Employee);
        EditText drivers_license = (EditText) findViewById(
                R.id.drivers_license_Register_Customer_Employee);
//        
//    	boolean fieldsOK= rce.validate(new EditText[]{username, password, first_name
//			, last_name, address, city, zip_code, phone, email, drivers_license});
//    	boolean lengthOK = rce.unpwLength(new EditText[] {username, password});
//    	boolean zipOK = rce.zipLength(zip_code);
    	
        @Override
        protected Void doInBackground(Void ...voids)
        {
//        	if (fieldsOK == true && lengthOK == true && zipOK == true)
//        	{
        		register();
        		Log.i("register", "okay");
//        	}
//        	Log.i("failed to register", "fail");
			return null;
        }
        
        private void register()
        {
          String username_string = "";
          String password_string = "";
          String first_name_string = "";
          String last_name_string = "";
          String address_string = "";
          String city_string = "";
          String state_string = "";
          String zip_code_string = "";
          String phone_string = "";
          String email_string = "";
          String drivers_license_string = "";
          String driver_type_string = "";
            
            if (username.getText().toString().length() >= 0)
            {
            	username_string = username.getText().toString();
            	Log.i("usernamezzz", username_string);
            }
            
            if (password.getText().toString().length() >= 0)
            {
            	password_string = password.getText().toString();
            }
            
            if (zip_code.getText().toString().length() == 0)
            {
            	zip_code_string = zip_code.getText().toString();
            }
            
            if (phone.getText().toString().length() == 0)
            {
            	phone_string = phone.getText().toString();
            }
            

            first_name_string = first_name.getText().toString();
            last_name_string = last_name.getText().toString();
            address_string = address.getText().toString();
            city_string = city.getText().toString();
            state_string = state_spinner.getSelectedItem().toString();
            email_string = email.getText().toString();
            drivers_license_string = drivers_license.getText().toString();
            driver_type_string = driver_spinner.getSelectedItem().toString();
            
            Log.d("myTag", "username_string = " + username_string);
            Log.d("myTag", "password_string = " + password_string);
            Log.d("myTag", "first_name_string = " + first_name_string);
            Log.d("myTag", "last_name_string = " + last_name_string);
            Log.d("myTag", "address_string = " + address_string);
            Log.d("myTag", "city_string = " + city_string);
            Log.d("myTag", "state_string = " + state_string);
            Log.d("myTag", "zip_code_string = " + zip_code_string);
            Log.d("myTag", "phone_string = " + phone_string);
            Log.d("myTag", "email_string = " + email_string);
            Log.d("myTag", "drivers_license_string = " + drivers_license_string);
            Log.d("myTag", "driver_type_string = " + driver_type_string);
            
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://rszeto.no-ip.biz/register.php");
            
            try
            {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(13);
                nameValuePairs.add(new BasicNameValuePair("username", username_string));
                nameValuePairs.add(new BasicNameValuePair("password", password_string));
                nameValuePairs.add(new BasicNameValuePair("first_name", first_name_string));
                nameValuePairs.add(new BasicNameValuePair("last_name", last_name_string));
                nameValuePairs.add(new BasicNameValuePair("address", address_string));
                nameValuePairs.add(new BasicNameValuePair("city", city_string));
                nameValuePairs.add(new BasicNameValuePair("state", state_string));
                nameValuePairs.add(new BasicNameValuePair("zip_code", zip_code_string));
                nameValuePairs.add(new BasicNameValuePair("phone_number", phone_string));
                nameValuePairs.add(new BasicNameValuePair("email", email_string));
                nameValuePairs.add(new BasicNameValuePair("drivers_license", drivers_license_string));
                nameValuePairs.add(new BasicNameValuePair("account_type", "customer"));
                nameValuePairs.add(new BasicNameValuePair("driver_type", driver_type_string));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                
                Log.d("myTag", "it works up to here");
                
                HttpResponse response = httpClient.execute(httpPost);
                HttpEntity entity = response.getEntity();
                String res = EntityUtils.toString(entity);
                
                if(res.contains("account successfully created"))
                {
                    Log.d("myTag", "account successfully created");
                }
                else
                {
                    Log.d("myTag", "account was not created");
                }
            }
            catch(ClientProtocolException e)
            {
                Log.e("myTag", "something went wrong " + e.toString());
            }
            catch(IOException e)
            {
                Log.e("myTag", "something went wrong IO " + e.toString());
            }
        }
        
        
    }    
}
