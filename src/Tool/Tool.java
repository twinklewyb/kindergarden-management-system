package Tool;
import java.util.ArrayList;
import java.util.Formatter;
//import java.awt.Menu;
import java.sql.*;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Menu.Menu;

import Database.Database;

public class Tool {
	private static Connection conn = Database.getConnection();
    private static Scanner console = new Scanner(System.in);
    private static Formatter formatter = new Formatter(System.out);
    
    /*static boolean isDate(String date){     //�ж����ڸ�ʽ�ͷ�Χ
        String rexp = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";   
        Pattern pat = Pattern.compile(rexp);      
        Matcher mat = pat.matcher(date);    
        boolean dateType = mat.matches();  
        return dateType;  
    }  */

    public static boolean confirm(String ID, String tablename) {//��������Ƿ��ظ��Լ��Ϸ�������Ϊ��ѯ�����ݣ���ѯ�ı���
        Statement sta;
        ResultSet rs;
        try {
            sta = conn.createStatement();
            rs = sta.executeQuery("SELECT * FROM " + tablename);
            while (rs.next()) {
                if (rs.getString(1).equals(ID))
                    return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void deleteStu(){
        String ID, sql = "";
        boolean flag = false;
        PreparedStatement pre;

        System.out.println("\n---------ɾ��ѧ��---------");
        System.out.println("������ѧ�ţ�");
        ID = console.next();

        while (!confirm(ID,"students")) {
        	System.out.println("[��ѧ��������]  ��������/�ո�+�س�����");
            
           
        }

        try {
                sql = "DELETE FROM students WHERE ID = '" + ID + "'";
                pre = conn.prepareStatement(sql);
                pre.execute();
                System.out.println("\n[ɾ���ɹ�]��");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateStu() {
        int choice;
        PreparedStatement pre;
        String ID, temp, sql = null;
        System.out.println("\n---------ѧ���޸�---------");
        System.out.println("������ѧ�ţ�");
        ID = console.next();

        while (!confirm(ID,"students")) {
            System.out.println("[��ѧ��������]  ��������/�ո�+�س�����");
            if((ID = Tool.back()) == null)
                return;
        }

        while (true) {
            System.out.println("1.����\t2.�꼶\t3.�༶\t4.��ͥסַ\t5.�˳�");

            while (true) {
                try {
                    System.out.println("������Ҫ�޸ĵ����ݣ�");
                    choice = console.nextInt();
                    if (choice < 1 || choice > 5)
                        throw new Exception();
                    break;
                } catch (Exception e) {
                    //console = new Scanner(System.in);
                    System.out.println("������'1'--'5'");
                }
            }
            if (choice != 5) {
                System.out.println("�������µ���Ϣ��");
                temp = console.next();
                switch (choice) {
                    case 1:
                        sql = "UPDATE students SET Name = '" + temp + "' WHERE ID = '" + ID + "'";
                        break;
                    case 2:
                        sql = "UPDATE students SET Grade = '" + temp + "' WHERE ID = '" + ID + "'";
                        break;
                    case 3:
                        sql = "UPDATE students SET ClassNum = '" + temp + "' WHERE ID = '" + ID + "'";
                        break;
                    case 4:
                        sql = "UPDATE students SET Address = '" + temp + "' WHERE ID = '" + ID + "'";
                        break;
                }
                try {
                    pre = conn.prepareStatement(sql);
                    pre.execute();
                    System.out.println("[�޸ĳɹ�]��");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else
                return;
        }
    }

    public static void deleteMeal() {
    	String meal;
        PreparedStatement pre;

        System.out.println("\n---------ɾ���ײ�---------");
        System.out.println("�������ײͱ�ţ�");
        meal = console.next();


        while (!confirm(meal,"menu_mode")) {
            System.out.println("[���ײͲ�����]  ��������/�ո�+�س�����");
            if((meal = Tool.back()) == null)
                return;
        }

        try {
                String sql = "DELETE FROM menu_mode WHERE m_ID = '" + meal + "'";
                pre = conn.prepareStatement(sql);
                pre.execute();
                System.out.println("\n[ɾ���ɹ�]��");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void updateMeal() {
    	int choice;
        PreparedStatement pre;
        String meal, temp, sql = null;
        System.out.println("\n---------�޸��ײ�---------");
        System.out.println("�������ײͱ�ţ�");
        meal = console.next();

        while (!confirm(meal,"menu_mode")) {
        	System.out.println("[���ײͲ�����]  ��������/�ո�+�س�����");
            if((meal = Tool.back()) == null)
                return;
        }

        while (true) {
            System.out.println("1.��ʳ\t2.��ˢ�\t3.��ˢ�\t4.��Ʒ\t5.�˳�");

            while (true) {
                try {
                    System.out.println("������Ҫ�޸ĵ����ݣ�");
                    choice = console.nextInt();
                    if (choice < 1 || choice > 5)
                        throw new Exception();
                    break;
                } catch (Exception e) {
                    System.out.println("������'1'--'5'");
                }
            }
            if (choice != 5) {
                System.out.println("�������µ���Ϣ��");
                temp = console.next();
                switch (choice) {
                    case 1:
                        sql = "UPDATE menu_mode SET staple_food = '" + temp + "' WHERE m_ID = '" + meal + "'";
                        break;
                    case 2:
                        sql = "UPDATE menu_mode SET dish_1 = '" + temp + "' WHERE m_ID = '" + meal + "'";
                        break;
                    case 3:
                        sql = "UPDATE menu_mode SET dish_2 = '" + temp + "' WHERE m_ID = '" + meal + "'";
                        break;
                    case 4:
                        sql = "UPDATE menu_mode SET soup = '" + temp + "' WHERE m_ID = '" + meal + "'";
                        break;
                }
                try {
                    pre = conn.prepareStatement(sql);
                    pre.execute();
                    System.out.println("[�޸ĳɹ�]��");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else
                return;
        }
    }
    
    public static void showMeal() {
        Statement sta;
        ResultSet rs;
        //int count = 0;
        System.out.println("\n--------------�ײ��б�--------------");

        try {
            sta = conn.createStatement();
            rs = sta.executeQuery("SELECT * FROM menu_mode");
            while (rs.next()){
              //  if((count++) == 0)
                  //  System.out.println("\t�ײͱ��\t��ˢ�\t��ˢ�\t��Ʒ");
                formatter.format("  %-9s%-9s%-9s%-9s%-9s\n", rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCourse(){
        String CourseID;
        PreparedStatement pre;

        System.out.println("\n---------ɾ���γ�---------");
        System.out.println("������γ̱�ţ�");

        CourseID = console.next();
        while (!(confirm(CourseID, "course"))){
            System.out.println("[�˿γ̲�����]  ��������/�ո�+�س�����");
            if((CourseID = Tool.back()) == null)
                return;
        }
        try{
            String sql = "DELETE FROM course WHERE CourseID = '" + CourseID + "'";
            pre = conn.prepareStatement(sql);
            pre.execute();
            System.out.println("[ɾ���ɹ�]");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void showCourse(){
        Statement sta;
        ResultSet rs;
        int count = 0;
        System.out.println("\n---------�γ��б�---------");

        try{
            sta = conn.createStatement();
            rs = sta.executeQuery("SELECT * FROM course");
            while (rs.next()){
                if(count++ == 0)
                    System.out.println("  �γ̱��  \t\t�γ�����");
                formatter.format("   %-12s  %-14s\n", rs.getString(1), rs.getString(2));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void classInfoFind(){
        Statement sta;
        ResultSet rs;
        int count = 0;
        System.out.println("\n-------�༶��Ϣ��ѯ-------");
        String Grade, Class;

        System.out.println("�������꼶��");
        Grade = console.next();
        System.out.println("������༶��");
        Class = console.next();

        try{
            sta = conn.createStatement();
            rs = sta.executeQuery("SELECT * FROM students");
            while(rs.next()){
                if(rs.getString(3).equals(Grade) && rs.getString(4).equals(Class)) {
                    System.out.println();
                    if(count == 0)
                        System.out.println("\tѧ��    \t\t���� ");
                    count++;
                    formatter.format(" %-14s  %-14s\n", rs.getString(1), rs.getString(2));
                }
            }
            if(count == 0)
                System.out.println("\n[�˰༶������]!");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void stuInfoFind(String temp){
    	Statement sta;
    	ResultSet rs;
    	int reg = 0;
    	String ID;
        List<String> course = new ArrayList<>();

        if(temp != null)
            ID = temp;
        else {
            System.out.println("\n-------ѧ����Ϣ��ѯ-------");
            System.out.println("������ѧ�ţ�");
            ID = console.next();
        }

        while (!(confirm(ID, "students"))){
            System.out.println("[��ѧ��������]  ��������/�ո�+�س�����");
            if((ID = Tool.back()) == null)
                return;
        }
        try{
            sta = conn.createStatement();
            rs = sta.executeQuery("SELECT * FROM info natural join students natural join course");
            while (rs.next()){
                if(rs.getString(2).equals(ID)){
                    if(reg == 0) {
                        course.add(rs.getString(2));
                        course.add(rs.getString(3)) ;
                        course.add(rs.getString(4)+"�꼶");
                        course.add(rs.getString(5)+"��");
                        reg++;
                    }
                    course.add(rs.getString(9));
                }
            }
            course = normal(course);
            for (int i = 0; i < 8; i++)
                formatter.format("%s", course.get(i));
            System.out.println("");
        }catch (SQLException e) {
            e.printStackTrace();
        }
        course.clear();
    }

    public static void showStu(){
        Statement sta;
        ResultSet rs;
        int count = 0;
        try{
            sta = conn.createStatement();
            rs = sta.executeQuery("SELECT * FROM students");
            while(rs.next()){
                if (count++ == 0)
                    System.out.println("\n--------ѧ���б�--------");
                formatter.format("  %-12s%10s%10s%10s%10s%10s%10s\n", rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7));
            }
            if(count == 0)
                System.out.println("[��ǰ�б�����ѧ��]");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateInfo() {
        int choice;
        String ID, temp, sql;
        PreparedStatement pre;
        System.out.println("\n--------��Ϣ�޸�--------");
        System.out.println("������ѧ�ţ�");
        ID = console.next();

        while(!(confirm(ID, "students"))){
            System.out.println("[��ѧ��������]  ��������/�ո�+�س�����");
            if((ID = Tool.back()) == null)
                return;
        }

        while  (true) {
            System.out.println("1.�ײ��޸�\t2.�γ��޸�\t3.�˳�");

            while (true) {
                try {
                    System.out.println("������Ҫ�޸ĵ����ݣ�");
                    choice = console.nextInt();
                    if (choice < 1 || choice > 3)
                        throw new Exception();
                    break;
                } catch (Exception e) {
                    System.out.println("������'1'--'3'");
                }
            }
            if (choice != 3) {
                switch (choice) {
                    case 1:
                        showMeal();
                        System.out.println("�������µ���Ϣ��");
                        temp = console.next();
                        sql =  "UPDATE combo SET lunch = '" + temp + "' WHERE ID = '" + ID + "'";
                        try {
                            pre = conn.prepareStatement(sql);
                            pre.execute();
                            System.out.println("[�޸ĳɹ�]");
                            break;
                        }catch (SQLException e){
                            e.printStackTrace();
                        }
                    case 2:
                        System.out.println("\n[ѧ����Ϣ]:");
                        stuInfoFind(ID);
                        Menu m=new Menu();
                        if(Menu.infoMatch(ID))
                            System.out.println("[�޸ĳɹ�]");
                        break;
                }
            }else
                return;
        }
    }
    
    protected static List<String> normal(List<String> list){
        String temp;
        int len = 0;
        for (String s : list) {
            if (len < s.length())
                len = s.length();
        }
        for (int i = 0; i < list.size(); i++) {
            temp = list.get(i);
            for (int j = 0; j < len - list.get(i).length(); j++)
                temp = " " + temp;
            list.set(i, temp);
        }
        return list;
    }
    
    public static String back(){
        //console.useDelimiter("\n");
        String ID = console.next();//).replace(" ", "");
        if (ID.equals("#"))
            return null;
        else
            return ID;
    }
}
