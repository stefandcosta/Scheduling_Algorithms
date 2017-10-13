package schedule;

import java.util.*;                                                             
// Scanner class used for in built Data Structures like LinkedList and Priority Queues and to take inputs from the user
import java.text.*;                                                             
// Text class used for limiting the format of float/fraction values to 2 decimal places

class schedule
{
    public static DecimalFormat df = new DecimalFormat("##.##");                // Used to format the float/fraction value format. Example 12.3456 ~ 12.34
    public static final double cost_context_switching = 0.5;
    public static void main(String args[])
    {
        Scanner src = new Scanner(System.in);                                   // Declaring Scanner object to get value from the user.
        System.out.println("\nEnter the number of process :");                  // Displaying on the console.
        System.out.println("\nFor debugging purposes please enter a value between 5 to 10");
        System.out.println("For almost 0 CPU idle time between two process please enter a value between 75 to 100");
        int number_Processes = src.nextInt();                                   // Enter the number of processes.
        Process list_of_all_processes[] = new Process[number_Processes];        // Create an array of Process object to simulate different processes.
        
        for(int i=1;i<=number_Processes;i++)                                    // for loop to populate random values to Process values PID, Arrival_time, Execution_time and Priority
        {
            list_of_all_processes[i-1]=new Process(i,(float)(Math.random()*99.0),(float)(Math.random()*10.0),(int)(Math.random()*4)+1); // Math.random() returns a fraction value between 0 and 1. 
        }
        
        System.out.println("\nPlease enter your choice of scheduling algorithm :");
        System.out.println("\tEnter 1 : First Come First Serve");
        System.out.println("\tEnter 2 : Shortest Job First");
        System.out.println("\tEnter 3 : Shortest Remaining Time");
        System.out.println("\tEnter 4 : Round Robin");
        System.out.println("\tEnter 5 : Highest Priority First (Non - Preemptive type)");
        System.out.println("\tEnter 6 : Highest Priority First (Preemptive type)");
        System.out.println("\tEnter 7 : Highest Priority First (Non - Preemptive type with Aging)");
        System.out.println("\tEnter 8 : Highest Priority First (Preemptive type with Aging)");
        System.out.println("\tEnter 10 : All Scheduling Algorithms (WITH Context Switching Simulation)");
        System.out.println("\tAny value : All Scheduling Algorithms");
        
        int choice = src.nextInt();
        
        switch(choice)
        {
            case 1: System.out.println();
                    System.out.println("----------------------------------FIRST COME FIRST SERVE-------------------------------------\n");
                    sort(list_of_all_processes,"Arrival_time");
                    FCFS(list_of_all_processes,"NO");
                    restore(list_of_all_processes);
                    System.out.println("----------------------------------------END--------------------------------------------------\n\n");
                    break;
            case 2: System.out.println();
                    System.out.println("----------------------------------SHORTEST JOB FIRST-----------------------------------------\n");
                    sort(list_of_all_processes,"Arrival_time");
                    SJF(list_of_all_processes,"NO");
                    restore(list_of_all_processes);
                    System.out.println("----------------------------------------END--------------------------------------------------\n\n");
                    break;
            case 3: System.out.println();
                    System.out.println("----------------------------------SHORTEST REMAINING TIME--------------------------------------\n");
                    sort(list_of_all_processes,"Arrival_time");
                    SRT(list_of_all_processes,"NO");
                    restore(list_of_all_processes);
                    System.out.println("----------------------------------------END--------------------------------------------------\n\n");
                    break;
            case 4: System.out.println();
                    System.out.println("----------------------------------ROUND ROBIN--------------------------------------------------\n");
                    sort(list_of_all_processes,"Arrival_time");
                    RR(list_of_all_processes,"NO");
                    restore(list_of_all_processes);
                    System.out.println("----------------------------------------END--------------------------------------------------\n\n");
                    break;
            case 5: System.out.println();
                    System.out.println("------------------------HIGHEST PRIORITY FIRST (Non-preemptive)--------------------------------\n");
                    sort(list_of_all_processes,"Arrival_time");
                    HPF_NP(list_of_all_processes,"NO");
                    restore(list_of_all_processes);
                    System.out.println("----------------------------------------END--------------------------------------------------\n\n");
                    break;
            case 6: System.out.println();
                    System.out.println("------------------------HIGHEST PRIORITY FIRST (Preemptive)------------------------------------\n");
                    sort(list_of_all_processes,"Arrival_time");
                    HPF_P(list_of_all_processes,"NO");
                    restore(list_of_all_processes);
                    System.out.println("----------------------------------------END--------------------------------------------------\n\n");
                    break;
            case 7: System.out.println();
                    System.out.println("----------------------HIGHEST PRIORITY FIRST (Non-preemptive with Aging)-----------------------\n");
                    sort(list_of_all_processes,"Arrival_time");
                    HPF_NP_Aging(list_of_all_processes,"NO");
                    restore(list_of_all_processes);
                    System.out.println("----------------------------------------END--------------------------------------------------\n\n");
                    break;
            case 8: System.out.println();
                    System.out.println("------------------------HIGHEST PRIORITY FIRST (Preemptive with Aging)-------------------------\n");
                    sort(list_of_all_processes,"Arrival_time");
                    HPF_P_Aging(list_of_all_processes,"NO");
                    restore(list_of_all_processes);
                    System.out.println("----------------------------------------END--------------------------------------------------\n\n");
                    break;
            case 10:
                System.out.println("----------------------------------FIRST COME FIRST SERVE-------------------------------------\n");
                sort(list_of_all_processes,"Arrival_time");
                FCFS(list_of_all_processes,"YES");
                restore(list_of_all_processes);
                System.out.println("----------------------------------------END--------------------------------------------------\n\n");
                System.out.println("----------------------------------SHORTEST JOB FIRST-----------------------------------------\n");
                sort(list_of_all_processes,"Arrival_time");
                SJF(list_of_all_processes,"YES");
                restore(list_of_all_processes);
                System.out.println("----------------------------------------END--------------------------------------------------\n\n");
                System.out.println("----------------------------------SHORTEST REMAINING TIME--------------------------------------\n");
                sort(list_of_all_processes,"Arrival_time");
                SRT(list_of_all_processes,"YES");
                restore(list_of_all_processes);
                System.out.println("----------------------------------------END--------------------------------------------------\n\n");
                System.out.println("----------------------------------ROUND ROBIN--------------------------------------------------\n");
                sort(list_of_all_processes,"Arrival_time");
                RR(list_of_all_processes,"YES");
                restore(list_of_all_processes);
                System.out.println("----------------------------------------END--------------------------------------------------\n\n");
                System.out.println("------------------------HIGHEST PRIORITY FIRST (Non-preemptive)--------------------------------\n");
                sort(list_of_all_processes,"Arrival_time");
                HPF_NP(list_of_all_processes,"YES");
                restore(list_of_all_processes);
                System.out.println("----------------------------------------END--------------------------------------------------\n\n");
                System.out.println("------------------------HIGHEST PRIORITY FIRST (Preemptive)------------------------------------\n");
                sort(list_of_all_processes,"Arrival_time");
                HPF_P(list_of_all_processes,"YES");
                restore(list_of_all_processes);
                System.out.println("----------------------------------------END--------------------------------------------------\n\n");
                System.out.println("----------------------HIGHEST PRIORITY FIRST (Non-preemptive with Aging)-----------------------\n");
                sort(list_of_all_processes,"Arrival_time");
                HPF_NP_Aging(list_of_all_processes,"YES");
                restore(list_of_all_processes);
                System.out.println("----------------------------------------END--------------------------------------------------\n\n");
                System.out.println("------------------------HIGHEST PRIORITY FIRST (Preemptive with Aging)-------------------------\n");
                sort(list_of_all_processes,"Arrival_time");
                HPF_P_Aging(list_of_all_processes,"YES");
                restore(list_of_all_processes);
                System.out.println("----------------------------------------END--------------------------------------------------\n\n");
            default: 
                System.out.println("----------------------------------FIRST COME FIRST SERVE-------------------------------------\n");
                sort(list_of_all_processes,"Arrival_time");
                FCFS(list_of_all_processes,"NO");
                restore(list_of_all_processes);
                System.out.println("----------------------------------------END--------------------------------------------------\n\n");
                System.out.println("----------------------------------SHORTEST JOB FIRST-----------------------------------------\n");
                sort(list_of_all_processes,"Arrival_time");
                SJF(list_of_all_processes,"NO");
                restore(list_of_all_processes);
                System.out.println("----------------------------------------END--------------------------------------------------\n\n");
                System.out.println("----------------------------------SHORTEST REMAINING TIME--------------------------------------\n");
                sort(list_of_all_processes,"Arrival_time");
                SRT(list_of_all_processes,"NO");
                restore(list_of_all_processes);
                System.out.println("----------------------------------------END--------------------------------------------------\n\n");
                System.out.println("----------------------------------ROUND ROBIN--------------------------------------------------\n");
                sort(list_of_all_processes,"Arrival_time");
                RR(list_of_all_processes,"NO");
                restore(list_of_all_processes);
                System.out.println("----------------------------------------END--------------------------------------------------\n\n");
                System.out.println("------------------------HIGHEST PRIORITY FIRST (Non-preemptive)--------------------------------\n");
                sort(list_of_all_processes,"Arrival_time");
                HPF_NP(list_of_all_processes,"NO");
                restore(list_of_all_processes);
                System.out.println("----------------------------------------END--------------------------------------------------\n\n");
                System.out.println("------------------------HIGHEST PRIORITY FIRST (Preemptive)------------------------------------\n");
                sort(list_of_all_processes,"Arrival_time");
                HPF_P(list_of_all_processes,"NO");
                restore(list_of_all_processes);
                System.out.println("----------------------------------------END--------------------------------------------------\n\n");
                System.out.println("----------------------HIGHEST PRIORITY FIRST (Non-preemptive with Aging)-----------------------\n");
                sort(list_of_all_processes,"Arrival_time");
                HPF_NP_Aging(list_of_all_processes,"NO");
                restore(list_of_all_processes);
                System.out.println("----------------------------------------END--------------------------------------------------\n\n");
                System.out.println("------------------------HIGHEST PRIORITY FIRST (Preemptive with Aging)-------------------------\n");
                sort(list_of_all_processes,"Arrival_time");
                HPF_P_Aging(list_of_all_processes,"NO");
                restore(list_of_all_processes);
                System.out.println("----------------------------------------END--------------------------------------------------\n\n");
        }
        
        
        
    }
    public static void sort(Process list_of_all_processes[],String type)        // Function used to sort Process array based on Arrival time or Completed time
    {
        if(type == "Arrival_time")                                              // Sort based on Arrival Time in ascending order
        {
            for(int i=0;i<list_of_all_processes.length-1;i++)                   // Bubble Sort algorithm
            for(int j=0;j<list_of_all_processes.length-i-1;j++)
            {
                if(list_of_all_processes[j].Arrival_time > list_of_all_processes[j+1].Arrival_time)
                {
                    Process temp = list_of_all_processes[j];                    // Swapping stage of Bubble Sort
                    list_of_all_processes[j] = list_of_all_processes[j+1];
                    list_of_all_processes[j+1] = temp;
                }
            }
        }
        if(type == "Completed_time")                                            // Sort based on Completed Time in ascending order
        {
            for(int i=0;i<list_of_all_processes.length-1;i++)                   // Bubble Sort algorithm
            for(int j=0;j<list_of_all_processes.length-i-1;j++)
            {
                if(list_of_all_processes[j].Completed_time > list_of_all_processes[j+1].Completed_time)
                {
                    Process temp = list_of_all_processes[j];                    // Swapping stage of Bubble Sort
                    list_of_all_processes[j] = list_of_all_processes[j+1];
                    list_of_all_processes[j+1] = temp;
                }
            }
        }
        
    }
    public static void display(Process list_of_all_processes[])                 // Function to display Process array in tablular form
    {
        System.out.println("Pid\tArrival_time\t\tExecution_time\t\tPriority\tCompleted\tTurnaround\tWaiting\t\tResponse");    // Printing Header Line
        for(int i=1;i<=list_of_all_processes.length;i++)
        {
            // Print Data
            System.out.println(list_of_all_processes[i-1].Pid+"\t"+df.format(list_of_all_processes[i-1].Arrival_time)+"\t\t"+df.format(list_of_all_processes[i-1].original_Execution_time)+"\t\t"+list_of_all_processes[i-1].original_Priority+"\t\t"+list_of_all_processes[i-1].Completed_time+"\t\t"+df.format(list_of_all_processes[i-1].Turnaround_Time)+"\t\t"+df.format(list_of_all_processes[i-1].Wait_time)+"\t\t"+df.format(list_of_all_processes[i-1].Response_time));
        }
    }
    public static void restore(Process list_of_all_processes[])                 // Function used to restore value to their default and/or initial values using temporary values
    {
        for(int i=0;i<list_of_all_processes.length;i++)
        {
            list_of_all_processes[i].flag=true;
            list_of_all_processes[i].Execution_time=list_of_all_processes[i].original_Execution_time;
            list_of_all_processes[i].Priority=list_of_all_processes[i].original_Priority;
            list_of_all_processes[i].Completed_time = -1;
            list_of_all_processes[i].Turnaround_Time = -1;
            list_of_all_processes[i].Response_time = -1;
            list_of_all_processes[i].Wait_time = -1;
            list_of_all_processes[i].Time_of_first_CPU_slot = Integer.MAX_VALUE;
        }
    }
    public static boolean check(Process p,ArrayList<Process> ready_state_processes) // Function used check if a process is present in ArrayList or not.
    {
        for(int i=0;i<ready_state_processes.size();i++)
        {
            if(p.Pid == ready_state_processes.get(i).Pid)                       // If process PID is equal to any process PID in ArrayList then return false otherwise return true.
            {
                return false;
            }
        }
        return true;
    }
    public static boolean checkRR(Process p, Queue<Process> ready_state_processes) // Function used check if a process is present in Queue or not.
    {
        for(Process pc : ready_state_processes)
        {
            if(p.Pid == pc.Pid)                                                 // If process PID is equal to any process PID in Queue then return false otherwise return true.
            {
                return false;
            }
        }
        return true;
        
    }
    public static void adjust(ArrayList<Process> ready_state_processes, int time)   // Function used to apply Aging technique to Priority for Non-preemptive algorithms
    {
        if(time == 0)                                                           // If time is 0 there is no need to apply aging
        {
            return;
        }
        for(int i=0;i<ready_state_processes.size();i++)                         // Iterate over entire ArrayList to check if a process has waited for multiple of 5 time units in order to bump Priority by 1 unit per 5 wait time units.
        {
            ready_state_processes.get(i).Wait_time = time - ready_state_processes.get(i).Arrival_time;       // Using Wait_time variable to store the time waited by process in the Queue
            if(ready_state_processes.get(i).Wait_time==0)                       // If the wait time is 0 continue.
            {
                continue;
            }
            ready_state_processes.get(i).Priority = ready_state_processes.get(i).original_Priority;                    // Get the original Priority
            ready_state_processes.get(i).Priority = ready_state_processes.get(i).Priority - (int)ready_state_processes.get(i).Wait_time/5;   // Bump the Priority. Example: if Wait_time = 16 and Priority = 4 then Final Priority = 4 - (int)16/5 = 1
            if(ready_state_processes.get(i).Priority <= 0)                      // If the Priority goes below 0 we reinitalize it to 1 (highest Priority)
            {
                ready_state_processes.get(i).Priority = 1;
            }
        }
    }
    public static void adjust_P(ArrayList<Process> ready_state_processes, int time) // Function used to apply Aging technique to Priority for Premeptive algorithms
    {
        if(time == 0)                                                           // If time is 0 there is no need to apply aging
        {
            return;
        }
        for(int i=0;i<ready_state_processes.size();i++)                         // Iterate over entire ArrayList to check if a process has waited for 5 time units in order to bump the Priority by 1 unit per 5 units of wait time.
        {                                                                       // Wait_time represents the time that the process last got the CPU time.
            if(ready_state_processes.get(i).Wait_time != time)                  // If the Wait_time is equal to the time it the the current running process hence DOESN'T need to be updated.
            {
                if(ready_state_processes.get(i).Wait_time == -1)                // If the Wait_time is -1 it should be initialized to the process's Arrival_time. 
                {
                    ready_state_processes.get(i).Wait_time = (float)Math.ceil(ready_state_processes.get(i).Arrival_time);
                }
            }
            if(time - ready_state_processes.get(i).Wait_time == 5)              // If the current time minus the Wait_time is 5 then Priority should be bumped by 1 unit and update Wait_time to time
            {
                ready_state_processes.get(i).Priority = ready_state_processes.get(i).Priority - 1;
                if(ready_state_processes.get(i).Priority <= 0)                  // If the Priority goes below 0 we reinitalize it to 1 (highest Priority)
                {
                    ready_state_processes.get(i).Priority = 1;
                }
                ready_state_processes.get(i).Wait_time = time;
            }
        }
    }
    public static void HPF_P_Aging(Process list_of_all_processes[], String context_switching_choice)  // Function to simulate Highest Priority First (Preemptive type) with aging process   // Similar to Highest Priority First (Preemptive type) without aging process
    {
        int time = 0;
        double sumTT = 0;
        double sumWT = 0;
        double sumRT = 0;
        int number_of_completed_processes = 0;
        ArrayList<Process> ready_state_processes = new ArrayList<Process>();
        int count_context_switch = 0;
        int previous_pid = -2;
        while(time<=1000)
        {
            for(int i=0; i< list_of_all_processes.length;i++)
            {
                if(list_of_all_processes[i].Arrival_time <= time && list_of_all_processes[i].flag && check(list_of_all_processes[i],ready_state_processes))
                {
                    ready_state_processes.add(list_of_all_processes[i]);
                    Collections.sort(ready_state_processes, new SortbyPriority());  
                }
            }
            if(ready_state_processes.size()==0)
            {
                System.out.print("-");
                previous_pid = -1;
                time = time +1;
            }
            else
            {
                adjust_P(ready_state_processes,time);                           // Updates the Priority based on the time passed in order to bump Priorities
                Collections.sort(ready_state_processes, new SortbyPriority());
                Process current_running_process = ready_state_processes.get(0);
                current_running_process.insert(time);
                if(previous_pid != current_running_process.Pid)
                {
                    count_context_switch = count_context_switch + 1;
                    previous_pid = current_running_process.Pid;
                }
                current_running_process.Wait_time = time + 1;
                System.out.print("[P"+current_running_process.Pid+"]");
                for(int j=0;j<list_of_all_processes.length;j++)
                {
                    if(list_of_all_processes[j].Pid == current_running_process.Pid)
                    {
                        list_of_all_processes[j] = current_running_process;
                        break;
                    }
                }
                time = time + 1;
                if((current_running_process.Execution_time-1)<=0)
                {
                    current_running_process.Execution_time = 0;
                }
                else
                {
                    current_running_process.Execution_time = current_running_process.Execution_time - 1;
                }
                ready_state_processes.remove(0);
                if(current_running_process.Execution_time >0)
                {
                    ready_state_processes.add(current_running_process);
                }
                if(current_running_process.Execution_time == 0)
                {
                    current_running_process.flag = false;
                    current_running_process.Completed_time = time;
                    current_running_process.Turnaround_Time = (float)current_running_process.Completed_time - current_running_process.Arrival_time;
                    current_running_process.Wait_time = current_running_process.Turnaround_Time - current_running_process.original_Execution_time;
                    current_running_process.Response_time = current_running_process.Time_of_first_CPU_slot - current_running_process.Arrival_time;
                    sumTT = sumTT + current_running_process.Turnaround_Time;
                    sumWT = sumWT + current_running_process.Wait_time;
                    sumRT = sumRT + current_running_process.Response_time;
                    number_of_completed_processes = number_of_completed_processes +1;
                }
            }
            if(number_of_completed_processes == list_of_all_processes.length)
            {
                System.out.println();
                break;
            }
        }
        System.out.println();
        display(list_of_all_processes);
        
        System.out.println();
        System.out.println("Average Turnaround time = " + sumTT/(double)list_of_all_processes.length);
        System.out.println("Average Waiting time = " + sumWT/(double)list_of_all_processes.length);
        System.out.println("Average Response time = " + sumRT/(double)list_of_all_processes.length);
        sort(list_of_all_processes,"Completed_time");
        if(context_switching_choice == "YES")
        {
            System.out.println("Throughput = " + (double)list_of_all_processes.length/(list_of_all_processes[list_of_all_processes.length-1].Completed_time+(count_context_switch)*cost_context_switching));
        }
        else
        {
            System.out.println("Throughput = " + (double)list_of_all_processes.length/(double)list_of_all_processes[list_of_all_processes.length-1].Completed_time);
        }
    }
    public static void HPF_NP_Aging(Process list_of_all_processes[], String context_switching_choice)   // Function to simulate Highest Priority First (Non-preemptive type) with aging process // Similar to Highest Priority First (Non-preemptive type) without aging process
    {
        int time = 0;
        double sumTT = 0;
        double sumWT = 0;
        double sumRT = 0;
        int number_of_completed_processes = 0;
        ArrayList<Process> ready_state_processes = new ArrayList<Process>();
        while(time<=1000)
        {
            for(int i=0; i< list_of_all_processes.length;i++)
            {
                if(list_of_all_processes[i].Arrival_time <= time && list_of_all_processes[i].flag)
                {
                    ready_state_processes.add(list_of_all_processes[i]);
                    Collections.sort(ready_state_processes, new SortbyPriority());
                    list_of_all_processes[i].flag = false;  
                }
            }
            if(ready_state_processes.size()==0)
            {
                time = time +1;
            }
            else
            {
                adjust(ready_state_processes,time);                             // Updates the Priority based on the time passed in order to bump Priorities
                Collections.sort(ready_state_processes, new SortbyPriority());
                Process current_running_process = ready_state_processes.remove(0);
                for(int i=0;i<list_of_all_processes.length;i++)
                {
                    if(list_of_all_processes[i].Pid == current_running_process.Pid)
                    {
                        current_running_process = list_of_all_processes[i];
                        break;
                    }
                }
                time = time + (int)Math.ceil(current_running_process.Execution_time);
                current_running_process.Completed_time = time;
                current_running_process.Turnaround_Time = (float)current_running_process.Completed_time - current_running_process.Arrival_time;
                current_running_process.Wait_time = current_running_process.Turnaround_Time - current_running_process.Execution_time;
                current_running_process.Response_time = current_running_process.Wait_time;
                sumTT = sumTT + current_running_process.Turnaround_Time;
                sumWT = sumWT + current_running_process.Wait_time;
                sumRT = sumRT + current_running_process.Response_time;
                number_of_completed_processes = number_of_completed_processes +1;
            }
            if(number_of_completed_processes == list_of_all_processes.length)
            {
                break;
            }
        }
        System.out.println();
        display(list_of_all_processes);
        System.out.println();
        
        sort(list_of_all_processes,"Completed_time");
        int pointer_for_current_process=0;
        for(int i=0;i<list_of_all_processes[list_of_all_processes.length-1].Completed_time;i++)
        {
            if((int)Math.ceil(list_of_all_processes[pointer_for_current_process].Arrival_time)>i)
            {
                System.out.print('-');
            }
            else
            {
                System.out.print("[P"+list_of_all_processes[pointer_for_current_process].Pid+"]");
                if(list_of_all_processes[pointer_for_current_process].Completed_time==(i+1))
                {
                    pointer_for_current_process=pointer_for_current_process+1;
                }
            }
        }
        
        
        System.out.println();
        System.out.println("Average Turnaround time = " + sumTT/(double)list_of_all_processes.length);
        System.out.println("Average Waiting time = " + sumWT/(double)list_of_all_processes.length);
        System.out.println("Average Response time = " + sumRT/(double)list_of_all_processes.length);
        sort(list_of_all_processes,"Completed_time");
        if(context_switching_choice == "YES")
        {
            System.out.println("Throughput = " + (double)list_of_all_processes.length/(list_of_all_processes[list_of_all_processes.length-1].Completed_time+(list_of_all_processes.length-1)*cost_context_switching));
        }
        else
        {
            System.out.println("Throughput = " + (double)list_of_all_processes.length/(double)list_of_all_processes[list_of_all_processes.length-1].Completed_time);
        }
    }
    public static void HPF_P(Process list_of_all_processes[], String context_switching_choice)  // Function to simulate Highest Priority First (Preemptive type) without aging process
    {
        int time = 0;                                                           // Initialize time simulation to 0
        double sumTT = 0;                                                       // Initialize the sum of turnaround time to 0 (Used to calculate average)
        double sumWT = 0;                                                       // Initialize the sum of wait time to 0 (Used to calculate average)
        double sumRT = 0;                                                       // Initialize the sum of response time to 0 (Used to calculate average)
        int number_of_completed_processes = 0;                                  // Initalize the number of completed processes to 0
        ArrayList<Process> ready_state_processes = new ArrayList<Process>();    // Create a ArrayList of Process type to maintain all the Ready state processes
        int count_context_switch = 0;                                           // Initialize the count of context 
        int previous_pid = -2;                                                  // Previous Pid used to check if conext switch occurred.
        while(time<=1000)                                                       // Initialize time to 1000 (arbitary number) for simulation
        {
            for(int i=0; i< list_of_all_processes.length;i++)                   // Iterate over all the processes
            {
                if(list_of_all_processes[i].Arrival_time <= time && list_of_all_processes[i].flag && check(list_of_all_processes[i],ready_state_processes))     // Find out all the process which have arrived, not yet completed and not yet in ready state ArrayList based on the flag value
                {
                    ready_state_processes.add(list_of_all_processes[i]);        // Add processes to the ready List
                }
            }
            if(ready_state_processes.size()==0)                                 // Check if ready state queue is Empty or not
            {
                System.out.print("-");                                          // If empty Print '-' to indicate CPU is Idle
                previous_pid = -1;                                              // Change previous_pid to -1 to indicate CPU was Idle
                time = time +1;                                                 // If empty increment time and continue
            }
            else
            {
                Collections.sort(ready_state_processes, new SortbyPriority());  // Sort the process in ready state based on their Priority
                Process current_running_process = ready_state_processes.get(0); // Remove the process with highest Priority to give CPU time
                current_running_process.insert(time);                           // Pass this instant of time to insert function to find out time at which this Process received the CPU for the first time
                System.out.print("[P"+current_running_process.Pid+"]");         // Print the Pid of the current running process for 1 time unit
                if(previous_pid != current_running_process.Pid)                 // If the current pid is not equal to the previous running pid then a context switch has occurred
                {
                    count_context_switch = count_context_switch + 1;            // Increment the count of context switches
                    previous_pid = current_running_process.Pid;                 // Change the previous pid to the current pid
                }
                for(int j=0;j<list_of_all_processes.length;j++)                 //  For loop to find current_running_process in list_of_all_processes to create a reference between them
                {
                    if(list_of_all_processes[j].Pid == current_running_process.Pid)
                    {
                        list_of_all_processes[j] = current_running_process;
                        break;
                    }
                }
                time = time + 1;                                                // Increment time by 1 unit
                if((current_running_process.Execution_time-1)<=0)               // Check if the decrement of Execution time by 1 unit results in a negative number and change it to 0 or simply just decrement it
                {
                    current_running_process.Execution_time = 0;
                }
                else
                {
                    current_running_process.Execution_time = current_running_process.Execution_time - 1;
                }
                ready_state_processes.remove(0);                                // Process was removed from the ready queue
                if(current_running_process.Execution_time >0)                   // Check it the Process has completed execution and if not add it back to ready queue
                {
                    ready_state_processes.add(current_running_process);
                }
                if(current_running_process.Execution_time == 0)                 // If the Process has completed Execution calculate the statistics
                {
                    current_running_process.flag = false;                       // Change the flag to false to indicate process has completed execution
                    current_running_process.Completed_time = time;              // Update Completed time
                    current_running_process.Turnaround_Time = (float)current_running_process.Completed_time - current_running_process.Arrival_time;     // Update Turnaround time
                    current_running_process.Wait_time = current_running_process.Turnaround_Time - current_running_process.original_Execution_time;      // Update Wait time
                    current_running_process.Response_time = current_running_process.Time_of_first_CPU_slot - current_running_process.Arrival_time;      // Update Response time
                    sumTT = sumTT + current_running_process.Turnaround_Time;    // Update sum of Turnaround time
                    sumWT = sumWT + current_running_process.Wait_time;          // Update sum of Wait time
                    sumRT = sumRT + current_running_process.Response_time;      // Update sum of Response time
                    number_of_completed_processes = number_of_completed_processes +1;       // Increment the number of completed processes by 1
                }
            }
            if(number_of_completed_processes == list_of_all_processes.length)   // Check if all the processes have been served and break the while loop if done
            {
                System.out.println();
                break;
            }
        }
        System.out.println();
        display(list_of_all_processes);                                         // Display the entire Process table
        
        System.out.println();
        System.out.println("Average Turnaround time = " + sumTT/(double)list_of_all_processes.length);      // Display average Turnaround time  
        System.out.println("Average Waiting time = " + sumWT/(double)list_of_all_processes.length);         // Display average Response time
        System.out.println("Average Response time = " + sumRT/(double)list_of_all_processes.length);        // Display average Response time    
        if(context_switching_choice == "YES")                                   // Calculate Throughput with/without effect of context switching
        {
            // For HPF (preemptive type) number of context switches will be (count_context_switches) and Calculate Throughput
            System.out.println("Throughput = " + (double)list_of_all_processes.length/(list_of_all_processes[list_of_all_processes.length-1].Completed_time+(count_context_switch)*cost_context_switching));
        }
        else
        {
            // Calculate Throughput
            System.out.println("Throughput = " + (double)list_of_all_processes.length/(double)list_of_all_processes[list_of_all_processes.length-1].Completed_time);
        }
    }
    public static void HPF_NP(Process list_of_all_processes[],String context_switching_choice)  // Function to simulate Highest Priority First (Non-preemptive type) without aging process
    {
        int time = 0;                                                           // Initialize time simulation to 0
        double sumTT = 0;                                                       // Initialize the sum of turnaround time to 0 (Used to calculate average)
        double sumWT = 0;                                                       // Initialize the sum of wait time to 0 (Used to calculate average)
        double sumRT = 0;                                                       // Initialize the sum of response time to 0 (Used to calculate average)
        int number_of_completed_processes = 0;                                  // Initalize the number of completed processes to 0
        ArrayList<Process> ready_state_processes = new ArrayList<Process>();    // Create a ArrayList of Process type to maintain all the Ready state processes
        while(time<=1000)                                                       // Initialize time to 1000 (arbitary number) for simulation
        {
            for(int i=0; i< list_of_all_processes.length;i++)                   // Iterate over all the processes
            {
                if(list_of_all_processes[i].Arrival_time <= time && list_of_all_processes[i].flag)      // Find out all the process which have arrived and not yet in ready state ArrayList based on the flag value
                {
                    ready_state_processes.add(list_of_all_processes[i]);        // Add processes to the ready List
                    list_of_all_processes[i].flag = false;                      // Change the flag to indicate that process is in the queue 
                }
            }
            if(ready_state_processes.size()==0)                                 // Check if ready state queue is Empty or not
            {
                time = time +1;                                                 // If empty increment time and continue
            }
            else
            {
                Collections.sort(ready_state_processes, new SortbyPriority());  // Sort the process in ready state based on their Priority
                Process current_running_process = ready_state_processes.remove(0);  // Remove the process with highest Priority to give CPU time
                for(int i=0;i<list_of_all_processes.length;i++)                 //  For loop to find current_running_process in list_of_all_processes to create a reference between them
                {
                    if(list_of_all_processes[i].Pid == current_running_process.Pid)
                    {
                        current_running_process = list_of_all_processes[i];
                        break;
                    }
                }
                time = time + (int)Math.ceil(current_running_process.Execution_time);       // Change time till end of current_process ends
                current_running_process.Completed_time = time;                  // Update Completed time
                current_running_process.Turnaround_Time = (float)current_running_process.Completed_time - current_running_process.Arrival_time;     // Update Turnaround time
                current_running_process.Wait_time = current_running_process.Turnaround_Time - current_running_process.Execution_time;           // Update Wait time
                current_running_process.Response_time = current_running_process.Wait_time;          // Update Response time
                sumTT = sumTT + current_running_process.Turnaround_Time;            // Update sum of Turnaround time
                sumWT = sumWT + current_running_process.Wait_time;                  // Update sum of Wait time
                sumRT = sumRT + current_running_process.Response_time;              // Update sum of Response time
                number_of_completed_processes = number_of_completed_processes +1;   // Increment the number of completed processes by 1
            }
            if(number_of_completed_processes == list_of_all_processes.length)       // Check if all the processes have been served and break the while loop if done
            {
                break;
            }
        }
        System.out.println();
        display(list_of_all_processes);                                         // Display the entire Process table
        System.out.println();
        sort(list_of_all_processes,"Completed_time");                           // Sort by Completed Process to print out the Process-time chart
        int pointer_for_current_process=0;                                      // Initialize pointers to point to first completed process
        for(int i=0;i<list_of_all_processes[list_of_all_processes.length-1].Completed_time;i++)     // Iterate over time from 0 till last Process' Completed time
        {
            if((int)Math.ceil(list_of_all_processes[pointer_for_current_process].Arrival_time)>i)   // If Process has not arrived print '-'
            {
                System.out.print('-');
            }
            else
            {
                System.out.print("[P"+list_of_all_processes[pointer_for_current_process].Pid+"]");  // If Process has arrived print Process Pid
                if(list_of_all_processes[pointer_for_current_process].Completed_time==(i+1))        // If the Process has reached it's completed time Increment the pointer. 
                {
                    pointer_for_current_process=pointer_for_current_process+1;
                }
            }
        }
        
        
        System.out.println();
        System.out.println("Average Turnaround time = " + sumTT/(double)list_of_all_processes.length);      // Display average of Turnaround time
        System.out.println("Average Waiting time = " + sumWT/(double)list_of_all_processes.length);         // Display average of Wait time
        System.out.println("Average Response time = " + sumRT/(double)list_of_all_processes.length);        // Display average of Response time
        if(context_switching_choice == "YES")                                   // Calculate Throughput with/without effect of context switching
        {
            // For HPF (non preemptive type) number of context switches will be (number of processes - 1) and Calculate Throughput
            System.out.println("Throughput = " + (double)list_of_all_processes.length/(list_of_all_processes[list_of_all_processes.length-1].Completed_time+(list_of_all_processes.length-1)*cost_context_switching));
        }
        else
        {
            // Calculate Throughput
            System.out.println("Throughput = " + (double)list_of_all_processes.length/(double)list_of_all_processes[list_of_all_processes.length-1].Completed_time);
        }
    }
    public static void RR(Process list_of_all_processes[], String context_switching_choice)    // Function to simulate Round Robin with time quanta = 2
    {
        int time = 0;                                                           // Initialize time simulation to 0
        double sumTT = 0;                                                       // Initialize the sum of turnaround time to 0 (Used to calculate average)
        double sumWT = 0;                                                       // Initialize the sum of wait time to 0 (Used to calculate average)
        double sumRT = 0;                                                       // Initialize the sum of response time to 0 (Used to calculate average)
        int number_of_completed_processes = 0;                                  // Initalize the number of completed processes to 0
        Queue<Process> ready_state_processes = new LinkedList<Process>();       // Create a Queue of Process type to maintain all the Ready state processes
        int count_context_switch = 0;                                           // Initialize the count of context switches to 0
        int previous_pid = -2;                                                  // Previous Pid used to check if conext switch occurred.
        while(time<=1000)                                                       // Initialize time to 1000 (arbitary number) for simulation
        {
            for(int i=0; i< list_of_all_processes.length;i++)                   // Iterate over all the processes
            {
                if(list_of_all_processes[i].Arrival_time <= time && list_of_all_processes[i].flag && checkRR(list_of_all_processes[i],ready_state_processes))       // Find out all the process which have arrived, not yet completed and not yet in ready state ArrayList based on the flag value
                {
                    ready_state_processes.add(list_of_all_processes[i]);        // Add processes to the ready List
                }
            }
            if(ready_state_processes.size()==0)                                 // Check if ready state queue is Empty or not
            {
                System.out.print("-");                                          // If empty Print '-' to indicate CPU is Idle
                previous_pid = -1;                                              // Change previous_pid to -1 to indicate CPU was Idle
                time = time +1;                                                 // If empty increment time and continue
            }
            else
            {
                Process current_running_process = ready_state_processes.remove();       // Remove the process with lowest Execution time to give CPU time
                current_running_process.insert(time);                           // Pass this instant of time to insert function to find out time at which this Process received the CPU for the first time
                if(previous_pid != current_running_process.Pid)                 // If the current pid is not equal to the previous running pid then a context switch has occurred
                {
                    count_context_switch = count_context_switch + 1;            // Increment the count of context switches
                    previous_pid = current_running_process.Pid;                 // Change the previous pid to the current pid
                }
                for(int j=0;j<list_of_all_processes.length;j++)                 // For loop to find current_running_process in list_of_all_processes to create a reference between them
                {
                    if(list_of_all_processes[j].Pid == current_running_process.Pid)
                    {
                        list_of_all_processes[j] = current_running_process;
                        break;
                    }
                }
                if(current_running_process.Execution_time>1)                    // If Execution time is greater than 1 the  it will take the entire 2 time units of 1 time quanta
                {
                    time = time + 2;                                            // Increment time by 2 units
                    System.out.print("[P"+current_running_process.Pid+"]");     // Print the Pid of the current running process for 1 time unit         
                    System.out.print("[P"+current_running_process.Pid+"]");     // Print the Pid of the current running process for 1 time unit 
                    current_running_process.Execution_time = current_running_process.Execution_time - 2;    // Decrement the Execution time of current process by 2 units.
                    if(current_running_process.Execution_time <= 0)             // If the Execution time is less than or equal to 0 it has completed execution
                    {
                        current_running_process.Execution_time = 0;             // Update Execution time to 0.
                        current_running_process.flag = false;                   // Change the flag to false to indicate process has completed execution 
                        current_running_process.Completed_time = time;          // Update Completed time
                        current_running_process.Turnaround_Time = (float)current_running_process.Completed_time - current_running_process.Arrival_time;     // Update Turnaround time
                        current_running_process.Wait_time = current_running_process.Turnaround_Time - current_running_process.original_Execution_time;      // Update Wait time
                        current_running_process.Response_time = current_running_process.Time_of_first_CPU_slot - current_running_process.Arrival_time;      // Update Response time
                        sumTT = sumTT + current_running_process.Turnaround_Time;        // Update sum of Turnaround time
                        sumWT = sumWT + current_running_process.Wait_time;              // Update sum of Wait time
                        sumRT = sumRT + current_running_process.Response_time;          // Update sum of Response time
                        number_of_completed_processes = number_of_completed_processes +1;   // Increment the number of completed processes by 1
                    }
                    else
                    {
                        for(int i=0; i< list_of_all_processes.length;i++)       // If process hasn't completed execution Add process that arrived in the 2 units time and then add the current process back to end of the queue
                        {
                            if(list_of_all_processes[i].Arrival_time <= time && list_of_all_processes[i].flag && checkRR(list_of_all_processes[i],ready_state_processes) && (list_of_all_processes[i].Pid!=current_running_process.Pid))
                            {
                                ready_state_processes.add(list_of_all_processes[i]); 
                            }
                        }
                        ready_state_processes.add(current_running_process);
                    }
                }
                else
                {
                    if(current_running_process.Execution_time==1)               // Repeat the above same steps of statstics calculation if the remaining Execution time is 1 time unit
                    {
                        time = time + 1;
                        System.out.print("[P"+current_running_process.Pid+"]");
                        current_running_process.Execution_time = current_running_process.Execution_time - 1;
                        current_running_process.flag = false;
                        current_running_process.Completed_time = time;
                        current_running_process.Turnaround_Time = (float)current_running_process.Completed_time - current_running_process.Arrival_time;
                        current_running_process.Wait_time = current_running_process.Turnaround_Time - current_running_process.original_Execution_time;
                        current_running_process.Response_time = current_running_process.Time_of_first_CPU_slot - current_running_process.Arrival_time;
                        sumTT = sumTT + current_running_process.Turnaround_Time;
                        sumWT = sumWT + current_running_process.Wait_time;
                        sumRT = sumRT + current_running_process.Response_time;
                        number_of_completed_processes = number_of_completed_processes +1;
                    }
                    else
                    {
                        if(current_running_process.Execution_time<1 && current_running_process.Execution_time>0)  // Repeat the above same steps of statstics calculation if the remaining Execution time is less than 1 time unit and greater than 0.
                        {
                            time = time + 1;
                            System.out.print("[P"+current_running_process.Pid+"]");
                            current_running_process.Execution_time = 0;
                            current_running_process.flag = false;
                            current_running_process.Completed_time = time;
                            current_running_process.Turnaround_Time = (float)current_running_process.Completed_time - current_running_process.Arrival_time;
                            current_running_process.Wait_time = current_running_process.Turnaround_Time - current_running_process.original_Execution_time;
                            current_running_process.Response_time = current_running_process.Time_of_first_CPU_slot - current_running_process.Arrival_time;
                            sumTT = sumTT + current_running_process.Turnaround_Time;
                            sumWT = sumWT + current_running_process.Wait_time;
                            sumRT = sumRT + current_running_process.Response_time;
                            number_of_completed_processes = number_of_completed_processes +1;
                        }
                    }
                }
                
            }
            if(number_of_completed_processes == list_of_all_processes.length)   // Check if all the processes have been served and break the while loop if done
            {
                System.out.println();
                break;
            }
        }
        System.out.println();
        display(list_of_all_processes);                                         // Display the entire Process table
        
        System.out.println();
        System.out.println("Average Turnaround time = " + sumTT/(double)list_of_all_processes.length);      // Display average Turnaround time  
        System.out.println("Average Waiting time = " + sumWT/(double)list_of_all_processes.length);         // Display average Wait time  
        System.out.println("Average Response time = " + sumRT/(double)list_of_all_processes.length);        // Display average Response time  
        if(context_switching_choice == "YES")                                   // Calculate Throughput with/without effect of context switching
        {
            // For RR number of context switches will be (count_context_switches) and Calculate Throughput
            System.out.println("Throughput = " + (double)list_of_all_processes.length/(list_of_all_processes[list_of_all_processes.length-1].Completed_time+(count_context_switch)*cost_context_switching));
        }
        else
        {
            // Calculate Throughput
            System.out.println("Throughput = " + (double)list_of_all_processes.length/(double)list_of_all_processes[list_of_all_processes.length-1].Completed_time);
        }
    }
    public static void SRT(Process list_of_all_processes[], String context_switching_choice)    // Function to simulate Shortest Remaining Time
    {
        int time = 0;                                                           // Initialize time simulation to 0
        double sumTT = 0;                                                       // Initialize the sum of turnaround time to 0 (Used to calculate average)
        double sumWT = 0;                                                       // Initialize the sum of wait time to 0 (Used to calculate average)
        double sumRT = 0;                                                       // Initialize the sum of response time to 0 (Used to calculate average)
        int number_of_completed_processes = 0;                                  // Initalize the number of completed processes to 0
        ArrayList<Process> ready_state_processes = new ArrayList<Process>();    // Create a ArrayList of Process type to maintain all the Ready state processes
        int count_context_switch = 0;                                           // Initialize the count of context switches to 0
        int previous_pid = -2;                                                  // Previous Pid used to check if conext switch occurred.
        while(time<=1000)                                                       // Initialize time to 1000 (arbitary number) for simulation
        {
            for(int i=0; i< list_of_all_processes.length;i++)                   // Iterate over all the processes
            {
                if(list_of_all_processes[i].Arrival_time <= time && list_of_all_processes[i].flag && check(list_of_all_processes[i],ready_state_processes))      // Find out all the process which have arrived, not yet completed and not yet in ready state ArrayList based on the flag value
                {
                    ready_state_processes.add(list_of_all_processes[i]);        // Add processes to the ready List
                }
            }
            if(ready_state_processes.size()==0)                                 // Check if ready state queue is Empty or not
            {
                System.out.print("-");                                          // If empty Print '-' to indicate CPU is Idle
                previous_pid = -1;                                              // Change previous_pid to -1 to indicate CPU was Idle
                time = time +1;                                                 // If empty increment time and continue
            }
            else
            {
                Collections.sort(ready_state_processes, new SortbyExecutionTime());         // Sort the process in ready state based on their 'Remaining' Execution time
                Process current_running_process = ready_state_processes.get(0);      // Read the process with lowest Execution time to give CPU time
                current_running_process.insert(time);                           // Pass this instant of time to insert function to find out time at which this Process received the CPU for the first time
                System.out.print("[P"+current_running_process.Pid+"]");         // Print the Pid of the current running process for 1 time unit         
                if(previous_pid != current_running_process.Pid)                 // If the current pid is not equal to the previous running pid then a context switch has occurred
                {
                    count_context_switch = count_context_switch + 1;            // Increment the count of context switches
                    previous_pid = current_running_process.Pid;                 // Change the previous pid to the current pid
                }
                for(int j=0;j<list_of_all_processes.length;j++)                 //  For loop to find current_running_process in list_of_all_processes to create a reference between them
                {
                    if(list_of_all_processes[j].Pid == current_running_process.Pid)
                    {
                        list_of_all_processes[j] = current_running_process;
                        break;
                    }
                }
                time = time + 1;                                                // Increment time by 1 unit
                if((current_running_process.Execution_time-1)<=0)               // Check if the decrement of Execution time by 1 unit results in a negative number and change it to 0 or simply just decrement it
                {
                    current_running_process.Execution_time = 0;
                }
                else
                {
                    current_running_process.Execution_time = current_running_process.Execution_time - 1;
                }
                ready_state_processes.remove(0);                                // Process was removed from the ready queue
                if(current_running_process.Execution_time >0)                   // Check it the Process has completed execution and if not add it back to ready queue
                {
                    ready_state_processes.add(current_running_process);
                }
                if(current_running_process.Execution_time == 0)                 // If the Process has completed Execution calculate the statistics
                {
                    current_running_process.flag = false;                       // Change the flag to false to indicate process has completed execution
                    current_running_process.Completed_time = time;              // Update Completed time
                    current_running_process.Turnaround_Time = (float)current_running_process.Completed_time - current_running_process.Arrival_time;     // Update Turnaround time
                    current_running_process.Wait_time = current_running_process.Turnaround_Time - current_running_process.original_Execution_time;      // Update Wait time
                    current_running_process.Response_time = current_running_process.Time_of_first_CPU_slot - current_running_process.Arrival_time;      // Update Response time
                    sumTT = sumTT + current_running_process.Turnaround_Time;            // Update sum of Turnaround time
                    sumWT = sumWT + current_running_process.Wait_time;                  // Update sum of Wait time
                    sumRT = sumRT + current_running_process.Response_time;              // Update sum of Response time
                    number_of_completed_processes = number_of_completed_processes +1;   // Increment the number of completed processes by 1
                }
            }
            if(number_of_completed_processes == list_of_all_processes.length)   // Check if all the processes have been served and break the while loop if done
            {
                System.out.println();
                break;
            }
        }
        System.out.println();
        display(list_of_all_processes);                                         // Display the entire Process table
        
        System.out.println();
        System.out.println("Average Turnaround time = " + sumTT/(double)list_of_all_processes.length);      // Display average Turnaround time  
        System.out.println("Average Waiting time = " + sumWT/(double)list_of_all_processes.length);         // Display average Wait time
        System.out.println("Average Response time = " + sumRT/(double)list_of_all_processes.length);        // Display average Response time
        if(context_switching_choice == "YES")                                   // Calculate Throughput with/without effect of context switching
        {
            // For SRT number of context switches will be (count_context_switches) and Calculate Throughput
            System.out.println("Throughput = " + (double)list_of_all_processes.length/(list_of_all_processes[list_of_all_processes.length-1].Completed_time+(count_context_switch)*cost_context_switching));
        }
        else
        {
            // Calculate Throughput
            System.out.println("Throughput = " + (double)list_of_all_processes.length/(double)list_of_all_processes[list_of_all_processes.length-1].Completed_time);
        }
    }
    public static void SJF(Process list_of_all_processes[],String context_switching_choice)     // Function to simulate Shortest Job First
    {
        int time = 0;                                                           // Initialize time simulation to 0
        double sumTT = 0;                                                       // Initialize the sum of turnaround time to 0 (Used to calculate average)
        double sumWT = 0;                                                       // Initialize the sum of wait time to 0 (Used to calculate average)
        double sumRT = 0;                                                       // Initialize the sum of response time to 0 (Used to calculate average)
        int number_of_completed_processes = 0;                                  // Initalize the number of completed processes to 0
        ArrayList<Process> ready_state_processes = new ArrayList<Process>();    // Create a ArrayList of Process type to maintain all the Ready state processes
        while(time<=1000)                                                       // Initialize time to 1000 (arbitary number) for simulation
        {
            for(int i=0; i< list_of_all_processes.length;i++)                   // Iterate over all the processes
            {
                if(list_of_all_processes[i].Arrival_time <= time && list_of_all_processes[i].flag)      // Find out all the process which have arrived and not yet in ready state ArrayList based on the flag value
                {
                    ready_state_processes.add(list_of_all_processes[i]);        // Add processes to the ready List
                    list_of_all_processes[i].flag = false;                      // Change the flag to indicate that process is in the queue  
                }
            }
            if(ready_state_processes.size()==0)                                 // Check if ready state queue is Empty or not
            {
                time = time +1;                                                 // If empty increment time and continue
            }
            else
            {
                Collections.sort(ready_state_processes, new SortbyExecutionTime());     // Sort the process in ready state based on their Execution time
                Process current_running_process = ready_state_processes.remove(0);      // Remove the process with lowest Execution time to give CPU time
                for(int i=0;i<list_of_all_processes.length;i++)                         //  For loop to find current_running_process in list_of_all_processes to create a reference between them
                {
                    if(list_of_all_processes[i].Pid == current_running_process.Pid)
                    {
                        current_running_process = list_of_all_processes[i];
                        break;
                    }
                }
                time = time + (int)Math.ceil(current_running_process.Execution_time);   // Change time till end of current_process ends
                current_running_process.Completed_time = time;                          // Update Completed Time
                current_running_process.Turnaround_Time = (float)current_running_process.Completed_time - current_running_process.Arrival_time; // Update Turnaround time
                current_running_process.Wait_time = current_running_process.Turnaround_Time - current_running_process.Execution_time;       // Update Wait time
                current_running_process.Response_time = current_running_process.Wait_time;      // Update Response time
                sumTT = sumTT + current_running_process.Turnaround_Time;                    // Update sum of Turnaround time
                sumWT = sumWT + current_running_process.Wait_time;                          // Update sum of Wait time
                sumRT = sumRT + current_running_process.Response_time;                      // Update sum of Response time
                number_of_completed_processes = number_of_completed_processes +1;           // Increment the number of completed processes by 1
            }
            if(number_of_completed_processes == list_of_all_processes.length)               // Check if all the processes have been served and break the while loop if done
            {
                break;
            }
        }
        System.out.println();
        display(list_of_all_processes);                                         // Display the entire Process table
        System.out.println();
        
        sort(list_of_all_processes,"Completed_time");                           // Sort by Completed Process to print out the Process-time chart
        int pointer_for_current_process=0;                                      // Initialize pointers to point to first completed process
        for(int i=0;i<list_of_all_processes[list_of_all_processes.length-1].Completed_time;i++)         // Iterate over time from 0 till last Process' Completed time
        {
            if((int)Math.ceil(list_of_all_processes[pointer_for_current_process].Arrival_time)>i)       // If Process has not arrived print '-'
            {
                System.out.print('-');
            }
            else
            {
                System.out.print("[P"+list_of_all_processes[pointer_for_current_process].Pid+"]");      // If Process has arrived print Process Pid
                if(list_of_all_processes[pointer_for_current_process].Completed_time==(i+1))            // If the Process has reached it's completed time Increment the pointer.
                {
                    pointer_for_current_process=pointer_for_current_process+1;
                }
            }
        }
        
        
        System.out.println();
        System.out.println("Average Turnaround time = " + sumTT/(double)list_of_all_processes.length);      // Display average Turnaround time
        System.out.println("Average Waiting time = " + sumWT/(double)list_of_all_processes.length);         // Display average Wait time
        System.out.println("Average Response time = " + sumRT/(double)list_of_all_processes.length);        // Display average Response time
        if(context_switching_choice == "YES")                                   // Calculate Throughput with/without effect of context switching
        {
            // For SJF number of context switches will be (number of processes - 1) and Calculate Throughput
            System.out.println("Throughput = " + (double)list_of_all_processes.length/(list_of_all_processes[list_of_all_processes.length-1].Completed_time+(list_of_all_processes.length-1)*cost_context_switching));
        }
        else
        {
            // Calculate Throughput
            System.out.println("Throughput = " + (double)list_of_all_processes.length/(double)list_of_all_processes[list_of_all_processes.length-1].Completed_time);
        }
    } 
    public static void FCFS(Process list_of_all_processes[],String context_switching_choice)    // Function to simulate First Come First Serve
    {
        int time = 0;                                                           // Initialize time simulation to 0
        double sumTT = 0;                                                       // Initialize the sum of turnaround time to 0 (Used to calculate average)
        double sumWT = 0;                                                       // Initialize the sum of wait time to 0 (Used to calculate average)
        double sumRT = 0;                                                       // Initialize the sum of response time to 0 (Used to calculate average)
        for(int i=0; i<list_of_all_processes.length;i++)                        // Iterate over all the processes (sorted based on Arrival time) to calculate statistics
        {
            if(time < list_of_all_processes[i].Arrival_time)                    // Check if the current process has arrived or not
            {
                time = (int)Math.ceil(list_of_all_processes[i].Arrival_time);   // If process hasn't arrived change the time to match the Arrival time of current process
            }
            time = time + (int)Math.ceil(list_of_all_processes[i].Execution_time);  // Change the time till the end of current process' execution time
            list_of_all_processes[i].Completed_time = time;                         // Update Completed time
            list_of_all_processes[i].Turnaround_Time = (float)list_of_all_processes[i].Completed_time - (float)list_of_all_processes[i].Arrival_time;   // Update Turnaround time
            list_of_all_processes[i].Wait_time = list_of_all_processes[i].Turnaround_Time - list_of_all_processes[i].Execution_time;    // Update Wait time
            list_of_all_processes[i].Response_time = list_of_all_processes[i].Wait_time;                // Update Response time
            sumTT = sumTT + list_of_all_processes[i].Turnaround_Time;           // Update the sum of Turnaround time
            sumWT = sumWT + list_of_all_processes[i].Wait_time;                 // Update the sum of Wait time
            sumRT = sumRT + list_of_all_processes[i].Response_time;             // Update the sum of Response time
        }
        System.out.println();
        display(list_of_all_processes);                                         // Print out the Process table
        System.out.println();
        sort(list_of_all_processes,"Completed_time");                           // Sort by Completed Process to print out the Process-time chart
        int pointer_for_current_process=0;                                      // Initialize pointers to point to first completed process
        for(int i=0;i<list_of_all_processes[list_of_all_processes.length-1].Completed_time;i++)     // Iterate over time from 0 till last Process' Completed time
        {
            if((int)Math.ceil(list_of_all_processes[pointer_for_current_process].Arrival_time)>i)       // If Process has not arrived print '-'
            {
                System.out.print('-');
            }
            else
            {
                System.out.print("[P"+list_of_all_processes[pointer_for_current_process].Pid+"]");      // If Process has arrived print Process Pid
                if(list_of_all_processes[pointer_for_current_process].Completed_time==(i+1))            // If the Process has reached it's completed time Increment the pointer.
                {
                    pointer_for_current_process=pointer_for_current_process+1;
                }
            }
        }
        System.out.println();
        System.out.println("Average Turnaround time = " + sumTT/(double)list_of_all_processes.length);      // Display average Turnaround time
        System.out.println("Average Waiting time = " + sumWT/(double)list_of_all_processes.length);         // Display average Waiting time
        System.out.println("Average Response time = " + sumRT/(double)list_of_all_processes.length);        // Display average Response time
        if(context_switching_choice == "YES")                                   // Calculate Throughput with/without effect of context switching
        {
            // For FCFS number of context switches will be (number of processes - 1) and Calculate Throughput
            System.out.println("Throughput = " + (double)list_of_all_processes.length/(list_of_all_processes[list_of_all_processes.length-1].Completed_time+(list_of_all_processes.length-1)*cost_context_switching));
        }
        else
        {
            // Calculate Throughput
            System.out.println("Throughput = " + (double)list_of_all_processes.length/(double)list_of_all_processes[list_of_all_processes.length-1].Completed_time);
        }
    }
}
class Process                           //Process Object
{
    public float Arrival_time;          //Arrival Time
    public float Execution_time;        //Execution Time / Burst Time
    public int Pid;                     //Process Number /ID
    public int Priority;                //Priority
    public int Time_of_first_CPU_slot;          //Time at which the Process got it's Time_of_first_CPU_slot CPU time
    public float original_Execution_time;       //Temporary Storage for Execution time (used in premeptive algorithms)
    public int original_Priority;               //Temporary Storage for Priority (used in aging algorithms)
    public int Completed_time;          //Completed Time
    public float Turnaround_Time;       //Turnaround Time
    public float Wait_time;             //Wait Time
    public float Response_time;         //Response Time
    public boolean flag;                //Flag to check if Process has been inserted into a Queue and/or finished Execution
    
