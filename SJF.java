import java.io.*;
import java.util.*;
import java.lang.*;
/**
 * Write a description of class SJF here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SJF
{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n,i,j,min,temp;
        float avg_wt=0,avg_tat=0;
        System.out.printf("\nNhập số tiến trình(process):\n");
        n = sc.nextInt();
        int arrivalTime[] = new int[n];
        int burstTime[] = new int[n];
        int completionTime[] = new int[n];
        int waitingTime[] = new int[n];
        int turnaroundTime[] = new int[n];
        int process[] = new int[n];
        
        System.out.printf("Nhập Arrival Time: \n");
        for(i=0;i<n;i++){
            System.out.printf("P[%d] : ",i+1);            
            arrivalTime[i] = sc.nextInt();
            process[i] = i+1;
        }
        
        System.out.printf("Nhập Burst Time: \n");
        for(i=0;i<n;i++){
            System.out.printf("P[%d] : ",i+1);            
            burstTime[i] = sc.nextInt();
            completionTime[i] = burstTime[i];
        }
        
        //Sắp xếp Arrival Time theo thứ tự tăng dần
        for(i=0;i<n;i++)
        {
            if(completionTime[0] > arrivalTime[i]){
                min=i;
                for(j=i+1;j<n;j++)
                {
                    if(burstTime[j] < burstTime[min])
                    min=j;
                }
 
                temp=burstTime[i];
                burstTime[i]=burstTime[min];
                burstTime[min]=temp;
                
                temp=completionTime[i];
                completionTime[i]=completionTime[min];
                completionTime[min]=temp;
                
                temp=arrivalTime[i];
                arrivalTime[i]=arrivalTime[min];
                arrivalTime[min]=temp;
 
                temp=process[i];
                process[i]=process[min];
                process[min]=temp;
            }
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
        System.out.printf("\nProcess\t\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time");
           
        for(i=0;i<n;i++)
        {
            System.out.printf("\nP[%d]\t\t%d\t\t%d\t\t%d\t\t%d",process[i],arrivalTime[i],burstTime[i],waitingTime[i],turnaroundTime[i]);           
        }
        
        //Tính Thời Gian Chờ Đợi Trung Bình(avg_wt)        
        System.out.printf("\n\nAverage Waiting Time: %.2f",avg_wt/n);
        System.out.printf("\n\nAverage Turnaround Time: %.2f",avg_tat/n);
        System.out.println("\n==============================END==============================");
    }
}
