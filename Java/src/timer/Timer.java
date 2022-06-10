package timer;


public class Timer {
    long endTime;
    boolean start = false;
    public long getRemainTime(){
        return Math.max(0,(-System.currentTimeMillis()+endTime)/1000);
    }


    public String getCountString(){
        if(start){
            if(ended())return "00:00";
            else {
                long s = (endTime - System.currentTimeMillis())/1000;
                return String.format("  %02d  :  %02d  ", s/60,s%60);
            }
        }else{
            return "99:99";
        }
    }
    public boolean ended(){
        return start&&System.currentTimeMillis()>=endTime;
    }
    public boolean started(){return start;}
    public void startTimer(int sec){
        start = true;
        endTime = 1000*sec + System.currentTimeMillis();
    }
}
