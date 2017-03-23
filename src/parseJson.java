package work_project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;



public class parseJson {
	
	public int findKthLargest(int[] nums, int k) {

        k = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            final int j = partition(nums, lo, hi);
            if(j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }

    private int partition(int[] a, int lo, int hi) {

        int i = lo;
        int j = hi + 1;
        while(true) {
            while(i < hi && less(a[++i], a[lo]));
            while(j > lo && less(a[lo], a[--j]));
            if(i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }
    
    private void exch(int[] a, int i, int j) {
        final int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private boolean less(int v, int w) {
        return v < w;
    }
	class DLinkedNode {
		int key;
		int value;
		DLinkedNode pre;
		DLinkedNode post;
	}

	/**
	 * Always add the new node right after head;
	 */
	private void addNode(DLinkedNode node){
		node.pre = head;
		node.post = head.post;
		
		head.post.pre = node;
		head.post = node;
	}

	/**
	 * Remove an existing node from the linked list.
	 */
	private void removeNode(DLinkedNode node){
		DLinkedNode pre = node.pre;
		DLinkedNode post = node.post;
		
		pre.post = post;
		post.pre = pre;
	}

	/**
	 * Move certain node in between to the head.
	 */
	private void moveToHead(DLinkedNode node){
		this.removeNode(node);
		this.addNode(node);
	}

	// pop the current tail. 
	private DLinkedNode popTail(){
		DLinkedNode res = tail.pre;
		this.removeNode(res);
		return res;
	}

	private Hashtable<Integer, DLinkedNode> 
		cache = new Hashtable<Integer, DLinkedNode>();
	private int count;
	private int capacity;
	private DLinkedNode head, tail;

	public void LRUCache(int capacity) {
		this.count = 0;
		this.capacity = capacity;

		head = new DLinkedNode();
		head.pre = null;
		
		tail = new DLinkedNode();
		tail.post = null;
		
		head.post = tail;
		tail.pre = head;
	}

	public int get(int key) {
	    
		DLinkedNode node = cache.get(key);
		if(node == null){
			return -1; // should raise exception here.
		}
		
		// move the accessed node to the head;
		this.moveToHead(node);
		
		return node.value;
	}


	public void set(int key, int value) {
		DLinkedNode node = cache.get(key);
		
		if(node == null){
			
			DLinkedNode newNode = new DLinkedNode();
			newNode.key = key;
			newNode.value = value;
			
			this.cache.put(key, newNode);
			this.addNode(newNode);
			
			++count;
			
			if(count > capacity){
				// pop the tail
				DLinkedNode tail = this.popTail();
				this.cache.remove(tail.key);
				--count;
			}
		}else{
			// update the value.
			node.value = value;
			this.moveToHead(node);
		}
		
	}
	
	public static class wrapper implements Comparable<wrapper>{
		
		int num;
		int count;
		wrapper(int num, int count)
		{
			this.num = num;
			this.count = count;
		}
		@Override
		public int compareTo(wrapper that) {
			
			return that.count - this.count;
		}
		
	}
	
	public int[] re(int[] nums)
	{
		Map<Integer,Integer> map = new HashMap<>();
		for(int num : nums)
		{
			if(map.containsKey(num))
			{
				map.put(num, map.get(num)+1);
			}
			else
			{
				map.put(num, 1);
			}
		}
		PriorityQueue<wrapper> pq = new PriorityQueue<>();
		
		PriorityQueue<Integer> pq1 = new PriorityQueue<Integer>(3, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return 0;
			}
		});
		
		for(Map.Entry<Integer, Integer> item : map.entrySet())
		{
			wrapper w = new wrapper(item.getKey(),item.getValue());
			pq.offer(w);
		}
		int result[] = new int[nums.length];
		int index = 0;
		while(!pq.isEmpty())
		{
			wrapper w = pq.poll();
			for(int i =0; i < w.count;i++)
			{
				result[index++] = w.num;
			}
		}
		return result;
	}
	
	public static class Interval{
		double start;
		double end;
		String type;
		//default constructor
		Interval()
		{
		}
		Interval(double start,double end,String type)
		{
			this.start = start;
			this.end = end;
			this.type = type;
		}
	}
	
	public static class Holder{
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public List<String> getSubEventTypes() {
			return subEventTypes;
		}
		public void setSubEventTypes(List<String> subEventTypes) {
			this.subEventTypes = subEventTypes;
		}
		public Double getTime() {
			return time;
		}
		public void setTime(Double time) {
			this.time = time;
		}
		public String get$$hashKey() {
			return $$hashKey;
		}
		public void set$$hashKey(String $$hashKey) {
			this.$$hashKey = $$hashKey;
		}
		public Double getEndTime() {
			return endTime;
		}
		public void setEndTime(Double endTime) {
			this.endTime = endTime;
		}
		public List<Double> getSubEventSplits() {
			return subEventSplits;
		}
		public void setSubEventSplits(List<Double> subEventSplits) {
			this.subEventSplits = subEventSplits;
		}
		public Holder(){};
		String name;
		List<String> subEventTypes = new ArrayList<>();
		Double time;
		String $$hashKey;
		Double endTime;
		List<Double> subEventSplits = new ArrayList<>();
		
	}
	class MapperClass{
		List<Holder> list = new ArrayList<>();
	}
	public static double findLowFHighF(List<Double> list){
		
		if(list.size()==0) return 0.0;
		int counter =0;
		for(int i =0; i < list.size();i++){
			if(list.get(i)>0||list.get(i)<0){
				counter++;
			}
		}
		return counter/1.0;
	}
	public static int findMin(List<Double> list)
	{
		double result = 500000.0;
		for(double i : list)
		{
			if(i<=result) result =i;
		}
		return (int)result;
	}
	
	public static int findMax(List<Double> list)
	{
		double result = -50000.0;
		for(double i : list)
		{
			if(i>=result) result =i;
		}
		return (int)result;
	}
	public static int findF(List<Double> list)
	{
		if(list.size()==0||list.size()==1) return 0;
		int result =0;
		double average =0.0;
		for(double i : list)
		{
			average += i;
		}
		average = average / (double) list.size();
		boolean increasing = list.get(0)>average;
		
		
		for(int i =1; i<list.size();i++)
		{
			if(increasing&&list.get(i)<average)
			{
				result++;
				increasing = false;
			}
			else if(!increasing&&list.get(i)>average)
			{
				result++;
				increasing = true;
			}
			else
			{
				increasing = list.get(i)>average;
			}
		}
		return result;
	}
	public static int findV(List<Double> list)
	{
		double result =0.0;
		double average = 0.0;
		for(double i : list)
		{
			result+=i;
		}
		average = result / (double) list.size();
		double sum =0.0;
		for(double i : list)
		{
			sum+= (average - i) * (average-i);
		}

		if(list.size()==1) return (int)sum;
		
		return (int) (sum/(list.size()-1));
	}
	
	public static int findAvg(List<Double> list){
		
		double result =0.0;
		for(double d: list){
			result+=d;
		}
		return (int) (result/list.size());
	}
	
	public static int findMed(List<Double> list){
		Collections.sort(list);
		int middleIndex = list.size()/2;

		if(list.size()%2==0){
			double a = list.get(middleIndex-1);
			double b = list.get(middleIndex);
			return (int)( (a+b)/2 );
		}
		double c = list.get(middleIndex);
		return (int) c;

	}
	
	
	
	public static void main(String[] args) throws IOException
	{
		Double A1minD =0.0;
		Double A2minD =0.0;
		Double A3minD =0.0;
		Double A1maxD =0.0;
		Double A2maxD =0.0;
		Double A3maxD =0.0;
		Double A1VD =0.0;
		Double A2VD =0.0;
		Double A3VD =0.0;
		Double A1FD =0.0;
		Double A2FD =0.0;
		Double A3FD =0.0;
		Double G1minD =0.0;
		Double G2minD =0.0;
		Double G3minD =0.0;
		Double G1maxD =0.0;
		Double G2maxD =0.0;
		Double G3maxD =0.0;
		Double G1VD =0.0;
		Double G2VD =0.0;
		Double G3VD =0.0;
		Double G1FD =0.0;
		Double G2FD =0.0;
		Double G3FD =0.0;

		
		List<Double> A1VD_10 = new ArrayList<>();
		List<Double> A2VD_10= new ArrayList<>();
		List<Double> A3VD_10 = new ArrayList<>();
		List<Double> A1minD_10 = new ArrayList<>();		
		List<Double> A2minD_10 = new ArrayList<>();
		List<Double> A3minD_10 = new ArrayList<>();		
		List<Double> A1maxD_10 = new ArrayList<>();
		List<Double> A2maxD_10 = new ArrayList<>();		
		List<Double> A3maxD_10 = new ArrayList<>();
		
		List<Double> G1VD_10 = new ArrayList<>();
		List<Double> G2VD_10 = new ArrayList<>();
		List<Double> G3VD_10 = new ArrayList<>();
		List<Double> G1minD_10 = new ArrayList<>();		
		List<Double> G2minD_10 = new ArrayList<>();
		List<Double> G3minD_10 = new ArrayList<>();		
		List<Double> G1maxD_10 = new ArrayList<>();
		List<Double> G2maxD_10 = new ArrayList<>();		
		List<Double> G3maxD_10 = new ArrayList<>();
		
		String[] training_csv = {"d106.csv","d22.csv","d33.csv","d44.csv","d77.csv","d88.csv","d100.csv",
				"d101.csv","d105.csv"};//"d1.csv","d2.csv","d3.csv",
		String[] testing_csv = {"d11.csv"};//"d102.csv","d103.csv","d104.csv"
		
		String[] training_json = {"j106.json","j22.json","j33.json","j44.json","j77.json","j88.json","j100.json","j101.json",
				"j105.json"};//"j11.json","j22.json","j33.json",
		String[] testing_json ={"j11.json"};//"j102.json","j103.json","j104.json"
		String outputFileName = "Pill_Training_TimeInterval_";

			//print to test firstPassData
	//		for(String str: firstPass)
	//		{
	//			System.out.println(str);
	//		}
			
			//divid into 1/10 intervals
		Double oneTen = 0.1;
		Double outputInterval = 0.05;
		for(int c = 0; c < 6;c++){
			HashMap<String,Integer> checkD = new HashMap<>();

			boolean generateTitle = false;

			String finalGeneratedResult = "";
			outputInterval= Math.round((outputInterval+ 0.05)*100.0)/100.0;

			String outputFileTemp = outputFileName +"_" + String.valueOf(outputInterval) +".csv";
			finalGeneratedResult+=outputFileTemp +";";
			File writeFile = new File(outputFileTemp);
			BufferedWriter output = null;
			output = new BufferedWriter(new FileWriter(writeFile));
			for(int a =0; a <training_csv.length;a++){
				
				
		//		Scanner t=new Scanner(System.in);
		//		System.out.println("Enter input files name saprated by ';' (enter CSV file first, then Json file, then output file) : ");
			//	String inputFileName=t.nextLine();
			//	String[] arr = inputFileName.split(";");
				
				String inputFileNameCSV = training_csv[a];
				String inputFileNameJson = training_json[a];

				//String outputFileName = t.nextLine();
				ObjectMapper mapper = new ObjectMapper();
				List<Holder>  list = mapper.readValue(new File(inputFileNameJson), mapper.getTypeFactory().constructCollectionType(
		                List.class, Holder.class));
				List<Interval> intervals = new ArrayList<Interval>();
				double firstStart = 0.0;//first start time
				boolean meetRedFlash = false;
				double previous = 0.0;
			//	System.out.println("Generating Results....");
				//different between current window and previous window

				//initialize list
				
				List<Double> A1VD_5 = new ArrayList<>();
				List<Double> A2VD_5 = new ArrayList<>();
				List<Double> A3VD_5 = new ArrayList<>();
				List<Double> A1minD_5 = new ArrayList<>();		
				List<Double> A2minD_5 = new ArrayList<>();
				List<Double> A3minD_5 = new ArrayList<>();		
				List<Double> A1maxD_5 = new ArrayList<>();
				List<Double> A2maxD_5 = new ArrayList<>();		
				List<Double> A3maxD_5 = new ArrayList<>();
				
				List<Double> G1VD_5 = new ArrayList<>();
				List<Double> G2VD_5 = new ArrayList<>();
				List<Double> G3VD_5 = new ArrayList<>();
				List<Double> G1minD_5 = new ArrayList<>();		
				List<Double> G2minD_5 = new ArrayList<>();
				List<Double> G3minD_5 = new ArrayList<>();		
				List<Double> G1maxD_5 = new ArrayList<>();
				List<Double> G2maxD_5 = new ArrayList<>();		
				List<Double> G3maxD_5 = new ArrayList<>();
				
				List<Double> A1Avg_5 = new ArrayList<>();
				List<Double> A2Avg_5 = new ArrayList<>();
				List<Double> A3Avg_5 = new ArrayList<>();
				List<Double> G1Avg_5 = new ArrayList<>();
				List<Double> G2Avg_5 = new ArrayList<>();
				List<Double> G3Avg_5 = new ArrayList<>();
				
				List<Double> A1Med_5 = new ArrayList<>();
				List<Double> A2Med_5 = new ArrayList<>();
				List<Double> A3Med_5 = new ArrayList<>();
				List<Double> G1Med_5 = new ArrayList<>();
				List<Double> G2Med_5 = new ArrayList<>();
				List<Double> G3Med_5 = new ArrayList<>();

				
				for(Holder holder : list)
				{
					if(holder.name.equals("First Red Flash"))
					{
						firstStart = holder.time;
						previous = holder.time;
						meetRedFlash = true;
					}
					if(meetRedFlash==true)
					{
						if(holder.subEventSplits.size()!=0)
						{
							//add previous left first
							Interval interval = new Interval();
		
							interval.start = previous;
							interval.end = holder.time;
							interval.type = "rest";
							intervals.add(interval);
		
							//current
							Interval firstInterval = new Interval();
							firstInterval.start = holder.time;
							firstInterval.end = holder.subEventSplits.get(0);
							firstInterval.type = holder.subEventTypes.get(0);
							intervals.add(firstInterval);
							for(int i =0;i<holder.subEventSplits.size()-1;i++)
							{
								Interval temp = new Interval(holder.subEventSplits.get(i),holder.subEventSplits.get(i+1),holder.subEventTypes.get(i+1));
								intervals.add(temp);
							}
							//last
							intervals.add(new Interval(holder.subEventSplits.get(holder.subEventSplits.size()-1),holder.endTime,holder.subEventTypes.get(holder.subEventTypes.size()-1)));
							previous = holder.endTime;
						}
		
					}
				}	
				Interval lastInterval = new Interval();
				lastInterval.start = previous;
				lastInterval.end = list.get(list.size()-1).time;
				lastInterval.type = "rest";
				intervals.add(lastInterval);
				
				//print to test Json
		//		for(Interval i: intervals)
		//		{
		//			System.out.println(i.start);
		//			System.out.println(i.end);
		//			System.out.println(i.type);
		//		}
				
				// start to parse the file
				BufferedReader br = null;
				String line = "";
				FileReader fr = null;
				fr = new FileReader(inputFileNameCSV);
				br = new BufferedReader(fr);
				br = new BufferedReader(new FileReader(inputFileNameCSV));
			//	br = new BufferedReader(new FileReader(inputFileNameCSV));
				int intervalCounter =0;
				boolean findLED = false;
				double offsetTime = 0.0;
				List<String> firstPass = new ArrayList<>();
				while((line=br.readLine())!=null)
				{
					String[] strArr = line.split(",");
					if(findLED==false&&strArr.length>1&&strArr[2].equals("LED Sync"))
					{
		
						offsetTime = Double.parseDouble(strArr[1]) / 512 - firstStart;
						findLED = true;
					}
					if(findLED==true&&strArr.length>4)
					{
						double time = Double.parseDouble(strArr[1])/512+offsetTime;
					
						if(intervalCounter<intervals.size()&&time<intervals.get(intervalCounter).end)
						{
							String str = strArr[0] +"," + (time) + "," + strArr[2] + "," + strArr[3]+","+strArr[4] +"," + intervals.get(intervalCounter).type;
							firstPass.add(str);
						}else
						{
							intervalCounter++;
							if(intervalCounter<intervals.size())
							{
								String str = strArr[0] +"," + (time ) + "," + strArr[2] + "," + strArr[3]+","+strArr[4] +"," + intervals.get(intervalCounter).type;
								firstPass.add(str);
							}
						}
					}
				}
			
			

			
			int countNumber = 0;

			List<Double> A1 = new ArrayList<>();
			List<Double> A2 = new ArrayList<>();
			List<Double> A3 = new ArrayList<>();
	
			List<Double> G1 = new ArrayList<>();
			List<Double> G2 = new ArrayList<>();
			List<Double> G3 = new ArrayList<>();
			String startType = "";
			String endType = "";
			List<String> finalResult = new ArrayList<>();
			int m =0;
			int threeSec = 30;
			int fiveSec = 50;
			int halfSec = 5;
			int oneSec = 10;
			for(int i =0; i < firstPass.size();i=+m)
			{
				//countNumber++;
				Double stopPoint = Double.parseDouble(firstPass.get(m).split(",")[1])+oneTen;
				startType = firstPass.get(i).split(",")[5];
				while(m<firstPass.size()&&Double.parseDouble(firstPass.get(m).split(",")[1]) <= stopPoint)
				{
					if(firstPass.get(m).split(",")[0].equals("A"))
					{
						A1.add(Double.parseDouble(firstPass.get(m).split(",")[2]));
						A2.add(Double.parseDouble(firstPass.get(m).split(",")[3]));
						A3.add(Double.parseDouble(firstPass.get(m).split(",")[4]));
	
					}
					else if(firstPass.get(m).split(",")[0].equals("G"))
					{
						G1.add(Double.parseDouble(firstPass.get(m).split(",")[2]));
						G2.add(Double.parseDouble(firstPass.get(m).split(",")[3]));
						G3.add(Double.parseDouble(firstPass.get(m).split(",")[4]));
					}
					endType = firstPass.get(m).split(",")[5];
					m++;
				}
				if(startType.equals(endType))
				{
					double A1min = findMin(A1);
					double A2min = findMin(A2);
					double A3min = findMin(A3);
					double A1max = findMax(A1);
					double A2max = findMax(A2);
					double A3max = findMax(A3);
					double A1V = findV(A1);
					double A2V = findV(A2);
					double A3V = findV(A3);
					double A1F = findF(A1);
					double A2F = findF(A2);
					double A3F = findF(A3);
					double G1min = findMin(G1);
					double G2min = findMin(G2);
					double G3min = findMin(G3);
					double G1max = findMax(G1);
					double G2max = findMax(G2);
					double G3max = findMax(G3);
					double G1V = findV(G1);
					double G2V = findV(G2);
					double G3V = findV(G3);
					double G1F = findF(G1);
					double G2F = findF(G2);
					double G3F = findF(G3);
					//newly added
					
					double A1Avg = findAvg(A1);
					double A2Avg = findAvg(A2);
					double A3Avg = findAvg(A3);
					double G1Avg = findMax(G1);
					double G2Avg = findMax(G2);
					double G3Avg = findMax(G3);
					double A1Med = findAvg(A1);
					double A2Med = findAvg(A2);
					double A3Med = findAvg(A3);
					double G1Med = findMax(G1);
					double G2Med = findMax(G2);
					double G3Med = findMax(G3);
					
					//newly added March 21st, avg and med
					A1Avg_5.add(A1Avg);
					A2Avg_5.add(A2Avg);
					A3Avg_5.add(A3Avg);
					G1Avg_5.add(G1Avg);
					G2Avg_5.add(G2Avg);
					G3Avg_5.add(G3Avg);
					
					A1Med_5.add(A1Med);
					A2Med_5.add(A2Med);
					A3Med_5.add(A3Med);
					G1Med_5.add(G1Med);
					G2Med_5.add(G2Med);
					G3Med_5.add(G3Med);
					//////////////////////
					
					
					
					A1VD_5.add(A1V);
					A2VD_5.add(A2V);
					A3VD_5.add(A3V);
					A1minD_5.add(A1min);
					A2minD_5.add(A2min);
					A3minD_5.add(A3min);	
					A1maxD_5.add(A1max);
					A2maxD_5.add(A2max);		
					A3maxD_5.add(A3max);
					
					G1VD_5.add(G1V);
					G2VD_5.add(G2V);
					G3VD_5.add(G3V);
					G1minD_5.add(G1min);		
					G2minD_5.add(G2min);
					G3minD_5.add(G3min);		
					G1maxD_5.add(G1max);
					G2maxD_5.add(G2max);		
					G3maxD_5.add(G3max);
				//	System.out.println(A1minD_5.size());
					int threeSecP = (int) ((threeSec/oneTen)/10);
					int fiveSecP =(int)((fiveSec/oneTen)/10);
					int halfSecP = (int) ((halfSec/oneTen)/10);
					int oneSecP = (int) ((oneSec/oneTen)/10);

				//	System.out.println(fiveSecP);
					if(countNumber>fiveSecP){
				//		System.out.println(countNumber);
						finalResult.add(String.valueOf(A1min) + "," +  String.valueOf(A2min) + "," +  String.valueOf(A3min) +
								","  +  String.valueOf(A1max) + ","  +  String.valueOf(A2max) + "," +  String.valueOf(A3max) +
								","  +  String.valueOf(A1V) + ","  +  String.valueOf(A2V) + ","  +  String.valueOf(A3V) + 
								","  +  String.valueOf(A1F) + ","  +  String.valueOf(A2F) + "," + String.valueOf(A3F) + ","+
								String.valueOf(G1min) + "," +  String.valueOf(G2min) + "," +  String.valueOf(G3min) +
								","  +  String.valueOf(G1max) + ","  +  String.valueOf(G2max) + "," +  String.valueOf(G3max) +
								","  +  String.valueOf(G1V) + ","  +  String.valueOf(G2V) + ","  +  String.valueOf(G3V) + 
								","  +  String.valueOf(G1F) + ","  +  String.valueOf(G2F) + "," + String.valueOf(G3F) + "," +
								//difference 3 sec
								String.valueOf(A1minD_5.get(countNumber)-A1minD_5.get(countNumber-threeSecP))+"," 
								+ String.valueOf(A2minD_5.get(countNumber)-A2minD_5.get(countNumber-threeSecP))+
								"," + String.valueOf(A3minD_5.get(countNumber)-A3minD_5.get(countNumber-threeSecP))+"," + String.valueOf(G1minD_5.get(countNumber)-G1minD_5.get(countNumber-threeSecP))+
								"," + String.valueOf(G2minD_5.get(countNumber)-G2minD_5.get(countNumber-threeSecP))+"," + String.valueOf(G3minD_5.get(countNumber)-G3minD_5.get(countNumber-threeSecP))+
								"," + String.valueOf(A1maxD_5.get(countNumber)-A1maxD_5.get(countNumber-threeSecP))+"," + String.valueOf(A2maxD_5.get(countNumber)-A2maxD_5.get(countNumber-threeSecP))+
								"," + String.valueOf(A3maxD_5.get(countNumber)-A3maxD_5.get(countNumber-threeSecP))+"," + String.valueOf(G1maxD_5.get(countNumber)-G1maxD_5.get(countNumber-threeSecP))+","
								+ String.valueOf(G2maxD_5.get(countNumber)-G2maxD_5.get(countNumber-threeSecP))+
								"," + String.valueOf(G3maxD_5.get(countNumber)-G3maxD_5.get(countNumber-threeSecP))+"," + String.valueOf(A1VD_5.get(countNumber)-A1VD_5.get(countNumber-threeSecP))+
								"," + String.valueOf(A2VD_5.get(countNumber)-A2VD_5.get(countNumber-threeSecP))+"," + String.valueOf(A3VD_5.get(countNumber)-A3VD_5.get(countNumber-threeSecP))+
								"," + String.valueOf(G1VD_5.get(countNumber)-G1VD_5.get(countNumber-threeSecP))+"," + String.valueOf(G2VD_5.get(countNumber)-G2VD_5.get(countNumber-threeSecP))+
								"," + String.valueOf(G3VD_5.get(countNumber)-G3VD_5.get(countNumber-threeSecP))+
								//5 sec
								"," + String.valueOf(A1minD_5.get(countNumber)-A1minD_5.get(countNumber-fiveSecP))+"," 
								+ String.valueOf(A2minD_5.get(countNumber)-A2minD_5.get(countNumber-fiveSecP))+
								"," + String.valueOf(A3minD_5.get(countNumber)-A3minD_5.get(countNumber-fiveSecP))+"," + String.valueOf(G1minD_5.get(countNumber)-G1minD_5.get(countNumber-fiveSecP))+
								"," + String.valueOf(G2minD_5.get(countNumber)-G2minD_5.get(countNumber-fiveSecP))+"," + String.valueOf(G3minD_5.get(countNumber)-G3minD_5.get(countNumber-fiveSecP))+
								"," + String.valueOf(A1maxD_5.get(countNumber)-A1maxD_5.get(countNumber-fiveSecP))+"," + String.valueOf(A2maxD_5.get(countNumber)-A2maxD_5.get(countNumber-fiveSecP))+
								"," + String.valueOf(A3maxD_5.get(countNumber)-A3maxD_5.get(countNumber-fiveSecP))+"," + String.valueOf(G1maxD_5.get(countNumber)-G1maxD_5.get(countNumber-fiveSecP))+"," + String.valueOf(G2maxD_5.get(countNumber)-G2maxD_5.get(countNumber-fiveSecP))+
								"," + String.valueOf(G3maxD_5.get(countNumber)-G3maxD_5.get(countNumber-fiveSecP))+"," + String.valueOf(A1VD_5.get(countNumber)-A1VD_5.get(countNumber-fiveSecP))+
								"," + String.valueOf(A2VD_5.get(countNumber)-A2VD_5.get(countNumber-fiveSecP))+"," + String.valueOf(A3VD_5.get(countNumber)-A3VD_5.get(countNumber-fiveSecP))+
								"," + String.valueOf(G1VD_5.get(countNumber)-G1VD_5.get(countNumber-fiveSecP))+"," + String.valueOf(G2VD_5.get(countNumber)-G2VD_5.get(countNumber-fiveSecP))+
								"," + String.valueOf(G3VD_5.get(countNumber)-G3VD_5.get(countNumber-fiveSecP))+ ","+ 
								
								//half sec
								 String.valueOf(A1minD_5.get(countNumber)-A1minD_5.get(countNumber-halfSecP))+"," 
								+ String.valueOf(A2minD_5.get(countNumber)-A2minD_5.get(countNumber-halfSecP))+
								"," + String.valueOf(A3minD_5.get(countNumber)-A3minD_5.get(countNumber-halfSecP))+"," + String.valueOf(G1minD_5.get(countNumber)-G1minD_5.get(countNumber-halfSecP))+
								"," + String.valueOf(G2minD_5.get(countNumber)-G2minD_5.get(countNumber-halfSecP))+"," + String.valueOf(G3minD_5.get(countNumber)-G3minD_5.get(countNumber-halfSecP))+
								"," + String.valueOf(A1maxD_5.get(countNumber)-A1maxD_5.get(countNumber-halfSecP))+"," + String.valueOf(A2maxD_5.get(countNumber)-A2maxD_5.get(countNumber-halfSecP))+
								"," + String.valueOf(A3maxD_5.get(countNumber)-A3maxD_5.get(countNumber-halfSecP))+"," + String.valueOf(G1maxD_5.get(countNumber)-G1maxD_5.get(countNumber-halfSecP))+"," + String.valueOf(G2maxD_5.get(countNumber)-G2maxD_5.get(countNumber-halfSecP))+
								"," + String.valueOf(G3maxD_5.get(countNumber)-G3maxD_5.get(countNumber-halfSecP))+"," + String.valueOf(A1VD_5.get(countNumber)-A1VD_5.get(countNumber-halfSecP))+
								"," + String.valueOf(A2VD_5.get(countNumber)-A2VD_5.get(countNumber-halfSecP))+"," + String.valueOf(A3VD_5.get(countNumber)-A3VD_5.get(countNumber-halfSecP))+
								"," + String.valueOf(G1VD_5.get(countNumber)-G1VD_5.get(countNumber-halfSecP))+"," + String.valueOf(G2VD_5.get(countNumber)-G2VD_5.get(countNumber-halfSecP))+
								"," + String.valueOf(G3VD_5.get(countNumber)-G3VD_5.get(countNumber-halfSecP))+ ","+ 
								
								
								
								
								
								// one sec
								 String.valueOf(A1minD_5.get(countNumber)-A1minD_5.get(countNumber-oneSecP))+"," 
								+ String.valueOf(A2minD_5.get(countNumber)-A2minD_5.get(countNumber-oneSecP))+
								"," + String.valueOf(A3minD_5.get(countNumber)-A3minD_5.get(countNumber-oneSecP))+"," + String.valueOf(G1minD_5.get(countNumber)-G1minD_5.get(countNumber-oneSecP))+
								"," + String.valueOf(G2minD_5.get(countNumber)-G2minD_5.get(countNumber-oneSecP))+"," + String.valueOf(G3minD_5.get(countNumber)-G3minD_5.get(countNumber-oneSecP))+
								"," + String.valueOf(A1maxD_5.get(countNumber)-A1maxD_5.get(countNumber-oneSecP))+"," + String.valueOf(A2maxD_5.get(countNumber)-A2maxD_5.get(countNumber-oneSecP))+
								"," + String.valueOf(A3maxD_5.get(countNumber)-A3maxD_5.get(countNumber-oneSecP))+"," + String.valueOf(G1maxD_5.get(countNumber)-G1maxD_5.get(countNumber-oneSecP))+"," + String.valueOf(G2maxD_5.get(countNumber)-G2maxD_5.get(countNumber-oneSecP))+
								"," + String.valueOf(G3maxD_5.get(countNumber)-G3maxD_5.get(countNumber-oneSecP))+"," + String.valueOf(A1VD_5.get(countNumber)-A1VD_5.get(countNumber-oneSecP))+
								"," + String.valueOf(A2VD_5.get(countNumber)-A2VD_5.get(countNumber-oneSecP))+"," + String.valueOf(A3VD_5.get(countNumber)-A3VD_5.get(countNumber-oneSecP))+
								"," + String.valueOf(G1VD_5.get(countNumber)-G1VD_5.get(countNumber-oneSecP))+"," + String.valueOf(G2VD_5.get(countNumber)-G2VD_5.get(countNumber-oneSecP))+
								"," + String.valueOf(G3VD_5.get(countNumber)-G3VD_5.get(countNumber-oneSecP))+ ","+ 
								
								//Avg and Med
								
								String.valueOf(A1Avg) +","+String.valueOf(A2Avg) +","+String.valueOf(A3Avg) +","+ 
								String.valueOf(G1Avg) +","+String.valueOf(G2Avg) +","+String.valueOf(G3Avg) +","+
								String.valueOf(A1Med) +","+String.valueOf(A2Med) +","+String.valueOf(A3Med) +","+ 
								String.valueOf(G1Med) +","+String.valueOf(G2Med) +","+String.valueOf(G3Med) +","+

								//Avg 0.5
								String.valueOf(A1Avg_5.get(countNumber)-A1Avg_5.get(countNumber-halfSecP))+"," +
								String.valueOf(A2Avg_5.get(countNumber)-A2Avg_5.get(countNumber-halfSecP))+"," +
								String.valueOf(A3Avg_5.get(countNumber)-A3Avg_5.get(countNumber-halfSecP))+"," +
								String.valueOf(G1Avg_5.get(countNumber)-G1Avg_5.get(countNumber-halfSecP))+"," +
								String.valueOf(G2Avg_5.get(countNumber)-G2Avg_5.get(countNumber-halfSecP))+"," +
								String.valueOf(G3Avg_5.get(countNumber)-G3Avg_5.get(countNumber-halfSecP))+"," +
								
								//Avg 1
								String.valueOf(A1Avg_5.get(countNumber)-A1Avg_5.get(countNumber-oneSecP))+"," +
								String.valueOf(A2Avg_5.get(countNumber)-A2Avg_5.get(countNumber-oneSecP))+"," +
								String.valueOf(A3Avg_5.get(countNumber)-A3Avg_5.get(countNumber-oneSecP))+"," +
								String.valueOf(G1Avg_5.get(countNumber)-G1Avg_5.get(countNumber-oneSecP))+"," +
								String.valueOf(G2Avg_5.get(countNumber)-G2Avg_5.get(countNumber-oneSecP))+"," +
								String.valueOf(G3Avg_5.get(countNumber)-G3Avg_5.get(countNumber-oneSecP))+"," +
								
								//Avg 3
								String.valueOf(A1Avg_5.get(countNumber)-A1Avg_5.get(countNumber-threeSecP))+"," +
								String.valueOf(A2Avg_5.get(countNumber)-A2Avg_5.get(countNumber-threeSecP))+"," +
								String.valueOf(A3Avg_5.get(countNumber)-A3Avg_5.get(countNumber-threeSecP))+"," +
								String.valueOf(G1Avg_5.get(countNumber)-G1Avg_5.get(countNumber-threeSecP))+"," +
								String.valueOf(G2Avg_5.get(countNumber)-G2Avg_5.get(countNumber-threeSecP))+"," +
								String.valueOf(G3Avg_5.get(countNumber)-G3Avg_5.get(countNumber-threeSecP))+"," +
								
								//Avg 5
								String.valueOf(A1Avg_5.get(countNumber)-A1Avg_5.get(countNumber-fiveSecP))+"," +
								String.valueOf(A2Avg_5.get(countNumber)-A2Avg_5.get(countNumber-fiveSecP))+"," +
								String.valueOf(A3Avg_5.get(countNumber)-A3Avg_5.get(countNumber-fiveSecP))+"," +
								String.valueOf(G1Avg_5.get(countNumber)-G1Avg_5.get(countNumber-fiveSecP))+"," +
								String.valueOf(G2Avg_5.get(countNumber)-G2Avg_5.get(countNumber-fiveSecP))+"," +
								String.valueOf(G3Avg_5.get(countNumber)-G3Avg_5.get(countNumber-fiveSecP))+"," +
								
								
								//Med 0.5
								String.valueOf(A1Med_5.get(countNumber)-A1Med_5.get(countNumber-halfSecP))+"," +
								String.valueOf(A2Med_5.get(countNumber)-A2Med_5.get(countNumber-halfSecP))+"," +
								String.valueOf(A3Med_5.get(countNumber)-A3Med_5.get(countNumber-halfSecP))+"," +
								String.valueOf(G1Med_5.get(countNumber)-G1Med_5.get(countNumber-halfSecP))+"," +
								String.valueOf(G2Med_5.get(countNumber)-G2Med_5.get(countNumber-halfSecP))+"," +
								String.valueOf(G3Med_5.get(countNumber)-G3Med_5.get(countNumber-halfSecP))+"," +
								
								//Med 1
								String.valueOf(A1Med_5.get(countNumber)-A1Med_5.get(countNumber-oneSecP))+"," +
								String.valueOf(A2Med_5.get(countNumber)-A2Med_5.get(countNumber-oneSecP))+"," +
								String.valueOf(A3Med_5.get(countNumber)-A3Med_5.get(countNumber-oneSecP))+"," +
								String.valueOf(G1Med_5.get(countNumber)-G1Med_5.get(countNumber-oneSecP))+"," +
								String.valueOf(G2Med_5.get(countNumber)-G2Med_5.get(countNumber-oneSecP))+"," +
								String.valueOf(G3Med_5.get(countNumber)-G3Med_5.get(countNumber-oneSecP))+"," +
								
								//Med 3
								String.valueOf(A1Med_5.get(countNumber)-A1Med_5.get(countNumber-threeSecP))+"," +
								String.valueOf(A2Med_5.get(countNumber)-A2Med_5.get(countNumber-threeSecP))+"," +
								String.valueOf(A3Med_5.get(countNumber)-A3Med_5.get(countNumber-threeSecP))+"," +
								String.valueOf(G1Med_5.get(countNumber)-G1Med_5.get(countNumber-threeSecP))+"," +
								String.valueOf(G2Med_5.get(countNumber)-G2Med_5.get(countNumber-threeSecP))+"," +
								String.valueOf(G3Med_5.get(countNumber)-G3Med_5.get(countNumber-threeSecP))+"," +
								
								//Med 5
								String.valueOf(A1Med_5.get(countNumber)-A1Med_5.get(countNumber-fiveSecP))+"," +
								String.valueOf(A2Med_5.get(countNumber)-A2Med_5.get(countNumber-fiveSecP))+"," +
								String.valueOf(A3Med_5.get(countNumber)-A3Med_5.get(countNumber-fiveSecP))+"," +
								String.valueOf(G1Med_5.get(countNumber)-G1Med_5.get(countNumber-fiveSecP))+"," +
								String.valueOf(G2Med_5.get(countNumber)-G2Med_5.get(countNumber-fiveSecP))+"," +
								String.valueOf(G3Med_5.get(countNumber)-G3Med_5.get(countNumber-fiveSecP))+"," +


								
								
								
								
								
								
								
								
								startType);

						//"A1minD5"+"," +"A2minD5"+"," + "A3minD5"+"," + "G1minD5"+"," + "G2minD5"+"," + "G3minD5"+"," + "A1maxD5"+"," +  "A2maxD5"+"," + "A3maxD5"+"," + "G1maxD5"+"," + "G2maxD5"+"," + "G3maxD5"+"," + "A1vD5"+","+"A2vD5"+","+"A3vD5"+","+"G1vD5"+","+"G2vD5"+","+"G3vD5"+",";
						
					}
					//d107.csv;j107.json;pill_full_17_
//					else if(countNumber>threeSecP){
//						finalResult.add(String.valueOf(A1min) + "," +  String.valueOf(A2min) + "," +  String.valueOf(A3min) +
//								","  +  String.valueOf(A1max) + ","  +  String.valueOf(A2max) + "," +  String.valueOf(A3max) +
//								","  +  String.valueOf(A1V) + ","  +  String.valueOf(A2V) + ","  +  String.valueOf(A3V) + 
//								","  +  String.valueOf(A1F) + ","  +  String.valueOf(A2F) + "," + String.valueOf(A3F) + ","+
//								String.valueOf(G1min) + "," +  String.valueOf(G2min) + "," +  String.valueOf(G3min) +
//								","  +  String.valueOf(G1max) + ","  +  String.valueOf(G2max) + "," +  String.valueOf(G3max) +
//								","  +  String.valueOf(G1V) + ","  +  String.valueOf(G2V) + ","  +  String.valueOf(G3V) + 
//								","  +  String.valueOf(G1F) + ","  +  String.valueOf(G2F) + "," + String.valueOf(G3F) + "," +
//								//difference
//								String.valueOf(A1minD_5.get(countNumber)-A1minD_5.get(countNumber-threeSecP))+"," 
//								+ String.valueOf(A2minD_5.get(countNumber)-A2minD_5.get(countNumber-threeSecP))+
//								"," + String.valueOf(A3minD_5.get(countNumber)-A3minD_5.get(countNumber-threeSecP))+"," + String.valueOf(G1minD_5.get(countNumber)-G1minD_5.get(countNumber-threeSecP))+
//								"," + String.valueOf(G2minD_5.get(countNumber)-G2minD_5.get(countNumber-threeSecP))+"," + String.valueOf(G3minD_5.get(countNumber)-G3minD_5.get(countNumber-threeSecP))+
//								"," + String.valueOf(A1maxD_5.get(countNumber)-A1maxD_5.get(countNumber-threeSecP))+"," + String.valueOf(A2maxD_5.get(countNumber)-A2maxD_5.get(countNumber-threeSecP))+
//								"," + String.valueOf(A3maxD_5.get(countNumber)-A3maxD_5.get(countNumber-threeSecP))+"," + String.valueOf(G1maxD_5.get(countNumber)-G1maxD_5.get(countNumber-threeSecP))+"," + String.valueOf(G2maxD_5.get(countNumber)-G2maxD_5.get(countNumber-threeSecP))+
//								"," + String.valueOf(G3maxD_5.get(countNumber)-G3maxD_5.get(countNumber-threeSecP))+"," + String.valueOf(A1VD_5.get(countNumber)-A1VD_5.get(countNumber-threeSecP))+
//								"," + String.valueOf(A2VD_5.get(countNumber)-A2VD_5.get(countNumber-threeSecP))+"," + String.valueOf(A3VD_5.get(countNumber)-A3VD_5.get(countNumber-threeSecP))+
//								"," + String.valueOf(G1VD_5.get(countNumber)-G1VD_5.get(countNumber-threeSecP))+"," + String.valueOf(G2VD_5.get(countNumber)-G2VD_5.get(countNumber-threeSecP))+
//								"," + String.valueOf(G3VD_5.get(countNumber)-G3VD_5.get(countNumber-threeSecP))+
//								"," + String.valueOf(0)+"," 
//								+ String.valueOf(0)+
//								"," + String.valueOf(0)+"," + String.valueOf(0)+
//								"," + String.valueOf(0)+"," + String.valueOf(0)+
//								"," + String.valueOf(0)+"," + String.valueOf(0)+
//								"," + String.valueOf(0)+"," + String.valueOf(0)+"," + String.valueOf(0)+
//								"," + String.valueOf(0)+"," + String.valueOf(0)+
//								"," + String.valueOf(0)+"," + String.valueOf(0)+
//								"," + String.valueOf(0)+"," + String.valueOf(0)+
//								"," + String.valueOf(0)+ ","+ startType);
//
//
//						
//					}else{
//						finalResult.add(String.valueOf(A1min) + "," +  String.valueOf(A2min) + "," +  String.valueOf(A3min) +
//								","  +  String.valueOf(A1max) + ","  +  String.valueOf(A2max) + "," +  String.valueOf(A3max) +
//								","  +  String.valueOf(A1V) + ","  +  String.valueOf(A2V) + ","  +  String.valueOf(A3V) + 
//								","  +  String.valueOf(A1F) + ","  +  String.valueOf(A2F) + "," + String.valueOf(A3F) + ","+
//								String.valueOf(G1min) + "," +  String.valueOf(G2min) + "," +  String.valueOf(G3min) +
//								","  +  String.valueOf(G1max) + ","  +  String.valueOf(G2max) + "," +  String.valueOf(G3max) +
//								","  +  String.valueOf(G1V) + ","  +  String.valueOf(G2V) + ","  +  String.valueOf(G3V) + 
//								","  +  String.valueOf(G1F) + ","  +  String.valueOf(G2F) + "," + String.valueOf(G3F) + "," +
//								//difference
//								String.valueOf(0) + "," +  String.valueOf(0) + "," +  String.valueOf(0) +
//								","  +  String.valueOf(0) + ","  +  String.valueOf(0) + "," +  String.valueOf(0) +
//								","  +  String.valueOf(0) + ","  +  String.valueOf(0) + ","  +  String.valueOf(0) + 
//								","  +  String.valueOf(0) + ","  +  String.valueOf(0) + "," + String.valueOf(0) + ","+
//								String.valueOf(0) + "," +  String.valueOf(0) + "," +  String.valueOf(0) +
//								","  +  String.valueOf(0) + ","  +  String.valueOf(0) + "," +  String.valueOf(0) +
//								","  +  String.valueOf(0) + ","  +  String.valueOf(0) + ","  +  String.valueOf(0) + 
//								","  +  String.valueOf(0) + ","  +  String.valueOf(0) + "," + String.valueOf(0) + 
//								"," + String.valueOf(0)+"," + String.valueOf(0)+
//								"," + String.valueOf(0)+"," + String.valueOf(0)+
//								"," + String.valueOf(0)+"," + String.valueOf(0)+
//								"," + String.valueOf(0)+"," + String.valueOf(0)+
//								"," + String.valueOf(0)+"," + String.valueOf(0)+"," + String.valueOf(0)+
//								"," + String.valueOf(0)+"," +  startType);
//					}
					
					countNumber++;
					A1minD =A1min;
					A2minD =A2min;
					A3minD =A3min;
					A1maxD =A1max;
					A2maxD =A2max;
					A3maxD =A3max;
					A1VD =A1V;
					A2VD =A2V;
					A3VD =A3V;
					A1FD =A1F;
					A2FD =A2F;
					A3FD =A3F;
					G1minD =G1min;
					G2minD =G2min;
					G3minD =G3min;
					G1maxD =G1max;
					G2maxD =G2max;
					G3maxD =G3max;
					G1VD =G1V;
					G2VD =G2V;
					G3VD =G3V;
					G1FD =G1F;
					G2FD =G2F;
					G3FD =G3F;
				}

				A1 = new ArrayList<>();
				A2 = new ArrayList<>();
				A3 = new ArrayList<>();
				G1 = new ArrayList<>();
				G2 = new ArrayList<>();
				G3 = new ArrayList<>();
				
			}
			//String csvFile = "1.csv";
			String s4 = "-1779.0,-2403.0,-1860.0,-462.0,2424.0,-996.0,182891.66666666666,2310729.285714286,103181.33333333333,2.0,4.0,1.0,-603.0,-1565.0,-1122.0,3454.0,491.0,292.0,2182291.619047619,362732.47619047627,225212.14285714287,1.0,4.0,2.0,272.0,-2273.0,-1763.0,-573.0,-1499.0,-1025.0,1571.0,2547.0,-923.0,3401.0,452.0,229.0,182841.0,2310723.476190476,103108.5238095238,2181351.333333333,361344.19047619053,221223.1904761905,265.0,-2265.0,-1776.0,-612.0,-1554.0,-1095.0,1570.0,2542.0,-938.0,3402.0,379.0,212.0,182866.7619047619,2310684.142857143,103100.5238095238,2182076.0,360576.8571428572,223446.14285714287,,15.0,-1099.0,-980.0,1039.0,-1519.0,-780.0,718.0,3590.0,-1108.0,4090.0,-1250.0,-217.0,122095.19047619047,2307798.142857143,-33501.285714285725,2013669.6666666665,-103762.47619047615,128556.33333333334,,-95.0,-1043.0,-1961.0,1175.0,-1977.0,-1129.0,347.0,3431.0,-1899.0,4403.0,-1472.0,-1221.0,104647.42857142857,2287605.380952381,27179.095238095222,2111847.714285714,126873.52380952387,-67629.33333333328,rest";
			String s3 = ""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+","+""+"," +""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","
					+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+"class";
String s1 = "A1min"+"," +"A2min"+","+"A3min"+","+"A1max"+","+"A2max"+","+"A3max"+","+"A1v"+","+"A2v"+","+"A3v"+","+"A1f"+","+"A2f"+","+"A3f"+","+"G1min"+","+"G2min"+","+"G3min"+","+"G1max"+","+"G2max"+","+"G3max"+","+"G1v"+","+"G2v"+","+"G3v"+","+"G1f"+","+"G2f"+","+"G3f"+","+
		"A1minD"+"," +"A2minD"+","+"A3minD"+","+"A1maxD"+","+"A2maxD"+","+"A3maxD"+","+"A1vD"+","+"A2vD"+","+"A3vD"+","+"G1minD"+","+"G2minD"+","+"G3minD"+","+"G1maxD"+","+"G2maxD"+","+"G3maxD"+","+"G1vD"+","+"G2vD"+","+"G3vD"+","
		  + "A1minD5"+"," +"A2minD5"+"," + "A3minD5"+"," + "G1minD5"+"," + "G2minD5"+"," + "G3minD5"+"," + "A1maxD5"+"," +  "A2maxD5"+"," + "A3maxD5"+"," + "G1maxD5"+"," + "G2maxD5"+"," + "G3maxD5"+"," + "A1vD5"+","+"A2vD5"+","+"A3vD5"+","+"G1vD5"+","+"G2vD5"+","+"G3vD5"+","+
		   "A1minD05"+"," +"A2minD05"+"," + "A3minD05"+"," + "G1minD05"+"," + "G2minD05"+"," + "G3minD05"+"," + "A1maxD05"+"," +  "A2maxD05"+"," + "A3maxD05"+"," + "G1maxD05"+"," + "G2maxD05"+"," + "G3maxD05"+"," + "A1vD05"+","+"A2vD05"+","+"A3vD05"+","+"G1vD05"+","+"G2vD05"+","+"G3vD05"+","+
		   "A1minD1"+"," +"A2minD1"+"," + "A3minD1"+"," + "G1minD1"+"," + "G2minD1"+"," + "G3minD1"+"," + "A1maxD1"+"," +  "A2maxD1"+"," + "A3maxD1"+"," + "G1maxD1"+"," + "G2maxD1"+"," + "G3maxD1"+"," + "A1vD1"+","+"A2vD1"+","+"A3vD1"+","+"G1vD1"+","+"G2vD1"+","+"G3vD1"+","+
			"target";
String s2 ="continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+"," +"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+"," +"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+"," +"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+"," +"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+
		"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+ "continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+ "continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+ "continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+ "continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+ "continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+ "continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+ "continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+ "continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+ "discrete";
			//String s3 = ""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+","+""+"," +""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+"class";
			String[] sarr1 = s1.split(",");
			String[] sarr2 = s2.split(",");
			String[] sarr4 = s4.split(",");
		//	System.out.println(sarr1.length + " " +  sarr2.length + " " + sarr4.length);
			
			if(generateTitle==false){
				generateTitle = true;
				output.write("A1min"+"," +"A2min"+","+"A3min"+","+"A1max"+","+"A2max"+","+"A3max"+","+"A1v"+","+"A2v"+","+"A3v"+","+"A1f"+","+"A2f"+","+"A3f"+","+"G1min"+","+"G2min"+","+"G3min"+","+"G1max"+","+"G2max"+","+"G3max"+","+"G1v"+","+"G2v"+","+"G3v"+","+"G1f"+","+"G2f"+","+"G3f"+","+
				"A1minD"+"," +"A2minD"+","+"A3minD"+","+"A1maxD"+","+"A2maxD"+","+"A3maxD"+","+"A1vD"+","+"A2vD"+","+"A3vD"+","+"G1minD"+","+"G2minD"+","+"G3minD"+","+"G1maxD"+","+"G2maxD"+","+"G3maxD"+","+"G1vD"+","+"G2vD"+","+"G3vD"+","
			  + "A1minD5"+"," +"A2minD5"+"," + "A3minD5"+"," + "G1minD5"+"," + "G2minD5"+"," + "G3minD5"+"," + "A1maxD5"+"," +  "A2maxD5"+"," + "A3maxD5"+"," + "G1maxD5"+"," + "G2maxD5"+"," + "G3maxD5"+"," + "A1vD5"+","+"A2vD5"+","+"A3vD5"+","+"G1vD5"+","+"G2vD5"+","+"G3vD5"+","+
			   "A1minD05"+"," +"A2minD05"+"," + "A3minD05"+"," + "G1minD05"+"," + "G2minD05"+"," + "G3minD05"+"," + "A1maxD05"+"," +  "A2maxD05"+"," + "A3maxD05"+"," + "G1maxD05"+"," + "G2maxD05"+"," + "G3maxD05"+"," + "A1vD05"+","+"A2vD05"+","+"A3vD05"+","+"G1vD05"+","+"G2vD05"+","+"G3vD05"+","+
			   "A1minD1"+"," +"A2minD1"+"," + "A3minD1"+"," + "G1minD1"+"," + "G2minD1"+"," + "G3minD1"+"," + "A1maxD1"+"," +  "A2maxD1"+"," + "A3maxD1"+"," + "G1maxD1"+"," + "G2maxD1"+"," + "G3maxD1"+"," + "A1vD1"+","+"A2vD1"+","+"A3vD1"+","+"G1vD1"+","+"G2vD1"+","+"G3vD1"+","+
			   "A1Avg" + "," + "A2Avg" + "," +"A3Avg" + "," + "G1Avg" + "," + "G2Avg" + "," + "G3Avg" + "," +
			   "A1Med" + "," + "A2Med" + "," +"A3Med" + "," + "G1Med" + "," + "G2Med" + "," + "G3Med" + "," +
			   "A1AvgD05" + "," + "A2AvgD05" + "," +"A3AvgD05" + "," + "G1AvgD05" + "," + "G2AvgD05" + "," + "G3AvgD05" + "," +
			   "A1AvgD1" + "," + "A2AvgD1" + "," +"A3AvgD1" + "," + "G1AvgD1" + "," + "G2AvgD1" + "," + "G3AvgD1" + "," +
			   "A1AvgD3" + "," + "A2AvgD3" + "," +"A3AvgD3" + "," + "G1AvgD3" + "," + "G2AvgD3" + "," + "G3AvgD3" + "," +
			   "A1AvgD5" + "," + "A2AvgD5" + "," +"A3AvgD5" + "," + "G1AvgD5" + "," + "G2AvgD5" + "," + "G3AvgD5" + "," +
			   
			   "A1MedD05" + "," + "A2MedD05" + "," +"A3MedD05" + "," + "G1MedD05" + "," + "G2MedD05" + "," + "G3MedD05" + "," +
			   "A1MedD1" + "," + "A2MedD1" + "," +"A3MedD1" + "," + "G1MedD1" + "," + "G2MedD1" + "," + "G3MedD1" + "," +
			   "A1MedD3" + "," + "A2MedD3" + "," +"A3MedD3" + "," + "G1MedD3" + "," + "G2MedD3" + "," + "G3MedD3" + "," +
			   "A1MedD5" + "," + "A2MedD5" + "," +"A3MedD5" + "," + "G1MedD5" + "," + "G2MedD5" + "," + "G3MedD5" + "," +


				"target"+"\n");
				output.write("continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+"," +"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+"," +"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+"," +"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+"," +"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+
						"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+ "continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+ "continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+ "continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+ "continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+ "continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+ "continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+ "continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+ "continuous"+","+"continuous"+","+"continuous"+","+"continuous"+"," 
						+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+
						"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+
						
						"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+
						"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+
						"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+
						"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+
						
						"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+
						"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+
						"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+
						"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+"continuous"+","+


						 "discrete"+"\n");
				output.write(""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+","+""+"," +""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","
						+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","+""+","
						+""+","+""+","+""+","+""+","+""+","+""+","+
						""+","+""+","+""+","+""+","+""+","+""+","+
						""+","+""+","+""+","+""+","+""+","+""+","+
						""+","+""+","+""+","+""+","+""+","+""+","+
						""+","+""+","+""+","+""+","+""+","+""+","+
						""+","+""+","+""+","+""+","+""+","+""+","+
						
						""+","+""+","+""+","+""+","+""+","+""+","+
						""+","+""+","+""+","+""+","+""+","+""+","+
						""+","+""+","+""+","+""+","+""+","+""+","+
						""+","+""+","+""+","+""+","+""+","+""+","+



						"class"+"\n");
			}
			for(String str: finalResult)
			{
//				if(checkD.containsKey(str)) checkD.put(str,checkD.get(str)+1);
//				else checkD.put(str,1);
				output.write(str + "\n");
			}
//			for(String s: checkD.keySet()){
//				System.out.println(checkD.get(s));
//			}
		}

			oneTen=((oneTen+ 0.05)*100.0)/100.0;

			output.close();


	  }
	}
}