    public Process(int Pid, float Arrival_time, float Execution_time, int Priority)        //Constructor used to initialize variables
    {
        this.Pid = Pid;                             //Initialization
        this.Arrival_time = Arrival_time;           //Initialization
        this.Execution_time = Execution_time;       //Initialization
        if(Execution_time == 0)                     // This is used to prevent
        {                                           // Execution time from being
            this.Execution_time = (float)0.1;       // of 0 time units.
        }                                           // (Case : when Math.random() = 0)
        this.Priority = Priority;                   //Initialization
        if(this.Priority == 5)                      // This is used to prevent
        {                                           // Priority from being
            this.Priority = 4;                      // of value 5.
        }                                           // (Case : when Math.random() = 1)
        this.original_Execution_time = this.Execution_time;      // Backup for Execution time (used in preemptive algorithms) 
        this.original_Priority = this.Priority;     // Backup for Priority (used in aging algorithms)
        this.Completed_time = -1;                   // Dummy value Initialization
        this.Turnaround_Time = -1;                  // Dummy value Initialization
        this.Wait_time = -1;                        // Dummy value Initialization
        this.Response_time = -1;                    // Dummy value Initialization
        this.flag = true;                           // Dummy value Initialization
        this.Time_of_first_CPU_slot = Integer.MAX_VALUE; // Dummy value Initialization to Maximum Integer value
    }
    
