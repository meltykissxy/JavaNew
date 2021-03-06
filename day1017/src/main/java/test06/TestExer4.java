package test06;

/*
案例：编写龟兔赛跑多线程程序，设赛跑长度为30米
兔子的速度是10米每秒，兔子每跑完10米休眠的时间10秒
乌龟的速度是1米每秒，乌龟每跑完10米的休眠时间是1秒
要求：只要兔子和乌龟中有人到达终点，就宣布比赛结束，没到达终点的也停下来。

每一个线程会有自己独立的内存空间。
当多个线程访问同一个变量值时，每个线程可能会对该变量做一个“缓存”，
flag被几个线程访问，兔子线程，乌龟线程，裁判线程，
每一个线程会对flag在自己的内存空间中，做一个缓存备份。
如果其中一个线程修改了flag，按理说，缓存备份也会跟着更新。
但是因为裁判线程一直在读取flag的值，非常频繁的读取flag的值，
导致兔子线程修改了flag，裁判线程中flag的缓存备份没有得到更新。（因为裁判线程太频繁的操作了，没有机会更新）
就导致了多个线程看起来访问同一个变量，确得到不同的值。

我们要保证一致性，不让线程“备份”，大家都访问主存中的值，时刻保持一致性。
要实现这个效果的话，需要给这个变量加一个修饰符：volatile
 */
public class TestExer4 {
    public static void main(String[] args) {
        Sporter s1 = new Sporter("兔子",100,10000);
        Sporter s2 = new Sporter("乌龟",1000,1000);

        s1.start();
        s2.start();

        while(true){
            /*try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            if(!Sporter.isFlag()){//条件满足，说明有人到达终点
                //让所有人停下来，从sleep状态出来
                s1.interrupt();//如果s1此时在sleep，从sleep恢复继续运行，才能去判断while条件
                s2.interrupt();//如果s2此时在sleep，从sleep恢复继续运行，才能去判断while条件

                //宣布结果
                if(s1.getDistance() > s2.getDistance()){
                    System.out.println("兔子赢");
                }else if(s1.getDistance() <s2.getDistance()){
                    System.out.println("乌龟赢");
                }else{
                    //距离一样
//                    System.out.println("平局");
//                    //如果要再严格一点
                    if(s1.getTime() < s2.getTime()){
                        System.out.println("兔子赢");
                    }else if(s1.getTime() > s2.getTime()){
                        System.out.println("乌龟赢");
                    }else{
                        System.out.println("平局");
                    }
                }
                break;
            }
        }

    }
}
