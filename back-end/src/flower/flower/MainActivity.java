package flower.flower;

import flower.flower.User;
import flower.flower.R;

import android.text.TextUtils;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {
EditText etUser,etPassw,etRePassw;
RadioButton rbMale,rbfaMale;
CheckBox cbRead,cbTourist,cbPlayGame;
Spinner spCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init() {
// TODO Auto-generated method stub
    etUser = (EditText) findViewById(R.id.etUser);
    etPassw = (EditText) findViewById(R.id.etPassw);
    etRePassw = (EditText) findViewById(R.id.etRePassw);
    rbMale =  (RadioButton) findViewById(R.id.rbMale);
    rbfaMale =  (RadioButton) findViewById(R.id.rbfaMale);
    cbRead = (CheckBox) findViewById(R.id.cbRead);
    cbTourist = (CheckBox) findViewById(R.id.cbTourist);
    cbPlayGame = (CheckBox) findViewById(R.id.cbPlayGame);
}
public void onclick(View view){
    switch(view.getId()){
    
    case R.id.btRegister:
    String userName = 
etUser.getText().toString();
    if(TextUtils.isEmpty(userName)){
    Toast.makeText(this, "�û�������Ϊ��", 2000).show();
    return;
    }
    String passW = etPassw.getText().toString();
    if(TextUtils.isEmpty(passW)){
    etPassw.setError("���벻��Ϊ��");
    return;
    }
    String rePassw = etRePassw.getText().toString();
    if(TextUtils.isEmpty(rePassw)){
    etRePassw.setError("ȷ�����벻��Ϊ��");
    return;
    }
    if(!passW.equals(rePassw)){
    Toast.makeText(this, "�������벻һ��", 2000).show();
    return ;
    }
    char sex ;
    if(rbMale.isChecked()){
    sex = rbMale.getText().charAt(0);
    }else{
    sex = rbfaMale.getText().charAt(0);
    }
    StringBuffer s = new StringBuffer();
    if(cbRead.isChecked()){
    s.append(cbRead.getText().toString()+",");
    }
    if(cbTourist.isChecked()){
      s.append(cbTourist.getText().toString()+",");
      }
    if(cbPlayGame.isChecked()){
      s.append(cbPlayGame.getText().toString()+",");
      }
    
    User user = new User(userName,passW,sex,
    s.toString().equals("")?"":s.toString().substring(0, s.length()));
    Toast.makeText(this, "�û�ע����Ϣ��"+user.toString(),100000).show();
        break;
    case R.id.btEixt:
    finish();
    break;
    }
    }

}