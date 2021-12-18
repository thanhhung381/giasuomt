package giasuomt.demo.person.Ultils;

import java.util.LinkedList;
import java.util.List;

import giasuomt.demo.educational.model.Subject;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.task.model.Application;

public class UpdateRegisterdSubjectAndCreateAppilcation {

	public static Tutor generateSubjectGroupMaybeInTutor(Tutor tutor) {

		List<String> tempString = new LinkedList<>();

		List<Subject> allRegisterdSubjects = tutor.getRegisteredSubjects();
		for (int i = 0; i < allRegisterdSubjects.size() - 1; i++) {
			boolean check = false; // không có phần tử trùng
			for (int j = 0; j < i; j++) {
				if (allRegisterdSubjects.get(i).getSubjectGroup().getShortName()
						.contains(allRegisterdSubjects.get(j).getSubjectGroup().getShortName())) {
					check = true; // có phần tử
					break;
				}
			}

			if (check == true) {
				for (int j = i + 1; j < allRegisterdSubjects.size(); j++) {

					if (allRegisterdSubjects.get(i).getSubjectGroup().getShortName()
							.contains(allRegisterdSubjects.get(j).getSubjectGroup().getShortName())) {

						allRegisterdSubjects.remove(j);
						j--;
					}

				}

			}
			tempString.add(allRegisterdSubjects.get(i).getSubjectGroup().getShortName());
		}

		List<Application> applications = tutor.getApplications();
		if (!applications.isEmpty()) {
			for (int j = 0; j < applications.size(); j++) {
				List<Subject> subjectFromTask = applications.get(j).getTask().getSubjects();
				for (int k = 0; k < subjectFromTask.size() - 1; k++) {

					boolean check = false; // không có phần tử trùng
					for (int l = 0; l < k; l++) {
						if (subjectFromTask.get(k).getSubjectGroup().getShortName()
								.contains(subjectFromTask.get(l).getSubjectGroup().getShortName())) {
							check = true;// có phần tử
							break;
						}

					}

					if (check == true) {
						for (int l = k + 1; l < subjectFromTask.size(); l++) {
							if (subjectFromTask.get(k).getSubjectGroup().getShortName()
									.contains(subjectFromTask.get(l).getSubjectGroup().getShortName())) {
								subjectFromTask.remove(l);
								l--;
							}
						}
					}

					tempString.add(subjectFromTask.get(k).getSubjectGroup().getShortName());
				}
			}
		}

		tutor.setSubjectGroupMaybe(removeDuplicateElenmet(tempString).toString().replace("[", "").replace("]", ""));

		return tutor;

	}

	private static List<String> removeDuplicateElenmet(List<String> string) {
		List<String> temp = new LinkedList<>();

		for (int i = 0; i < string.size() - 1; i++) {
			boolean check = false;
			for (int j = 0; j < i; j++) {
				if (string.get(i).contains(string.get(j))) {
					check = true;
					break;
				}
			}

			if (check == true) {
				for (int j = i + 1; j < string.size(); j++) {
					if (string.get(i).contains(string.get(j))) {
						string.remove(j);
						j--;
					}
				}
			}

			temp.add(string.get(i));

		}

		return temp;
	}

}
