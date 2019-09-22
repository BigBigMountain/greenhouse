package com.lyyh.fertilizer.pojo.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ValveStatistics implements Serializable {

	private static final long serialVersionUID = 1L;

	private int valveNum;

	private List<IrriRecord> irriRecords;

	public ValveStatistics() {
		super();
	}

	public ValveStatistics(int valveNum) {
		super();
		this.valveNum = valveNum;
		this.irriRecords = new ArrayList<IrriRecord>();
	}

	public int getValveNum() {
		return valveNum;
	}

	public void setValveNum(int valveNum) {
		this.valveNum = valveNum;
	}

	public List<IrriRecord> getIrriRecords() {
		return irriRecords;
	}

	public double getTotalIrrigation() {
		double d = 0;
		for (IrriRecord irriRecord : irriRecords) {
			d = d + irriRecord.getIrrigationVolume();
		}
		return d;
	}

	public static class IrriRecord {

		private Date start;

		private Date end;

		private double irrigationVolume;

		public IrriRecord(Date start, Date end, double irrigationVolume) {
			super();
			this.start = start;
			this.end = end;
			this.irrigationVolume = irrigationVolume;
		}
		
		public IrriRecord(Date start) {
			super();
			this.start = start;
		}

		public IrriRecord() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Date getStart() {
			return start;
		}

		public void setStart(Date start) {
			this.start = start;
		}

		public Date getEnd() {
			return end;
		}

		public void setEnd(Date end) {
			this.end = end;
		}

		public double getIrrigationVolume() {
			return irrigationVolume;
		}

		public void setIrrigationVolume(double irrigationVolume) {
			this.irrigationVolume = irrigationVolume;
		}

	}
}
