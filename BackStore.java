import java.io.RandomAccessFile;
import java.io.*;


public class BackStore{

 
        /**
         * function of getDate
         * 
         * @param pageNum        page number
         */
        public static int[] getData(int pageNum){
                byte[] value = new byte[256]; //256칸짜리 value라는 이름의 새 array 생성
        int[] result = new int[256]; //result 라는 이름의 256칸 int array 생성
                File fileName; 
                RandomAccessFile disk = null;
                try{
                        fileName = new File("BACKING_STORE");
                    disk = new RandomAccessFile(fileName, "r"); //새 랜덤액세스파일을 만들고 r=읽기전용으로 만든다


            // seek in byte
                        disk.seek(pageNum*256); //디스크에서 해당 위치 찾기??// 여기를 128로 바꾸면 되려나???
            disk.read(value); // 디스크에서 현재포인터위치(pageNum*256)에서부터 바이트 배열길이(256)만큼 읽어들임 

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
                System.arraycopy(getData(100),0,i,0,32);//getData(100) array 에서 데이터를 0위치 부터 복사해와서 i array 0위치부터 32위치까지 복사해서 붙여넣음
                for(int j=0;j<32;j++)
                        System.out.println(i[j]);


                System.arraycopy(getData(255),0,i,0,32);
                for(int j=0;j<32;j++)
                        System.out.println(i[j]);
        }
}

