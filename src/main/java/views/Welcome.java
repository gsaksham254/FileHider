package views;

import dao.UserDao;
import model.User;
import service.GenerateOTP;
import service.SendOTPService;
import service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Welcome {
    public void welcomeScreen(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the App");
        System.out.println("Press 1 to login");
        System.out.println("Press 2 to SignUp");
        System.out.println("Press 0 to Exit");
        int choice = 0;
        try{
            choice = Integer.parseInt(br.readLine());
        }catch(IOException ex){
            ex.printStackTrace();

        }
        switch(choice){
            case 1 -> login();
            case 2 -> signup();
            case 0 -> System.exit(0);
        }
    }
    private void login() {
        Scanner sc = new Scanner(System.in);
        String email = sc.nextLine();
        try{
            if(UserDao.isExists(email)){
                String genOTP = GenerateOTP.getOTP();
                SendOTPService.sendOTP(email,genOTP);
                System.out.println("Enter the OTP");
                String otp = sc.nextLine();
                if(otp.equals(genOTP)){
                    System.out.println("Welcome");
                }
                else{
                    System.out.println("Wrong OTP");
                }
            }
            else{
                System.out.println("User Not Found");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }


    }
    private void signup() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Name");
        String name = sc.nextLine();
        System.out.println("Enter the Email");
        String email = sc.nextLine();
        String genOTP = GenerateOTP.getOTP();
        SendOTPService.sendOTP(email,genOTP);
        System.out.println("Enter the OTP");
        String otp = sc.nextLine();
        if(otp.equals(genOTP)){
            User user = new User(name,email);
            int response = UserService.saveUser(user);
            switch(response){
                case 0 -> System.out.println("User Registered");
                case 1 -> System.out.println("User Already Exists");
            }
        }
        else{
            System.out.println("Wrong OTP");
        }

    }

}
