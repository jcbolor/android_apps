package jcb.vindex.searchContact;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchContact extends Activity {
	
	Button btnSearch;
	EditText etSearch;
	TextView tvSearch;
	Cursor cur;
	ContentResolver cr;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btnSearch = (Button)findViewById(R.id.btn_search);
        etSearch = (EditText)findViewById(R.id.et_name);
        tvSearch = (TextView)findViewById(R.id.tv_display);
        
        btnSearch.setOnClickListener(
        	new View.OnClickListener() {
					
				public void onClick(View arg0) {
					cr = getContentResolver();
			        cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
			        		null, null, null, null);
					// action to execute
				    if(cur.getCount() > 0){
				        String name = "";
				        while(cur.moveToNext()){
				        	if(Integer.parseInt(cur.getString(cur.getColumnIndex(
				        			ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0){
				        		name = cur.getString(
				        				cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				        			
				        		tvSearch.setText(name);
				        	}
				        }
				     }
				}
			});
    }
}