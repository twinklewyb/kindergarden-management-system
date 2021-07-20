package login;
import java.sql.*;
import java.util.Scanner;
import Menu.*;
import Database.Database;
import Tool.Tool;
public class superman implements loginable {
	PreparedStatement pre;
	Scanner console = new Scanner(System.in);
	Connection conn = Database.getConnection();
    String ID, code;
	@Override
	public boolean createman() {
		// TODO Auto-generated method stub
		 String guanli="��";
	        System.out.println("\n---------ע��---------");
	        System.out.println("������ID��");
	        ID = console.next();
	        if(Tool.confirm(ID, "log")) {
	            System.out.println("[��ID�Ѵ���]  ��������#����");
	           return false;
	        }
	        System.out.println("���������룺");
	        code = console.next();
	     

	        String sql = "INSERT log (ID, code, guanli)" + "VALUES(?,?,?)";
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
	            rs = sta.executeQuery("SELECT code,guanli FROM log WHERE ID="+ID_C);
	            rs.next();
	                if (rs.getString(1).equals(code_c)&&rs.getString(2).equals("��"))
	                {
	                	System.out.println("��½�ɹ�");
	                    return true;
	                }
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return false;
		
	}

	@Override
	public void showmenu() {
		// TODO Auto-generated method stub
		Menu.showMenu();
	}

}
