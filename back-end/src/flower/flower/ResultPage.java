package flower.flower;

import java.lang.reflect.Field;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;

public class ResultPage extends Activity {
    
    private TextView textView_marquee;                            //�����
    private TextView textView_fuwaImage;                       //����ͼƬ
    private TextView textView_fuwaText;                           //�������޵�����
    
    public int getResourceId(String name) {                          //��ȡ����ͼƬ��Դid
        try {            
            Field field = R.drawable.class.getField(name);                   //������Դid�ı��������Field����ʹ�÷��������ʵ��            
            return Integer.parseInt(field.get(null).toString());          //ȡ�ò�������Դid���ֶ�ֵ����̬������
        }
        catch (Exception e) {
            //TODO
        }
        return 0;
    }

    public void showTextViewMarquee() {                               //��ʾ�����
        String html_marquee = 
                "������Ŀ�ı������˻�<a href = ��http://www.baidu.com��>������</a>" +              //�����ӡ�������ֶε��ٶ�
                "�ڱ���ʱ��11��20:18��ʽ������" +
                "���˼����︣�ޣ�����Ϊ�㡢��è������ʥ�𡢲��������ӣ�" +
                "�����Ǳ�����������������ӭӭ�����ݣ���������ӭ��";
        CharSequence charSequence_marquee = Html.fromHtml(html_marquee);
        textView_marquee.setText(charSequence_marquee);
        textView_marquee.setMovementMethod(LinkMovementMethod.getInstance());    //���ʱ����������Ч��
    }
    
    public void showFuWaImage() {                                            //��ʾ����ͼƬ        
        String html_fuwa = "<img src = 'beibei' />" +
                                                 "<img src = 'jingjing' />" +
                                                 "<a href = 'http://www.baidu.com'><img src = 'huanhuan' /></a>" +      //�����ӡ�������ͼƬ���ٶ�
                                                 "<img src = 'yingying' />" + 
                                                 "<img src = 'nini' />";    
        
        CharSequence charSequence_fuwa = Html.fromHtml(html_fuwa, new ImageGetter() {              //��ͼƬ���ص��ַ�������
               public Drawable getDrawable (String source) {                 
                   Drawable drawable = getResources().getDrawable(getResourceId(source));                   //���ͼƬ��Դ��Ϣ                   
                   if (source.equals("beibei") || source.equals("nini")) {                             //��ͼƬ���������͡����ݡ�ѹ����ԭͼƬ�ߴ��80%
                       drawable.setBounds(0, 0, drawable.getIntrinsicWidth()*4/5, drawable.getIntrinsicHeight()*4/5);
                   } else if (source.equals("huanhuan")) {                                                           //��ͼƬ���������Ŵ�ԭͼƬ�ߴ��120%
                       drawable.setBounds(0, 0, drawable.getIntrinsicWidth()*5/4, drawable.getIntrinsicHeight()*5/4);
                   } else {                                                                                                                               //��������ͼƬ����ԭͼƬ�ߴ�
                       drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                   }
                   return drawable;
               }             
        }, null);    
        
        textView_fuwaImage.setText(charSequence_fuwa);                   //���ַ����м��ص�textView�ؼ���
        textView_fuwaImage.setMovementMethod(LinkMovementMethod.getInstance());
    }
    
    public void showFuWaText() {                                                                    //��ʾ�������޵�����
        textView_fuwaText.setTextColor(Color.BLACK);
        textView_fuwaText.setBackgroundColor(Color.WHITE);
        textView_fuwaText.setTextSize(15);
        String string_fuwaText = 
                "         �����ޡ���������˻������ޣ����ǵ�ԭ�ͺ�ͷ���̺����뺣��ɭ�֡��𡢴�غ���յ���ϵ��" +
                "Ӧ�����й���ͳ�����ı��ַ�ʽ��չ���˲��õ��й��Ļ��Ĳ����" +
                "�������˻Ἢ�����ÿ�����޶�������һ�����õ�ףԸ��" +
                "�����������١������������֡������������顢ӭӭ���������������������ˡ�" +
                "������޷ֱ�С�������������������������������ӭӭ���������ݡ�����ȡ���������е�һ�����д���������г����������ӭ�㡱��";
        textView_fuwaText.setText(string_fuwaText);
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        textView_marquee = (TextView)this.findViewById(R.id.textview_marquee);
        textView_fuwaImage = (TextView)this.findViewById(R.id.textview_fuwaImage);
        textView_fuwaText = (TextView)this.findViewById(R.id.textview_fuwaText);
              
        showTextViewMarquee();                       //��ʾ�����
        showFuWaImage();                                     //��ʾ����ͼƬ
        showFuWaText();                                         //��ʾ�������޵�����
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_result, menu);
        return true;
    }
   
}