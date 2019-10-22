package serialization;

public class Start {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        fastJson();
        pb();
        System.out.println(System.currentTimeMillis() - start);
    }
    
    public static void fastJson() {
        FastjsonUtil fastjson = new FastjsonUtil();
        for (int i = 0; i < Config.ITERATIONS; i++) {
            fastjson.deserialize();
        }
    }
    
    public static void pb() {
        ProtoBufUtil pb = new ProtoBufUtil();
        pb.serialize();
    }

}
