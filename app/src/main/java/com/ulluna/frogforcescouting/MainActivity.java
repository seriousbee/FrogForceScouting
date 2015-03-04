package com.ulluna.frogforcescouting;

import android.animation.Animator;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    final int MAX_STACK=6;
    final int MAX_BIN = 6;

    int[] stacks = new int[MAX_STACK];
    int[] bins = new int[MAX_BIN];
    Button[] buttons = new Button[25];
    EditText[] editTexts = new EditText[13];
    CheckBox[] checkBoxes = new CheckBox[10];
    EditText matchNumber, teamNumber;

    /*TODO:
    -settings - edit goal email
            -credits
    -first open - ask about email
    -save multiple teams and send summary
    -analyze results and give recommendation
    -edit the comments
    -count noodles
    -icon

     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        establishReferences();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_send_now:
                sendData();
                return true;
            case R.id.action_settings:
                return true;
            case R.id.action_clear_screen:
                clearScreen();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void clearScreen() {
        finish();
        recreate();
    }

    private void sendData() {
        if(teamNumber.getText().toString().equals("") || matchNumber.getText().toString().equals("") || ((RatingBar) findViewById(R.id.ratingBar)).getRating()==0) {
            Toast.makeText(getApplicationContext(), "Make sure to fill out all fields and to rank" +
                            " the team", Toast.LENGTH_SHORT).show();
        }
        else {
            String s = "";
            s += "Stacks: ";
            for (int i = 0; i < MAX_STACK; i++) {
                s += stacks[i] + ", ";
            }
            s += "\n";

            s += "Bins: ";
            for (int i = 0; i < MAX_BIN; i++) {
                s += bins[i] + ", ";
            }
            s += "\n";
            s += "Coop totes: " + ((EditText) findViewById(R.id.editTextCoop)).getText() + "\n";
            s += "Stolen Bins: " + ((EditText) findViewById(R.id.editTextBinsStolen)).getText() + "\n";
            s += "Other: " + getCheckboxData() + "\n";
            s += "Overall: " + ((RatingBar) findViewById(R.id.ratingBar)).getRating();

            Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Team #" + teamNumber.getText() + " Match #" +
                    matchNumber.getText());
            intent.putExtra(Intent.EXTRA_TEXT, s);
            intent.setData(Uri.parse("mailto:tczernuszenko@gmail.com, tczernuszenko@novischools.net")); // or just "mailto:" for blank
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
            startActivity(intent);
        }
    }

    public void buttonClicked(View view) {
        for(int i=1; i<25; i++){
            if(view.getId()==buttons[i].getId()){
                if(i<7)
                    changeValue(stacks, i-1, 1, MAX_STACK);
                else if(i<13)
                    changeValue(stacks, i-7, 0, 0);
                else if(i<19)
                    changeValue(bins, i-13, 1, MAX_BIN);
                else
                    changeValue(bins, i-19, 0, 0);
            }
        }
        updateView();
    }

    public void updateView(){
        for(int i=1; i<13; i++){
            if(i<7)
                editTexts[i].setText(String.valueOf(stacks[i - 1]));
            else
                editTexts[i].setText(String.valueOf(bins[i-7]));
        }
    }

    public void changeValue(int[] array, int index, int value, int bound){
        if(value>0){
            if(array[index]+1<=bound){
                array[index]++;
            }
        }
        else{
            if(array[index]-1>=bound){
                array[index]--;
            }
        }
    }


    public String getCheckboxData(){
        String s="";
        for(int i=1; i<checkBoxes.length; i++){
            if(checkBoxes[i]==null)
                break;
            if(checkBoxes[i].isChecked())
                s+= checkBoxes[i].getText() + ", ";
        }
        return s;
    }

    private void establishReferences(){
        buttons[1] = (Button)findViewById(R.id.buttonS1);
        buttons[2] = (Button)findViewById(R.id.buttonS2);
        buttons[3] = (Button)findViewById(R.id.buttonS3);
        buttons[4] = (Button)findViewById(R.id.buttonS4);
        buttons[5] = (Button)findViewById(R.id.buttonS5);
        buttons[6] = (Button)findViewById(R.id.buttonS6);
        buttons[7] = (Button)findViewById(R.id.buttonS7);
        buttons[8] = (Button)findViewById(R.id.buttonS8);
        buttons[9] = (Button)findViewById(R.id.buttonS9);
        buttons[10] = (Button)findViewById(R.id.buttonS10);
        buttons[11] = (Button)findViewById(R.id.buttonS11);
        buttons[12] = (Button)findViewById(R.id.buttonS12);
        buttons[13] = (Button)findViewById(R.id.buttonB1);
        buttons[14] = (Button)findViewById(R.id.buttonB2);
        buttons[15] = (Button)findViewById(R.id.buttonB3);
        buttons[16] = (Button)findViewById(R.id.buttonB4);
        buttons[17] = (Button)findViewById(R.id.buttonB5);
        buttons[18] = (Button)findViewById(R.id.buttonB6);
        buttons[19] = (Button)findViewById(R.id.buttonB7);
        buttons[20] = (Button)findViewById(R.id.buttonB8);
        buttons[21] = (Button)findViewById(R.id.buttonB9);
        buttons[22] = (Button)findViewById(R.id.buttonB10);
        buttons[23] = (Button)findViewById(R.id.buttonB11);
        buttons[24] = (Button)findViewById(R.id.buttonB12);


        editTexts[1] = (EditText)findViewById(R.id.editTextS1);
        editTexts[2] = (EditText)findViewById(R.id.editTextS2);
        editTexts[3] = (EditText)findViewById(R.id.editTextS3);
        editTexts[4] = (EditText)findViewById(R.id.editTextS4);
        editTexts[5] = (EditText)findViewById(R.id.editTextS5);
        editTexts[6] = (EditText)findViewById(R.id.editTextS6);
        editTexts[7] = (EditText)findViewById(R.id.editTextB1);
        editTexts[8] = (EditText)findViewById(R.id.editTextB2);
        editTexts[9] = (EditText)findViewById(R.id.editTextB3);
        editTexts[10] = (EditText)findViewById(R.id.editTextB4);
        editTexts[11] = (EditText)findViewById(R.id.editTextB5);
        editTexts[12] = (EditText)findViewById(R.id.editTextB6);

        checkBoxes[1] = (CheckBox)findViewById(R.id.checkBox1);
        checkBoxes[2] = (CheckBox)findViewById(R.id.checkBox2);
        checkBoxes[3] = (CheckBox)findViewById(R.id.checkBox3);
        checkBoxes[4] = (CheckBox)findViewById(R.id.checkBox4);
        checkBoxes[5] = (CheckBox)findViewById(R.id.checkBox5);
        checkBoxes[6] = (CheckBox)findViewById(R.id.checkBox6);
        checkBoxes[7] = (CheckBox)findViewById(R.id.checkBox7);
        checkBoxes[8] = (CheckBox)findViewById(R.id.checkBox8);

        matchNumber = (EditText)findViewById(R.id.editTextMatchNumber);
        teamNumber = (EditText)findViewById(R.id.editTextTeamNumber);

    }
}
