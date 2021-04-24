package HashTab;

import java.util.Scanner;
public class hashtabDemo {
	public static void main(String[] args) {
	    HashTab a=new HashTab(7);
		String key="";
		Scanner scanner=new Scanner(System.in);
		while(true) {
			System.out.println("add:加入员工信息");
			System.out.println("exit:退出程序");
			System.out.println("List:显示链表信息");
			System.out.println("case:查找雇员");
            System.out.println("请输入:");
            key=scanner.next();
            switch(key) {
            case"add":
            	System.out.println("请输入要加入员工的id:");
            	int id1=scanner.nextInt();
            	System.out.println("请输入员工的姓名:");
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
            	System.out.println("请输入要查找雇员的id:");
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
				System.out.printf("第%d链表为空!",i+1);
				System.out.println();
				return;
			}
			System.out.printf("第%d条链表:",i+1);
			while(true) {
			System.out.printf(" =>id=%d,name=%s\t=>",CurEmp.id,CurEmp.name);			
		    if(CurEmp.next==null) {
		    	System.out.println();
		    	break;
		    }
			CurEmp=CurEmp.next;	
		}
	}
	//根据id返回雇员,如果查找到返回Emp,否则返回null
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
	int size;//表示有多少条链表
	public HashTab(int size) {
		this.size=size;
		emplinkedlist=new EmpLinkedList[size];
		//初始化每一条链表
		for(int i=0;i<size;i++) {
			emplinkedlist[i]=new EmpLinkedList(); 
		}
		
	}
	public void add(Emp emp) {
		//根据员工的id得到该员工添加到哪条链表
	    int emplinkedlistno=hashfun(emp.id);
		emplinkedlist[emplinkedlistno].add(emp);
	}
	public void List(){
		for(int i=0;i<size;i++) {
		emplinkedlist[i].show(i);
		}
		
	}
	//编写散列函数,使用取模法
	public int hashfun(int id) {
		return id%size;
	}
	//根据输入的id查找雇员
	//根据散列函数确定要查找哪条链表
	public void FindEmp(int id) {
		int emplinkedlistno=hashfun(id);
		Emp emp=emplinkedlist[emplinkedlistno].findEmp(id);
		if(emp!=null) {
			System.out.printf("在第%d条链表中找到雇员%d",emplinkedlistno+1,id);
			System.out.println();
		}
		else {
			System.out.println("在哈希表中没有找到该雇员");
		}
	}
}