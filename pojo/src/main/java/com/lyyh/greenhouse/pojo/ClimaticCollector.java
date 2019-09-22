package com.lyyh.greenhouse.pojo;

import java.util.Date;

public class ClimaticCollector {

	public static final int IO_L=4;			//输出电流下限
	public static final int IO_U=20;			//输出电流上限
//	private static final int VO_L=1;			//输出电压下限
//	private static final int VO_U=5;			//输出电压上限
	//温度	°C
	public static final int T_LRV = -40;	//LRV:Lower Range Value 量程下限
	public static final int T_URV = 60;	//URV:Upper Range Value 量程上限
	//湿度	%
	public static final int H_LRV = 0;	
	public static final int H_URV = 100;	
	//大气压	KPa
	public static final int P_LRV = 0;	
	public static final int P_URV = 110;	
	//风速	m/s
	public static final int WS_LRV = 0;	
	public static final int WS_URV = 30;	
//	//风向	
//	private static final int WD_LRV = -40;	
//	private static final int WD_URV = 60;	
	//光照	Lux
	public static final int L_LRV = 0;	
	public static final int L_URV = 200000;	
	//PM2.5	ug/m3
	public static final int PM_LRV = 0;	
	public static final int PM_URV = 1000;	
	//雨水PH	
	public static final int PH_LRV = 0;	
	public static final int PH_URV = 14;	
	//雨量
	public static final int RF_LRV = 0;	
	public static final int RF_URV = 100;	
//	//雨雪
//	private static final int RS_LRV = ;	
//	private static final int RS_URV = 60;	
	/*
	 * 公式
	 * v=(IO-IO_L)*(URV-LRV)/(IO_U-IO_L)+LRV
	 */
	
	// 主键
	private Integer id;
	// 风速
	private Double windSpeed;
	// 风向
	private String windDirection;
	// 雨雪
	private String rainAndSnow;
	// 降水量
	private Double rainFall;
	// 气压
	private Double pressure;
	// PM2.5
	private Double pm25;
	// PH
	private Double ph;
	// 温度
	private Double temperature;
	// 湿度
	private Double humidity;
	// 光照
	private Double lighting;
	// 所属区域
	private String zoneName;
	private Integer zoneId;
	// 时间
	private Date datetime;

	public void setWindSpeed(Double io) {
		if(null != io){
			if(io<4.1){
				this.windSpeed=0.0;
			}else if(io<20){
				this.windSpeed = (io-IO_L)*(WS_URV-WS_LRV)/(IO_U-IO_L)+WS_LRV;
			}else{
				this.windSpeed = 30.0;
			}
		}
	}

	// TODO 风向的解析
	public void setWindDirection(Double io) {
		if(4<=io && io<5){
			this.windDirection="北";
		}else if(io<6){
			this.windDirection="东北偏北";
		}else if(io<7){
			this.windDirection="东北";
		}else if(io<8){
			this.windDirection="东北偏东";
		}else if(io<9){
			this.windDirection="东";
		}else if(io<10){
			this.windDirection="东南偏东";
		}else if(io<11){
			this.windDirection="东南";
		}else if(io<12){
			this.windDirection="东南偏南";
		}else if(io<13){
			this.windDirection="南";
		}else if(io<14){
			this.windDirection="西南偏南";
		}else if(io<15){
			this.windDirection="西南";
		}else if(io<16){
			this.windDirection="西南偏西";
		}else if(io<17){
			this.windDirection="西";
		}else if(io<18){
			this.windDirection="西北偏西";
		}else if(io<19){
			this.windDirection="西北";
		}else if(io<=20){
			this.windDirection="西北偏北";
		}
	}
	// TODO 雨雪的解析
	public void setRainAndSnow(Double io) {
		if (io == 0) {
			this.rainAndSnow = "无";
		}
		if (io == 1) {
			this.rainAndSnow = "雨";
		}
	}

	public void setRainFall(Double io) {
		if(null != io){
			if(io<4){
				io=4.0;
			}
			if(io>20){
				io=20.0;
			}
			this.rainFall = (io-IO_L)*(RF_URV-RF_LRV)/(IO_U-IO_L)+RF_LRV;
		}
	}

	public void setRainFallReal(Double value){
		if(value < 0){
			value = 0.0;
		}
		this.rainFall = value;
	}
	public void setPressure(Double io) {
		if(null != io){
			if(io<4){
				io=4.0;
			}
			if(io>20){
				io=20.0;
			}
			this.pressure = (io-IO_L)*(P_URV-P_LRV)/(IO_U-IO_L)+P_LRV;
		}
	}

	public void setPm25(Double io) {
		if(null != io){
			if(io<4){
				io=4.0;
			}
			if(io>20){
				io=20.0;
			}
			this.pm25 = (io-IO_L)*(PM_URV-PM_LRV)/(IO_U-IO_L)+PM_LRV;
		}
	}

	public void setPh(Double io) {
		if(null != io){
			if(io<4){
				io=4.0;
			}
			if(io>20){
				io=20.0;
			}
			this.ph = (io-IO_L)*(PH_URV-PH_LRV)/(IO_U-IO_L)+PH_LRV;
		}
	}

	public void setTemperature(Double io) {
		if(null != io){
			if(io<4){
				io=4.0;
			}
			if(io>20){
				io=20.0;
			}
			this.temperature = (io-IO_L)*(T_URV-T_LRV)/(IO_U-IO_L)+T_LRV;
		}
	}

	//湿度
	public void setHumidity(Double io) {
		if(null != io){
			if(io<4){
				io=4.0;
			}
			if(io>20){
				io=20.0;
			}
			this.humidity = (io-IO_L)*(H_URV-H_LRV)/(IO_U-IO_L)+H_LRV;
		}
	}

	public void setLighting(Double io) {
		if(null != io){
			if(io<4){
				io=4.0;
			}
			if(io>20){
				io=20.0;
			}
			this.lighting = ((io-IO_L)*(L_URV-L_LRV)/(IO_U-IO_L)+L_LRV);
		}
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}



	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public void setRainAndSnow(String rainAndSnow) {
		this.rainAndSnow = rainAndSnow;
	}

	public Integer getId() {
		return id;
	}

	public Double getWindSpeed() {
		return windSpeed;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public String getRainAndSnow() {
		return rainAndSnow;
	}

	public Double getRainFall() {
		return rainFall;
	}

	public Double getPressure() {
		return pressure;
	}

	public Double getPm25() {
		return pm25;
	}

	public Double getPh() {
		return ph;
	}

	public Double getTemperature() {
		return temperature;
	}

	public Double getHumidity() {
		return humidity;
	}

	public Double getLighting() {
		return lighting;
	}

	public String getZoneName() {
		return zoneName;
	}


	public Integer getZoneId() {
		return zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	@Override
	public String toString() {
		return "ClimaticCollector [id=" + id + ", windSpeed=" + windSpeed + ", windDirection=" + windDirection
				+ ", rainAndSnow=" + rainAndSnow + ", rainFall=" + rainFall + ", pressure=" + pressure + ", pm25="
				+ pm25 + ", ph=" + ph + ", temperature=" + temperature + ", humidity=" + humidity + ", lighting="
				+ lighting + ", zoneName=" + zoneName + ", zoneId=" + zoneId + ", datetime=" + datetime + "]";
	}

	
	
}
