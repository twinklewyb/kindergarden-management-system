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
        System.out.println("\n---------注册---------");
        System.out.println("请输入ID：");
        ID = console.next();
        while (Tool.confirm(ID, "log")) {
        	//System.out.println("hhhh");
        	System.out.println("[此ID已存在]  重新输入#返回");
            //if((console.next()) == "#")
            //{
            	//System.out.println("hhhh");
                return false;
           // }
        }
        System.out.println("请输入密码：");
        code = console.next();
        guanli="否";

        String sql = "INSERT into log(ID, code, guanli) " + "VALUES (?,?,?)";
        try{
            pre = conn.prepareStatement(sql);
            pre.setString(1, ID);
            pre.setString(2, code);
            pre.setString(3, guanli);

            pre.execute();
            System.out.println("[创建成功!]");
        }catch (SQLException e){
            e.printStackTrace();
        }
		return true;
	}

	@Override
	public boolean check() {
		// TODO Auto-generated method stub
		System.out.println("\n---------登陆---------");
		System.out.println("请输入ID");
		String ID_C=console.next();
		System.out.println("请输入密码");
		String code_c=console.next();
		 if (!Tool.confirm(ID_C, "log")) {
	            System.out.println("[此ID不存在]  重新输入#返回");
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
    
           System.out.println("请选择1.查询 2.退出");
           choice=console.nextInt();
         
            switch (choice){
               
                case 1:
                    Menu.searchMenu();
                    break;
                default:
                	System.out.println("感谢您使用本系统  欢迎下次使用！");
                    System.exit(0);
            }
            
    
	}
}
