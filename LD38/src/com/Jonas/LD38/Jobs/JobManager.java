package com.Jonas.LD38.Jobs;

import java.util.ArrayList;
import java.util.List;

public class JobManager {
	public List<JobContainer> joblist = new ArrayList<JobContainer>();
	
	public JobManager() {
	}
	
	public void add(Job job, int quantity) {
		if (quantity > 9) quantity = 9;
		
		if (joblist.size() >= 6) return;
		
		/*
		for (int i = 0; i < joblist.size(); i++) {
			if (joblist.get(i).job.getClass().equals(job)) {
				joblist.get(i).quantity += quantity;
				return;
			}
		}
		*/

		joblist.add(new JobContainer(job, quantity));
	}
	
	public boolean contains(Job job) {
		for (int i = 0; i < joblist.size(); i++) {
			if (joblist.get(i).job.getClass().equals(job)) return true;
		}
		
		return false;
	}

	public void remove(int i, int quntity) {
		if (joblist.get(i).quantity - quntity <= 0)
			joblist.remove(i);
		else
			joblist.get(i).quantity -= quntity;
		
	}
}