    public void insert(int Time_of_first_CPU_slot) // Function used to find the Time at which this Process got it's Time_of_first_CPU_slot CPU time
    {
        if(this.Time_of_first_CPU_slot >= Time_of_first_CPU_slot)               // This function is called each time,
        {                                                                       // this process gets a CPU time unit.
            this.Time_of_first_CPU_slot = Time_of_first_CPU_slot;               // This blocks makes sure that only
        }                                                                       // the smallest time unit is stored in 'Time_of_first_CPU_slot' variable.
    }
}
class SortbyExecutionTime implements Comparator<Process>        // Function used by the Comparator class to distinguish two Process objects
{
    public int compare(Process a, Process b)                    // The comparison is based on which process
    {                                                           // has a larger execution time.
        if(a.Execution_time==b.Execution_time)                  // If the process have equal execution time return 0.
        {                                                       // If process 'a' has larger execution time than process 'b'
            return 0;                                           // return 1 otherwise return -1.
        }
        else
        {
            if(a.Execution_time>b.Execution_time)
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
    }
}
class SortbyPriority implements Comparator<Process>             // Function used by Comparator class to distinguish two Process objects
{
    public int compare(Process a, Process b)            // The comparison is based on which process
    {                                                   // has a lower priority and lower Arrival time.
        if(a.Priority==b.Priority)                      // If the process have equal priority and equal arrival time then return 0.
        {                                               // If the process have equal priority and process 'a' has later arrival time
            if(a.Arrival_time == b.Arrival_time)        // than process 'b' then return 1 otherwise return -1.
            {                                           // Similarly, if process 'a' has higher priority than process 'b' return 1
                return 0;                               // otherwise return -1.
            }
            if(a.Arrival_time > b.Arrival_time)
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
        else
        {
            if(a.Priority>b.Priority)
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
    }
}
