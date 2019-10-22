package serialization;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeWriter;

public class FastjsonUtil implements Serializable{

    private TestObject testObject;
    private ByteArrayOutputStream byteArrayOutputStream;
    private SerializeConfig serializeConfig;
    
    public FastjsonUtil() {
        testObject = TestObject.createTestObject();
        byteArrayOutputStream = new ByteArrayOutputStream();
        serializeConfig = SerializeConfig.getGlobalInstance();
    }
    
    @Override
    public void serialize() {
        byteArrayOutputStream.reset();
        SerializeWriter writer = new SerializeWriter();
        JSONSerializer serializer = new JSONSerializer(writer, serializeConfig);
        serializer.write(testObject);
        try {
            writer.writeTo(byteArrayOutputStream, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        serializer.close();
    }

    @Override
    public void deserialize() {
        JSON.parseObject(TestObject.createTestBytes(), TestObject.class);
    }
}
