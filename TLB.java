import java.util.*;
import java.lang.*;


public class TLB{


        /**
         * Hashtable table
         * used to implement the TLB
         */
    Hashtable table;
    LinkedList <Integer> list = new LinkedList<Integer>(); //tlb에 들어온 순서대로 큐에 저장됨


    public TLB(){//구조체
        this.table = new Hashtable();
        for(int i=0; i<16; i++){
            this.table.put(-i, -1);
            this.list.add(-i);
        }
    }


        /**
         * function of get
         * 
         * @param p_num        page num
         */
    public int get(int p_num){
        if(this.table.containsKey(p_num)){// TLB 에서 해당 키를 가지고 있는지 여부 확인
            return (int) this.table.get(p_num);//있을경우 값을 반환해줌
        }
        else{
            return -1; // 없을시 -1 반환
        }
    }


        /**
         * function of put
         * 
         * @param p_num                page number
         * @param f_num                frame number
         */
    public void put(int p_num, int f_num){//TLB에 새 값을 입력하는 메소드
        // we need to do two things:
        // 1. delete the oldest data in TLB
        // 2. insert new data
        //
        // oldest is determined by the Queue this.list//가장 오래된 데이터부터 삭제하고 거기 삽입하는데, 가장오래된데이터는 큐 를 이용해 선택




        // get the first item in queue
        // may return null, so we needs Integer
        Integer i = this.list.poll();//리스트에서 가장 먼저넣은거 꺼내기
        if(i != null){
            // remove this item from hashtable
            this.table.remove(i.intValue()); //가장 먼저 들어간 값 i 삭제
        }


        this.list.add(p_num); //큐에 새 주소 삽입
        this.table.put(p_num, f_num); //큐에 새 logical주소, physical주소 삽입?
    }
 

    public static void main(String[] args){
        TLB tlb = new TLB();//구조체 선언
        for(int i=0; i<=16; i++){
            tlb.put(i,i);
        }
        System.out.println(tlb.get(0));//왜 결과값이 값 없음(-1) 로 나오는것?? => 위 for 문에서 삽입한 개수가 0-16까지 17개이므로, 처음 넣은 (0,0) 값은 삭제되고 (16,16)으로 대체됨. 그걸 보여주는 예시.
       
        //System.out.println(tlb.get(15));
        //System.out.println(tlb.get(17));
    }
}

