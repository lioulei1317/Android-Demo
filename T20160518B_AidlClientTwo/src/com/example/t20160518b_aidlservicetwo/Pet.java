package com.example.t20160518b_aidlservicetwo;

import android.os.Parcel;
import android.os.Parcelable;

public class Pet implements Parcelable {
	private String name;
	private double weight;

	public Pet() {

	}

	public Pet(String name, double weight) {
		this.name = name;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// 把该对象所包含的数据写到Parcel
		dest.writeString(name);
		dest.writeDouble(weight);
	}

	// 添加一个静态成员，名为CREATOR,该对象实现了Parcelable.Creator接口
	public static final Parcelable.Creator<Pet> CREATOR = new Parcelable.Creator<Pet>() {

		@Override
		public Pet createFromParcel(Parcel source) {
			// 从Parcel中读取数据，返回Person对象
			return new Pet(source.readString(), source.readDouble());
		}

		@Override
		public Pet[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Pet[size];
		}
	};

	public String toString() {
		return "Pet[name=" + name + ",weight=" + weight + "]";
	};
}
