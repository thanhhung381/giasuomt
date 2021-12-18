package giasuomt.demo.person.Ultils;

import java.util.List;

import giasuomt.demo.educational.model.Subject;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.task.model.Application;

public class UpdateRegisterdSubjectAndCreateAppilcation {
	
	public static Tutor generateSubjectGroupMaybeInTutor(Tutor tutor)
	{
		String subjectGroupByRegisteredSubject="";
		
		String subjectGroupByApplication="";
		
		List<Subject> allRegisterdSubjects=tutor.getRegisteredSubjects();
		for(int i=0;i<allRegisterdSubjects.size();i++)
		{
			subjectGroupByRegisteredSubject=subjectGroupByRegisteredSubject.concat(allRegisterdSubjects.get(i).getSubjectGroup().getShortName());
		
		}
		
		System.out.println(subjectGroupByRegisteredSubject);
		List<Application> applications=tutor.getApplications();
		if(!applications.isEmpty())
		{
			for(int j=0;j<applications.size();j++)
			{
				List<Subject> subjectFromTask=applications.get(j).getTask().getSubjects();
				for(int k=0;k<subjectFromTask.size();k++)
				{
					subjectGroupByApplication.concat(subjectFromTask.get(k).getSubjectGroup().getShortName());
				}
			}
		}
		
		if(!subjectGroupByApplication.contains(subjectGroupByRegisteredSubject))
		{
			tutor.setSubjectGroupMaybe(subjectGroupByRegisteredSubject.concat(",".concat(subjectGroupByApplication)));
		}
		else
		{
			tutor.setSubjectGroupMaybe(subjectGroupByRegisteredSubject);
		}
		
		return tutor;
		
	}
	
	
}
