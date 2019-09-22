package com.lyyh.greenhouse.util.fertilizer;

public class ManualControlSetting {
	
	/*输出线圈Q0的含义定义
	 * {0,1}表示脚标为0的Q组(输出线圈组)中脚标为1的输出线圈
	 */
//	public static final int[] irrigWaterPump = {0,0};//灌溉水泵;		0.0
//	public static final int[] inWaterPump   = {0,1};//进水水泵;		0.1
//	public static final int[] inWaterValve1 = {0,2};//进水电磁阀1;	0.2
//	public static final int[] inWaterValve2 = {0,3};//进水电磁阀2;	0.3
//	public static final int[] inFertValve1 = {0,4};//吸肥电磁阀;		0.4
//	public static final int[] inFertValve2 = {0,5};//吸肥电磁阀2；	0.5
//	public static final int[] inFertValve3 = {0,6};//吸肥电磁阀3；	0.6
//	public static final int[] inFertValve4 = {0,7};//吸肥电磁阀4；	0.7
	
	/*
	 * 输出线圈Q1的含义定义
	 */
	public static final int[] irrigValve1 = {0,0};//灌溉阀1；	1.0
	public static final int[] irrigValve2 = {0,1};//灌溉阀2；	1.1
	public static final int[] irrigValve3 = {0,2};//灌溉阀3；	1.2
	public static final int[] irrigValve4 = {0,3};//灌溉阀4；	1.3
	public static final int[] irrigValve5 = {0,4};//灌溉阀5；	1.4
	public static final int[] irrigValve6 = {0,5};//灌溉阀6；	1.5
	public static final int[] irrigValve7 = {0,6};//灌溉阀7；	1.6
	public static final int[] irrigValve8 = {0,7};//灌溉阀8；	1.7
	/*
	 * 输出线圈Q2的含义定义
	 */
	public static final int[] irrigValve9  = {1,0};//灌溉阀9；		2.0
	public static final int[] irrigValve10 = {1,1};//灌溉阀10；	2.1
	public static final int[] irrigValve11 = {1,2};//灌溉阀11；	2.2
	public static final int[] irrigValve12 = {1,3};//灌溉阀12；	2.3
	public static final int[] irrigValve13 = {1,4};//灌溉阀13；	2.4
	public static final int[] irrigValve14 = {1,5};//灌溉阀14；	2.5
	public static final int[] irrigValve15 = {1,6};//灌溉阀15；	2.6
	public static final int[] irrigValve16 = {1,7};//灌溉阀16；	2.7
	/*
	 * 输出线圈Q3的状态
	 */
	public static final int[] irrigValve17 = {2,0}; //灌溉阀17； 3.0
	public static final int[] irrigValve18 = {2,1}; //灌溉阀18； 3.1
	public static final int[] irrigValve19 = {2,2}; //灌溉阀19； 3.2
	public static final int[] irrigValve20 = {2,3}; //灌溉阀20； 3.3
	public static final int[] irrigValve21 = {2,4}; //灌溉阀21； 3.4
	public static final int[] irrigValve22 = {2,5}; //灌溉阀22； 3.5
	public static final int[] irrigValve23 = {2,6}; //灌溉阀23； 3.6
	public static final int[] irrigValve24 = {2,7}; //灌溉阀24； 3.7
	
	//输出线圈Q4的状态
	public static final int[] irrigValve25 = {3,0}; //灌溉阀25； 4.0
	public static final int[] irrigValve26 = {3,1}; //灌溉阀26； 4.1
	public static final int[] irrigValve27 = {3,2}; //灌溉阀27； 4.2
	public static final int[] irrigValve28 = {3,3}; //灌溉阀28； 4.3
	public static final int[] irrigValve29 = {3,4}; //灌溉阀29； 4.4
	public static final int[] irrigValve30 = {3,5}; //灌溉阀30； 4.5
	public static final int[] irrigValve31 = {3,6}; //灌溉阀31； 4.6
	public static final int[] irrigValve32 = {3,7}; //灌溉阀32； 4.7

	//输出线圈Q5的状态
	public static final int[] irrigValve33 = {4,0}; //灌溉阀33； 5.0
	public static final int[] irrigValve34 = {4,1}; //灌溉阀34； 5.1
	public static final int[] irrigValve35 = {4,2}; //灌溉阀35； 5.2
	public static final int[] irrigValve36 = {4,3}; //灌溉阀36； 5.3
	public static final int[] irrigValve37 = {4,4}; //灌溉阀37； 5.4
	public static final int[] irrigValve38 = {4,5}; //灌溉阀38； 5.5
	public static final int[] irrigValve39 = {4,6}; //灌溉阀39； 5.6
	public static final int[] irrigValve40 = {4,7}; //灌溉阀40； 5.7
	                                             
	//输出线圈Q6的状态
	public static final int[] irrigValve41 = {5,0}; //灌溉阀41； 6.0
	public static final int[] irrigValve42 = {5,1}; //灌溉阀42； 6.1
	public static final int[] irrigValve43 = {5,2}; //灌溉阀43； 6.2
	public static final int[] irrigValve44 = {5,3}; //灌溉阀44； 6.3
	public static final int[] irrigValve45 = {5,4}; //灌溉阀45； 6.4
	public static final int[] irrigValve46 = {5,5}; //灌溉阀46； 6.5
	public static final int[] irrigValve47 = {5,6}; //灌溉阀47； 6.6
	public static final int[] irrigValve48 = {5,7}; //灌溉阀48； 6.7
	
	//输出线圈Q7的状态
	public static final int[] irrigValve49 = {6,0}; //灌溉阀49； 7.0
	public static final int[] irrigValve50 = {6,1}; //灌溉阀50； 7.1
	public static final int[] irrigValve51 = {6,2}; //灌溉阀51； 7.2
	public static final int[] irrigValve52 = {6,3}; //灌溉阀52； 7.3
	public static final int[] irrigValve53 = {6,4}; //灌溉阀53； 7.4
	public static final int[] irrigValve54 = {6,5}; //灌溉阀54； 7.5
	public static final int[] irrigValve55 = {6,6}; //灌溉阀55； 7.6
	public static final int[] irrigValve56 = {6,7}; //灌溉阀56； 7.7
	
	//输出线圈Q8的状态
	public static final int[] irrigValve57 = {7,0}; //灌溉阀57； 8.0
	public static final int[] irrigValve58 = {7,1}; //灌溉阀58； 8.1
	public static final int[] irrigValve59 = {7,2}; //灌溉阀59； 8.2
	public static final int[] irrigValve60 = {7,3}; //灌溉阀60； 8.3
	public static final int[] irrigValve61 = {7,4}; //灌溉阀61； 8.4
	public static final int[] irrigValve62 = {7,5}; //灌溉阀62； 8.5
	public static final int[] irrigValve63 = {7,6}; //灌溉阀63； 8.6
	public static final int[] irrigValve64 = {7,7}; //灌溉阀64； 8.7
	
	
	
	
	
}
