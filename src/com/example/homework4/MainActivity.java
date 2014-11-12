package com.example.homework4;

import com.parse.Parse;
import com.parse.ParseUser;

import android.R.integer;
import android.R.string;
import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;


public class MainActivity extends ActionBarActivity 
{

	private EditText EditInput;
	
	private Button ButtonNewGame;
	private Button ButtonOK;
	
	private int Number;
	private int Counter;  //static !?
	
	private TextView textTile;
	
	private Dialog dialogName;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// parse ��l��
		Parse.initialize(this, "PC17TlNAa5805dHMEbHs1xUSoBtW6V0cn1rdMKQk", "GNuUoUJTZiBed6OpuxzLe3F0nv24t4ekguRtVNZx");
		// parse �n�J
        ParseUser.enableAutomaticUser();
		
		// TextView
		textTile=(TextView)findViewById(R.id.textView4);

		// EditText  //�o�{���r
		EditInput=(EditText)findViewById(R.id.editText1);
		
		// Button
		ButtonNewGame=(Button)findViewById(R.id.button1);
		ButtonNewGame.setOnClickListener(newbutton);

		ButtonOK=(Button)findViewById(R.id.button2);
		ButtonOK.setOnClickListener(OK);
		

		textTile.setText("("+Counter+")");

		initGame();

	}
	
	private void initGame() {
		Number=(int)(Math.random()*99)+1;
		Counter=1;
		textTile.setText("("+Counter+")");
	}

	private void submitScore() {
		// �إ�parse����  - ���W��score board
 		ParseObject pushObject = new ParseObject("scoreboard");
 		// ��J�ϥΪ̱b��
		pushObject.put("User_name", textTile.getText().toString());
		// ��J����
		pushObject.put("score", Counter);
		pushObject.saveInBackground();		
		//���JOAO
		

		textTile.setText(pushObject.getObjectId()); 
		
	}

	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private Button.OnClickListener OK =new Button.OnClickListener() 
	{

		@Override
		public void onClick(View v) 
		{

			
			if (!EditInput.getText().toString().equals("")) 
			{

				//Integer(�L�O�@�Ӥu��c)�̦��@��parseInt�i�H�N�r���ন�Ʀr
				int Input=Integer.parseInt(EditInput.getText().toString());
				
				
				if(Input==Number) 
				{
				
					showAlertDialog("���ߧA�����o",
						"���׬�"+Integer.toString(Number)+"�A�`�@�q�F:"+ Integer.toString (Counter)+"��");
						EditInput.setText("");
						
						//��J���a�m�W
						dialogName=new Dialog(MainActivity.this);
						dialogName.setTitle("�q�Ʀr");
						dialogName.setCancelable(false);
						
						
				}
				else if (Input>Number) 
				{
					showAlertDialog("���~!","�q���o!�A�Ʀr�Ӥj");
					Counter++;
					EditInput.setText("");
					textTile.setText("("+Counter+")");
					
					
					//EditText edtUserName=(EditText)
					
				
				}
				else if (Input<Number) 
				{
					showAlertDialog("���~!","�q���o!�A�Ʀr�Ӥp");
					Counter++;
					EditInput.setText("");
					textTile.setText("("+Counter+")");
				}


			}
			
			else
				showAlertDialog("���~!","�п�J�r��");
			
				
			}

	};


	private void showAlertDialog(final String title, String msg) {
		AlertDialog.Builder dialog=new AlertDialog.Builder(this);
		dialog.setTitle(title);
		dialog.setMessage(msg);
		dialog.setPositiveButton("�T�w", null);
		dialog.show();

	}



	private Button.OnClickListener newbutton =new Button.OnClickListener () {

		@Override
		public void onClick(View v) {
			// TODO �۰ʲ��ͪ���k Stub
			initGame();

		}

	};








}