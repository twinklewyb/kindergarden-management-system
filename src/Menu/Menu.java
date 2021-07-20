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
        System.out.println("\n  欢迎来到幼儿园管理系统!\n ");
        show();
    }

    private static void createStu(){
        PreparedStatement pre;
        String ID, Name, Grade, ClassNum, Sex, Birthday, Address;

        System.out.println("\n---------添加学生---------");
        System.out.println("请输入学号：");
        ID = console.next();
        while (Tool.confirm(ID, "students")) {
            System.out.println("[此学号已存在]  重新输入/空格+回车键返回");
            if((ID = Tool.back()) == null)
                return;
        }
        System.out.println("请输入姓名：");
        Name = console.next();
        System.out.println("请输入年级：");
        Grade = console.next();
        System.out.println("请输入班级：");
        ClassNum = console.next();
        System.out.println("请输入性别：");
        Sex = console.next();
        System.out.println("请输入出生日期：");
        Birthday = console.next();
        /*while(!Tool.isDate(Birthday)) {
        	System.out.println("请输入正确日期格式：");
        	Birthday = console.next();
        }*/
        System.out.println("请输入家庭住址：");
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
            System.out.println("[创建成功!]");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void createMeal() {
        PreparedStatement pre;
        String m_ID, staple_food, dish_1, dish_2, soup;

        System.out.println("\n---------添加新套餐---------");
        System.out.println("请输入套餐编号：");
        m_ID = console.next();
        while (Tool.confirm(m_ID, "menu_mode")) {
            System.out.println("[套餐重复]  重新输入/空格+回车键返回");
            if((m_ID = Tool.back()) == null)
                return;
        }
        System.out.println("请输入套餐主食：");
        staple_food = console.next();
        System.out.println("请输入配菜Ⅰ：");
        dish_1 = console.next();
        System.out.println("请输入配菜Ⅱ：");
        dish_2 = console.next();
        System.out.println("请输入汤品：");
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
            System.out.println("[创建成功!]");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void createCourse(){
        String CourseID, CourseName;
        PreparedStatement pre;

        System.out.println("\n---------创建课程---------");
        System.out.println("请输入课程编号：");
        CourseID = console.next();
        while (Tool.confirm(CourseID, "course")){
            System.out.println("[此学生不存在]  重新输入/空格+回车键返回");
            if((CourseID = Tool.back()) == null)
                return;
        }
        System.out.println("请输入课程名称：");
        CourseName = console.next();

        String sql = "INSERT INTO course (CourseID, CourseName)" + "VALUES(?,?)";
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, CourseID);
            pre.setString(2, CourseName);
            pre.execute();
            System.out.println("[创建成功]！");
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void creatInfo(){
        int count;
        String ID, meal;
        PreparedStatement pre;

        System.out.println("\n---------添加信息---------");
        System.out.println("请输入学号：");
        ID = console.next();
        while(true){
            count = 0;
            if (!(Tool.confirm(ID, "students")))
                System.out.println("[此学生不存在]  重新输入/空格+回车键返回");
            else if(Tool.confirm(ID, "combo"))
                System.out.println("[此学生已有信息]  重新输入/空格+回车键返回");
            else
                count++;
            if(count == 0){
                if((ID = Tool.back()) == null)
                    return;
            }
            else
                break;
        }

        System.out.println("\n请选择套餐 [输入编号]：");
        Tool.showMeal();
        meal = console.next();
        while(!(Tool.confirm(meal, "menu_mode"))){
            System.out.println("[此套餐不存在]  重新输入/空格+回车键返回 ");
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
        System.out.println("[创建成功]！");
    }

      public static boolean infoMatch(String ID){
        int tag = 0;
        String temp, sql;
        PreparedStatement pre;
        int xuhao;
        List<String> course = new ArrayList<>();
        Set<String> set = new HashSet<>();

        System.out.println("\n请选择4门课程 [输入编号]：");

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
                    System.out.println("[部分课程不存在]  重新输入/空格+回车键返回");
                    if((ID = Tool.back()) == null)
                        return false;
                    tag++;
                    break;
                }
            }
            if(tag == 0) {
                for (String s : course) {
                    if (set.contains(s)) {
                        System.out.println("[存在重复课程]  重新输入/空格+回车键返回");
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

        //实行删除原有记录的操作
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
                System.out.println("插入一次课");
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
                    System.out.println("请输入'1'~'6':");
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
                	System.out.println("感谢您使用本系统  欢迎下次使用！");
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
                    System.out.println("请输入'1'~'5':");
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
            showMealMenu();     //显示餐食管理部分菜单

            while(true){
                try{
                    choice = console.nextInt();  //判断输入数字是否越界
                    if(choice < 1 || choice > 5)
                        throw new Exception();
                    break;
                } catch (Exception e) {
                    System.out.println("请输入'1'~'5':");
                }
            }
            switch (choice){                //根据选择数字进入相应函数
                case 1:                     //添加套餐
                    createMeal();
                    break;
                case 2:                     //删除套餐
                    Tool.deleteMeal();
                    break;
                case 3:                     //修改套餐
                    Tool.updateMeal();
                    break;
                case 4:                     //套餐列表
                    Tool.showMeal(); 
                    break;
                default:                    //返回上一级
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
                    System.out.println("请输入'1'~'4':");
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
                    System.out.println("请输入'1'~'4':");
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
                    System.out.println("请输入'1'~'4':");
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
        System.out.println("\n-----------选项-----------");
        System.out.println("1.学籍管理");
        System.out.println("2.食谱管理");
        System.out.println("3.课程管理");
        System.out.println("4.信息修改");
        System.out.println("5.查询");
        System.out.println("6.退出");
        System.out.println("请输入选项对应的序号:");
    }

    private static void showRollMenu(){
        System.out.println("\n---------学籍管理---------");
        System.out.println("1.添加学生");
        System.out.println("2.删除学生");
        System.out.println("3.学籍修改");
        System.out.println("4.学生列表");
        System.out.println("5.返回上一级");
        System.out.println("请输入选项对应的序号:");
    }

    private static void showMealMenu(){
        System.out.println("\n---------餐食管理---------");
        System.out.println("1.添加套餐");
        System.out.println("2.删除套餐");
        System.out.println("3.修改套餐");
        System.out.println("4.套餐列表");
        System.out.println("5.返回上一级");
        System.out.println("请输入选项对应的序号:");
    }

    private static void showCourseMenu(){
        System.out.println("\n---------课程管理---------");
        System.out.println("1.添加课程");
        System.out.println("2.删除课程");
        System.out.println("3.课程列表");
        System.out.println("4.返回上一级");
        System.out.println("请输入选项对应的序号:");
    }

    private static void showInfoMenu(){
        System.out.println("\n---------资料管理---------");
        System.out.println("1.添加信息");
        System.out.println("2.信息修改");
        System.out.println("3.返回上一级");
        System.out.println("请输入选项对应的序号:");
    }

    private static void showSearchMenu(){
        System.out.println("\n-----------查询-----------");
        System.out.println("1.班级信息查询");
        System.out.println("2.学生信息查询");
        System.out.println("3.返回上一级");
        System.out.println("请输入选项对应的序号:");
    }
    private static void show() {
    	
    	do{System.out.println("1.注册管理员 2.注册非管理员  3.管理员登陆 4.非管理员登陆 5.输入#返回上级");
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
    			System.out.println("用户名或密码错误");
    			break;
    			}
    		s.showmenu();
    		break;
    	case 4:
    		//m.check();
    		if(m.check()!=true)
    			{
    			System.out.println("用户名或密码错误");
    			break;
    			}
    		m.showmenu();
    		break;
    	case 5:
    		System.exit(0);
    	}}while(console.next().equals("#"));
    }
}
