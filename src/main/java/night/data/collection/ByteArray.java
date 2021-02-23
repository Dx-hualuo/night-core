package night.data.collection;

/**
 *  可伸长的基本类型byte数组
 * @author dx_hualuo
 */
public class ByteArray {
    private byte[] bytes = new byte[16];
    private int length = 16;
    private int size = 0;

    public ByteArray add(byte b){
        if(size + 1 > length){
            length *= 2;
            bytes = new byte[length];
            size++;
        }
        return this;
    }

    public byte[] getBytes(){
        return bytes;
    }
}
