package com.lyyh.greenhouse.util.fertilizer;
/**
 * 
 * @author lt
 *	每个数组的第一位表示该值占几个字节 0-1位, 1-1个字节,2-两个字节,3-三个字节,4-四个字节
 *			如果第一位是0,则后面的字节位置需要两个数表示,第一个数表示字节,第二个数表示第几位,如{0,509,1,509,2 ... }   表示509字节的第一位,509字节的第二位 ...
 *			
 *	第2位~第9(或17)位分别表示1~8程序灌溉对应的字节位置
 */
public class IrrigationProgramSetting {

	public static final int effective[] = {0,867,1,867,2,867,3,867,4,867,5,867,6,867,7,867,8};
	//9个阀
	public static final int[] valve1 = {1,501,526,551,576,601,626,651,676};//8个程序对应的位置
	public static final int[] valve2 = {1,502,527,552,577,602,627,652,677};
	public static final int[] valve3 = {1,503,528,553,578,603,628,653,678};
	public static final int[] valve4 = {1,504,529,554,579,604,629,654,679};
	public static final int[] valve5 = {1,505,530,555,580,605,630,655,680};
	public static final int[] valve6 = {1,506,531,556,581,606,631,656,681};
	public static final int[] valve7 = {1,507,532,557,582,607,632,657,682};
	public static final int[] valve8 = {1,508,533,558,583,608,633,658,683};
	public static final int[] valve9 = {1,1022,1023,1024,1025,1026,1027,1028,1029};

	public static final int[] mon 	= {0,509,1,534,1,559,1,584,1,609,1,634,1,659,1,684,1}; 	//星期一
	public static final int[] tues	= {0,509,2,534,2,559,2,584,2,609,2,634,2,659,2,684,2}; 	//星期二
	public static final int[] wed	= {0,509,3,534,3,559,3,584,3,609,3,634,3,659,3,684,3}; 	//星期三
	public static final int[] thur	= {0,509,4,534,4,559,4,584,4,609,4,634,4,659,4,684,4}; 	//星期四
	public static final int[] fri	= {0,509,5,534,5,559,5,584,5,609,5,634,5,659,5,684,5}; 	//星期五
	public static final int[] sat	= {0,509,6,534,6,559,6,584,6,609,6,634,6,659,6,684,6}; 	//星期六
	public static final int[] sun	= {0,509,7,534,7,559,7,584,7,609,7,634,7,659,7,684,7}; 	//星期七

	public static final int[] h1 = {1,510,535,560,585,610,635,660,685};				//启动时间小时1
	public static final int[] m1 = {1,511,536,561,586,611,636,661,686};				//启动时间分钟1
	
	public static final int[] h2 = {1,512,537,562,587,612,637,662,687};				
	public static final int[] m2 = {1,513,538,563,588,613,638,663,688};	
	
	public static final int[] h3 = {1,514,539,564,589,614,639,664,689};				
	public static final int[] m3 = {1,515,540,565,590,615,640,665,690};
	
	public static final int[] h4 = {1,516,541,566,591,616,641,666,691};				
	public static final int[] m4 = {1,517,542,567,592,617,642,667,692};	
	
	
	
	public static final int[] prescription1 = {1,518,543,568,593,618,643,668,693}; 	//配方1
	public static final int[] prescription2 = {1,519,544,569,594,619,644,669,694};
	public static final int[] prescription3 = {1,520,545,570,595,620,645,670,695}; 
	public static final int[] prescription4 = {1,521,546,571,596,621,646,671,696};
	
	
	
	public static final int[] irrigation1 = {4,522,547,572,597,622,647,672,697};	//灌溉量1
	public static final int[] irrigation2 = {4,834,858,874,886,898,910,922,934};	
	public static final int[] irrigation3 = {4,838,862,878,890,902,914,926,938};	
	public static final int[] irrigation4 = {4,842,870,882,894,906,918,930,942};	

	

	public static final int[] isWhell	= {0,868,1,868,3,868,5,868,7,869,1,869,3,869,5,869,7}; 	//是否轮灌
	public static final int[] isByGroup	= {0,868,2,868,4,868,6,868,8,869,2,869,4,869,6,869,8};	//按组灌溉

	public static final int[] preWater	= {4,958,966,974,982,990,998,1006,1014};	//肥前水
	public static final int[] sufWater	= {4,962,970,978,986,994,1002,1010,1018};	//肥后水
}
