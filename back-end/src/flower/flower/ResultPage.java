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
    
    private TextView textView_marquee;                            //跑马灯
    private TextView textView_fuwaImage;                       //福娃图片
    private TextView textView_fuwaText;                           //描述福娃的文字
    
    public int getResourceId(String name) {                          //获取福娃图片资源id
        try {            
            Field field = R.drawable.class.getField(name);                   //根据资源id的变量名获得Field对象，使用反射机制来实现            
            return Integer.parseInt(field.get(null).toString());          //取得并返回资源id的字段值（静态变量）
        }
        catch (Exception e) {
            //TODO
        }
        return 0;
    }

    public void showTextViewMarquee() {                               //显示跑马灯
        String html_marquee = 
                "万众瞩目的北京奥运会<a href = ‘http://www.baidu.com’>吉祥物</a>" +              //超链接“吉祥物”字段到百度
                "于北京时间11日20:18正式揭晓，" +
                "奥运吉祥物福娃：形象为鱼、熊猫、奥运圣火、藏羚羊、燕子，" +
                "名字是贝贝、晶晶、欢欢、迎迎、妮妮，即北京欢迎你";
        CharSequence charSequence_marquee = Html.fromHtml(html_marquee);
        textView_marquee.setText(charSequence_marquee);
        textView_marquee.setMovementMethod(LinkMovementMethod.getInstance());    //点击时产生超链接效果
    }
    
    public void showFuWaImage() {                                            //显示福娃图片        
        String html_fuwa = "<img src = 'beibei' />" +
                                                 "<img src = 'jingjing' />" +
                                                 "<a href = 'http://www.baidu.com'><img src = 'huanhuan' /></a>" +      //超链接“欢欢”图片到百度
                                                 "<img src = 'yingying' />" + 
                                                 "<img src = 'nini' />";    
        
        CharSequence charSequence_fuwa = Html.fromHtml(html_fuwa, new ImageGetter() {              //将图片加载到字符序列中
               public Drawable getDrawable (String source) {                 
                   Drawable drawable = getResources().getDrawable(getResourceId(source));                   //获得图片资源信息                   
                   if (source.equals("beibei") || source.equals("nini")) {                             //将图片“贝贝”和“妮妮”压缩到原图片尺寸的80%
                       drawable.setBounds(0, 0, drawable.getIntrinsicWidth()*4/5, drawable.getIntrinsicHeight()*4/5);
                   } else if (source.equals("huanhuan")) {                                                           //将图片“欢欢”放大到原图片尺寸的120%
                       drawable.setBounds(0, 0, drawable.getIntrinsicWidth()*5/4, drawable.getIntrinsicHeight()*5/4);
                   } else {                                                                                                                               //其他福娃图片保持原图片尺寸
                       drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                   }
                   return drawable;
               }             
        }, null);    
        
        textView_fuwaImage.setText(charSequence_fuwa);                   //将字符序列加载到textView控件中
        textView_fuwaImage.setMovementMethod(LinkMovementMethod.getInstance());
    }
    
    public void showFuWaText() {                                                                    //显示描述福娃的文字
        textView_fuwaText.setTextColor(Color.BLACK);
        textView_fuwaText.setBackgroundColor(Color.WHITE);
        textView_fuwaText.setTextSize(15);
        String string_fuwaText = 
                "         “福娃”是五个拟人化的娃娃，他们的原型和头饰蕴含着与海洋、森林、火、大地和天空的联系，" +
                "应用了中国传统艺术的表现方式，展现了灿烂的中国文化的博大精深。" +
                "北京奥运会吉祥物的每个娃娃都代表着一个美好的祝愿：" +
                "贝贝象征繁荣、晶晶象征欢乐、欢欢象征激情、迎迎象征健康、妮妮象征好运。" +
                "五个福娃分别叫“贝贝”、“晶晶”、“欢欢”、“迎迎”、“妮妮”。各取它们名字中的一个字有次序的组成了谐音“北京欢迎你”。";
        textView_fuwaText.setText(string_fuwaText);
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        textView_marquee = (TextView)this.findViewById(R.id.textview_marquee);
        textView_fuwaImage = (TextView)this.findViewById(R.id.textview_fuwaImage);
        textView_fuwaText = (TextView)this.findViewById(R.id.textview_fuwaText);
              
        showTextViewMarquee();                       //显示跑马灯
        showFuWaImage();                                     //显示福娃图片
        showFuWaText();                                         //显示描述福娃的文字
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_result, menu);
        return true;
    }
   
}