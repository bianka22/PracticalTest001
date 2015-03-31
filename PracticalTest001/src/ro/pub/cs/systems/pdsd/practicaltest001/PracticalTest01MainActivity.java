package ro.pub.cs.systems.pdsd.practicaltest001;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class PracticalTest01MainActivity extends Activity {

	private EditText left, right;
	private ButtonClickListener bcl = new ButtonClickListener();
	private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_main);

		left = (EditText) findViewById(R.id.editText1);
		right = (EditText) findViewById(R.id.editText2);

		left.setText(String.valueOf(0));
		right.setText(String.valueOf(0));

		Button buttonLeft = (Button) findViewById(R.id.button2);
		buttonLeft.setOnClickListener(bcl);

		Button buttonRight = (Button) findViewById(R.id.button3);
		buttonRight.setOnClickListener(bcl);

		if (savedInstanceState != null) {
			String leftCount = savedInstanceState.getString("leftCount");
			if (leftCount != null) {
				left.setText(leftCount);
			} else {
				left.setText(String.valueOf(0));
			}
			String rightCount = savedInstanceState.getString("rightCount");
			if (rightCount != null) {
				right.setText(rightCount);
			} else {
				right.setText(String.valueOf(0));
			}
		} else {
			left.setText(String.valueOf(0));
			right.setText(String.valueOf(0));
		}
		
		Button navigateToSecondaryActivityButton = (Button) findViewById(R.id.button1);
		navigateToSecondaryActivityButton.setOnClickListener(bcl);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	protected class ButtonClickListener implements Button.OnClickListener {

		@Override
		public void onClick(View view) {

			switch (view.getId()) {

				case R.id.button2:
					left.setText(String.valueOf(Integer.parseInt(left.getText()
							.toString()) + 1));
					break;
	
				case R.id.button3:
					right.setText(String.valueOf(Integer.parseInt(right.getText()
							.toString()) + 1));
					break;
					
				case R.id.button1:
					Intent intent = new Intent(
							"ro.pub.cs.systems.pdsd.intent.action.PracticalTest01SecondaryActivity");
					intent.putExtra("number_of_clicks", String.valueOf(Integer
							.parseInt(left.getText().toString())
							+ Integer.parseInt(right.getText().toString())));
					startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
					break;
			}
		}
	}
	
	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		EditText leftEditText = (EditText) findViewById(R.id.editText1);
		EditText rightEditText = (EditText) findViewById(R.id.editText2);
		savedInstanceState.putString("leftCount", leftEditText.getText().toString());
		savedInstanceState.putString("rightCount", rightEditText.getText().toString());
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		Toast.makeText(this, "The activity returned with result " + resultCode,
				Toast.LENGTH_LONG).show();
	}
}
