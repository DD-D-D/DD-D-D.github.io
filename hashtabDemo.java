package HashTab;

import java.util.Scanner;
public class hashtabDemo {
	public static void main(String[] args) {
	    HashTab a=new HashTab(7);
		String key="";
		Scanner scanner=new Scanner(System.in);
		while(true) {
			System.out.println("add:����Ա����Ϣ");
			System.out.println("exit:�˳�����");
			System.out.println("List:��ʾ������Ϣ");
			System.out.println("case:���ҹ�Ա");
            System.out.println("������:");
            key=scanner.next();
            switch(key) {
            case"add":
            	System.out.println("������Ҫ����Ա����id:");
            	int id1=scanner.nextInt();
            	System.out.println("������Ա��������:");
            	String name=scanner.next();
                a.add(new Emp(id1,name));
                break;
            case"exit":
            	scanner.close();
            	System.exit(0);
            case"List":
            	a.List();
            	break;
            case"case":
            	System.out.println("������Ҫ���ҹ�Ա��id:");
            	int id2=scanner.nextInt();
            	a.FindEmp(id2);
            	break;
            	default:
            		break;
            }
			
			
		}
	}
}
class Emp{
	public int id;
	public String name;
	Emp next=null;
	public Emp(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}	
}
class EmpLinkedList{
	Emp head;
	public void add(Emp emp) {
		if(head==null) {
			head=emp;
			return;
		}
		Emp CurEmp=head;
		while(true){
			if(CurEmp.next==null) {
				break;
			}
			CurEmp=CurEmp.next;
		}
		CurEmp.next=emp;
	}
	public void show(int i) {
		   Emp CurEmp=head;		
			if(head==null) {
				System.out.printf("��%d����Ϊ��!",i+1);
				System.out.println();
				return;
			}
			System.out.printf("��%d������:",i+1);
			while(true) {
			System.out.printf(" =>id=%d,name=%s\t=>",CurEmp.id,CurEmp.name);			
		    if(CurEmp.next==null) {
		    	System.out.println();
		    	break;
		    }
			CurEmp=CurEmp.next;	
		}
	}
	//����id���ع�Ա,������ҵ�����Emp,���򷵻�null
		public Emp findEmp(int id) {
			if(head==null) {
				return null;
			}
			Emp CurEmp=head;
			while(true) {
				if(CurEmp.id==id) {
					return CurEmp;
				}
				if(CurEmp.next==null) {
					return null;
				}
				CurEmp=CurEmp.next;
			}			
		}
}
class HashTab{
	EmpLinkedList []emplinkedlist;
	int size;//��ʾ�ж���������
	public HashTab(int size) {
		this.size=size;
		emplinkedlist=new EmpLinkedList[size];
		//��ʼ��ÿһ������
		for(int i=0;i<size;i++) {
			emplinkedlist[i]=new EmpLinkedList(); 
		}
		
	}
	public void add(Emp emp) {
		//����Ա����id�õ���Ա����ӵ���������
	    int emplinkedlistno=hashfun(emp.id);
		emplinkedlist[emplinkedlistno].add(emp);
	}
	public void List(){
		for(int i=0;i<size;i++) {
		emplinkedlist[i].show(i);
		}
		
	}
	//��дɢ�к���,ʹ��ȡģ��
	public int hashfun(int id) {
		return id%size;
	}
	//���������id���ҹ�Ա
	//����ɢ�к���ȷ��Ҫ������������
	public void FindEmp(int id) {
		int emplinkedlistno=hashfun(id);
		Emp emp=emplinkedlist[emplinkedlistno].findEmp(id);
		if(emp!=null) {
			System.out.printf("�ڵ�%d���������ҵ���Ա%d",emplinkedlistno+1,id);
			System.out.println();
		}
		else {
			System.out.println("�ڹ�ϣ����û���ҵ��ù�Ա");
		}
	}
}