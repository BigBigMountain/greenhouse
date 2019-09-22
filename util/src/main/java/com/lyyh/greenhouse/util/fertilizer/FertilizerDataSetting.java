package com.lyyh.greenhouse.util.fertilizer;

public class FertilizerDataSetting {
	
	public static final int[] programNo 		= {1,495};  //1个字节,第495为字节
	public static final int[] valve1 			= {1,494};
	public static final int[] valve2 			= {1,462};
	public static final int[] valve3 			= {1,463};
	public static final int[] totalIrrigation	= {4,490};	//4个字节,第490~493(共4个字节)
	
	public static final int[] soilHumidity1 	= {1,497};
	public static final int[] soilHumidity2 	= {1,496};
	public static final int[] soilHumidity3 	= {1,478};
	public static final int[] soilHumidity4 	= {1,470};
	public static final int[] mainFlow 			= {4,430};
	public static final int[] liquidLevel 		= {4,464};
	public static final int[] ph 				= {4,480};	//4个字节,第480~483(共4个字节)
	public static final int[] ec 				= {2,484};	//2个字节,第484~485(共2个字节)
	public static final int[] rateFlow 			= {4,486};
	
}
