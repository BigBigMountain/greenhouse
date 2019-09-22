package com.lyyh.greenhouse.util.fertilizer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.lyyh.fertilizer.pojo.FertilizerDataCollector;
import com.lyyh.fertilizer.pojo.IrrigationFormula;
import com.lyyh.fertilizer.pojo.IrrigationProgram;
import com.lyyh.fertilizer.pojo.ManualControlPojo;
import com.lyyh.fertilizer.pojo.ProgramControlPojo;
import com.lyyh.fertilizer.pojo.ProgramValveMapping;
import com.lyyh.fertilizer.pojo.PumpState;
import com.lyyh.greenhouse.util.CommonUtils;
import com.lyyh.greenhouse.util.PlcUtils;
import com.lyyh.greenhouse.util.dtu.DtuUtils;
import com.lyyh.greenhouse.util.exception.DisconnectException;

public class FertilizerUtils {
	// 用于接收读取数据
//	public static byte[] read = new byte[1024];

	public static byte[] writeAndRead(OutputStream os, byte[] write, InputStream is,String dtuCode)
			throws IOException {
		if(os == null || is == null){
			throw new IOException("设备未连接!");
		}
		synchronized(os){
			os.write(write);
//		CommonUtils.printByteArray("写数据", write);
			os.flush();
			byte[] read = new byte[1024];
			int length = is.read(read);
			byte[] result = Arrays.copyOf(read, length);
//		CommonUtils.printByteArray("读数据", result);
			if(!(PlcUtils.verifyPlcModbus(write,result) && PlcUtils.verifyData(result))){// TODO 校验数据的crc有问题
				System.out.println("校验错误>>>>>, 第1次重发命令, "+DateFormatUtils.format(new Date(), "yy-MM-dd HH:mm:ss")+" : DTU编号 : " + dtuCode);
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>crc校验错误<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				os.write(write);
				os.flush();
				length = is.read(read);
				result = Arrays.copyOf(read, length);
				if(!(PlcUtils.verifyPlcModbus(write,result) && PlcUtils.verifyData(result))){
					System.out.println("校验错误>>>>>, 第2次重发命令, "+DateFormatUtils.format(new Date(), "yy-MM-dd HH:mm:ss")+" : DTU编号 : " + dtuCode);
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>crc校验错误<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
					os.write(write);
					os.flush();
					length = is.read(read);
					result = Arrays.copyOf(read, length);
					if(!(PlcUtils.verifyPlcModbus(write,result) && PlcUtils.verifyData(result))){
						System.out.println("校验错误>>>>>, 抛出IOException, "+DateFormatUtils.format(new Date(), "yy-MM-dd HH:mm:ss")+" : DTU编号 : " + dtuCode);
						System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>crc校验错误<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
						throw new IOException("通讯异常: PLC返回数据出现错误, DTU编号 : "+dtuCode);
					}
				}
			}
			return result;
		}
	}

