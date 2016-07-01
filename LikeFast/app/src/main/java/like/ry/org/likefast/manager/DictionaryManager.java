package like.ry.org.likefast.manager;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/7/1
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public class DictionaryManager {

    private String[] strExpression,strChinese,strEnglish,strAdjective,strNoun,strNick;

    public DictionaryManager(Context context){
        try{
            //expression
            String expressionData = load(context,"expression_data.txt","gb2312");
            strExpression = expressionData.split("&");
            //chinese
            String chineseData = load(context,"chinese_data.txt","gb2312");
            strChinese = chineseData.split("&");
            //english
            String englishData = load(context,"english_data.txt","gb2312");
            strEnglish = englishData.split("&");
            //adjective
            String adjectiveData = load(context,"adjective_data.txt","gb2312");
            strAdjective = adjectiveData.split("&");
            //noun
            String nounData = load(context,"noun_data.txt","gb2312");
            strNoun = nounData.split("&");
            //nick
            String nickData = load(context,"nick_data.txt","gb2312");
            strNick = nickData.split("&");
        }catch (IOException e){

        }
    }

    public rx.Observable<String> getNickName(int num){
        return Observable.range(0, num).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                int temp = getRandomNum(100);
                if(temp < 60) return strNick[getRandomNum(strNick.length)];
                if(temp < 90) return strChinese[getRandomNum(strChinese.length)];
                return strEnglish[getRandomNum(strEnglish.length)];
            }
        }).scan(new Func2<String, String, String>() {
            @Override
            public String call(String s, String s2) {
                return s+","+s2;
            }
        }).takeLast(1);
    }

    private String load(Context context,String path,String charsetName) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(context.getAssets().open(path),charsetName));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line=r.readLine())!=null){
            stringBuilder.append(line);
        }
        r.close();
        return stringBuilder.toString();
    }

    private int getRandomNum(int length){
        return (int) (Math.random() * length);
    }


}
