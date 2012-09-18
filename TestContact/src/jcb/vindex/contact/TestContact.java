package jcb.vindex.contact;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;

public class TestContact extends Activity {
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	View test;
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
                
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, 
        		null, null, null, null);
        if(cur.getCount() > 0){
        	String name = "";
        	while(cur.moveToNext()){
        		String id = cur.getString(
        				cur.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
        		
        		name += cur.getString(
        				cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        		name += "\n";
        		if(Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0){
        			Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
        					null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", 
        					new String[]{id}, null);
        			test = this.findViewById(R.layout.main);
        			TextView tv = new TextView(this);
        			tv.setText(name);
        			tv.setMovementMethod(ScrollingMovementMethod.getInstance());
        			setContentView(tv);
        			/*while(pCur.moveToNext()){
        				//Display
        				TextView tv = new TextView(this);
	        			tv.setText(pCur.getCount());
	        			setContentView(tv);
        			}
        			pCur.close();*/
        		}
        	}
        }
    }
}