package com.example.base.commons.lang;

        import org.apache.commons.text.similarity.JaccardSimilarity;
        import org.apache.commons.text.similarity.JaroWinklerDistance;

public class StringUtils {

    public static void main(String[] args){

        JaroWinklerDistance distance = new JaroWinklerDistance();
        System.out.println(distance.apply("中华人民共和国", "中华民国"));

        JaccardSimilarity similarity = new JaccardSimilarity();
        System.out.println(similarity.apply("中华人民共和国","中华民国"));

    }
}
