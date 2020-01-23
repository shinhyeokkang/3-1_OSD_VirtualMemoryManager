import java.io.RandomAccessFile;
import java.io.*;


public class BackStore{

 
        /**
         * function of getDate
         * 
         * @param pageNum        page number
         */
        public static int[] getData(int pageNum){
                byte[] value = new byte[256]; //256ĭ¥�� value��� �̸��� �� array ����
        int[] result = new int[256]; //result ��� �̸��� 256ĭ int array ����
                File fileName; 
                RandomAccessFile disk = null;
                try{
                        fileName = new File("BACKING_STORE");
                    disk = new RandomAccessFile(fileName, "r"); //�� �����׼��������� ����� r=�б��������� �����


            // seek in byte
                        disk.seek(pageNum*256); //��ũ���� �ش� ��ġ ã��??// ���⸦ 128�� �ٲٸ� �Ƿ���???
            disk.read(value); // ��ũ���� ������������ġ(pageNum*256)�������� ����Ʈ �迭����(256)��ŭ �о���� 

                        disk.close();
                }
                catch (IOException e) {
                        System.err.println ("Unable to start the disk");
                        System.exit(1);
                }
        for(int i=0; i<256; i++){
            result[i] = value[i];
        }
        return result;
        }


        public static void main(String args[]){
        	
                int[] i = new int[32];
                System.arraycopy(getData(100),0,i,0,32);//getData(100) array ���� �����͸� 0��ġ ���� �����ؿͼ� i array 0��ġ���� 32��ġ���� �����ؼ� �ٿ�����
                for(int j=0;j<32;j++)
                        System.out.println(i[j]);


                System.arraycopy(getData(255),0,i,0,32);
                for(int j=0;j<32;j++)
                        System.out.println(i[j]);
        }
}

