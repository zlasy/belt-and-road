package serialization;

import com.alibaba.fastjson.JSON;

public class TestObject {

    public String field1;
    public String field2;
    public String field3;
    public String field4;
    public String field5;
    public String field6;
    public String field7;
    public String field8;
    public String field9;
    public String field10;
    public String field11;
    public String field12;
    public String field13;
    public String field14;
    public String field15;

    public static TestObject createTestObject() {
        TestObject testObject = new TestObject();
        testObject.field1 = "field1";
        testObject.field2 = "field2";
        testObject.field3 = "field3";
        testObject.field4 = "field4";
        testObject.field5 = "field5";
        testObject.field6 = "field6";
        testObject.field7 = "field7";
        testObject.field8 = "field8";
        testObject.field9 = "field9";
        testObject.field10 = "field10";
        testObject.field11 = "field11";
        testObject.field12 = "field12";
        testObject.field13 = "field13";
        testObject.field14 = "field14";
        testObject.field15 = "field15";
        return testObject;
    }

    public static byte[] createTestJSON() {
        return JSON.toJSONBytes(createTestObject());
    }
    
    public static byte[] createTestBytes() {
        return JSON.toJSONBytes(createTestObject());
    }
}
