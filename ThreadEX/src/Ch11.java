
public class Ch11 extends Thread{
   public int total;
   private SellManager sm;
   
   public void run() {
      String tname = Thread.currentThread().getName();
      for(int i =0; i<3; i++) {
         System.out.println(tname + "-판매"+sm.sell());
      }
      System.out.println(tname + "종료");
   }
   
   public Ch11() {
      sm = new SellManager();
      total =100;
      
   }
   
   public static void main(String[] args) {
      System.out.println("## Ticket Sell Program ##");
      Ch11 app =new Ch11();
      for(int i =0; i<10; i++) {
         Thread mt = new Thread(app);
         mt.start();
         try {
            Thread.sleep(1000);
            
         }catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   
      System.out.println("main 종료");
   }
   
   public class SellManager{
      synchronized int sell() {
         total--;
         try {
            Thread.sleep(1000);
         }catch(InterruptedException e) {
            e.printStackTrace();
         }
         return total;
      }
   }

}