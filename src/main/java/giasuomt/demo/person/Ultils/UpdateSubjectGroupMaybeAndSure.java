package giasuomt.demo.person.Ultils;

import java.util.LinkedList;

import java.util.List;


import giasuomt.demo.job.model.Job;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.task.model.Application;

public class UpdateSubjectGroupMaybeAndSure {

	public static Tutor generateSubjectGroupMaybeInTutor(Tutor tutor) {

	//	List<String> tempString = new LinkedList<>();

//		List<Subject> allRegisterdSubjects = tutor.getRegisteredSubjects();
	//	for (int i = 0; i < allRegisterdSubjects.size(); i++) {

	//		tempString.add(allRegisterdSubjects.get(i).getSubjectGroup().getShortName());
	//	}

	//	List<Application> applications = tutor.getApplications();
	//	if (!applications.isEmpty()) {
		//	for (int j = 0; j < applications.size(); j++) {
		//		List<Subject> subjectFromTask = applications.get(j).getTask().getSubjects();
		//		for (int k = 0; k < subjectFromTask.size(); k++) {

	//				tempString.add(subjectFromTask.get(k).getSubjectGroup().getShortName());
		//		}
	//		}
	//	}

	//	tutor.setSubjectGroupMaybe(removeDuplicateElemet(tempString).toString().replace("[", "").replace("]", ""));

		return tutor;

	}

	public static Tutor generateSubjectGroupSureInTutor(Tutor tutor) {
//		List<String> temp = new LinkedList<>();

//		List<Job> jobs = tutor.getJobs();
	//	for (int i = 0; i < jobs.size(); i++) {
	//		if (jobs.get(i).getJobResult().contains("success")) {
	//			List<Subject> subjects = jobs.get(i).getTask().getSubjects();
	//			for (int j = 0; j < subjects.size(); j++) {
		//			temp.add(subjects.get(j).getSubjectGroup().getShortName());
		//		}
			//}
	//	}

	//	tutor.setSubjectGroupSure(removeDuplicateElemet(temp).toString().replace("[", "").replace("]", ""));

		return tutor;

	}

	private static List<String> removeDuplicateElemet(List<String> string) {
		List<String> temp = new LinkedList<>();
		for (int i = 0; i < string.size(); i++) {
			boolean check = false;
			for (int j = 0; j < i; j++) {
				if (string.get(i).contains(string.get(j))) {
					check = true;
					break;
				}
			}
			if (check == false) {

				for (int j = i + 1; j < string.size(); j++) {
					if (string.get(i).contains(string.get(j))) {

						string.remove(j);
						j--;
					}
				}
				temp.add(string.get(i));
			}
		}
		return temp;
	}

}
