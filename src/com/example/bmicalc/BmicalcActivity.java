package com.example.bmicalc;


// http://www.a-g-a.de/Leitlinies2.pdf

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;



public class BmicalcActivity extends Activity implements OnClickListener {
    
	protected Button CalcLabel;
	protected TextView ErgebnisLabel;
	protected EditText HeightLabel;
	protected EditText WeightLabel;	
	protected EditText AgeLabel;
	protected RadioButton MaleLabel;
	
	
	protected double[] avalues;
	protected double[] lvalues;
	protected double[] svalues;
	protected double[] pvalues;
	protected double sds;
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        CalcLabel = (Button) findViewById(R.id.calc_bmi);
        ErgebnisLabel = (TextView) findViewById(R.id.et_result);	
        HeightLabel = (EditText) findViewById(R.id.et_height);
    	WeightLabel = (EditText) findViewById(R.id.et_weight);
    	AgeLabel = (EditText) findViewById(R.id.et_age);
    	MaleLabel = (RadioButton) findViewById(R.id.radio_male);   
        
        CalcLabel.setOnClickListener( this);
        
        
  
        	
        
/*        String s2 =  Double.toString(avalues[8])+Double.toString(pvalues[8])+Double.toString(lvalues[8])+Double.toString(svalues[8]);
        ErgebnisLabel.setText(s2);
*/        
        
        
        HeightLabel.setText("105");
        WeightLabel.setText("14.5");
        AgeLabel.setText("4.5");
        MaleLabel.setChecked(true);
    }
        
    public void onClick(View v) {
            	if(v == CalcLabel) {
                 String s1 = HeightLabel.getText().toString();
                 Double h = Double.parseDouble(s1);

                 String s2 = WeightLabel.getText().toString();
                 Double w = Double.parseDouble(s2);

                 Double age = Double.parseDouble(AgeLabel.getText().toString());
                 
                 Boolean sex = MaleLabel.isChecked(); // male = 1
                 
                 h = h/100;
                 
                 Double bmi = w / ( h * h ) ;

                 // l,s,m
                 // Double sds = ((bmi/m)^l-1) / ( l *s)
                 // perzentil = kum.stdnormvert(sds)
                 
               //  double perc = cumulativeProbability(double -2,02);
                 
                 
                 
                 NumberFormat formatter = new DecimalFormat(".00");
                 String s3 = formatter.format(bmi); 
                
                 String s4 = formatter.format(getperc(age, bmi, sex));
                 
            	ErgebnisLabel.setText(  getString(R.string.result1) + 
            			" " + s3 + " " + getString(R.string.result2) + 
            			" sds=" + s4 );
                 
            	}
     }
      
    public double getperc (double age, double bmi, boolean sex) {
    
    String[] perc;
    Resources res = getResources();
    Double s;
    
    if(sex = true) 
    	 {  perc = res.getStringArray(R.array.boys); }
    else
   	 {  perc = res.getStringArray(R.array.girls); }
   	
    int n = perc.length;
    
    Double[] avalues = new Double[n];
    Double[] lvalues = new Double[n]; 
    Double[] svalues = new Double[n]; 
    Double[] pvalues = new Double[n]; 
    
    
    for (int i=0; i < n; i++) 
    {
    	
    	String x1 = (perc[i]).toString();
    	String x[] = x1.split(" ");
    	avalues[i] = Double.parseDouble( x[0].toString() );
    	lvalues[i] = Double.parseDouble( x[1].toString() ); 
    	svalues[i] = Double.parseDouble( x[2].toString() ); 
    	pvalues[i] = Double.parseDouble( x[6].toString() ); 
        
    }
    
    int i=8;
    
    s  = (  Math.pow( bmi / pvalues[i] , lvalues[i]) - 1 ) / (lvalues[i] * svalues[i]);
    
 // l,s,m
    // Double sds = ((bmi/m)^l-1) / ( l *s)
    // perzentil = kum.stdnormvert(sds)
    
  //  double perc = cumulativeProbability(double -2,02);
    
    
    return s;
    }
}