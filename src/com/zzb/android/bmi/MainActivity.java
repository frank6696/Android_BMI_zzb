package com.zzb.android.bmi;

import com.zzb.android.util.ConstValue;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	//0定义字段
	private EditText etHeigth=null;
	private EditText etWeigth=null;
	private Button btnBmi=null;
	private TextView bmi=null;
	private TextView advice=null;
	
	private String strBmi=null;
	private String strHeigth="";
	private String strWeigth="";
	
	private double doubleHeigth=-1;
	private double doubleWeigth=-1;
	private double doubleBmi=-1;
	
	private AlertDialog.Builder builder=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//1实例化
		this.m1();
		//2监听器
		this.m2();
		//3调用
		//4最终处理
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		strHeigth=etHeigth.getText().toString().trim();
		strWeigth=etWeigth.getText().toString().trim();
		if(("".equals(strHeigth))||("".equals(strWeigth))){
			Toast.makeText(getApplication(), "请完整信息！", Toast.LENGTH_SHORT).show();
		}else {
			try {
				//计算BMI值
				strBmi=this.calBmi(strHeigth, strWeigth);
				bmi.setText("您的BMI值是："+strBmi);
				if(doubleBmi<20){
					advice.setText(ConstValue.adviceLight);
				}else if(doubleBmi>25){
					advice.setText(ConstValue.adviceHeaven);
				}else {
					advice.setText(ConstValue.adviceMiddle);
				}
				//显示对话框
				this.openOptionsDialog();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Toast.makeText(getApplication(), "只能输入数字哦", Toast.LENGTH_SHORT).show();
			}
		}
		
	}

	private void m2() {
		// TODO Auto-generated method stub
		btnBmi.setOnClickListener(this);
	}

	private void m1() {
		// TODO Auto-generated method stub
		etHeigth=(EditText) this.findViewById(R.id.etHeigth);
		etWeigth=(EditText) this.findViewById(R.id.etWeigth);
		btnBmi=(Button) this.findViewById(R.id.btnBmi);
		bmi=(TextView) this.findViewById(R.id.bmi);
		advice=(TextView) this.findViewById(R.id.advice);
		builder=new Builder(MainActivity.this);
	}
	private String calBmi(String strHeigth,String strWeigth){
		doubleHeigth=Double.parseDouble(strHeigth);
		doubleWeigth=Double.parseDouble(strWeigth);
		doubleBmi=doubleWeigth/((doubleHeigth/100)*(doubleHeigth/100));
		return ((Double)doubleBmi).toString();
	}
	private void openOptionsDialog(){
		builder.setTitle("关于Android BMI");
		builder.setMessage("Android BMI Calc");
		builder.setPositiveButton(ConstValue.OK,
		new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}

		});
		builder.show();
	}
}
