package other;

public class HtmlDecode {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String str = "<a href='http://www.qq.com'>QQ</a><script>";
        System.out.println(org.springframework.web.util.HtmlUtils.htmlEscape(str));
        
        String str1 = "生活&bull;读书&bull;新知三联书店有限公司";
        System.out.println(org.springframework.web.util.HtmlUtils.htmlUnescape(str1));
    }

}
