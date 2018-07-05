package com.hochnt;
import static com.hochnt.ThreadColor.ANSI_GREEN;
import static com.hochnt.ThreadColor.ANSI_PURPLE;
import static com.hochnt.ThreadColor.ANSI_RED;

public class Main {
    public static void main(String[] args){
        System.out.println(ANSI_PURPLE + "Hello from the main thread.");
        //2 ways
        //sub class override run method
        //1. extends thread => su dung khi muon implement nhieu phuong thuc cua thread
        Thread anotherThread = new AnotherThread();
        anotherThread.setName("---Another Thread---");
        anotherThread.start();

        new Thread(){
            public void run(){
                System.out.println(ANSI_GREEN + "Hello from the anonymous class thread");
            }
        }.start();


        //2. Runnable=> nen su dung

        MyRunnable myRunnable = new MyRunnable();
        Thread myRunnableThread = new Thread(myRunnable){
            @Override
            public void run() {
//                super.run();
                System.out.println(ANSI_RED + "hello form the Annonymous Runnable");
                try {
                    anotherThread.join();
                    System.out.println("Another thread teminate, so im running again");
                } catch (InterruptedException e) {
                    System.out.println(ANSI_RED + "I couldn't wait after all. Interupt");
                }
            }
        };
        myRunnableThread.start();
//        anotherThread.interrupt();
        System.out.println(ANSI_PURPLE +  "Hello again from the main thread.");
    }

}