	/*
	 * @para firstIndex:从第几个byte开始是需要解析的数据,此byte的index为firstIndex;
	 * 
	 * @para values: 为从plc查询返回的byte数组;
	 */
	private static void parseToFertilizerData(FertilizerDataCollector data, int firstIndex, byte[] values) {
		Field[] fields = FertilizerDataCollector.class.getDeclaredFields();

		for (Field field : fields) {

			Integer v = 0;
			try {
				Field field2 = FertilizerDataSetting.class.getField(field.getName());
				int[] setting = (int[]) field2.get(null);
				for (int i = 0; i < setting[0]; i++) {
					v = (v << 8) | (values[setting[1] - firstIndex + i] & 0xff);
				}
				// System.out.println(field2.getName() + " " + v);

				FertilizerDataCollector.class
						.getMethod("set" + CommonUtils.firstToUpperCase(field.getName()), Integer.class)
						.invoke(data, v);

			} catch (NoSuchFieldException e) {
				// e.printStackTrace();
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * 从对应的dtu读取施肥机的实时数据,用fertilizerDataCollector收集起来
	 */
	public static FertilizerDataCollector readValue(String dtuCode) throws IOException {
		// 获取施肥机的实时数据
		OutputStream os = DtuUtils.getOutputStreamById(dtuCode);
		InputStream is = DtuUtils.getInputStreamById(dtuCode);
		FertilizerDataCollector data = null;
		if (null != os && null != is) {

			byte[] result = writeAndRead(os, FertilizerConstant.readTimeData, is,dtuCode);
			data = new FertilizerDataCollector();
			data.setDtuCode(dtuCode);
			data.setTime(new Date());
			try{
				parseToFertilizerData(data, 400 - 3, result);
			} catch(Exception e){
				System.out.println("解析实时数据错误>>>>>"+DateFormatUtils.format(new Date(), "yy-MM-dd HH:mm:ss")+" : DTU编号 : "+dtuCode);
				e.printStackTrace();
			}
			// }
		}
		return data;
	}

	public static ManualControlPojo readManualControl(String dtuCode) throws IOException{
		// 获取施肥机的线圈数据
		ManualControlPojo controlPojo = null;
		OutputStream os = DtuUtils.getOutputStreamById(dtuCode);
		InputStream is = DtuUtils.getInputStreamById(dtuCode);
		if (null != os && null != is) {
			byte[] result = writeAndRead(os, FertilizerConstant.readManuelControl, is,dtuCode);
//			System.out.println("读手动控制状态的数据:");
//			for (byte b : result) {
//				System.out.print(b + "  ");
//			}
//			System.out.println();
			controlPojo = parseToManualControlPojo(3, result);
			controlPojo.setDtuCode(dtuCode);
			controlPojo.setTime(new Date());
		}
		return controlPojo;
	}

	/*
	 * @para firstIndex:从第几个byte开始是需要解析的数据,此byte的index为firstIndex;
	 * 
	 * @para values: 为从plc查询返回的byte数组;
	 */
	private static ManualControlPojo parseToManualControlPojo(int firstIndex, byte[] values) {
		ManualControlPojo contrlVo = new ManualControlPojo();
		Field[] fields = ManualControlSetting.class.getDeclaredFields();
		for (Field field : fields) {
			Integer v = null;

			try {
				int[] setting = (int[]) field.get(null);
				v = ((values[setting[0] + firstIndex] & 0xff) >> setting[1]) % 2;
				Field declaredField = ManualControlPojo.class.getDeclaredField(field.getName());
				declaredField.setAccessible(true);
				declaredField.set(contrlVo, v);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return contrlVo;
	}

	public static void writeManualControl(ManualControlPojo manualControlPojo) throws IOException {
		// 把施肥机线圈状态对象解析成byte数组,
		byte[] write = parseToManualWriteArray(manualControlPojo);
		CommonUtils.printByteArray("本次发送阀控命令: ", write);
		// 用dtu的输出流写出去;
		OutputStream os = DtuUtils.getOutputStreamById(manualControlPojo.getDtuCode());
		InputStream is = DtuUtils.getInputStreamById(manualControlPojo.getDtuCode());
		if (null != os && null != is) {
			writeAndRead(os, write, is, manualControlPojo.getDtuCode());
		}

	}

	private static byte[] parseToManualWriteArray(ManualControlPojo manualControlPojo) {
		// 获取控制设置解析类的class对象
		Field[] fields = ManualControlSetting.class.getFields();
		byte[] head = FertilizerConstant.writeManuelControlHead;
		byte[] temp = Arrays.copyOf(head, head.length + head[head.length - 1]);
		// 遍历字段的定义解析
		for (Field field : fields) {
			// 找到对应的pojo的属性,获得属性值
			try {
				int[] setting = (int[]) field.get(null);
				Class<ManualControlPojo> clazz = ManualControlPojo.class;
				Method method = clazz.getMethod("get" + CommonUtils.firstToUpperCase(field.getName()));
				Object vv = method.invoke(manualControlPojo);
				int v = (int) vv;
				temp[setting[0] + 7] = (byte) ((v << setting[1]) | temp[setting[0] + 7]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return PlcUtils.addCrc(temp);
	}

	public static ProgramControlPojo readProgramControl(String dtuCode) throws IOException{
		
		// 获取施肥机的线圈数据
		ProgramControlPojo programControl = null;
		OutputStream os = DtuUtils.getOutputStreamById(dtuCode);
		InputStream is = DtuUtils.getInputStreamById(dtuCode);
		if (null != os && null != is) {
			byte[] result = writeAndRead(os, FertilizerConstant.readProgramControl, is, dtuCode);
			// if(PlcUtils.verifyData(result)){
			programControl = parseToProgramControlPojo(3, result);
			programControl.setDtuCode(dtuCode);
			programControl.setTime(new Date());
			// }
		}
		return programControl;
	}

	/*
	 * 
	 * 把byte数组 ---> 程序控制pojo
	 */
	private static ProgramControlPojo parseToProgramControlPojo(int firstIndex, byte[] values) {
//		System.out.println("查询到的program-values");
//		for (byte b : values) {
//			System.out.print(b + " ");
//		}
//		System.out.println();

		ProgramControlPojo programControl = new ProgramControlPojo();

		Field[] fields = ProgramControlSetting.class.getFields();
		for (Field field : fields) {
			try {
				int v = 0;
				int setting = (int) field.get(null);
				v = (values[firstIndex + setting]);
				Field field2 = ProgramControlPojo.class.getDeclaredField(field.getName());
				field2.setAccessible(true);
				field2.set(programControl, v);
				// FertilizerProgramControlPojo.class.getMethod("set"+CommonUtils.firstToUpperCase(field.getName()),Integer.class).invoke(programControl,
				// v);

			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return programControl;

	}

	public static void writeProgramControl(String dtuCode,int programNum,int value,int programOrManual) throws IOException {
		// 组织程序控制的命令
		byte programs[] = new byte[16];
		programs[programNum-1] = (byte)value;
		
		byte[] head = FertilizerConstant.writeProgramControlHead;
		byte[] temp = Arrays.copyOf(head, head.length + head[head.length - 1]);
		temp[head.length] = (byte)programOrManual;
		System.arraycopy(programs, 0, temp, head.length+1, programs.length);
		byte[] write = PlcUtils.addCrc(temp);
		CommonUtils.printByteArray("本次发送的程序控制命令: ", write);
		// 用dtu的输出流写出去;
		OutputStream os = DtuUtils.getOutputStreamById(dtuCode);
		InputStream is = DtuUtils.getInputStreamById(dtuCode);
		if (null != os && null != is) {
			writeAndRead(os, write, is, dtuCode);
		}
	}
	public static void writeProgramControl(ProgramControlPojo programPojo) throws IOException {
		// 把施肥机程序控制对象解析成byte数组,
		byte[] write = parseToProgramWriteArray(programPojo);
//		System.out.println("写程序控制的数据为:");
//		for (byte b : write) {
//			System.out.print(b + " ");
//		}
//		System.out.println();
		// 用dtu的输出流写出去;
		OutputStream os = DtuUtils.getOutputStreamById(programPojo.getDtuCode());
		InputStream is = DtuUtils.getInputStreamById(programPojo.getDtuCode());
		if (null != os && null != is) {
			writeAndRead(os, write, is, programPojo.getDtuCode());
		}
	}

	private static byte[] parseToProgramWriteArray(ProgramControlPojo programPojo) {
		Field[] fields = ProgramControlSetting.class.getFields();
		byte[] head = FertilizerConstant.writeProgramControlHead;
		byte[] temp = Arrays.copyOf(head, head.length + head[head.length - 1]);
		// 遍历字段的定义解析
		for (Field field : fields) {
			// 找到对应的pojo的属性,获得属性值
			try {
				int setting = (int) field.get(null);
				int v = (int) ProgramControlPojo.class.getMethod("get" + CommonUtils.firstToUpperCase(field.getName()))
						.invoke(programPojo);
				temp[setting + 7] = (byte) v;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return PlcUtils.addCrc(temp);
	}

	// 获取所有程序的电磁阀设置内容
	public static ProgramValveMapping getProgramValveMapping(String dtuCode) {
		OutputStream os = DtuUtils.getOutputStreamById(dtuCode);
		InputStream is = DtuUtils.getInputStreamById(dtuCode);
		ProgramValveMapping pvm = null;
		if (null != os && null != is) {
			try {
				byte[] result = writeAndRead(os, FertilizerConstant.readPVM, is, dtuCode);
//				System.out.println("读取pvm映射的值为:");
//				for (byte b : result) {
//					System.out.print(b + " ");
//				}
//				System.out.println();
				// 把获取到的字节解析成 程序&阀的映射关系对象
				pvm = parseToPVM(3, result);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pvm;

	}

	private static ProgramValveMapping parseToPVM(int firstIndex, byte[] values) {

		ProgramValveMapping pvm = new ProgramValveMapping();
		Field[] fields = ProgramValveMappingSetting.class.getFields();
		for (Field field : fields) {
			// 获取相应的mvp的字段
			try {
				Field field2 = ProgramValveMapping.class.getDeclaredField(field.getName());
				field2.setAccessible(true);
				byte[] program$ = (byte[]) field2.get(pvm);
				// 遍历数据封装到mvp
				int[] setting = (int[]) field.get(null);
				for (int i = 0; i < setting[1]; i++) {
					program$[i] = values[firstIndex + setting[0] + i];
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pvm;
	}

	public static List<Integer> readValves(String dtuCode) throws IOException, DisconnectException {
		List<Integer> valves = null;
		try{
		// 获取施肥机的线圈阀的状态数据
		OutputStream os = DtuUtils.getOutputStreamById(dtuCode);
		InputStream is = DtuUtils.getInputStreamById(dtuCode);
		valves = new ArrayList<Integer>();
		if (null != os && null != is) {
			byte[] result = writeAndRead(os, FertilizerConstant.read64ValveState, is, dtuCode);
//			System.out.println("读64个线圈的状态数据:");
//			for (byte b : result) {
//				System.out.print(b + "  ");
//			}
//			System.out.println();

			// 把读到的数据转成64个字节集合
			for (int i = 3; i < result.length - 2; i++) {
				for (int j = 0; j < 8; j++) {
					valves.add(((result[i]&0xff) >>> j) % 2);
				}
			}
		}
		} catch(SocketException e){
			if (e.getMessage().contains("socket write error")){
				throw new DisconnectException("dtu断开连接");
			}
		}
		if(valves.size() < 1){
			for(int i = 0; i<64;i++ )
				valves.add(0);
		}
		return valves;
	}

	public static List<Integer> readPrograms(String dtuCode) throws IOException {
		// 获取施肥机的程序的状态数据
		OutputStream os = DtuUtils.getOutputStreamById(dtuCode);
		InputStream is = DtuUtils.getInputStreamById(dtuCode);
		List<Integer> programs = new ArrayList<Integer>();

		if (null != os && null != is) {
			byte[] result = writeAndRead(os, FertilizerConstant.readProgramControl, is, dtuCode);
//			System.out.println("读17个程序状态数据:");
//			for (byte b : result) {
//				System.out.print(b + "  ");
//			}
//			System.out.println();

			// 把读到的数据转成集合
			for (int i = 4; i < result.length - 2; i++) {
				programs.add(result[i] & 0xff);
			}
		}
		return programs;
	}
	public static int readCurrentProgram(String dtuCode) throws IOException {
		// 获取施肥机的程序的状态数据
		int programNum = 0;
		OutputStream os = DtuUtils.getOutputStreamById(dtuCode);
		InputStream is = DtuUtils.getInputStreamById(dtuCode);
		
		if (null != os && null != is) {
			byte[] result = writeAndRead(os, FertilizerConstant.readProgramControl2, is, dtuCode);
//			for (byte b : result) {
//				System.out.println(b);
//			}
			programNum = result[4];
		}
		return programNum;
	}

	
	public static IrrigationFormula getFormulaInfo(String dtuCode, Integer formulaNum) throws IOException {
		
		byte[] programAndFormulaInfoByteArray = getProgramAndFormulaInfoByteArray(dtuCode);
		
		return parseToFormulaPojo(programAndFormulaInfoByteArray,formulaNum);
	}
	
	

	public static IrrigationProgram getProgramInfo(String dtuCode, Integer programNum) throws IOException {
		
		byte[] programAndFormulaInfoByteArray = getProgramAndFormulaInfoByteArray(dtuCode);
	
		return parseToProgramControlPojo(programAndFormulaInfoByteArray,programNum);
	}
	
	private static IrrigationFormula parseToFormulaPojo(byte[] programAndFormulaInfoByteArray, Integer formulaNum) {
		// TODO Auto-generated method stub
		int firstIndex = 500;// 减去500,
		IrrigationFormula formula = new IrrigationFormula();
		formula.setFormulaNum(formulaNum);
		Field[] fields = IrrigationFormulaSetting.class.getFields();
		for (Field field : fields) {
			try {
				int[] setting = (int[])field.get(null);
				int length = setting[0];//字节个数
				int value = 0;
				int bytePosition;
				if(length == 0){
					// length = 0 时,表示该值占一个字节中的一位,
					bytePosition = setting[formulaNum*2-1]-firstIndex;
					byte valueByte = programAndFormulaInfoByteArray[bytePosition];
					int bitPosition = setting[formulaNum*2];
					value = ((valueByte&255) & (1 << (bitPosition-1))) >>> (bitPosition-1);
				}else{
					// TODO 字节运算
					bytePosition = setting[formulaNum]-firstIndex;
					byte[] valueByte = new byte[length];
					System.arraycopy(programAndFormulaInfoByteArray, bytePosition, valueByte, 0, length);
					for(int i =0;i<length;i++){
						value += (valueByte[i]&255) << (length-i-1)*8;
					}
				}
				
				Field declaredField = IrrigationFormula.class.getDeclaredField(field.getName());
				declaredField.setAccessible(true);
				String type = declaredField.getType().getName();
				
				switch (type){
				case "float":
				case "Float":
					declaredField.setFloat(formula, Float.intBitsToFloat(value));
					break;
				case "int":
				case "Integer":
					declaredField.setInt(formula, value);
					break;
				case "double":
				case "Double":
					declaredField.setDouble(formula, Double.longBitsToDouble(value&((1L << 32)-1)));
					break;
				case "String":
					declaredField.set(formula, String.valueOf(value));
					break;
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		return formula;
	}
	private static IrrigationProgram parseToProgramControlPojo(byte[] programAndFormulaInfoByteArray,
			Integer programNum) {
		// TODO Auto-generated method stub
		int firstIndex = 500;// 减去500,
		IrrigationProgram program = new IrrigationProgram();
		program.setProgramNum(programNum);
		Field[] fields = IrrigationProgramSetting.class.getFields();
		for (Field field : fields) {
			try {
				int[] setting = (int[])field.get(null);
				int length = setting[0];//字节个数
				int value = 0;
				int bytePosition;
				if(length == 0){
					// length = 0 时,表示该值占一个字节中的一位,
					bytePosition = setting[programNum*2-1]-firstIndex;
					byte valueByte = programAndFormulaInfoByteArray[bytePosition];
					int bitPosition = setting[programNum*2];
					value = ((valueByte&255) & (1 << (bitPosition-1))) >>> (bitPosition-1);
				}else{
					// TODO 字节运算
					bytePosition = setting[programNum]-firstIndex;
					byte[] valueByte = new byte[length];
					System.arraycopy(programAndFormulaInfoByteArray, bytePosition, valueByte, 0, length);
					for(int i =0;i<length;i++){
						value += (valueByte[i]&255) << (length-i-1)*8;
					}
				}
				
				Field declaredField = IrrigationProgram.class.getDeclaredField(field.getName());
				declaredField.setAccessible(true);
				String type = declaredField.getType().getName();
				
				switch (type){
				case "float":
				case "Float":
					declaredField.setFloat(program, Float.intBitsToFloat(value));
					break;
				case "int":
				case "Integer":
					declaredField.setInt(program, value);
					break;
				case "double":
				case "Double":
					declaredField.setDouble(program, Double.longBitsToDouble(value&((1L << 32)-1)));
					break;
				case "String":
					declaredField.set(program, String.valueOf(value));
					break;
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		return program;
	}
	

	public static byte[] getProgramAndFormulaInfoByteArray(String dtuCode) throws IOException {
		byte[] programAndFormulaInfoByte = new byte[1024];
		OutputStream os = DtuUtils.getOutputStreamById(dtuCode);
		InputStream is = DtuUtils.getInputStreamById(dtuCode);
		if (null != os && null != is) {
			byte[] result1 = writeAndRead(os, FertilizerConstant.readProgramAndFormula1, is, dtuCode);
			byte[] result2 = writeAndRead(os, FertilizerConstant.readProgramAndFormula2, is, dtuCode);
			byte[] result3 = writeAndRead(os, FertilizerConstant.readProgramAndFormula3, is, dtuCode);
			byte[] result4 = writeAndRead(os, FertilizerConstant.readProgramAndFormula4, is, dtuCode);
			
			System.arraycopy(result1, 3, programAndFormulaInfoByte, 0, result1[2]&255);
			System.arraycopy(result2, 3, programAndFormulaInfoByte, result1[2]&255, result2[2]&255);
			System.arraycopy(result3, 3, programAndFormulaInfoByte, (result1[2]&255) + (result2[2]&255), result3[2]&255);
			System.arraycopy(result4, 3, programAndFormulaInfoByte, (result1[2]&255) + (result2[2]&255) + (result3[2]&255), result4[2]&255);
			
			//因为硬件接口设计的原因,把原先VB867的字节数据设置到VB470了,所以下面的result5是单独查询VB470数据,并再替换VB867(programAndFormulaInfoByte[367])
			byte[] result5 = writeAndRead(os, FertilizerConstant.readProgramAndFormula5, is, dtuCode);
			programAndFormulaInfoByte[367] = result5[3];
//			for (byte b : programAndFormulaInfoByte) {
//				System.out.println(b);
//			}
			return programAndFormulaInfoByte;
		}else{
			throw new IOException("设备未连接!");
		}
	}
	
	
	
	public static void setProgramInfo(String dtuCode, IrrigationProgram irrigationProgram) throws IOException {
		//先获取plc的数据
		byte[] programAndFormulaInfoByteArray = getProgramAndFormulaInfoByteArray(dtuCode);
		//解析数据,并合并到原数据中
		programAndFormulaInfoByteArray = parseProgramToByte(irrigationProgram,programAndFormulaInfoByteArray);
		//发送数据
		writeProgramAndFormulaByteArray(dtuCode,programAndFormulaInfoByteArray);
	}

	private static byte[] parseProgramToByte(IrrigationProgram program,
			byte[] programAndFormulaInfoByteArray) {
		//把灌溉对象的每个字段翻译为byte数组,然后与原数据结合
		int programNum = program.getProgramNum();
		int firstIndex = 500;// 减去500,
		Field[] fields = IrrigationProgramSetting.class.getFields();
		for (Field field : fields) {
			try {
				int[] setting = (int[])field.get(null);
				int length = setting[0];//字节个数
				int value = 0;
				int bytePosition;
				
				Field declaredField = IrrigationProgram.class.getDeclaredField(field.getName());
				declaredField.setAccessible(true);
				String type = declaredField.getType().getName();
				
				switch (type){
				case "float":
				case "Float":
					value = Float.floatToIntBits(declaredField.getFloat(program));
					break;
				case "int":
				case "Integer":
					value = declaredField.getInt(program);
					break;
				case "double":
				case "Double":
					value = (int)Double.doubleToLongBits(declaredField.getDouble(program));
					break;
				case "String":
					value = Integer.valueOf((String)declaredField.get(program));
					break;
				}
				
				if(length == 0){
					// length = 0 时,表示该值占一个字节中的一位,
					bytePosition = setting[programNum*2-1]-firstIndex;
					int bitPosition = setting[programNum*2];
					byte valueByte = programAndFormulaInfoByteArray[bytePosition];
					valueByte = (byte)((valueByte&255) - ((valueByte&255)&(1 << (bitPosition - 1))) + (value << (bitPosition - 1)));
					programAndFormulaInfoByteArray[bytePosition] = valueByte;
				}else{
					// 字节运算
					bytePosition = setting[programNum]-firstIndex;
					byte[] valueByte = new byte[length];
					for (int i = 0 ; i < length ; i++) {
						valueByte[length-i-1] = (byte)(value >>> (i*8));
					}
					System.arraycopy(valueByte, 0, programAndFormulaInfoByteArray, bytePosition, length);
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return programAndFormulaInfoByteArray;
	}
	
	private static void writeProgramAndFormulaByteArray(String dtuCode, byte[] programAndFormulaInfoByteArray) throws IOException {
		// 用dtu的输出流写出去;
		OutputStream os = DtuUtils.getOutputStreamById(dtuCode);
		InputStream is = DtuUtils.getInputStreamById(dtuCode);
		
		byte[] head1 = FertilizerConstant.writeProgramAndFormula1Head;
		int dataLength1 = head1[head1.length - 1]&255;
		byte[] temp1 = Arrays.copyOf(head1, head1.length + dataLength1);
		System.arraycopy(programAndFormulaInfoByteArray, 0, temp1, head1.length, dataLength1);
		byte[] write1 = PlcUtils.addCrc(temp1);
		writeAndRead(os, write1, is, dtuCode);
		
		byte[] head2 = FertilizerConstant.writeProgramAndFormula2Head;
		int dataLength2 = head2[head2.length - 1]&255;
		byte[] temp2 = Arrays.copyOf(head2, head2.length + dataLength2);
		System.arraycopy(programAndFormulaInfoByteArray, dataLength1, temp2, head2.length, dataLength2);
		byte[] write2 = PlcUtils.addCrc(temp2);
		writeAndRead(os, write2, is, dtuCode);
		
		byte[] head3 = FertilizerConstant.writeProgramAndFormula3Head;
		int dataLength3 = head3[head3.length - 1]&255;
		byte[] temp3 = Arrays.copyOf(head3, head3.length + dataLength3);
		System.arraycopy(programAndFormulaInfoByteArray, dataLength1 + dataLength2, temp3, head3.length, dataLength3);
		byte[] write3 = PlcUtils.addCrc(temp3);
		writeAndRead(os, write3, is, dtuCode);

		byte[] head4 = FertilizerConstant.writeProgramAndFormula4Head;
		int dataLength4 = head4[head4.length - 1]&255;
		byte[] temp4 = Arrays.copyOf(head4, head4.length + dataLength4);
		System.arraycopy(programAndFormulaInfoByteArray, dataLength1 + dataLength2 + dataLength3, temp4, head4.length, dataLength4);
		byte[] write4 = PlcUtils.addCrc(temp4);
		writeAndRead(os, write4, is, dtuCode);
		
		//因为硬件接口设计的原因,把原先VB867的字节数据设置到VB470了,所以下面的result5是单独查询VB470数据,并再替换VB867(programAndFormulaInfoByte[367])
		byte[] result5 = writeAndRead(os, FertilizerConstant.readProgramAndFormula5, is, dtuCode);
		byte[] head5 = FertilizerConstant.writeProgramAndFormula5Head;
		int dataLength5 = head5[head5.length - 1]&255;
		byte[] temp5 = Arrays.copyOf(head5, head5.length + dataLength5);
		temp5[head5.length] = programAndFormulaInfoByteArray[367];//result5[3]对应的位置
		temp5[head5.length+1] = result5[4];
		byte[] write5 = PlcUtils.addCrc(temp5);
		writeAndRead(os, write5, is, dtuCode);
	}

	public static void setFormulaInfo(String dtuCode, IrrigationFormula irrigationFormula) throws IOException {
		//先获取plc的数据
		byte[] programAndFormulaInfoByteArray = getProgramAndFormulaInfoByteArray(dtuCode);
		//解析数据,并合并到原数据中
		programAndFormulaInfoByteArray = parseFormulaToByte(irrigationFormula,programAndFormulaInfoByteArray);
		//发送数据
		writeProgramAndFormulaByteArray(dtuCode,programAndFormulaInfoByteArray);
		
	}

	private static byte[] parseFormulaToByte(IrrigationFormula formula,
			byte[] programAndFormulaInfoByteArray) {
		//把灌溉对象的每个字段翻译为byte数组,然后与原数据结合
		int formulaNum = formula.getFormulaNum();
		int firstIndex = 500;// 减去500,
		Field[] fields = IrrigationFormulaSetting.class.getFields();
		for (Field field : fields) {
			try {
				int[] setting = (int[])field.get(null);
				int length = setting[0];//字节个数
				int value = 0;
				int bytePosition;
				
				Field declaredField = IrrigationFormula.class.getDeclaredField(field.getName());
				declaredField.setAccessible(true);
				String type = declaredField.getType().getName();
				
				switch (type){
				case "float":
				case "Float":
					value = Float.floatToIntBits(declaredField.getFloat(formula));
					break;
				case "int":
				case "Integer":
					value = declaredField.getInt(formula);
					break;
				case "double":
				case "Double":
					value = (int)Double.doubleToLongBits(declaredField.getDouble(formula));
					break;
				case "String":
					value = Integer.valueOf((String)declaredField.get(formula));
					break;
				}
				
				if(length == 0){
					// length = 0 时,表示该值占一个字节中的一位,
					bytePosition = setting[formulaNum*2-1]-firstIndex;
					int bitPosition = setting[formulaNum*2];
					byte valueByte = programAndFormulaInfoByteArray[bytePosition];
					valueByte = (byte)((valueByte&255) - ((valueByte&255)&(1 << (bitPosition - 1))) + (value << (bitPosition - 1)));
					programAndFormulaInfoByteArray[bytePosition] = valueByte;
				}else{
					// 字节运算
					bytePosition = setting[formulaNum]-firstIndex;
					byte[] valueByte = new byte[length];
					for (int i = 0 ; i < length ; i++) {
						valueByte[length-i-1] = (byte)(value >>> (i*8));
					}
					System.arraycopy(valueByte, 0, programAndFormulaInfoByteArray, bytePosition, length);
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return programAndFormulaInfoByteArray;
	}

	public static PumpState getPumpStatus(String dtuCode) throws IOException {
		// TODO Auto-generated method stub
		OutputStream os = DtuUtils.getOutputStreamById(dtuCode);
		InputStream is = DtuUtils.getInputStreamById(dtuCode);
		PumpState pump = null;
		byte[] result = writeAndRead(os, FertilizerConstant.readPump, is, dtuCode);
		pump = parseToPump(3, result);
		
		return pump;
	}

	private static PumpState parseToPump(int start, byte[] result) {
		PumpState pump = new PumpState();
				
		int value = result[start] & 0xff;
		pump.setIrrigWaterPump(value%2);
		pump.setInWaterPump((value>>>1)%2);
		pump.setInWaterValve1((value>>>2)%2);
		pump.setInWaterValve2((value>>>3)%2);
		pump.setInFertValve1((value>>>4)%2);
		pump.setInFertValve2((value>>>5)%2);
		pump.setInFertValve3((value>>>6)%2);
		pump.setInFertValve4((value>>>7)%2);
		return pump;
	}

	

	
	
}
