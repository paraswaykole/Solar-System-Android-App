package org.avcoe.solarsystem;

import org.avcoe.data.Information;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

/**
 * @author Paras Waykole
 */

public class InfoActivity extends Activity{

	int planetno;
	String planetname;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.infoactivity);
		
		planetno = this.getIntent().getIntExtra("planetno",-1);
		planetname = this.getIntent().getStringExtra("planetname");
		
		TextView titleView = (TextView) findViewById(R.id.title);
		titleView.setText(planetname);
		
		TextView infoView = (TextView) findViewById(R.id.information);
		infoView.setText(Information.getInstance().getInfo(planetno));
		
	}
	
}
