package io.lethinh.github.mantle.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Credit goes to Mojang.
 */
public class NBTTagFloat extends NBTPrimitive {

	/** The float value for the tag. */
	private float data;

	NBTTagFloat() {
	}

	public NBTTagFloat(float data) {
		this.data = data;
	}

	/**
	 * Write the actual data contents of the tag, implemented in NBT extension
	 * classes
	 */
	@Override
	void write(DataOutput output) throws IOException {
		output.writeFloat(this.data);
	}

	@Override
	void read(DataInput input, int depth, NBTSizeTracker sizeTracker) throws IOException {
		sizeTracker.read(96L);
		this.data = input.readFloat();
	}

	/**
	 * Gets the type byte for the tag.
	 */
	@Override
	public byte getId() {
		return 5;
	}

	@Override
	public String toString() {
		return this.data + "f";
	}

	/**
	 * Creates a clone of the tag.
	 */
	@Override
	public NBTTagFloat copy() {
		return new NBTTagFloat(this.data);
	}

	@Override
	public boolean equals(Object p_equals_1_) {
		return super.equals(p_equals_1_) && this.data == ((NBTTagFloat) p_equals_1_).data;
	}

	@Override
	public int hashCode() {
		return super.hashCode() ^ Float.floatToIntBits(this.data);
	}

	@Override
	public long getLong() {
		return (long) this.data;
	}

	@Override
	public int getInt() {
		return (int) Math.floor(this.data);
	}

	@Override
	public short getShort() {
		return (short) ((int) Math.floor(this.data) & 65535);
	}

	@Override
	public byte getByte() {
		return (byte) ((int) Math.floor(this.data) & 255);
	}

	@Override
	public double getDouble() {
		return this.data;
	}

	@Override
	public float getFloat() {
		return this.data;
	}

}