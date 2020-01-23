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
                 * variable of page number / logical memory를 특정 사이즈로 나눈거
                 */
                int p_num;


                /**
                 * variable of offset
                 */
                int offset;


                /**
                 * variable of frame number / physical memory를 특정 사이즈로 나눈거
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
                		addr = sc.nextInt();//int의 최대값이 65536이므로? 이상이되면 잘리기 때문에 최대 5자리숫자로 분리됨
                		// 2^16 = 4^8 = 16^4
                		// mask the high 16bit
                		addr = addr % 65536; //최대 65536미만인 숫자들로 잘라짐
                		offset = addr % 256; //256등분된 p_num 안에서 몇번째 위치인지
                		p_num = addr / 256;//디스크를 256등분


                		f_num = -1;//처음 f 값은 -1 =null 로 초기화
                		f_num = tlb.get(p_num);//먼저 tlb에서 있는지 확인
                		if(f_num == -1){//
                			tlb_miss++;
                			// frame not in TLB
                			// try page table
                			f_num = pt.get(p_num);//없으면 page table 에서 확인
                			if(f_num == -1){//pt 에도 없으면?
                				page_fault++;
                				// fraem not in page table
                				// read frame from BackStore
                				Frame f = new Frame(bs.getData(p_num));//backstore 에서 데이터를 얻어와서 frame f 에 삽입

                				// add frame to PhysicalMemory
                				f_num = pm.addFrame(f);// f 값을 physical memory f_num 에 삽입// f_num에는 주소번호만 저장됨!!!!!
                				pt.add(p_num, f_num);//이후 pt, tlb 에 값 넣어줌
                				tlb.put(p_num, f_num);
                			}
                		}


		                phy_addr = f_num * 256 + offset;//실제 physical memory 의 주소값
		                value = pm.getValue(f_num, offset); //f_num 번째에 offset 위치에있는 데이터 출력
		           


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

