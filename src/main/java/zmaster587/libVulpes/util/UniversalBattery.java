package zmaster587.libVulpes.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import zmaster587.libVulpes.api.IUniversalEnergy;

public class UniversalBattery implements IUniversalEnergy {

	private int energy;
	private int maxEnergy;
	
	public UniversalBattery() {
		maxEnergy = 0;
	}
	
	public UniversalBattery(int maxEnergy) {
		this.maxEnergy = maxEnergy;
	}
	
	@Override
	public int acceptEnergy(int maxReceive,
			boolean simulate) {

		if(maxReceive > maxEnergy - energy) {
			
			if(!simulate)
				energy = maxEnergy;
			
			return maxEnergy - energy;
		}
		
		if(!simulate)
			energy += maxReceive;
		
		return maxReceive;
	}

	@Override
	public int extractEnergy(int maxExtract,
			boolean simulate) {
		if(maxExtract > energy) {
			if(!simulate)
				energy = 0;
			return energy;
		}
		
		if(!simulate)
			energy -= maxExtract;
		
		return maxExtract;
	}
	
	@Override
	public int getEnergyStored() {
		return energy;
	}

	@Override
	public int getMaxEnergyStored() {
		return maxEnergy;
	}

	@Override
	public void setEnergyStored(int energy) {
		this.energy = energy;
	}
	
	public void writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("energy", this.energy);
		nbt.setInteger("maxEnergy", this.maxEnergy);
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		this.energy = nbt.getInteger("energy");
		this.maxEnergy = nbt.getInteger("maxEnergy");
	}
}