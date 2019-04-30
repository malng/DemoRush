package design.maliang.string;

import java.util.Scanner;

/**
 * 模拟用户登陆
 */
public class SignInDemo {

    private static String username = "admin";
    private static String password = "admin";

    public static void main(String[] args) {
        //4.有三次重试的机会
        for (int i = 0; i < 3; i++) {
            //1.提示输入用户名
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入用户名:");
            String name = sc.nextLine();
            //2.提示输入密码
            System.out.println("请输入密码:");
            String pwd = sc.nextLine();
            //3.将输入的用户名和密码和已有的用户名和密码比较
            if (username.equals(name)&&password.equals(pwd)) {
                System.out.println("登陆成功");
                break;
            }else {
                if ((2-i)<=0) {
                    System.out.println("您的账号已锁定请与管理员联系。");
                    break;
                } else{
                    System.out.println("登陆失败，您还有" + (2 - i) + "次机会。");
                }
            }
        }
    }
}
