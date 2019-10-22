package serialization;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.google.protobuf.InvalidProtocolBufferException;

public class ProtoBufUtil implements Serializable{

    private FirstProtobuf.testBuf.Builder builder;
    private ByteArrayOutputStream byteArrayOutputStream;
    
    public ProtoBufUtil() {
        builder = FirstProtobuf.testBuf.newBuilder();
        byteArrayOutputStream = new ByteArrayOutputStream();
    }
    
    @Override
    public void serialize() {
        createTestObject();
        byteArrayOutputStream.reset();
        FirstProtobuf.testBuf info = builder.build();
        try {
            info.writeTo(byteArrayOutputStream);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            byte[] result = info.toByteArray();
            System.out.println("google protobuf====="+result.length);
            FirstProtobuf.testBuf testBuf = FirstProtobuf.testBuf.parseFrom(result);
            System.out.println(testBuf);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        
    }

    private void createTestObject() {
        builder.clear();
        builder.setID(1);
        builder.setUrl("www.google.com");
        builder.addName("aaa");
        builder.addName("bbb");
        builder.addName("ccc");
    }

    @Override
    public void deserialize() {
    }

}
