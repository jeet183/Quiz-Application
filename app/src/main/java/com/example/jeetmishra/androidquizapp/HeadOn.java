package com.example.jeetmishra.androidquizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;

public class HeadOn extends AppCompatActivity {
    int result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_on);
       Calendar cal=Calendar.getInstance();
        long currt=cal.getTimeInMillis();
        long after10=cal.getTimeInMillis();
        //String concate=Long.toString(currt)+" "+Long.toString(after10);
       // sendMessage(concate);
    }
   /* public void startServerSocket() {

        Thread thread = new Thread(new Runnable() {

            public String stringData = null;

            @Override
            public void run() {

                try {

                    ServerSocket ss=new ServerSocket(7777);

                    while (true) {
                        //Server is waiting for client here, if needed
                        Socket s = null;
                        s = ss.accept();
                        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                        PrintWriter output = new PrintWriter(s.getOutputStream());

                        stringData = input.readLine();
                        int t[]=new int[2];
                        int i=0;
                        for(String x: stringData.split(" ")){
                            t[i]=Integer.parseInt(x);
                            i++;
                        }
                        int winnerno=0;
                        if(t[0]>t[1]){
                            winnerno=2;
                        }
                        else if(t[0]<t[1]){
                            winnerno=1;
                        }
                        output.println(Integer.toString(winnerno));
                        output.flush();

                        try{
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        output.close();
                        s.close();
                        ss.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        thread.start();
    }

    public int sendMessage(final String msg) {
        final Handler handler = new Handler();


        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {

                try {

                    Socket s = new Socket("192.168.1.104", 7777);

                    OutputStream out = s.getOutputStream();

                    PrintWriter output = new PrintWriter(out);

                    output.println(msg);
                    output.flush();
                    BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    final String st = input.readLine();
                    result=Integer.parseInt(st);

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(st.length()!=0){

                                System.out.println("Data received from server");
                            }
                        }
                    });

                    output.close();
                    out.close();
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        return result;
    }*/

}
