package www.aql.com.thirdplatform.wechat;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

import www.aql.com.entity.response.OrderResult;

public class XMLParserHelper {
    /**
     * PULL解析xml数据
     *
     * @param is
     * @return
     */
    public static OrderResult parseXml(InputStream is) {
        XmlPullParser parser = Xml.newPullParser();
        OrderResult orderResult = null;
        try {
            parser.setInput(is, "UTF-8");
            int type = parser.getEventType();
            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        String name = parser.getName();
                        if (name.equals("xml")) {
                            orderResult = new OrderResult();
                        } else if (name.equals("return_code")) {
                            orderResult.setReturn_code(parser.nextText());
                        } else if (name.equals("return_msg")) {
                            orderResult.setReturn_msg(parser.nextText());
                        } else if (name.equals("appid")) {
                            orderResult.setAppid(parser.nextText());
                        } else if (name.equals("mch_id")) {
                            orderResult.setMch_id(parser.nextText());
                        } else if (name.equals("device_info")) {
                            orderResult.setDevice_info(parser.nextText());
                        } else if (name.equals("nonce_str")) {
                            orderResult.setNonce_str(parser.nextText());
                        } else if (name.equals("sign")) {
                            orderResult.setSign(parser.nextText());
                        } else if (name.equals("result_code")) {
                            orderResult.setResult_code(parser.nextText());
                        } else if (name.equals("err_code")) {
                            orderResult.setErr_code(parser.nextText());
                        } else if (name.equals("err_code_des")) {
                            orderResult.setErr_code_des(parser.nextText());
                        } else if (name.equals("trade_type")) {
                            orderResult.setTrade_type(parser.nextText());
                        } else if (name.equals("prepay_id")) {
                            orderResult.setPrepay_id(parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }

                type = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return orderResult;
    }
}
