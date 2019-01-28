import java.util.*;
/*NOTES
input "6 0 6 3 1 4 3 5 6 1 6 6 1 7 6 5 8 6 6" 
when asked "Enter number of processes"

this is exact question from assignment
*/
public class ShortestJob {
	public static void main (String args[]){
		int totb = 0; //totalbursttime
		int count = 0;//timer
		ArrayList<Process> processes = new ArrayList<>();
		ArrayList<Integer> result = new ArrayList<>();
		ArrayList<Integer> gant = new ArrayList<>();
		
		// input 
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of process: ");
		int n = sc.nextInt(); //number of process
		
		for(int i=0; i<n; i++){
			int x,y,z;
			System.out.println("PROCESS [" + i + "] arrival time ,burst time ,priority \nplease seperate with [SPACE]");
			
			x = sc.nextInt();
			y = sc.nextInt();
			z = sc.nextInt();
			processes.add(new Process(x,y,z));
		}
		
		//printing contents of array processes
		System.out.println("\n |Arrival" + "\t|Burst Time" + "\t|Priority" + "\t|" );
		for(Process p : processes){
			System.out.println(p);	
		}
		int minc = 1000;
		for (Process p: processes){ //calculating total burst time
			totb += p.getburst();
			if(p.getarrival()<=minc){
				minc = p.getarrival();
			}
		}
		
		for(Process p : processes){
			p.setarrival(p.getarrival() - minc);
		}
		
		do{ 
			System.out.println(count);
			result.add(count);
			int min = 1000, tbp = 0; //minimum burst time, to be processed
			for (int i=0; i<n; i++){
				if ((processes.get(i).getarrival() <= count) && (processes.get(i).getburst()) != 0){
					if(processes.get(i).getburst() == min){
						if(processes.get(tbp).getpriority() > processes.get(i).getpriority()){ //compare priority
						min = processes.get(i).getburst();
						tbp = i;
						System.out.println("ENTERED PRIORITY");
						}
					}
					
					else if (processes.get(i).getburst()<min){
						min = processes.get(i).getburst();
						tbp = i;
					}
							
					
					//else processes.get(tbh).getprio() <= processes.get(i).getprio();
					//min is still the same
				}
				
			}

			if (tbp > n){
				System.out.println("error");
			}
			
			else{
				int res = (processes.get(tbp).getburst()) - 1;
				processes.get(tbp).setburst(res);
				count ++;
			}
			//printing the results
			result.add(tbp);
			
			
		}while(count<totb);
		
		gant.add(result.get(0)); //time 0 
		gant.add(result.get(1)); //first process
		for(int i=3; i<result.size(); i=i+2){ 
			if (result.get(i) == result.get(i-2)){
				//nothing
			}
			else{
				gant.add(result.get(i-1));//time
				gant.add(result.get(i));//add process
				
			}
		}
		gant.add(totb);
		
		System.out.println("\n GANT CHART");
		boolean print = false;
		for(int i=1; i<=gant.size(); i=i+2){
			if(i==(gant.size())){
				System.out.println("");
				print = true;
				i = -2;
			}
			else if(print == false){
				System.out.print("p"+ gant.get(i) + "\t| ");
			}
			
			else if(print == true){
				System.out.print(gant.get(i) + "\t");
			}
			
		}
		
	}
	
}

class Process{
	int name;
	int burstTime;
	int arrival;
	int prio;
	public Process(int arr, int bt, int prio){
		setburst(bt);
		setarrival(arr);
		this.prio = prio;
	}
	
	public int getname(){
		return name;
	}
	
	public int getburst(){
		return burstTime;
	}
	
	public void setburst(int bt){
		this.burstTime = bt;
	}
	
	public int getarrival(){
		return arrival;
	}
	
	public void setarrival(int arr){
		this.arrival = arr;
	}
	
	public int getpriority(){
		return prio;
	}
	
	public String toString(){
		return " |" + arrival + "\t\t|" + burstTime + "\t\t|" + prio +"\t\t|";
	}	
	
}


