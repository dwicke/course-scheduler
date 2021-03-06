package coursescheduleapp.model;

import java.util.ArrayList;
import java.util.Iterator;

public class TimeSpacingPrefs implements Preference {

	private double score;
	private String courseSpacing;
	
	public TimeSpacingPrefs()
	{
		courseSpacing = CourseSpacing.NOPREF.toString();
		score = .1;
	}
	public TimeSpacingPrefs(String s)
	{
		courseSpacing = s;
		score = .1;
	}
	
	public void setSpacing(String s)
	{
		courseSpacing  = s;
	}
	
	
	@Override
	public double getFitness(Schedule individual) {
		
		if (courseSpacing.equals(CourseSpacing.NOPREF.toString()))
		{
			return 0;
		}
		
		TimeDayCollection sched = new TimeDayCollection();
		Iterator<Course> it = individual.getCourses().iterator();

		// Iterate over each course to create a time table for the days and times
		// of the courses.
		while(it.hasNext()) 
		{
			Course curr = it.next();
			// I expect that each course has an ArrayList of Day enums
			// and a TimePeriod object
			if (curr != null)
			{
				boolean wasAdded = sched.addTimeCollection((TimePeriod)curr.getValue(CourseData.TIME.toString()),(ArrayList<Day>)curr.getValue(CourseData.DAY.toString()));
				// If wasAdded is false then two courses occur during or right after the other
				// like if one class starts at 10am and the other class ends at 10am
				if (wasAdded == false)
				{
					return 0;
				}
			}
		}
		return getFitness(sched);
	}
	private double getFitness(TimeDayCollection sched) {
		double fitness = 0;
		if (courseSpacing != CourseSpacing.NOPREF.toString())
		{
			for (int i = 0; i < Day.values().length; i++)
			{

				Iterator<TimePeriod> schedSet = sched.getCollection(Day.values()[i]).getCollection().iterator();
				while(schedSet.hasNext())
				{
					TimePeriod curr = schedSet.next();
					if (schedSet.hasNext())
					{
						TimePeriod next = schedSet.next();
						if (next.getStart().subtract(curr.getEnd()) <= 10 && courseSpacing.equals(CourseSpacing.TENMIN.toString()))
						{
							fitness += score;
						}
						else if (next.getStart().subtract(curr.getEnd()) <= 60 && courseSpacing.equals(CourseSpacing.HOUR.toString()))
						{
							fitness += score;
						}
					}
				}
			}
		}

		return fitness;
	}
	
	@Override
	public String toString()
	{
		return courseSpacing;
	}
	@Override
	public void addPref(String pref) {
		// TODO Auto-generated method stub
		setSpacing(pref);
	}

}
