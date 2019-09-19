import java.io.*;
import java.util.*;
import java.lang.*;
/**
 * Write a description of class FCFS here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FCFS
{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n,i,j;
        float avg_wt=0,avg_tat=0;
        System.out.printf("\nNhập số tiến trình(process):\n");
        n = sc.nextInt();
        int arrivalTime[] = new int[n];
        int burstTime[] = new int[n];
        int completionTime[] = new int[n];
        int turnaroundTime[] = new int[n];
        int waitingTime[] = new int[n];
        
        System.out.printf("Nhập Arrival Time: \n");
        for(i=0;i<n;i++){
            System.out.printf("P[%d] : ",i+1);            
            arrivalTime[i] = sc.nextInt();
        }
        
        System.out.printf("Nhập Burst Time: \n");
        for(i=0;i<n;i++){
            System.out.printf("P[%d] : ",i+1);            
            burstTime[i] = sc.nextInt();
            completionTime[i] = burstTime[i];
        }
                             
        turnaroundTime[0] = burstTime[0] - arrivalTime[0];
        //Tính Turnaround Time(hiện tại) = (Burst Time(trước) + Burst Time(hiện tại)) - Arrival Time(Hiện tại) (Time hoàn thành - Time xuất hiện)
        for(i=1;i<n;i++)
        {   
            completionTime[i] += completionTime[i-1];
            turnaroundTime[i] = completionTime[i] - arrivalTime[i];
            avg_tat += turnaroundTime[i];
        }
            avg_tat = avg_tat + turnaroundTime[0];
        //Tính Waiting Time(hiện tại) = Turnaround Time(hiện tại) + Arrival Time(Hiện tại)
        for(i=0;i<n;i++)
        {
            waitingTime[i] = turnaroundTime[i] - burstTime[i];
            avg_wt += waitingTime[i];
        }
        
        System.out.println("==============================Kết Quả==============================");
        System.out.printf("\nProcess\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time");
        
        //Tính Turnaround Time = Burst Time + Waiting Time
        
        for(i=0;i<n;i++)
        {
            System.out.printf("\nP[%d]\t\t%d\t\t%d\t\t%d\t\t%d",i+1,arrivalTime[i],burstTime[i],waitingTime[i],turnaroundTime[i]);
        }
        
        //Tính Thời Gian Chờ Đợi Trung Bình(avg_wt)                       
        System.out.printf("\n\nAverage Turnaround Time: %.2f",avg_tat/n);
        System.out.printf("\n\nAverage Waiting Time: %.2f",avg_wt/n);
        System.out.println("\n==============================END==============================");
    }
}
