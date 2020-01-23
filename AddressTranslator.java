// This implementation is for Lab07 in the Operating System courses in SeoulTech
// The original version of this implementation came from UGA

import java.io.*;
import java.util.*;


public class AddressTranslator {

 
        public static void main(String[] args){
                //String inputFile = args[0];
                String inputFile = "InputFile.txt";


                /**
                 * variable of logical address
                 */
                int addr;


                /**
                 * variable of page number / logical memory�� Ư�� ������� ������
                 */
                int p_num;


                /**
                 * variable of offset
                 */
                int offset;


                /**
                 * variable of frame number / physical memory�� Ư�� ������� ������
                 */
                int f_num;


                /**
                 * variable of value stored in address
                 */
                int value;


                /**
                 * variable of physics address
                 */
                int phy_addr;


                /**
                 * variable of count of tlb miss
                 */
                int tlb_miss = 0;


                /**
                 * variable of count of page fault
                 */
                int page_fault = 0;




                try{
                	Scanner sc = new Scanner(new File(inputFile));

                	TLB tlb = new TLB();
                	PageTable pt = new PageTable();
                	PhysicalMemory pm = new PhysicalMemory();
                	BackStore bs = new BackStore();


                	while(sc.hasNextInt()){
                		addr = sc.nextInt();//int�� �ִ밪�� 65536�̹Ƿ�? �̻��̵Ǹ� �߸��� ������ �ִ� 5�ڸ����ڷ� �и���
                		// 2^16 = 4^8 = 16^4
                		// mask the high 16bit
                		addr = addr % 65536; //�ִ� 65536�̸��� ���ڵ�� �߶���
                		offset = addr % 256; //256��е� p_num �ȿ��� ���° ��ġ����
                		p_num = addr / 256;//��ũ�� 256���


                		f_num = -1;//ó�� f ���� -1 =null �� �ʱ�ȭ
                		f_num = tlb.get(p_num);//���� tlb���� �ִ��� Ȯ��
                		if(f_num == -1){//
                			tlb_miss++;
                			// frame not in TLB
                			// try page table
                			f_num = pt.get(p_num);//������ page table ���� Ȯ��
                			if(f_num == -1){//pt ���� ������?
                				page_fault++;
                				// fraem not in page table
                				// read frame from BackStore
                				Frame f = new Frame(bs.getData(p_num));//backstore ���� �����͸� ���ͼ� frame f �� ����

                				// add frame to PhysicalMemory
                				f_num = pm.addFrame(f);// f ���� physical memory f_num �� ����// f_num���� �ּҹ�ȣ�� �����!!!!!
                				pt.add(p_num, f_num);//���� pt, tlb �� �� �־���
                				tlb.put(p_num, f_num);
                			}
                		}


		                phy_addr = f_num * 256 + offset;//���� physical memory �� �ּҰ�
		                value = pm.getValue(f_num, offset); //f_num ��°�� offset ��ġ���ִ� ������ ���
		           


                       System.out.println(
                    		   String.format("Virtual address: %s Physical address: %s Value: %s", addr, phy_addr , value)
                    	);
                	}
                	
                	System.out.println(String.format("TLB miss: %s, Page Fault: %s", tlb_miss, page_fault));
                } catch(Exception e){
                e.printStackTrace();
                System.exit(0);
                }
        	}
}

