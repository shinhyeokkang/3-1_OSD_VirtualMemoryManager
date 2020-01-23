import java.util.*;
import java.lang.*;


public class TLB{


        /**
         * Hashtable table
         * used to implement the TLB
         */
    Hashtable table;
    LinkedList <Integer> list = new LinkedList<Integer>(); //tlb�� ���� ������� ť�� �����


    public TLB(){//����ü
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
        if(this.table.containsKey(p_num)){// TLB ���� �ش� Ű�� ������ �ִ��� ���� Ȯ��
            return (int) this.table.get(p_num);//������� ���� ��ȯ����
        }
        else{
            return -1; // ������ -1 ��ȯ
        }
    }


        /**
         * function of put
         * 
         * @param p_num                page number
         * @param f_num                frame number
         */
    public void put(int p_num, int f_num){//TLB�� �� ���� �Է��ϴ� �޼ҵ�
        // we need to do two things:
        // 1. delete the oldest data in TLB
        // 2. insert new data
        //
        // oldest is determined by the Queue this.list//���� ������ �����ͺ��� �����ϰ� �ű� �����ϴµ�, ��������ȵ����ʹ� ť �� �̿��� ����




        // get the first item in queue
        // may return null, so we needs Integer
        Integer i = this.list.poll();//����Ʈ���� ���� ���������� ������
        if(i != null){
            // remove this item from hashtable
            this.table.remove(i.intValue()); //���� ���� �� �� i ����
        }


        this.list.add(p_num); //ť�� �� �ּ� ����
        this.table.put(p_num, f_num); //ť�� �� logical�ּ�, physical�ּ� ����?
    }
 

    public static void main(String[] args){
        TLB tlb = new TLB();//����ü ����
        for(int i=0; i<=16; i++){
            tlb.put(i,i);
        }
        System.out.println(tlb.get(0));//�� ������� �� ����(-1) �� �����°�?? => �� for ������ ������ ������ 0-16���� 17���̹Ƿ�, ó�� ���� (0,0) ���� �����ǰ� (16,16)���� ��ü��. �װ� �����ִ� ����.
       
        //System.out.println(tlb.get(15));
        //System.out.println(tlb.get(17));
    }
}

