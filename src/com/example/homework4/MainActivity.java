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
		
		// parse 初始化
		Parse.initialize(this, "PC17TlNAa5805dHMEbHs1xUSoBtW6V0cn1rdMKQk", "GNuUoUJTZiBed6OpuxzLe3F0nv24t4ekguRtVNZx");
		// parse 登入
        ParseUser.enableAutomaticUser();
		
		// TextView
		textTile=(TextView)findViewById(R.id.textView4);

		// EditText  //發現錯字
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
		// 建立parse物件  - 表單名稱score board
 		ParseObject pushObject = new ParseObject("scoreboard");
 		// 放入使用者帳號
		pushObject.put("User_name", textTile.getText().toString());
		// 放入次數
		pushObject.put("score", Counter);
		pushObject.saveInBackground();		
		//打入OAO
		

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

				//Integer(他是一個工具箱)裡有一個parseInt可以將字串轉成數字
				int Input=Integer.parseInt(EditInput.getText().toString());
				
				
				if(Input==Number) 
				{
				
					showAlertDialog("恭喜你答對囉",
						"答案為"+Integer.toString(Number)+"你總共猜了:"+ Integer.toString (Counter)+"次");
						EditInput.setText("");
						
						//輸入玩家姓名
						dialogName=new Dialog(MainActivity.this);
						dialogName.setTitle("猜數字");
						dialogName.setCancelable(false);
						
						
				}
				else if (Input>Number) 
				{
					showAlertDialog("錯誤!","猜錯囉!，數字太大");
					Counter++;
					EditInput.setText("");
					textTile.setText("("+Counter+")");
					
					
					//EditText edtUserName=(EditText)
					
				
				}
				else if (Input<Number) 
				{
					showAlertDialog("錯誤!","猜錯囉!，數字太小");
					Counter++;
					EditInput.setText("");
					textTile.setText("("+Counter+")");
				}


			}
			
			else
				showAlertDialog("錯誤!","請輸入字串");
			
				
			}

	};


	private void showAlertDialog(final String title, String msg) {
		AlertDialog.Builder dialog=new AlertDialog.Builder(this);
		dialog.setTitle(title);
		dialog.setMessage(msg);
		dialog.setPositiveButton("確定", null);
		dialog.show();

	}



	private Button.OnClickListener newbutton =new Button.OnClickListener () {

		@Override
		public void onClick(View v) {
			// TODO 自動產生的方法 Stub
			initGame();

		}

	};








}