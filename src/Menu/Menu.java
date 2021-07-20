package Menu;
import java.sql.*;
import login.*;
import java.util.*;

import Database.Database;
import Tool.*;
public class Menu {
	private static Connection conn = Database.getConnection();
    private static Scanner console = new Scanner(System.in);


    public static void main() {
        System.out.println("\n  ��ӭ�����׶�԰����ϵͳ!\n ");
        show();
    }

    private static void createStu(){
        PreparedStatement pre;
        String ID, Name, Grade, ClassNum, Sex, Birthday, Address;

        System.out.println("\n---------���ѧ��---------");
        System.out.println("������ѧ�ţ�");
        ID = console.next();
        while (Tool.confirm(ID, "students")) {
            System.out.println("[��ѧ���Ѵ���]  ��������/�ո�+�س�������");
            if((ID = Tool.back()) == null)
                return;
        }
        System.out.println("������������");
        Name = console.next();
        System.out.println("�������꼶��");
        Grade = console.next();
        System.out.println("������༶��");
        ClassNum = console.next();
        System.out.println("�������Ա�");
        Sex = console.next();
        System.out.println("������������ڣ�");
        Birthday = console.next();
        /*while(!Tool.isDate(Birthday)) {
        	System.out.println("��������ȷ���ڸ�ʽ��");
        	Birthday = console.next();
        }*/
        System.out.println("�������ͥסַ��");
        Address = console.next();
     

        String sql = "INSERT students (ID, Name, Grade, ClassNum, Sex, Birthday, Address)" + "VALUES(?,?,?,?,?,?,?)";
        try{
            pre = conn.prepareStatement(sql);
            pre.setString(1, ID);
            pre.setString(2, Name);
            pre.setString(3, Grade);
            pre.setString(4, ClassNum);
            pre.setString(5, Sex);
            pre.setString(6, Birthday);
            pre.setString(7, Address);

            pre.execute();
            System.out.println("[�����ɹ�!]");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void createMeal() {
        PreparedStatement pre;
        String m_ID, staple_food, dish_1, dish_2, soup;

        System.out.println("\n---------������ײ�---------");
        System.out.println("�������ײͱ�ţ�");
        m_ID = console.next();
        while (Tool.confirm(m_ID, "menu_mode")) {
            System.out.println("[�ײ��ظ�]  ��������/�ո�+�س�������");
            if((m_ID = Tool.back()) == null)
                return;
        }
        System.out.println("�������ײ���ʳ��");
        staple_food = console.next();
        System.out.println("��������ˢ�");
        dish_1 = console.next();
        System.out.println("��������ˢ�");
        dish_2 = console.next();
        System.out.println("��������Ʒ��");
        soup = console.next();

        String sql = "INSERT menu_mode (m_ID, staple_food, dish_1, dish_2, soup)" + "VALUES(?,?,?,?,?)";
        try{
            pre = conn.prepareStatement(sql);
            pre.setString(1, m_ID);
            pre.setString(2, staple_food);
            pre.setString(3, dish_1);
            pre.setString(4, dish_2);
            pre.setString(5, soup);

            pre.execute();
            System.out.println("[�����ɹ�!]");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void createCourse(){
        String CourseID, CourseName;
        PreparedStatement pre;

        System.out.println("\n---------�����γ�---------");
        System.out.println("������γ̱�ţ�");
        CourseID = console.next();
        while (Tool.confirm(CourseID, "course")){
            System.out.println("[��ѧ��������]  ��������/�ո�+�س�������");
            if((CourseID = Tool.back()) == null)
                return;
        }
        System.out.println("������γ����ƣ�");
        CourseName = console.next();

        String sql = "INSERT INTO course (CourseID, CourseName)" + "VALUES(?,?)";
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, CourseID);
            pre.setString(2, CourseName);
            pre.execute();
            System.out.println("[�����ɹ�]��");
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void creatInfo(){
        int count;
        String ID, meal;
        PreparedStatement pre;

        System.out.println("\n---------�����Ϣ---------");
        System.out.println("������ѧ�ţ�");
        ID = console.next();
        while(true){
            count = 0;
            if (!(Tool.confirm(ID, "students")))
                System.out.println("[��ѧ��������]  ��������/�ո�+�س�������");
            else if(Tool.confirm(ID, "combo"))
                System.out.println("[��ѧ��������Ϣ]  ��������/�ո�+�س�������");
            else
                count++;
            if(count == 0){
                if((ID = Tool.back()) == null)
                    return;
            }
            else
                break;
        }

        System.out.println("\n��ѡ���ײ� [������]��");
        Tool.showMeal();
        meal = console.next();
        while(!(Tool.confirm(meal, "menu_mode"))){
            System.out.println("[���ײͲ�����]  ��������/�ո�+�س������� ");
            if((meal = Tool.back()) == null)
                return;
        }
        if(!infoMatch(ID))
            return;

        try {
            String sql = "INSERT INTO combo(ID, lunch) " + "VALUES(?, ?)";
            pre = conn.prepareStatement(sql);
            pre.setString(1, ID);
            pre.setString(2, meal);
            pre.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("[�����ɹ�]��");
    }

      public static boolean infoMatch(String ID){
        int tag = 0;
        String temp, sql;
        PreparedStatement pre;
        int xuhao;
        List<String> course = new ArrayList<>();
        Set<String> set = new HashSet<>();

        System.out.println("\n��ѡ��4�ſγ� [������]��");

        while(true) {
            Tool.showCourse();
            for (int i = 0; i < 4; i++) {
                //console.useDelimiter("\n");
                temp = console.next();//).replace(" ", "");
                course.add(temp);
                if (course.get(0).equals(""))
                    return false;
            }

            for (int i = 0; i < 4; i++) {
                if (!(Tool.confirm(course.get(i), "course"))) {
                    System.out.println("[���ֿγ̲�����]  ��������/�ո�+�س�������");
                    if((ID = Tool.back()) == null)
                        return false;
                    tag++;
                    break;
                }
            }
            if(tag == 0) {
                for (String s : course) {
                    if (set.contains(s)) {
                        System.out.println("[�����ظ��γ�]  ��������/�ո�+�س�������");
                        if((ID = Tool.back()) == null)
                            return false;
                           
                        tag++;
                        break;
                    } else
                        set.add(s);
                }
            }
            if(tag == 0)
                break;
            else {
                tag = 0;
                set.clear();
                course.clear();
            }
        }

        //ʵ��ɾ��ԭ�м�¼�Ĳ���
        try {
            sql = "DELETE FROM info WHERE ID = '" + ID + "'";
            pre = conn.prepareStatement(sql);
            pre.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }

        for(int i = 0; i < 4; i++){
            sql = "INSERT INTO info(ID, CourseID) " + "VALUES(?, ?)";
            try {
                pre = conn.prepareStatement(sql);
                pre.setString(1, ID);
                pre.setString(2, course.get(i));
                pre.execute();
                System.out.println("����һ�ο�");
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        return true;
    }

    public static void showMenu() {
        int choice;
        while (true){
            showFirstMenu();
            while (true){
                try{
                    choice = console.nextInt();
                    if(choice < 1 || choice > 6)
                        throw new Exception();
                    break;
                }catch (Exception e){
                    System.out.println("������'1'~'6':");
                }
            }
            switch (choice){
                case 1:
                    rollMenu();
                    break;
                case 2:
                    mealMenu();
                    break;
                case 3:
                    courseMenu();
                    break;
                case 4:
                    infoMenu();
                    break;
                case 5:
                    searchMenu();
                    break;
                default:
                	System.out.println("��л��ʹ�ñ�ϵͳ  ��ӭ�´�ʹ�ã�");
                    System.exit(0);
            }
        }
    }

    private static void rollMenu(){
        int choice;
        while(true){
            showRollMenu();

            while(true){
                try{
                    choice = console.nextInt();
                    if(choice < 1 || choice > 5)
                        throw new Exception();
                    break;
                } catch (Exception e) {
                    System.out.println("������'1'~'5':");
                }
            }
            switch (choice){
                case 1:
                    createStu();
                    break;
                case 2:
                    Tool.deleteStu();
                    break;
                case 3:
                    Tool.updateStu();
                    break;
                case 4:
                    Tool.showStu();
                    break;
                default:
                    return;
            }
        }
    }

    private static void mealMenu() {
    	int choice;

        while(true){
            showMealMenu();     //��ʾ��ʳ�����ֲ˵�

            while(true){
                try{
                    choice = console.nextInt();  //�ж����������Ƿ�Խ��
                    if(choice < 1 || choice > 5)
                        throw new Exception();
                    break;
                } catch (Exception e) {
                    System.out.println("������'1'~'5':");
                }
            }
            switch (choice){                //����ѡ�����ֽ�����Ӧ����
                case 1:                     //����ײ�
                    createMeal();
                    break;
                case 2:                     //ɾ���ײ�
                    Tool.deleteMeal();
                    break;
                case 3:                     //�޸��ײ�
                    Tool.updateMeal();
                    break;
                case 4:                     //�ײ��б�
                    Tool.showMeal(); 
                    break;
                default:                    //������һ��
                    return; 
            }
        }
    }

    private static void courseMenu(){
    	int choice;
        while (true) {
            showCourseMenu();

            while (true) {
                try {
                    choice = console.nextInt();
                    if (choice < 1 || choice > 4)
                        throw new Exception();
                    break;
                } catch (Exception e) {
                    System.out.println("������'1'~'4':");
                }
            }

            switch (choice) {
                case 1:
                    createCourse();
                    break;
                case 2:
                    Tool.deleteCourse();
                    break;
                case 3:
                    Tool.showCourse();
                    break;
                default:
                    return;
            }
        }
    }

    private static void infoMenu(){
    	int choice;
        while(true){
            showInfoMenu();

            while(true){
                try{
                    choice = console.nextInt();
                    if(choice < 1 || choice > 4)
                        throw new Exception();
                    break;
                } catch (Exception e) {
                    System.out.println("������'1'~'4':");
                }
            }
            switch (choice){
                case 1:
                    creatInfo();
                    break;
                case 2:
                    Tool.updateInfo();
                    break;
                default:
                    return;
            }
        }
    }

    public static void searchMenu(){
    	int choice;
        while(true){
            showSearchMenu();

            while(true){
                try{
                    choice = console.nextInt();
                    if(choice < 1 || choice > 3)
                        throw new Exception();
                    break;
                } catch (Exception e) {
                    System.out.println("������'1'~'4':");
                }
            }
            switch (choice){
                case 1:
                    Tool.classInfoFind();
                    break;
                case 2:
                    Tool.stuInfoFind(null);
                    break;
                default:
                    return;
            }
        }
    }

    public static void showFirstMenu(){
        System.out.println("\n-----------ѡ��-----------");
        System.out.println("1.ѧ������");
        System.out.println("2.ʳ�׹���");
        System.out.println("3.�γ̹���");
        System.out.println("4.��Ϣ�޸�");
        System.out.println("5.��ѯ");
        System.out.println("6.�˳�");
        System.out.println("������ѡ���Ӧ�����:");
    }

    private static void showRollMenu(){
        System.out.println("\n---------ѧ������---------");
        System.out.println("1.���ѧ��");
        System.out.println("2.ɾ��ѧ��");
        System.out.println("3.ѧ���޸�");
        System.out.println("4.ѧ���б�");
        System.out.println("5.������һ��");
        System.out.println("������ѡ���Ӧ�����:");
    }

    private static void showMealMenu(){
        System.out.println("\n---------��ʳ����---------");
        System.out.println("1.����ײ�");
        System.out.println("2.ɾ���ײ�");
        System.out.println("3.�޸��ײ�");
        System.out.println("4.�ײ��б�");
        System.out.println("5.������һ��");
        System.out.println("������ѡ���Ӧ�����:");
    }

    private static void showCourseMenu(){
        System.out.println("\n---------�γ̹���---------");
        System.out.println("1.��ӿγ�");
        System.out.println("2.ɾ���γ�");
        System.out.println("3.�γ��б�");
        System.out.println("4.������һ��");
        System.out.println("������ѡ���Ӧ�����:");
    }

    private static void showInfoMenu(){
        System.out.println("\n---------���Ϲ���---------");
        System.out.println("1.�����Ϣ");
        System.out.println("2.��Ϣ�޸�");
        System.out.println("3.������һ��");
        System.out.println("������ѡ���Ӧ�����:");
    }

    private static void showSearchMenu(){
        System.out.println("\n-----------��ѯ-----------");
        System.out.println("1.�༶��Ϣ��ѯ");
        System.out.println("2.ѧ����Ϣ��ѯ");
        System.out.println("3.������һ��");
        System.out.println("������ѡ���Ӧ�����:");
    }
    private static void show() {
    	
    	do{System.out.println("1.ע�����Ա 2.ע��ǹ���Ա  3.����Ա��½ 4.�ǹ���Ա��½ 5.����#�����ϼ�");
    	int choice=console.nextInt();
    	man m=new man();
    	superman s=new superman();
    	switch(choice)
    	{
    	case 1:
    		s.createman();
    		break;
    	case 2:
    		m.createman();
    		break;
    	case 3:
    		
    		if(s.check()!=true)
    			{
    			System.out.println("�û������������");
    			break;
    			}
    		s.showmenu();
    		break;
    	case 4:
    		//m.check();
    		if(m.check()!=true)
    			{
    			System.out.println("�û������������");
    			break;
    			}
    		m.showmenu();
    		break;
    	case 5:
    		System.exit(0);
    	}}while(console.next().equals("#"));
    }
}
