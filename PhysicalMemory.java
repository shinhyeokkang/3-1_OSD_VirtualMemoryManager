/**
 * Class to emulate the physical memory
 */
public class PhysicalMemory{
    /**
     * variable to emulate frames in memory
     */
    Frame[] frames;
    /**
     * we need a variable to store how many
     * frames are used
     */ 
    int currentFreeFrame;


    /**
     * Constructor
     */
    public PhysicalMemory(){
        this.frames = new Frame[256];//여기를 128로???
        this.currentFreeFrame = 0;
    }

 
    /**
     * function to add a new frame to memory
     *
     * @param f Frame to be added
     * @return int the frame number just added
     */
    public int addFrame(Frame f){
        this.frames[this.currentFreeFrame] = new Frame(f.data); //0에서 부터 순서대로 삽입 //여기를 바뀌야 알고리즘이 바뀌나???
        this.currentFreeFrame++;//0부터 1씩 올리며 삽입
        return this.currentFreeFrame-1;//리턴값은 저장된 위치번호
    }


    /**
     * function to get value in memory
     *
     * @param f_num int frame number
     * @param offset int offset
     * @return int content in specified location
     */
    public int getValue(int f_num, int offset){
        Frame frame = this.frames[f_num];
        return frame.data[offset];
    }


}


/**
 * wrapper class to group all frame related logics
 */
class Frame {
    /**
     * variable to store datas of this frame
     */
    int[] data;


    /**
     * Constructor
     *
     * @param d int[256] for initializing frame
     */
    public Frame(int[] d){
        this.data = new int[256];
        for(int i=0;i<256;i++){
            this.data[i] = d[i];
        }
    }


    /**
     * Copy Constructor
     *
     * @param f Frame to be copied
     */
    public Frame(Frame f){
        this.data = new int[256];
        System.arraycopy(f.data, 0, this.data, 0, 256);//f.data 의 값을 0위치부터 this.data에 0위치부터 256위치까지 복사
    }
}

