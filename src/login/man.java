package login;
import java.sql.*;
import java.util.Scanner;
import Menu.*;
import Database.Database;
import Tool.Tool;
public class man implements loginable{
	PreparedStatement pre;
	Scanner console = new Scanner(System.in);
	Connection conn = Database.getConnection();
    String ID, code;
	@Override
	 public boolean createman() {
		// TODO Auto-generated method stub
		
		
		
        String guanli;
        System.out.println("\n---------ע��---------");
        System.out.println("������ID��");
        ID = console.next();
        while (Tool.confirm(ID, "log")) {
        	//System.out.println("hhhh");
        	System.out.println("[��ID�Ѵ���]  ��������#����");
            //if((console.next()) == "#")
            //{
            	//System.out.println("hhhh");
                return false;
           // }
        }
        System.out.println("���������룺");
        code = console.next();
        guanli="��";

        String sql = "INSERT into log(ID, code, guanli) " + "VALUES (?,?,?)";
        try{
            pre = conn.prepareStatement(sql);
            pre.setString(1, ID);
            pre.setString(2, code);
            pre.setString(3, guanli);

            pre.execute();
            System.out.println("[�����ɹ�!]");
        }catch (SQLException e){
            e.printStackTrace();
        }
		return true;
	}

	@Override
	public boolean check() {
		// TODO Auto-generated method stub
		System.out.println("\n---------��½---------");
		System.out.println("������ID");
		String ID_C=console.next();
		System.out.println("����������");
		String code_c=console.next();
		 if (!Tool.confirm(ID_C, "log")) {
	            System.out.println("[��ID������]  ��������#����");
	            if((ID = Tool.back()) == null)
	                return false;
	        }
		 Statement sta;
	        ResultSet rs;
	        try {
	            sta = conn.createStatement();
	            rs = sta.executeQuery("SELECT code FROM log WHERE ID="+ID_C);
	            rs.next();
	                if (rs.getString(1).equals(code_c))
	                    return true;
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return false;
	}

	@Override
	public void showmenu() {
		// TODO Auto-generated method stub
		
		int choice;
    
           System.out.println("��ѡ��1.��ѯ 2.�˳�");
           choice=console.nextInt();
         
            switch (choice){
               
                case 1:
                    Menu.searchMenu();
                    break;
                default:
                	System.out.println("��л��ʹ�ñ�ϵͳ  ��ӭ�´�ʹ�ã�");
                    System.exit(0);
            }
            
    
	}
}
